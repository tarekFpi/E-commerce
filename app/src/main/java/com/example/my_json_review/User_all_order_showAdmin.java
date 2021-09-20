package com.example.my_json_review;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.app.VoiceInteractor;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.textclassifier.TextLanguage;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User_all_order_showAdmin extends AppCompatActivity {
  private RecyclerView recyclerView;
    private List<User_order_showModel>order_showModelList=new ArrayList<>();
    private Admin_order_ShowAdapter adapter;
   private String sell_id,sell_pdName,order_date,pd_size,customar_name,
           customar_email,areaName,flat_building,city_name,phone_number,category_name,
           delivery_status,region_name,serial_id;
   private int sell_price,sell_quanitiy,totalPrice,Discount,Shipping_Rate;
  private View view;
  private EditText editText_message;
  private Button send_meseage_btn;
  private LayoutInflater layoutInflater;
  private String current_orderDate;
  private EditText editText_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_all_order_show_admin);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_all_orderShow);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        editText_search=(EditText)findViewById(R.id.product_search_date);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/UserSell_Order_Show.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                JSONArray jsonArray=new JSONArray(response);
                 for (int i = 0; i <jsonArray.length() ; i++) {

                     JSONObject jsonObject=jsonArray.getJSONObject(i);
                     sell_id= jsonObject.getString("sell_id");
                     serial_id= jsonObject.getString("serial_id");
                     sell_pdName= jsonObject.getString("sell_name");
                     sell_quanitiy=jsonObject.getInt("sell_quanitiy");
                     sell_price=jsonObject.getInt("sell_price");
                     totalPrice= jsonObject.getInt("Total_Sell_price");
                     Discount= jsonObject.getInt("Discount");
                     order_date= jsonObject.getString("DelivaryDate");
                     pd_size=jsonObject.getString("sell_size");
                     customar_name= jsonObject.getString("customar_name");
                     customar_email= jsonObject.getString("Email");
                     areaName= jsonObject.getString("area_name");
                     flat_building= jsonObject.getString("flat_building_name");
                     city_name= jsonObject.getString("City_twon");
                     phone_number=jsonObject.getString("phon_number");
                     Shipping_Rate= jsonObject.getInt("Shipping_Rate");
                     category_name= jsonObject.getString("Category_name");
                     delivery_status= jsonObject.getString("Delivery_status");
                     region_name= jsonObject.getString("Region_name");


   User_order_showModel item=new
   User_order_showModel(serial_id,customar_name,customar_email,areaName,flat_building,city_name,phone_number,totalPrice,order_date,Shipping_Rate,delivery_status,region_name,sell_id,sell_pdName,sell_price,sell_quanitiy,Discount,pd_size,"",category_name);
   order_showModelList.add(item);
 Toast.makeText(User_all_order_showAdmin.this, "data:"+item.getUser_name(), Toast.LENGTH_SHORT).show();
       }
   adapter=new Admin_order_ShowAdapter(order_showModelList,getApplicationContext());
     recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new Admin_order_ShowAdapter.OnItemListener() {
            @Override
            public void Onclick(int position) {
       User_order_showModel getData=order_showModelList.get(position);
                Intent intent=new Intent();

            }

            @Override
            public void Order_Delete(int position) {

                StringRequest stringRequest_delete=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/user_order_delete.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                 order_showModelList.clear();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                 Toast.makeText(getApplicationContext(), "Category Name Not Found!!!"+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() {
                        HashMap hashMap=new HashMap();
                        hashMap.put("user_phon",order_showModelList.get(position).getUser_phon());
                        return hashMap;
                    }
                };
                RequestQueue request_delete=Volley.newRequestQueue(getApplicationContext());
                request_delete.add(stringRequest_delete);

            }

            @Override
            public void Order_message(int position) {
                Date date=new Date();
                SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd");
                current_orderDate= sm.format(date.getTime());

       User_order_showModel getData=order_showModelList.get(position);

   AlertDialog  alertDialog= new AlertDialog.Builder(User_all_order_showAdmin.this).create();

                alertDialog.setCancelable(false);
                layoutInflater=getLayoutInflater();
                 view= layoutInflater.inflate(R.layout.message_layout,null);

                editText_message=view.findViewById(R.id.user_message_phone);
                send_meseage_btn=view.findViewById(R.id.send_messages);
             TextView textView_phone=view.findViewById(R.id.text_messPhone);

                String user_phon=  getData.getUser_phon();
                textView_phone.setText(user_phon);
                alertDialog.setView(view);

             send_meseage_btn.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                    String getMessgess= editText_message.getText().toString();
                     StringRequest string_messagess=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/User_orderExcept.php", new Response.Listener<String>() {
                         @Override
                         public void onResponse(String response) {

     Toast.makeText(User_all_order_showAdmin.this, response, Toast.LENGTH_LONG).show();
     alertDialog.dismiss();
   //message set permission
         if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
                SmsManager smsManager=SmsManager.getDefault();
                smsManager.sendTextMessage(user_phon,null,getMessgess,null,null);
            }else{
            requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
               }
                }

                      }
                     }, new Response.ErrorListener() {
                         @Override
                         public void onErrorResponse(VolleyError error) {
                             alertDialog.dismiss();
               Toast.makeText(User_all_order_showAdmin.this, "Error:"+error.getMessage(), Toast.LENGTH_SHORT).show();
                         }
                     }){
                         @Override
                         protected Map<String, String> getParams() {

                             HashMap hashMap=new HashMap();
                             hashMap.put("sell_discount",getData.getDiscount());
                             hashMap.put("sell_id",getData.getSerial_id());
                             hashMap.put("user_name",getData.getUser_name());
                             hashMap.put("user_gmail",getData.getUser_email());
                             hashMap.put("user_phone",user_phon);
                             hashMap.put("region_name",getData.getRegion_name());
                             hashMap.put("pd_id",getData.getPd_id());
                             hashMap.put("pd_name",getData.getPd_name());
                             hashMap.put("sell_price",String.valueOf(getData.getPd_price()));
                             hashMap.put("category_name",getData.getCategory_name());
                             hashMap.put("quantity",String.valueOf(getData.getSell_quanitiy()));
                             hashMap.put("pd_size",getData.getPd_size());
                             hashMap.put("pd_total_price",String.valueOf(getData.getProd_TotalPrice()));
                             hashMap.put("sell_discount",String.valueOf(getData.getDiscount()));
                             hashMap.put("Messages",getMessgess);
                             hashMap.put("order_status",getData.getOrder_status());
                             hashMap.put("shipping_rate",String.valueOf(getData.getShipping_rate()));
                             hashMap.put("order_date",getData.getCurrent_date());
                             hashMap.put("except_date",current_orderDate);
                             return hashMap;
                         }
                     };
                     RequestQueue requestQueue_messsage=Volley.newRequestQueue(getApplicationContext());
                     requestQueue_messsage.add(string_messagess);


                 }
             });
                alertDialog.show();
            }
        });
     } catch (JSONException e) {
           e.printStackTrace();
        }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
        Toast.makeText(User_all_order_showAdmin.this, "User Order Not Found..", Toast.LENGTH_SHORT).show();
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
               // List<User_order_showModel>
            }
        });
    }

void fileList(String text){
    List<User_order_showModel>filterList=new ArrayList<>();
    for (User_order_showModel item :order_showModelList){
        if(item.getCurrent_date().toLowerCase().contains(text.toLowerCase())){
            filterList.add(item);
        }
    }
    adapter.filterListChange(filterList);
}


}