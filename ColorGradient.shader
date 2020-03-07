Shader "Custom/ColorGradient"
{
    Properties
    {
        _Color ("Color", Color) = (1,0,0,1)
        _MainTex ("Albedo (RGB)", 2D) = "white" {}
        _Glossiness ("Smoothness", Range(0,1)) = 0.5
        _Metallic ("Metallic", Range(0,1)) = 0.0
    }
    SubShader
    {
        Tags { "RenderType"="Opaque" }
        LOD 200

        CGPROGRAM
        // Physically based Standard lighting model, and enable shadows on all light types
        #pragma surface surf Standard fullforwardshadows

        // Use shader model 3.0 target, to get nicer looking lighting
        #pragma target 3.0

        sampler2D _MainTex;

        struct Input
        {
            float2 uv_MainTex;
            float3 worldPos;
            float3 worldNormal;
        };

        half _Glossiness;
        half _Metallic;
        fixed4 _Color;
        

        // Add instancing support for this shader. You need to check 'Enable Instancing' on materials that use the shader.
        // See https://docs.unity3d.com/Manual/GPUInstancing.html for more information about instancing.
        // #pragma instancing_options assumeuniformscaling
        UNITY_INSTANCING_BUFFER_START(Props)
            // put more per-instance properties here
        UNITY_INSTANCING_BUFFER_END(Props)
        
        
         float3 HUEtoRGB(in float H)
            {
                float R = abs(H * 6 - 3) - 1;
                float G = 2 - abs(H * 6 - 2);
                float B = 2 - abs(H * 6 - 4);
                return saturate(float3(R,G,B));
            }           

            float Epsilon = 1e-10;
            float3 RGBtoHCV(in float3 RGB)
            {
                // Based on work by Sam Hocevar and Emil Persson
                float4 P = (RGB.g < RGB.b) ? float4(RGB.bg, -1.0, 2.0/3.0) : float4(RGB.gb, 0.0, -1.0/3.0);
                float4 Q = (RGB.r < P.x) ? float4(P.xyw, RGB.r) : float4(RGB.r, P.yzx);
                float C = Q.x - min(Q.w, Q.y);
                float H = abs((Q.w - Q.y) / (6 * C + Epsilon) + Q.z);
                return float3(H, C, Q.x);
            }

            float3 RGBtoHSL(in float3 RGB)
            {
                float3 HCV = RGBtoHCV(RGB);
                float L = HCV.z - HCV.y * 0.5;
                float S = HCV.y / (1 - abs(L * 2 - 1) + Epsilon);
                return float3(HCV.x, S, L);
            }

            float3 HSLtoRGB(in float3 HSL)
            {
                float h = HSL.x % 1;
                float s = min(HSL.y, 1);
                float l = HSL.z;
                float3 RGB = HUEtoRGB(h);
                float C = (1 - abs(2 * l - 1)) * s;
                return (RGB - 0.5) * C + l;
            }
            
        
        void surf (Input IN, inout SurfaceOutputStandard o)
        {
            // Albedo comes from a texture tinted by color
           fixed4 c = tex2D (_MainTex, IN.uv_MainTex) * _Color;
           // fixed4 c = tex2D (_MainTex, IN.worldPos.yz*.1) * _Color;  //sampling based on world position of object
           // fixed4 c = tex2D (_MainTex, IN.worldNormal.xy) * _Color;  //sampling based on normal of object
           o.Albedo = c.rgb;
           //o.Albedo = IN.worldPos/10;
          // o.Albedo = IN.worldPos.y/10;  
          
           float3 baseColor = float3 ((IN.worldPos.y/-10)+3, 1, .6);
           o.Albedo = HSLtoRGB(baseColor);
           // o.Albedo= IN.worldNormal;
          
          // o.Albedo = lerp( float3(1, 13, 133), float3(227,114,186), IN.worldPos.y);
          
            
            // Metallic and smoothness come from slider variables
            o.Metallic = _Metallic;
            o.Smoothness = _Glossiness;
            o.Alpha = c.a;
        }
        ENDCG
    }
    FallBack "Diffuse"
}
