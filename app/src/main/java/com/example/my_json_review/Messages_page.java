package com.example.my_json_review;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.VoiceInteractor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.textclassifier.TextLanguage;
import android.widget.EditText;
import android.widget.TextView;
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

public class Messages_page extends AppCompatActivity {
   private RecyclerView recyclerView;
   private List<Order_Except_Model>modelList=new ArrayList<>();
   private userMessage_Adapter adapter;
String user_name,user_gmail,user_phone,region,pd_id,pd_name,pd_category,pd_size
        ,message,order_status,ordate,except_date,serial_id;
int shipping_rate,pd_quanitiy,pd_price,total_price,discount;
private EditText editText_search;
private int totalPrice=0;
private TextView textView_totalPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages_page);
        recyclerView=(RecyclerView)findViewById(R.id.recylcer_user_Order_message);
        recyclerView.setHasFixedSize(true);
        editText_search=(EditText)findViewById(R.id.product_search_id);
     textView_totalPrice=(TextView)findViewById(R.id.text_total_sell);
      recyclerView.setLayoutManager(new LinearLayoutManager(this));

        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/order_exceptMessage_show.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i = 0; i<jsonArray.length() ; i++) {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);

                     serial_id= jsonObject.getString("serial_id");
                     user_name=jsonObject.getString("user_name");
                      user_gmail=jsonObject.getString("user_gmail");
                       user_phone= jsonObject.getString("user_phone");
                       region=jsonObject.getString("Region_name");
                      pd_id= jsonObject.getString("product_id");
                      pd_name= jsonObject.getString("prouct_name");
                       pd_category= jsonObject.getString("catgory_name");
                       pd_size= jsonObject.getString("product_size");
                       pd_quanitiy= jsonObject.getInt("product_quantity");
                      pd_price=  jsonObject.getInt("product_price");
                      total_price=  jsonObject.getInt("Total_price");
                     discount=   jsonObject.getInt("Discount");
                    message=  jsonObject.getString("Messagess");
                     order_status=   jsonObject.getString("Order_status");
                      ordate=  jsonObject.getString("Order_date");
                        except_date=   jsonObject.getString("except_date");
                      shipping_rate= jsonObject.getInt("Shipping_rate");

   Order_Except_Model item=new
   Order_Except_Model(serial_id,user_name,user_gmail,user_phone,total_price,ordate,except_date,shipping_rate,order_status,region,pd_id,pd_name,pd_price,pd_quanitiy,discount,pd_size,pd_category,message);
   modelList.add(item);
   total_price=item.getProd_TotalPrice()+total_price;
     }
    textView_totalPrice.setText("Total:"+total_price);
      adapter=new userMessage_Adapter(getApplicationContext(),modelList);
      recyclerView.setAdapter(adapter);
      adapter.setOnItemClickListener(new userMessage_Adapter.OnItemListener() {
          @Override
          public void OnItmclick(int position) {
          }

          @Override
          public void OnDelete(int position) {
              Order_Except_Model getData=modelList.get(position);

           StringRequest stringRequest_delete=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/Execpt_order_Delete.php", new Response.Listener<String>() {
               @Override
               public void onResponse(String response) {
           Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
           modelList.clear();
               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
         Toast.makeText(Messages_page.this, "Your Data Update Faild.."+error.getMessage(), Toast.LENGTH_SHORT).show();
               }
           }){
               @Override
               protected Map<String, String> getParams() {
                   HashMap hashMap=new HashMap();
                   hashMap.put("user_phon",getData.getUser_phon());
                   return hashMap;
               }
           };
           RequestQueue requestQueue_delete=Volley.newRequestQueue(getApplicationContext());
           requestQueue_delete.add(stringRequest_delete);
          }
      });
    } catch (JSONException e) {

    e.printStackTrace();
  }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
         Toast.makeText(Messages_page.this, "Order Except Not Founde..", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


        editText_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            fileList(editable.toString());
            }
        });
    }
    void fileList(String text){
        List<Order_Except_Model>filterList=new ArrayList<>();

         for(Order_Except_Model item:modelList){
             if(item.getExcept_date().toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);
             }
         }
         adapter.filtetListChane(filterList);
    }
}