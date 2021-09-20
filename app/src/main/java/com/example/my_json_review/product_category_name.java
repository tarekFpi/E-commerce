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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class product_category_name extends AppCompatActivity {
  private RecyclerView recyclerView; //category_name_recycler
 private List<product_categoryModel>modelList=new ArrayList<>();
   private product_categoryAdapter adapter;
    private String pd_id,pdName,date;
   private EditText editText_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_category_name);
        recyclerView=(RecyclerView)findViewById(R.id.category_name_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
      editText_search=(EditText)findViewById(R.id.product_search_category);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/Category_Name_show.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray=  new JSONArray(response);
                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        pd_id=jsonObject.getString("pi");
                        pdName=  jsonObject.getString("Category_name");
                        date= jsonObject.getString("Date");

                        product_categoryModel item=new product_categoryModel(pd_id,pdName,date);
                        modelList.add(item);
                    }
                    adapter=new product_categoryAdapter(getApplicationContext(),modelList);
                    recyclerView.setAdapter(adapter);
                    adapter.setOnItemClickLisiner(new product_categoryAdapter.onItemClickLisiner() {
                        @Override
                        public void Onclick(int position) {

                        }

                        @Override
                        public void OnDelete(int position) {
                            product_categoryModel item=modelList.get(position);

                            StringRequest string_delete=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/category_nameDelete.php", new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                                    modelList.clear();
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(), "Category Name Delete Faild.."+error.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }){
                                @Override
                                protected Map<String, String> getParams()  {
                                    HashMap hashMap=new HashMap();
                                    hashMap.put("category_id",item.getCategory_id());
                                    return hashMap;
                                }
                            };
                            RequestQueue requestQueue_delete=Volley.newRequestQueue(getApplicationContext());
                            requestQueue_delete.add(string_delete);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Your Category Name Not Found..", Toast.LENGTH_SHORT).show();
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
        List<product_categoryModel>filterList=new ArrayList<>();
        for (product_categoryModel item :modelList){
            if(item.getCategory_name().toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);
            }
        }
        adapter.filterListChange(filterList);
    }

}