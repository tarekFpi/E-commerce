package com.example.my_json_review;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.internal.Util;

public class product_name_Add extends AppCompatActivity {
  private  String url="https://my-sotfwar.000webhostapp.com/product_name_Add.php";
  private EditText editText_pdName;
  private TextView textView_date;
  private     String uploadDate;
  private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_name__add);
        editText_pdName=(EditText)findViewById(R.id.edit_product_name);
        textView_date=(TextView)findViewById(R.id.upload_date);
        SimpleDateFormat sm=new SimpleDateFormat("yyyy/MM/dd");
           uploadDate= sm.format(new Date());
         textView_date.setText("Date:"+uploadDate);

        Toolbar toolbar= (Toolbar) findViewById(R.id.pd_add_toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
         menuInflater.inflate(R.menu.prodcut_name,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.pd_name_show){
            Intent intent= new Intent(getApplicationContext(),product_nameShow.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Save(View view) {
      String product_name= editText_pdName.getText().toString().trim();
        if(editText_pdName.getText().toString().isEmpty()){
        // Toast.makeText(getApplicationContext(),"Product Name is Empty..",Toast.LENGTH_LONG).show();
            editText_pdName.setError("Product Name is Empty..");
            editText_pdName.requestFocus();
        }else{
            progressDialog=new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Please Wite.....");
            progressDialog.show();
            StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    editText_pdName.setText("");
                    Toast.makeText(product_name_Add.this, "Your product Name Add SuccessFull.."+response, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
         Toast.makeText(product_name_Add.this, "Product Name Add Faild!!!!"+error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams(){
                    HashMap hashMap=new HashMap();
                    hashMap.put("pd_name",product_name);
                    hashMap.put("Date",uploadDate);
                    return hashMap;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }

    }



}