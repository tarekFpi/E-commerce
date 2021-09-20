package com.example.my_json_review;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllUser_show extends AppCompatActivity {
private String serial_id,user_name,gmail,password,comforPass;
    private List<AllUser_singUp_Model> modelList=new ArrayList<>();
    private UserSignUp_Adapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user_show);
       recyclerView=(RecyclerView)findViewById(R.id.recyclser_singup);
       recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));

        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/AllSign_up.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i = 0; i <jsonArray.length(); i++) {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);

                        serial_id= jsonObject.getString("serial_id");
                        user_name= jsonObject.getString("User_name");
                        gmail= jsonObject.getString("Gmail");
                        password=jsonObject.getString("password");
                        comforPass= jsonObject.getString("comfrom_password");

       AllUser_singUp_Model item=new AllUser_singUp_Model(serial_id,user_name,gmail,comforPass);
       modelList.add(item);
       adapter=new UserSignUp_Adapter(getApplicationContext(),modelList);
       recyclerView.setAdapter(adapter);
                    }

                    adapter.setOnItemClickListener(new UserSignUp_Adapter.OnItemListener() {
                        @Override
                        public void Onclick(int position) {

                        }

                        @Override
                        public void Order_Delete(int position) {
                    AllUser_singUp_Model itemposition=modelList.get(position);

                         StringRequest stringRequest1=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/sign_upDelete.php", new Response.Listener<String>() {
                             @Override
                             public void onResponse(String response) {
                       Toast.makeText(AllUser_show.this, response, Toast.LENGTH_SHORT).show();
                       adapter.notifyDataSetChanged();
                             }
                         }, new Response.ErrorListener() {
                             @Override
                             public void onErrorResponse(VolleyError error) {
             Toast.makeText(AllUser_show.this, "Your User Delete Faild..", Toast.LENGTH_SHORT).show();
                             }
                         }){
                             @Override
                             protected Map<String, String> getParams() {
                                 HashMap hashMap=new HashMap();
                                 hashMap.put("serial_id",itemposition.getUser_serial());
                                 return hashMap;
                             }
                         };
                         RequestQueue requestQueue_delete=Volley.newRequestQueue(getApplicationContext());
                         requestQueue_delete.add(stringRequest1);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
       Toast.makeText(AllUser_show.this, "User Information Not Found.."+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}