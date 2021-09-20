package com.example.my_json_review;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.*;
import com.android.volley.toolbox.*;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class product_purchase extends AppCompatActivity {
private EditText editText_id,editText_price,editText_details,
        editText_quanitiy,editText_discount,editText_pdSize,editText_sell_price,editText_sublayer_name,
        editText_purchase_discount;
private Spinner spinner_product_name,spinner_category_name;
private ImageView imageView;
private String mylink="https://my-sotfwar.000webhostapp.com/productAdd.php";
private Uri uri_image;
private String encodeImage;
private int request_cord;
private Bitmap bitmap;
private Button button_add;
private ProgressDialog progressDialog;
private ArrayAdapter<String>arrayAdapt_pdName;
private ArrayList<String>arrayList_pd_Name=new ArrayList<String>();

    private ArrayAdapter<String>arrayAdapt_Category_Name;
    private ArrayList<String>arrayList_Catgory_Name=new ArrayList<String>();
private String product_name;
    private String categroy_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_purchase);

     imageView=(ImageView)findViewById(R.id.image_id);
     editText_id=(EditText)findViewById(R.id.edit_Id);
        spinner_product_name=(Spinner) findViewById(R.id.spinner_product_name);
     editText_price=(EditText)findViewById(R.id.edit_price);
     editText_details=(EditText)findViewById(R.id.edit_pdDetails);

     editText_quanitiy=(EditText)findViewById(R.id.edit_quantity);

     editText_pdSize=(EditText)findViewById(R.id.edit_pdSize);
     editText_quanitiy=(EditText)findViewById(R.id.edit_quantity);
     editText_sell_price=(EditText)findViewById(R.id.edit_selling_price);
     editText_discount=(EditText)findViewById(R.id.edit_Discount);
     editText_purchase_discount=(EditText)findViewById(R.id.edit_purchase_discount);
   editText_sublayer_name=(EditText)findViewById(R.id.edit_sublayer);
  spinner_category_name=(Spinner) findViewById(R.id.spinner_category_name);

        button_add=(Button)findViewById(R.id.btn_image_add);
      progressDialog=new ProgressDialog(this);
      progressDialog.setMessage("Please Wite....");
      progressDialog.setCancelable(false);

       imageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               file_Choose();
           }
       });


       button_add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


               if (editText_id.getText().toString().isEmpty()) {
                   editText_id.setError("Please Your Bill No Empty..");
                   editText_id.requestFocus();
               } else if (spinner_product_name.getSelectedItem().toString().contains("Select Product Name")) {
                   Toast.makeText(product_purchase.this, "please Your product Name is Empty..", Toast.LENGTH_SHORT).show();
                   spinner_product_name.requestFocus();
               } else if (spinner_category_name.getSelectedItem().toString().contains("Select Category Name")) {
                   Toast.makeText(product_purchase.this, "please Your Cateogry Name is Empty..", Toast.LENGTH_SHORT).show();
                   spinner_category_name.requestFocus();
               } else if (editText_quanitiy.getText().toString().isEmpty()) {
                   editText_quanitiy.setError("Please Your Quantity is Empty..");
                   editText_quanitiy.requestFocus();

               } else if (editText_price.getText().toString().isEmpty()) {
                   editText_price.setError("Please Your Quantity is Empty..");
                   editText_price.requestFocus();
               } else if (editText_sell_price.getText().toString().isEmpty()) {
                   editText_sell_price.setError("Please Your Quantity is Empty..");
                   editText_sell_price.requestFocus();
               } else if (editText_sublayer_name.getText().toString().isEmpty()) {
                   editText_sublayer_name.setError("Please Your Quantity is Empty..");
                   editText_sublayer_name.requestFocus();
               } else if (editText_discount.getText().toString().isEmpty()) {
                   editText_discount.setError("Please Your Quantity is Empty..");
                   editText_discount.requestFocus();
               } else if (editText_purchase_discount.getText().toString().isEmpty()) {
                   editText_purchase_discount.setError("Please Your Quantity is Empty..");
                   editText_purchase_discount.requestFocus();
               }else if (editText_details.getText().toString().isEmpty()) {
                   editText_details.setError("Please Your Description is Empty..");
                   editText_details.requestFocus();
               }else if (editText_pdSize.getText().toString().isEmpty()) {
                   editText_pdSize.setError("Please Your prodcut Size is Empty..");
                   editText_pdSize.requestFocus();
               }else{


                   progressDialog.show();
                   String bill_no = editText_id.getText().toString();
                   String name = spinner_product_name.getSelectedItem().toString();
                   String price = editText_price.getText().toString();
                   String pDetails = editText_details.getText().toString();
                   String pquanitiy = editText_quanitiy.getText().toString();
                   String sell_discount = editText_discount.getText().toString();
                   String pd_size = editText_pdSize.getText().toString();
                   String pd_sellprice = editText_sell_price.getText().toString();

                   String purchase_discount = editText_purchase_discount.getText().toString();
                   String sublayer_name = editText_sublayer_name.getText().toString();
                   String category_name = spinner_category_name.getSelectedItem().toString();

                   StringRequest stringRequest = new StringRequest(Request.Method.POST, mylink, new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       Toast.makeText(product_purchase.this, response, Toast.LENGTH_LONG).show();
                       editText_id.setText("");
                       spinner_category_name.setSelection(0);
                       editText_price.setText("");
                       editText_details.setText("");
                       editText_quanitiy.setText("");
                       editText_discount.setText("");
                       editText_pdSize.setText("");
                       editText_sell_price.setText("");
                       imageView.setImageResource(0);
                       progressDialog.dismiss();
                       editText_purchase_discount.setText("");
                       editText_sublayer_name.setText("");
                       progressDialog.dismiss();
                   }
               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       progressDialog.dismiss();
                       Toast.makeText(product_purchase.this, "Your Data Not Found:" + error.getMessage(), Toast.LENGTH_SHORT).show();
                   }
               }) {
                   @Override
                   protected Map<String, String> getParams() {

                       Map<String, String> hasmp = new HashMap<String, String>();
                       hasmp.put("pd_id", bill_no);
                       hasmp.put("pd_name", name);
                       hasmp.put("purchase_price", price);
                       hasmp.put("pd_quanitiy", pquanitiy);
                       hasmp.put("pd_Details", pDetails);
                       hasmp.put("sell_discount", sell_discount);
                       hasmp.put("purchase_discount", purchase_discount);
                       hasmp.put("category_name", category_name);
                       hasmp.put("sublayer_name", sublayer_name);
                       hasmp.put("pd_size", pd_size);
                       hasmp.put("image", encodeImage);
                       hasmp.put("pd_sell_price", pd_sellprice);
                       return hasmp;
                   }
               };
               RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
               requestQueue.add(stringRequest);
           }
           }
       });

        Spnner_Name_Set();
        Spnner_CategoryName_Set();
    }

    private void Spnner_CategoryName_Set() {
    StringRequest stringRequest_categroy=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/Category_name_Api.php", new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
          //  Toast.makeText(product_purchase.this, "", Toast.LENGTH_SHORT).show();
            try {
                JSONArray jsonArray=new JSONArray(response);

                arrayList_Catgory_Name.clear();
                arrayList_Catgory_Name.add("Select Category Name");
                for (int i=0; i  <jsonArray.length();i++){
           JSONObject jsonObject=jsonArray.getJSONObject(i);
               categroy_name= jsonObject.getString("Category_name").toString();
               Category_Name_Model category_model=new Category_Name_Model(categroy_name);
                    arrayList_Catgory_Name.add(category_model.getCategoryName());
               }
                arrayAdapt_Category_Name=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,arrayList_Catgory_Name);
                spinner_category_name.setAdapter(arrayAdapt_Category_Name);

                spinner_category_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String category_name=arrayList_Catgory_Name.get(position).toString();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(product_purchase.this, "Category Name Not Found!!!"+error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
    RequestQueue request_category=Volley.newRequestQueue(getApplicationContext());
      request_category.add(stringRequest_categroy);

    }

    private void Spnner_Name_Set(){

        StringRequest stringRequest_pdName=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/product_nameApi.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    arrayList_pd_Name.add("Select Product Name");
                    for(int i=0; i<jsonArray.length();i++){
                   JSONObject jsonObject=jsonArray.getJSONObject(i);
                  product_name= jsonObject.getString("product_Name").toString();
                 product_Name_Model model=new product_Name_Model(product_name);
                  arrayList_pd_Name.add(model.getProduct_name());
                    }
        arrayAdapt_pdName=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line,arrayList_pd_Name);
                    spinner_product_name.setAdapter(arrayAdapt_pdName);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                spinner_product_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                       // arrayList.clear();
                     String getItem=arrayList_pd_Name.get(position).toString();
                  //  Toast.makeText(product_purchase.this, "select:"+getItem, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
           Toast.makeText(product_purchase.this, "product Name is Not Found..", Toast.LENGTH_SHORT).show();
            }
        });
     RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
     requestQueue.add(stringRequest_pdName);

    }

    private void file_Choose(){
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,request_cord);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(request_cord==requestCode && resultCode==RESULT_OK && data!=null){
            uri_image=data.getData();
            try {
            InputStream stream=getContentResolver().openInputStream(uri_image);

                bitmap=BitmapFactory.decodeStream(stream);
                imageView.setImageBitmap(bitmap);
                imageStorge(bitmap);

               /// Toast.makeText(this, "path:"+bitmap, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
           e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

     private void imageStorge(Bitmap bitmap) {
     ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
     bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);

     byte [] imageaByte=byteArrayOutputStream.toByteArray();

        encodeImage=android.util.Base64.encodeToString(imageaByte, Base64.DEFAULT);

    }



}