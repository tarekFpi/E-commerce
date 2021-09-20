package com.example.my_json_review;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class User_areaAdd extends AppCompatActivity {
private String url="https://my-sotfwar.000webhostapp.com/User_AreaAdd.php";
private Button button_save;
private EditText editText_regionName,editText_cityName,editText_areaName;
private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area_add);
        button_save=(Button)findViewById(R.id.user_areaAdd);
        editText_areaName=(EditText)findViewById(R.id.edit_areaName);
        editText_cityName=(EditText)findViewById(R.id.edit_cityName);
        editText_regionName=(EditText)findViewById(R.id.edit_region);
        progressDialog=new ProgressDialog(this);

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setMessage("Please Wite....");
                progressDialog.setCancelable(false);
                progressDialog.show();

                String area_name= editText_areaName.getText().toString();
              String cityName= editText_cityName.getText().toString();
              String regionName= editText_regionName.getText().toString();

                StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
           Toast.makeText(User_areaAdd.this, response, Toast.LENGTH_SHORT).show();
                       editText_areaName.setText("");
                        editText_cityName.setText("");
                        editText_regionName.setText("");
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                 Toast.makeText(User_areaAdd.this, "Your User Area Add Faild", Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams(){

                        HashMap hashMap=new HashMap();
                        hashMap.put("region_name",regionName);
                        hashMap.put("city_name",cityName);
                        hashMap.put("area_name",area_name);
                        return hashMap;
                    }
                };
                RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }
        });
    }


}