using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class vertexFinder : MonoBehaviour
{
    Mesh mesh;
    //Cloth cloth;
    Vector3[] vertices;
    public GameObject component;
    List<GameObject> instances = new List<GameObject>();
    private float variation = 0f;
    float range = 0.02f; //range of values to control range of motion of squares
    float switchValue  = .3f; //range of circle around the mouse

    // Start is called before the first frame update
    void Start()
    {
        //Fetch the Renderer from the GameObject
        Renderer rend = GetComponent<Renderer>();

        //Mesh component
        mesh = GetComponent<MeshFilter>().mesh;
        vertices = mesh.vertices;

        for (var i = 0; i < vertices.Length; i++)
        {
            var instance = Instantiate(component);
            instance.transform.position = transform.TransformVector(vertices[i]);
            instances.Add(instance);
        }
    }

        // Update is called once per frame
        void Update()
        {
         variation = .04f;

        //mouse position interaction data
        Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);
        var camPoint = ray.GetPoint(1);
        camPoint.y = 0;

       // change the abstraction displayed based on the time 
        float realtimeSinceStartup = Time.realtimeSinceStartup;
        realtimeSinceStartup = (float)System.Math.Round((realtimeSinceStartup % 10), 1);
       

       if ( realtimeSinceStartup < 1f)
        {
           
            int system = Random.Range(1,2);
            switch (system)
            {
                case 2: range = Random.Range(0.5f, 1f);
                    switchValue = Random.Range(.5f, 1f);
                    break;
                case 1:
                     range = Random.Range(.01f, .5f);
                    switchValue = Random.Range(.1f, .5f);
                    break;
                default: variation = .5f;
                    break; 
            }
        }


        for (var i = 0; i < vertices.Length; i++)
        {
            float x = vertices[i].x;
            float y = vertices[i].y;
            float z = vertices[i].z;

            Vector3 samplePos = new Vector3(x, 0, z);


            float distance = Vector3.Distance(samplePos, camPoint) * range;
            y = (distance *switchValue) +(Mathf.Sin(Time.realtimeSinceStartup + i));

            vertices[i] = new Vector3(x, y, z);
            instances[i].transform.position = vertices[i];
            instances[i].transform.position = transform.TransformVector(vertices[i]);

            Vector3 normal = mesh.normals[i];
            instances[i].transform.rotation = Quaternion.FromToRotation(Vector3.up, normal);

        }

        mesh.vertices = vertices;
        mesh.RecalculateBounds();
        mesh.RecalculateNormals();
        }
   }

