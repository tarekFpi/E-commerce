package com.example.my_json_review;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Admin_informationShow extends AppCompatActivity {
  private RecyclerView recyclerView;
  private List<Admin_infoModel>modelList=new ArrayList<>();
  private Admin_infoAdapter adapter;
    private String serial_id,user_name,gmail,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_information_show);

        recyclerView=(RecyclerView)findViewById(R.id.amdin_recyler_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/Admin_informationShow.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int count=0;
                try {
                    JSONArray jsonArray= new JSONArray(response);
                    for (int i = 0; i <jsonArray.length(); i++) {
                        count++;

                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        serial_id= jsonObject.getString("serial_id");
                        user_name= jsonObject.getString("User_name");
                        gmail= jsonObject.getString("Gmail");
                        password=jsonObject.getString("password");

                        Admin_infoModel item=new Admin_infoModel(serial_id,user_name,gmail,password);
                        modelList.add(item);
                        adapter=new Admin_infoAdapter(modelList,getApplicationContext());
                        recyclerView.setAdapter(adapter);
                    }
                    Toast.makeText(Admin_informationShow.this, "User:"+count, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
      Toast.makeText(Admin_informationShow.this, "Data Not Found.."+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}