using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ClockMotion : MonoBehaviour
{

    public GameObject second;
    public GameObject minute;
    public GameObject hour;

    //ListObjects
    List<GameObject> Hours = new List<GameObject>();
    List<GameObject> Minutes = new List<GameObject>();
    List<GameObject> Seconds = new List<GameObject>();
    // Start is called before the first frame update
    void Start()
    {
        Debug.Log(System.DateTime.Now);
        //checks these specifics individually
        Debug.Log("hour: " + System.DateTime.Now.Hour);
        Debug.Log("minute: " + System.DateTime.Now.Minute);
        Debug.Log("second: " + System.DateTime.Now.Second);

        //hour
        for (int i = 1; i < 13; i++)         {
            GameObject instance1 = Instantiate(hour, new Vector3(i * 4.0F, 0, -6F), Quaternion.identity);
            Hours.Add(instance1);
        }

        //minute
        for (var i = 1; i < 61; i++)
        {
            GameObject instance2 = Instantiate(minute, new Vector3(i * 1F, 0, 0f), Quaternion.identity);
            Minutes.Add(instance2);
        }

        //second
        for (var i = 1; i < 61; i++)         {
            GameObject instance3 = Instantiate(second, new Vector3(i * 1F, 0, 3f), Quaternion.identity);
            Seconds.Add(instance3);
        }

        //create the main objects to be inactive
        hour.active = false;         minute.active = false;         second.active = false;

}
void Update()
    {
        float currentHour = System.DateTime.Now.Hour % 12;         //hour
        for (var i = 1; i < 13; i++)
        {
            if(System.Math.Abs(currentHour - i) < 1)         
            {
                Vector3 pos = Hours[i].transform.position;
                pos.y = 2.0F;
                Hours[i].transform.position = pos;
            }
            //else
            //{
            //    Vector3 pos = Hours[i].transform.position;
            //    pos.y = 0;
            //    Hours[i].transform.position = pos;
            //}
        }

        //minute
        for (var i = 1; i < 61; i++)
        {
            if (System.DateTime.Now.Minute == i)
            {
                Vector3 pos = Minutes[i].transform.position;
                pos.y = 1f;
                Minutes[i].transform.position = pos;
            }
            //else
            //{
            //    Vector3 pos = Minutes[i].transform.position;
            //    pos.y = 0;
            //    Minutes[i].transform.position = pos;
            //}
        }

        //second
        for (var i = 1; i < 61; i++)
        {
            if (System.DateTime.Now.Second == i)
            {
                Vector3 posS = Seconds[i].transform.position;
                posS.y = 1f;
                Seconds[i].transform.position = posS;
            }
            else 
            {
                Vector3 posS = Seconds[i].transform.position;
                posS.y = 0;
                Seconds[i].transform.position = posS;
            }
        }

    }
}
