package com.example.my_json_review;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
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

public class product_show extends AppCompatActivity {
private RecyclerView recyclerView;
private image_Adapter adapter;
private List<Purchase_Model>image_modelList=new ArrayList<>();
//private String myLike="https://my-sotfwar.000webhostapp.com/product_show.php";
private EditText editText_search;
private CardView cardView;
 private int pd_sellprice,purchase_price,pd_discount,pd_stock,purchase_quanitiy;
    private LayoutInflater layoutInflater;
    private View view;
    private AlertDialog alertDialog;
    private Button button_UPdate,button_exit;

    private EditText editText_name,editText_price;
    private TextView textView_up_id;
    private String name,image_url,deatils,pd_Id,pd_Size,bill_no,category_name;
     private  int pq_quan=0,totoal_qu=0;
     int sell_quanitiy=0;
     private ProgressDialog progressDialog;
    String status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_show);
        recyclerView=(RecyclerView)findViewById(R.id.recyler_pd_show);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       editText_search=(EditText)findViewById(R.id.product_search);
      progressDialog=new ProgressDialog(this);
      progressDialog.setCancelable(false);
      progressDialog.setMessage("Please Wite...");
      progressDialog.show();
        //image_modelList.clear();
        StringRequest stringRequest1=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/product_show.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    sell_quanitiy=0;
                 //  JSONObject jsonMain=new JSONObject(response);
                    JSONArray jsonArray=new JSONArray(response);

                    for(int i = 0; i <jsonArray.length(); i++) {
                        JSONObject object=jsonArray.getJSONObject(i);
                        bill_no=object.getString("Bill_No");
                        category_name=object.getString("Category_name");
                        name=object.getString("pName");
                        deatils=object.getString("details");
                        pd_Id=object.getString("serial_id");
                        pd_Size=object.getString("product_size");
                        pd_sellprice=object.getInt("selling_price");
                        pd_discount=object.getInt("sell_Discount");
                        purchase_price=object.getInt("price");
                        purchase_quanitiy=object.getInt("parchase_quanitiy");
                        image_url=object.getString("image").toString();

                         if(!object.getString("sell_quanitiy").contains("null")){
                           sell_quanitiy=object.getInt("sell_quanitiy");
                             totoal_qu= purchase_quanitiy-sell_quanitiy;
                           // totoal_qu=object.getInt("Stock");
                        }else{
                        // sell_quanitiy=0;
                       totoal_qu= purchase_quanitiy-sell_quanitiy;
                        }

                        String url="https://my-sotfwar.000webhostapp.com/Images/"+image_url;

             Purchase_Model addItem= new Purchase_Model(bill_no,category_name,pd_Id,name,totoal_qu,purchase_price,pd_sellprice,pd_discount,purchase_quanitiy,sell_quanitiy,url,deatils,pd_Size);
                 image_modelList.add(addItem);

                adapter=new image_Adapter(getApplicationContext(),image_modelList);
                recyclerView.setAdapter(adapter);
                        progressDialog.dismiss();
               //adapter.notifyDataSetChanged();
                    }

                   adapter.setOnItemClick(new image_Adapter.onItemClickLisiner() {
                            @Override
                            public void onClick(int position) {
                                Purchase_Model itemPosition= image_modelList.get(position);

                            String name=image_modelList.get(position).getPdName();
                             Toast.makeText(product_show.this, "Name:"+itemPosition.getPdSize(), Toast.LENGTH_SHORT).show();

                                Intent inten=new Intent(getApplicationContext(),product_Category.class);
                                inten.putExtra("pd_cord",itemPosition.getPid());
                                inten.putExtra("pName",itemPosition.getPdName());
                                inten.putExtra("pd_price",itemPosition.getSelling_price());
                                inten.putExtra("pd_Desr",itemPosition.getPdDeatils());
                                inten.putExtra("pd_stock",itemPosition.getStock());
                                 inten.putExtra("pd_discount",itemPosition.getPd_discount());
                                inten.putExtra("pd_image",itemPosition.getPdImage());
                                inten.putExtra("pd_size",itemPosition.getPdSize());
                                inten.putExtra("category_name",itemPosition.getCategoryName());
                                startActivity(inten);
                            }

                        });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
          Toast.makeText(product_show.this, "Data Not Found"+error, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest1);

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

void  fileList(String text){
     List<Purchase_Model>filterList=new ArrayList<>();
     for (Purchase_Model item: image_modelList){
         if(item.getPdName().toLowerCase().contains(text.toLowerCase())){
             filterList.add(item);
         }
     }
     adapter.filterList(filterList);
}

}