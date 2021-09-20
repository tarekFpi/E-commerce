package com.example.my_json_review;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User_order_Show extends AppCompatActivity {
private RecyclerView recyclerView;
private User_order_showAdapter adapter;
private List<User_order_showModel>modelList=new ArrayList<>();
private String User_name,User_email,User_area,User_flat_no,User_city,User_phon,order_date,getAll_Order,order_status,regoin_name;
private String product_id,product_name,product_size;
private int shipping_Rate,total_price=0,discount,product_price,purchase_quanitiy,sell_quanitiy=0,stock;
    private EditText editText_name,editText_email,editText_loact,editText_number;
    private LayoutInflater layoutInflater;
    private View view;
    AlertDialog alertDialog;
    private Button exit_button,Update_button;
    private String pd_id ,pd_name,pd_discount,pd_sell_quanitiy,pd_sell_price,pd_size;
    private product_Category_Add_sqlit category_AddSqlit;
    private ElegantNumberButton numberButton;
    private  TextView textView_orderQuantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order__show);

        recyclerView=(RecyclerView)findViewById(R.id.user_order_show_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        category_AddSqlit=new product_Category_Add_sqlit(User_order_Show.this);
        List<pd_Category_showModel>category_ShowData=category_AddSqlit.getAllData();

        List<pd_Category_showModel>categoryShowModel_arrayList=new ArrayList<>();


        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/User_address_orderShow.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                     //   if (response.isEmpty()){}
                    categoryShowModel_arrayList.clear();

                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        regoin_name=jsonObject.getString("Region_name");
                        User_name= jsonObject.getString("user_name");
                        User_email= jsonObject.getString("email");
                        User_area= jsonObject.getString("area_street");
                        User_flat_no= jsonObject.getString("flat_buildig");
                        User_city= jsonObject.getString("city");
                        User_phon= jsonObject.getString("phon_number");
                        order_date= jsonObject.getString("Order_date");
                        shipping_Rate= jsonObject.getInt("Shipping_Rate");
                        //jsonObject.getInt("Total_price");
                        order_status=jsonObject.getString("Delivery_status");
                    }

                    for(pd_Category_showModel category_model_Item :category_ShowData){
                        discount= category_model_Item.getPd_discount();
                        total_price=category_model_Item.getSelling_price() *category_model_Item.getSell_quanitiy()+total_price;
                        total_price=total_price-discount;
                       categoryShowModel_arrayList.add(category_model_Item);
                     //   Toast.makeText(User_order_Show.this, "mysqli:"+category_model_Item.getUser_gmail(), Toast.LENGTH_SHORT).show();

                        if(category_model_Item.getUser_gmail().contains(User_email)){
  User_order_showModel addItem=new User_order_showModel("",User_name,User_email,User_area,User_flat_no,User_city,User_phon,total_price,order_date,shipping_Rate,order_status,regoin_name,category_model_Item.getPid(),category_model_Item.getPdName(),category_model_Item.getSelling_price(),category_model_Item.getSell_quanitiy(),category_model_Item.getPd_discount(),category_model_Item.getPdSize(),category_model_Item.getPdImage(),category_model_Item.getCategoryName());

                            modelList.add(addItem);
                            adapter=new User_order_showAdapter(getApplicationContext(),modelList);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            adapter.setOnItemClickLisiner(new User_order_showAdapter.onItem_ClickLisiner() {
                                @Override
                                public void onClick(int position) {
                                    String user_name=  modelList.get(position).getUser_name();
                                    Toast.makeText(User_order_Show.this, "Name:"+user_name, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void Update(int position) {

                                    User_order_showModel get_item=modelList.get(position);
                                    alertDialog=new AlertDialog.Builder(User_order_Show.this).create();
                                     alertDialog.setCancelable(false);
                                    layoutInflater=getLayoutInflater();
                                    view=layoutInflater.inflate(R.layout.user_details_update,null);
                                    alertDialog.setView(view);

                                    editText_number=view.findViewById(R.id.edit_user_phon);
                                    exit_button=view.findViewById(R.id.exit_button);
                                    Update_button=view.findViewById(R.id.edit_Adress_save);
                                 TextView textView_PdName=view.findViewById(R.id.pd_upName);
                                    textView_PdName.setText("Name:"+category_model_Item.getPdName());
                                    editText_number.setText(get_item.getUser_phon());

                                    String user_phon=editText_number.getText().toString();
                                   // String user_gmail=category_model_Item.get.toString();
                                    textView_orderQuantity=view.findViewById(R.id.pd_quantity);
                                numberButton=view.findViewById(R.id.update_quantity);
                                    numberButton.setNumber(String.valueOf(category_model_Item.getSell_quanitiy()));
                       textView_orderQuantity.setText("Order Quantity:"+category_model_Item.getSell_quanitiy());


                                    Update_button.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                       /*     numberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
                                                @Override
                                                public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                                                    Toast.makeText(User_order_Show.this, ""+newValue, Toast.LENGTH_SHORT).show();

                                                }
                                            });
*/

                                            int quantity = Integer.parseInt(numberButton.getNumber().toString());
                                            String Up_id= categoryShowModel_arrayList.get(position).getPid();
                                            pd_Category_showModel Item=new pd_Category_showModel();

                                            StringRequest string_request_stock=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/product_show.php", new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response_stock) {
                                                    try {
                                                        JSONArray jsonArray = new JSONArray(response_stock);
                                                        ///  categoryShowModel_arrayList.clear();
                                                        for(int i = 0; i <jsonArray.length(); i++) {
                                                            JSONObject object=jsonArray.getJSONObject(i);
                                                            pd_id=object.getString("serial_id");
                                                            if(pd_id.contains(Up_id)){
                                                                purchase_quanitiy=object.getInt("parchase_quanitiy");
                                                                if(!object.getString("sell_quanitiy").contains("null")){
                                                                    sell_quanitiy=object.getInt("sell_quanitiy");
                                                                }else{
                                                                    stock= purchase_quanitiy-sell_quanitiy;
                                                                    if(stock<quantity){
                                                                        Toast.makeText(User_order_Show.this, "Your Quantity Stock From Over..", Toast.LENGTH_SHORT).show();
                                                                    }else{

                                                                        Item.setPid(Up_id);
                                                                        Item.setSell_quanitiy(quantity);
                                                                        if(category_AddSqlit.Update_data(Item)>0){
                                                                            Toast.makeText(User_order_Show.this, "Update SuccessFull..:", Toast.LENGTH_SHORT).show();

                                                                           // alertDialog.dismiss();
                                                                        }else{
                                                                            Toast.makeText(User_order_Show.this, "Update Faild", Toast.LENGTH_SHORT).show();
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

                                     //user phone number update

                                            String getUser_gmail= get_item.getUser_email();
                                StringRequest stringRequest1=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/User_Address_update.php", new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
                                                    editText_number.setText("");
                                                    alertDialog.dismiss();
                                         Toast.makeText(User_order_Show.this,response, Toast.LENGTH_SHORT).show();

                                                }
                                            }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    Toast.makeText(User_order_Show.this, "Your Data Update Faild.."+error.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }){
                                                @Override
                                                protected Map<String, String> getParams() throws AuthFailureError {
                                                    Map<String,String>hasmp=new HashMap<String,String>();
                                                    hasmp.put("email",getUser_gmail);
                                                    hasmp.put("phon",user_phon);
                                                    return  hasmp;
                                                }

                                            };
                                            RequestQueue request=Volley.newRequestQueue(getApplicationContext());
                                            request.add(stringRequest1);

                                        };

                                    });


                                    exit_button.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            alertDialog.dismiss();
                                        }
                                    });
                                    alertDialog.show();

                                }

                                @Override
                                public void Order_CategorySelect(int position) {
                                    User_order_showModel item=modelList.get(position);
                                    String categoryName= categoryShowModel_arrayList.get(position).getCategoryName();
                                     String pd_id=categoryShowModel_arrayList.get(position).getPid();
                                    int pd_sell_quanitiy=categoryShowModel_arrayList.get(position).getSell_quanitiy();
                                    int pd_sell_price=categoryShowModel_arrayList.get(position).getSelling_price();
                                    int pd_discount=categoryShowModel_arrayList.get(position).getPd_discount();
                                    String pd_name=categoryShowModel_arrayList.get(position).getPdName();
                                    String  pd_size=categoryShowModel_arrayList.get(position).getPdSize();

                                    StringRequest stringRe=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/sell_product.php", new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Toast.makeText(User_order_Show.this,response , Toast.LENGTH_SHORT).show();
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Toast.makeText(User_order_Show.this, "Your Order Delivary Faild.."+error.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }){
                                        @Override
                                        protected Map<String, String> getParams() throws AuthFailureError {
                                            Map<String,String>hasmp=new HashMap<String,String>();

                                            hasmp.put("sell_id",pd_id);
                                            hasmp.put("sell_name",pd_name);
                                            hasmp.put("sell_quanitiy", String.valueOf(pd_sell_quanitiy));
                                            hasmp.put("sell_price", String.valueOf(pd_sell_price));
                                            hasmp.put("sell_discount", String.valueOf(pd_discount));
                                            hasmp.put("sell_size",pd_size);
                                            hasmp.put("category_name",categoryName);
                                            hasmp.put("user_name",item.getUser_name());
                                            hasmp.put("region_name",item.getRegion_name());
                                            hasmp.put("user_email",item.getUser_email());
                                            hasmp.put("user_area",item.getUser_location());
                                            hasmp.put("user_city",item.getUser_city());
                                            hasmp.put("user_flat_no",item.getUser_flat_no());
                                            hasmp.put("user_phon",item.getUser_phon());
                                            hasmp.put("user_order_date",item.getCurrent_date());
                                            hasmp.put("delivery_status",item.getOrder_status());
                                            hasmp.put("shipping_rate",String.valueOf(item.getShipping_rate()));
                                            hasmp.put("sell_Total_price",String.valueOf(item.getProd_TotalPrice()));
                                            //
                                            return hasmp;
                                        }
                                    };

                                    RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
                                    requestQueue.add(stringRe);

                                }

                                @Override
                                public void on_Delete(int position) {
                              pd_Category_showModel get_Item=new pd_Category_showModel();
                                    String id= categoryShowModel_arrayList.get(position).getPid();
                                       get_Item.setPid(id);
                                    if(category_AddSqlit.Delete_data(get_Item)>0){
                                        Toast.makeText(getApplicationContext(), "Delete SuccessFull..", Toast.LENGTH_SHORT).show();
                                        adapter.notifyDataSetChanged();
                                        categoryShowModel_arrayList.clear();
                                    }else{
                                   Toast.makeText(getApplicationContext(), "Delete Faild", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
         Toast.makeText(User_order_Show.this, "Your Data Not Found"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}