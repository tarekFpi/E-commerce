package com.example.my_json_review;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;

public class Update_product extends AppCompatActivity {
    private EditText editText_name,editText_price;
    private TextView textView;
    private Button button; //btn_update
  //  private String mylink="https://my-sotfwar.000webhostapp.com/product_update.php";
  String position;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        textView=(TextView) findViewById(R.id.text_up_Id);
        editText_name=(EditText)findViewById(R.id.edit_Up_Name);
        editText_price=(EditText)findViewById(R.id.edit_up_price);
        button=(Button)findViewById(R.id.btn_update);
        getIntentData();


     button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //  String id= textView.getText().toString();
               String name= editText_name.getText().toString();
               String price= editText_price.getText().toString();


               StringRequest stringRequest= new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/product_update.php", new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       Toast.makeText(getApplicationContext(), "Your Data Update SuccessFull....", Toast.LENGTH_SHORT).show();
                       textView.setText("");
                       editText_name.setText("");
                       editText_price.setText("");
                   }
               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       Toast.makeText(getApplicationContext(), "Your Data Not Founde"+error.getMessage(), Toast.LENGTH_SHORT).show();
                   }
               }){
                   @Override
                   protected Map<String, String> getParams() throws AuthFailureError {

                       Map<String,String>hasmp=new HashMap<String,String>();
                       hasmp.put("data_id",id);
                       hasmp.put("data_name",name);
                       hasmp.put("data_price",price);
                       return  hasmp;
                   }
               };
               RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
               requestQueue.add(stringRequest);
           }
       });
    }
    void getIntentData(){
    Bundle bundle=getIntent().getExtras();
      id=bundle.getString("pid");
    String name=bundle.getString("pName");
      position=bundle.getString("Item_position");
    int price=bundle.getInt("pPrice");

   textView.setText("Id:"+id);
   editText_name.setText(name);
   editText_price.setText(""+price);
    }
}