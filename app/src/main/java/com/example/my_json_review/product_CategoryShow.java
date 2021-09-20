package com.example.my_json_review;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class product_CategoryShow extends AppCompatActivity {
private pd_categoryShowAdapter adapter;
private RecyclerView recyclerView;
//private List<Purchase_Model>sellModelList=new ArrayList<>();

private LayoutInflater layoutInflater;
    private View view;
    private AlertDialog alertDialog;
    private Button button_UPdate,button_exit;
    private ElegantNumberButton numberButton;
    private String name,image_url,deatils,pd_Id,pd_Size;
    private  int pq_quan=0,totoal_qu=0;
    private int pd_sellprice,purchase_price,pd_discount,pd_stock,purchase_quanitiy;
    private SharedPreferences sharedPreferences;
    int sell_quanitiy=0,total_price=0,discount=0;
    private TextView textView_total,textView_update_name;
    private ProgressDialog progressDialog;
    private EditText editText_update;
    private String bill_no,category_name;
    private product_Category_Add_sqlit category_AddSqlit;
    pd_Category_showModel get_Item;
    private int total_quan,stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__category_show);
        recyclerView=(RecyclerView)findViewById(R.id.pd_category_showRcycler);
        textView_total=(TextView)findViewById(R.id.text_total_score);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        category_AddSqlit=new product_Category_Add_sqlit(product_CategoryShow.this);

        get_Item=new pd_Category_showModel();
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please Wite...");
        progressDialog.show();

        List<pd_Category_showModel>category_ShowData=category_AddSqlit.getAllData();

        List<pd_Category_showModel>categoryShowModel_arrayList=new ArrayList<>();

        for(pd_Category_showModel category_model_Item :category_ShowData){

            discount= category_model_Item.getPd_discount();
            total_price=category_model_Item.getSelling_price() *category_model_Item.getSell_quanitiy()+total_price;
           total_price=total_price-discount;
            categoryShowModel_arrayList.add(category_model_Item);
           // Toast.makeText(this, "userGmail"+category_model_Item.getUser_gmail(), Toast.LENGTH_SHORT).show();
        }
        textView_total.setText("Amount:"+total_price);
        adapter=new pd_categoryShowAdapter(getApplicationContext(),categoryShowModel_arrayList);
        recyclerView.setAdapter(adapter);
        progressDialog.dismiss();
       adapter.setOnItemClick(new pd_categoryShowAdapter.onItemClickLisiner() {
           @Override
           public void onClick(int position) {
        String st= categoryShowModel_arrayList.get(position).getPid();
               String name= categoryShowModel_arrayList.get(position).getPdName();
               String gg= categoryShowModel_arrayList.get(position).getUser_gmail();
       Toast.makeText(product_CategoryShow.this, "id:"+st+"name:"+name+"gmail"+gg, Toast.LENGTH_LONG).show();
           }

           @Override
           public void OnUpdate(int position) {

     //stock= categoryShowModel_arrayList.get(position).getPd_stock();
   alertDialog=new AlertDialog.Builder(product_CategoryShow.this).create();
   alertDialog.setCancelable(false);
   alertDialog.setTitle("Update Catagory");
               layoutInflater=getLayoutInflater();
               view= layoutInflater.inflate(R.layout.category_update,null);
               alertDialog.setView(view);
               button_UPdate=view.findViewById(R.id.update_button);
               button_exit=view.findViewById(R.id.exit_id);
             //  editText_update=view.findViewById(R.id.edit_update_quantity);
                 numberButton=view.findViewById(R.id.update_quantity);
               textView_update_name=view.findViewById(R.id.pd_upName);
               textView_update_name.setText("Name:"+categoryShowModel_arrayList.get(position).getPdName());

               button_UPdate.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

            int quantity = Integer.parseInt(numberButton.getNumber().toString());
              String Up_id= categoryShowModel_arrayList.get(position).getPid();
       pd_Category_showModel Item=new pd_Category_showModel();
     StringRequest string_request_stock=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/product_show.php", new Response.Listener<String>() {
                           @Override
                         public void onResponse(String response) {
                               try {
                                   JSONArray jsonArray = new JSONArray(response);
                                 ///  categoryShowModel_arrayList.clear();
                                   for(int i = 0; i <jsonArray.length(); i++) {
                                       JSONObject object=jsonArray.getJSONObject(i);
                                       pd_Id=object.getString("serial_id");
                                       if(pd_Id.contains(Up_id)){
                                           purchase_quanitiy=object.getInt("parchase_quanitiy");
                                           if(!object.getString("sell_quanitiy").contains("null")){
                                               sell_quanitiy=object.getInt("sell_quanitiy");
                                           }else{
                                               stock= purchase_quanitiy-sell_quanitiy;
                                               if(stock<quantity){
                                Toast.makeText(product_CategoryShow.this, "Your Quantity Stock From Over..", Toast.LENGTH_SHORT).show();
                                               }else{

                                                   Item.setPid(Up_id);
                                                   Item.setSell_quanitiy(quantity);
                                                   if(category_AddSqlit.Update_data(Item)>0){
                                           Toast.makeText(product_CategoryShow.this, "Update SuccessFull..:", Toast.LENGTH_SHORT).show();
                                             adapter.notifyDataSetChanged();
                                             }else{
                                    Toast.makeText(product_CategoryShow.this, "Update Faild", Toast.LENGTH_SHORT).show();
                                          }
                                     }
                                     }
                                       }
                                   }

                               } catch (JSONException e) {
                                   e.printStackTrace();
                               }

                           }
                       }, new Response.ErrorListener() {
                           @Override
                           public void onErrorResponse(VolleyError error) {
                               Toast.makeText(getApplicationContext(), "Your Order Card Faild.."+error.getMessage(), Toast.LENGTH_SHORT).show();
                           }
                       });
                       RequestQueue requestQueue_stock=Volley.newRequestQueue(getApplicationContext());
                       requestQueue_stock.add(string_request_stock);

                   }
               });
               button_exit.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                     alertDialog.dismiss();
                   }
               });
               alertDialog.show();
           }

           @Override
           public void OnDelete(int position) {

               String id= categoryShowModel_arrayList.get(position).getPid();
               get_Item.setPid(id);
               if(category_AddSqlit.Delete_data(get_Item)>0){
                   Toast.makeText(product_CategoryShow.this, "Delete SuccessFull..", Toast.LENGTH_SHORT).show();
                   adapter.notifyDataSetChanged();
                 categoryShowModel_arrayList.clear();
               }else{
          Toast.makeText(product_CategoryShow.this, "Delete Faild", Toast.LENGTH_SHORT).show();
               }
           }
       });

    }

    public void buy_category_Data(View view) {


     final StringBuffer stringBuffer=new StringBuffer();
        StringBuffer stringBuffer_pid=new StringBuffer();
      List<pd_Category_showModel>category_ShowData=category_AddSqlit.getAllData();

        List<pd_Category_showModel>categoryShowModel_arrayList=new ArrayList<>();
        for(pd_Category_showModel category_model_Item :category_ShowData){

         pd_Id= category_model_Item.getPid();
            name= category_model_Item.getPdName();
            pd_sellprice= category_model_Item.getSelling_price();
            sell_quanitiy= category_model_Item.getSell_quanitiy();

            pd_Size= category_model_Item.getPdSize();
            pd_discount= category_model_Item.getPd_discount();
            image_url= category_model_Item.getPdImage();


            stringBuffer.append("Id="+category_model_Item.getPid()+",");
            stringBuffer.append("Name="+category_model_Item.getPdName()+",");
            stringBuffer.append("Quantity="+category_model_Item.getSell_quanitiy()+",");
            stringBuffer.append("price="+category_model_Item.getSelling_price()+",");
            stringBuffer.append("Discount="+category_model_Item.getPd_discount()+",");
            stringBuffer.append("categoryName="+category_model_Item.getCategoryName()+",");
            stringBuffer.append("Size="+category_model_Item.getPdSize()+"/");
            stringBuffer_pid.append(category_model_Item.getPid()+",");
         categoryShowModel_arrayList.add(category_model_Item);
        }

      //  Toast.makeText(this, "Data:"+stringBuffer.toString(), Toast.LENGTH_SHORT).show();
        if(total_price!=0){
       Intent intent=new Intent(getApplicationContext(),User_Address.class);
            intent.putExtra("data_all_key", (Serializable) stringBuffer);
            intent.putExtra("pd_totalPrice",total_price);
            intent.putExtra("pid_all", (Serializable) stringBuffer_pid);
            startActivity(intent);

        }else{
            Toast.makeText(this, "Your Order Not Found", Toast.LENGTH_SHORT).show();
        }
    }

}