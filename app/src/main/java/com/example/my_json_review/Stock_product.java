package com.example.my_json_review;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Stock_product extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<stockModel> stockModelList=new ArrayList<>();
    stock_Adapter adapter;
    private EditText editText_search;
    private String serial_id, name,image_url,deatils,pd_Id,pd_Size,bill_no,category_name;
    private  int pq_quan=0,stock=0,purchase_price,sell_price,sell_discount,sell_quantity,purchase_quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_product);
        recyclerView=(RecyclerView)findViewById(R.id.stock_recylcer);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
      editText_search=(EditText)findViewById(R.id.stock_search);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/product_show.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                  //  Toast.makeText(Stock_product.this, "hello", Toast.LENGTH_SHORT).show();

                    JSONArray jsonArray=new JSONArray(response);

                    for(int i = 0; i <jsonArray.length(); i++) {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);

                        jsonObject.getString("serial_id");
                        bill_no= jsonObject.getString("Bill_No");
                        name= jsonObject.getString("pName");
                        category_name= jsonObject.getString("Category_name");
                        pd_Size= jsonObject.getString("product_size");
                        purchase_price= jsonObject.getInt("price");
                        sell_price= jsonObject.getInt("selling_price");
                        sell_discount= jsonObject.getInt("sell_Discount");
                        purchase_quantity= jsonObject.getInt("parchase_quanitiy");
                        jsonObject.getString("stock");
                        image_url= jsonObject.getString("image");
                        deatils= jsonObject.getString("details");

                        if(!jsonObject.getString("sell_quanitiy").contains("null")){
                            sell_quantity=jsonObject.getInt("sell_quanitiy");
                            stock= purchase_quantity-sell_quantity;
                        }else{

                            stock= purchase_quantity-sell_quantity;
                        }
                     String url="https://my-sotfwar.000webhostapp.com/Images/"+image_url;
 stockModel item=new
stockModel(bill_no,name,category_name,pd_Size,sell_price,sell_discount,purchase_quantity,sell_quantity,stock,url,deatils);
 stockModelList.add(item);

 adapter=new stock_Adapter(getApplicationContext(),stockModelList);
  recyclerView.setAdapter(adapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
 Toast.makeText(Stock_product.this, "Data Not Found.."+error.getMessage(), Toast.LENGTH_SHORT).show();
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
        List<stockModel>filterList=new ArrayList<>();
        for (stockModel getItem:stockModelList) {
            if(getItem.getProduct_name().toLowerCase().contains(text.toLowerCase())){
                filterList.add(getItem);

            }
        }
        adapter.filterListChinge(filterList);
    }
}