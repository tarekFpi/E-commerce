package com.example.my_json_review;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class purchsase_orderShow extends AppCompatActivity {
   private RecyclerView recyclerView;
    private List<purchase_orderShow_Model>order_list=new ArrayList<>();
   private purchase_orderShow_Adapter adapter;
    private AlertDialog alertDialog;
    private EditText editText_name,editText_price;
    private TextView textView_up_id;
    private LayoutInflater layoutInflater;
    private View view;
    private Button button_UPdate,button_exit;
    private String serial_id,bill_on,pd_name,pd_categoryName
    ,pd_size,supplayerName,pd_deatisl;
    private   int purchase_price,purchase_quanitiy,sell_price,sell_discount,puchase_discount;
    private EditText editText_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchsase_order_show);
        recyclerView=(RecyclerView)findViewById(R.id.purchase_orderShow);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       editText_search=(EditText)findViewById(R.id.purchase_order_search);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/purchase_orderShow.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for(int i = 0; i <jsonArray.length(); i++) {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);

                       serial_id=jsonObject.getString("serial_id");
                        bill_on= jsonObject.getString("Bill_No");
                        pd_name= jsonObject.getString("pName");
                        pd_categoryName=jsonObject.getString("Category_name");
                     purchase_price= jsonObject.getInt("price");
                     sell_price= jsonObject.getInt("selling_price");
                     sell_discount= jsonObject.getInt("sell_Discount");
                     purchase_quanitiy= jsonObject.getInt("parchase_quanitiy");
                     puchase_discount= jsonObject.getInt("purhase_discount");
                    pd_deatisl=  jsonObject.getString("details");
                    pd_size= jsonObject.getString("product_size");
                    supplayerName= jsonObject.getString("Sublayer_name");
     String url="https://my-sotfwar.000webhostapp.com/Images/"+jsonObject.getString("image");

     purchase_orderShow_Model item=
 new purchase_orderShow_Model(serial_id,bill_on,pd_name,pd_categoryName,purchase_quanitiy,purchase_price,
         sell_price,supplayerName,sell_discount,puchase_discount,pd_deatisl,pd_size,url);
       order_list.add(item);

                    }
                    adapter=new purchase_orderShow_Adapter(order_list,getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter.setOnItemClick(new purchase_orderShow_Adapter.onItemClickLisiner() {
                    @Override
                    public void onClick(int position) {

                    }

                    @Override
                    public void OnUpdate(int position) {
                   purchase_orderShow_Model itemPosition=order_list.get(position);
                      //  Toast.makeText(purchsase_orderShow.this, ""+itemPosition.getSupplayer_name(), Toast.LENGTH_SHORT).show();


                        alertDialog=new AlertDialog.Builder(purchsase_orderShow.this).create();
                        alertDialog.setTitle("Update Product");
                        alertDialog.setCancelable(false);
                        layoutInflater=getLayoutInflater();
                        view= layoutInflater.inflate(R.layout.update_layout,null);
                        alertDialog.setView(view);
                        alertDialog.show();

                        textView_up_id=view.findViewById(R.id.text_update_Id);
                        editText_name=  view.findViewById(R.id.edit_Upd_Name);
                        editText_price= view.findViewById(R.id.edit_upd_price);

                        button_UPdate=view.findViewById(R.id.btn_update);
                        button_exit=view.findViewById(R.id.btn_exit);

                        editText_name.setText(itemPosition.getProduct_name());
                        editText_price.setText(""+itemPosition.getPurchase_price());
                        textView_up_id.setText("Serial On:"+itemPosition.getSerial_id());


                        button_UPdate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String id= itemPosition.getSerial_id();
                                String name= editText_name.getText().toString();
                                String price= editText_price.getText().toString();

                                StringRequest stringRequest= new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/product_update.php", new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                                        textView_up_id.setText("");
                                        editText_name.setText("");
                                        editText_price.setText("");
                                        alertDialog.dismiss();
                                        adapter.notifyDataSetChanged();
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                              Toast.makeText(getApplicationContext(), "Your Data Not Found"+error.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams()  {


                                        Map<String,String>hasmp=new HashMap<String,String>();
                                        hasmp.put("serial_on",id);
                                        hasmp.put("pdName",name);
                                        hasmp.put("pur_price",price);
                                        return  hasmp;
                                    }
                                };

                                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                                requestQueue.add(stringRequest);
                            }

                        });

                        button_exit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.dismiss();
                            }
                        });


                    }

                    @Override
                    public void OnDelete(int position) {
                        purchase_orderShow_Model itemPosition=order_list.get(position);
                        String id= itemPosition.getSerial_id();

                        StringRequest stringRequest_delete= new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/purchase_orderDelete.php", new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                     Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                      alertDialog.dismiss();
                      adapter.notifyDataSetChanged();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "Your Data Delete Faild.."+error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() {

                                Map<String,String>hasmp=new HashMap<String,String>();
                                hasmp.put("serial_id",id);
                                return  hasmp;
                            }
                        };
                        RequestQueue requestQueue_delete= Volley.newRequestQueue(getApplicationContext());
                        requestQueue_delete.add(stringRequest_delete);
                    }
                });
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
    Toast.makeText(purchsase_orderShow.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
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
        List<purchase_orderShow_Model>filter_list=new ArrayList<>();
        for (purchase_orderShow_Model item :order_list){

            if(item.getProduct_name().toLowerCase().contains(text.toLowerCase())){
                filter_list.add(item);
            }
        }
       adapter.filter_ListChange(filter_list);
    }
}