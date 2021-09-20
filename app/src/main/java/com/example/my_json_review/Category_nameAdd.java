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

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Category_nameAdd extends AppCompatActivity {
  private EditText editText_category_name;
  private TextView textView_date;
  private String upload_date;
   private ProgressDialog progressDialog;
   private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_name_add);

        editText_category_name=(EditText)findViewById(R.id.edit_product_name);
        textView_date=(TextView)findViewById(R.id.upload_date);
        SimpleDateFormat sm=new SimpleDateFormat("yyy-MM-dd");
        upload_date= sm.format(new Date());
        textView_date.setText("Date:"+upload_date);
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wite.....");

        toolbar=(Toolbar)findViewById(R.id.category_toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.category_name,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.category_Name_id){
            Intent intent=new Intent(getApplicationContext(),product_category_name.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void save_category(View view) {

        String category_name=editText_category_name.getText().toString().trim();
        if(editText_category_name.getText().toString().isEmpty()){
            editText_category_name.setError("Your Category Name is Empty");
            editText_category_name.requestFocus();
        }else{
            progressDialog.show();
            StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/Category_name_Add.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    editText_category_name.setText("");
                    Toast.makeText(Category_nameAdd.this, response, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(Category_nameAdd.this, "Category Name Add Faild"+error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams(){
                    HashMap hashMa=new HashMap();
                    hashMa.put("category_name",category_name);
                    hashMa.put("upload_date",upload_date);
                    return hashMa;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
             requestQueue.add(stringRequest);
        }

    }


}