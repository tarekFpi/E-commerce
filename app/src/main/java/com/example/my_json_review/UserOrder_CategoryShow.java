package com.example.my_json_review;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserOrder_CategoryShow extends AppCompatActivity {
    private View view;
    private AlertDialog alertDialog;
    private Button button_UPdate,button_exit;
    private ElegantNumberButton numberButton;
    private String pd_name,image_url,deatils,pd_Id,pd_Size,user_gmail,catgegory_name;
    private  int pq_quan=0,totoal_qu=0;
    private int pd_sellprice,purchase_price,pd_discount,pd_stock;
    private SharedPreferences sharedPreferences;
    int sell_quanitiy=0,total_price=0,discount=0;
    private TextView textView_total,textView_update_name;
    private ProgressDialog progressDialog;
    private EditText editText_update;
    private RecyclerView recyclerView;
    private LayoutInflater layoutInflater;
    pd_Category_showModel get_Item;
    private int total_quan,stock;
    List<pd_Category_showModel> categoryShowAdapterList=new ArrayList<pd_Category_showModel>();
    pd_categoryShowAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order__category_show);

        recyclerView=(RecyclerView)findViewById(R.id.pd_category_showRcycler);
        textView_total=(TextView)findViewById(R.id.text_total_score);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please Wite...");
        progressDialog.show();

        StringRequest stringRequest_pdName=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/User_categoryOrder_Api.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //pd_name,image_url,deatils,pd_Id,pd_Size,user_gmail,categpr;
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        pd_Id=  jsonObject.getString("id").toString();
                        pd_name= jsonObject.getString("product_Name").toString();
                        catgegory_name= jsonObject.getString("category_name").toString();
                        sell_quanitiy=  jsonObject.getInt("sell_quantity");
                        pd_sellprice= jsonObject.getInt("sell_price");
                        pd_discount= jsonObject.getInt("sell_discount");
                        pd_Size= jsonObject.getString("product_size").toString();
                        deatils =jsonObject.getString("product_deatils").toString();
                        user_gmail= jsonObject.getString("user_gmail").toString();
                        image_url= jsonObject.getString("image").toString();
              String url="https://my-sotfwar.000webhostapp.com/userOrderImage/"+image_url;
  pd_Category_showModel item_Model=new pd_Category_showModel(pd_Id,pd_name,catgegory_name,pd_sellprice,pd_discount,sell_quanitiy,url,pd_Size,user_gmail);
               categoryShowAdapterList.add(item_Model);
               progressDialog.dismiss();
                    }
        adapter=new pd_categoryShowAdapter(getApplicationContext(),categoryShowAdapterList);
                    recyclerView.setAdapter(adapter);
                    adapter.setOnItemClick(new pd_categoryShowAdapter.onItemClickLisiner() {
                        @Override
                        public void onClick(int position) {

                        }

                        @Override
                        public void OnUpdate(int position) {

                        }

                        @Override
                        public void OnDelete(int position) {

                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserOrder_CategoryShow.this, "product Name is Not Found..", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest_pdName);

    }
}