package com.example.my_json_review;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Volley_test extends AppCompatActivity {
    private TextView textView_value;
  private String myLink="https://utc-softwar.000webhostapp.com/json_data.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_test);
        textView_value=(TextView)findViewById(R.id.text_volley_id);


        myJsonData();
    }
    void myJsonData(){
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, myLink, new  Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                StringBuffer stringBuff=new StringBuffer();
                String name;
                String roll;
                String address;

                try {
                    JSONObject jsonMain= new JSONObject(response);
                    JSONArray jsonArray=jsonMain.getJSONArray("myData");

                    for (int i=0; i<jsonArray.length();i++){
                        JSONObject json=jsonArray.getJSONObject(i);
                        name=json.getString("Name");
                        roll=json.getString("Roll");
                        address=json.getString("Address");
                        stringBuff.append("Name::"+name+"\nRoll:"+roll+"\nAddress:"+address+"\n-------------\n");
                    }
                    textView_value.setText(stringBuff.toString());
                    requestQueue.stop();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView_value.setText("Data Not Founde");
                requestQueue.stop();
            }
        });
        requestQueue.add(stringRequest);
    }
}