package com.example.my_json_review;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

public class Add_json extends AppCompatActivity {
  private EditText editText_id,editText_name,editText_price,editText_details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_json);

        editText_id=(EditText)findViewById(R.id.edit_pd_id);
        editText_name=(EditText)findViewById(R.id.edit_pdName);
        editText_price=(EditText)findViewById(R.id.edit_pdprice);
        editText_details=(EditText)findViewById(R.id.edit_pdDetails);
       // editText_id=(EditText)findViewById(R.id.edit_pd_id);
    }

    public void Add_jsonData(View view) {
        String pid = editText_id.getText().toString();
        String pName = editText_name.getText().toString();
        String pPrice = editText_price.getText().toString();
        String pDetails = editText_details.getText().toString();

        //  String myLisk="https://my-sotfwar.000webhostapp.com/test_Add.php";

        RequestQueue requestQueue = Volley.newRequestQueue(Add_json.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/mywork.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Add_json.this, "Data Add SuccessFull..", Toast.LENGTH_SHORT).show();
                editText_price.setText("");
                editText_name.setText("");
                editText_id.setText("");
                editText_details.setText("");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Add_json.this, "Error :" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> hasmap = new HashMap<String, String>();
                hasmap.put("product_id", pid);
                hasmap.put("product_name", pName);
                hasmap.put("product_price", pPrice);
                hasmap.put("product_deatils", pDetails);
                return hasmap;
            }


        };
        requestQueue.add(stringRequest);


    }
}