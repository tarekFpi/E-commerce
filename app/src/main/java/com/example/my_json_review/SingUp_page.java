package com.example.my_json_review;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.util.HashMap;
import java.util.Map;

public class SingUp_page extends AppCompatActivity {
    private EditText editText_userNeme,editText_userGmail,editText_password,editText_comfromPassword;
    private Button sigUpbtn;
    private TextView textView_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up_page);

        editText_userNeme=(EditText)findViewById(R.id.edit_userName);
        editText_userGmail=(EditText)findViewById(R.id.edit_email);
        editText_password=(EditText)findViewById(R.id.edit_password);
        editText_comfromPassword=(EditText)findViewById(R.id.edit_password_com);
        sigUpbtn=(Button)findViewById(R.id.save_singup);
        textView_login=(TextView)findViewById(R.id.log_page);

        textView_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         Intent intent=new Intent(getApplicationContext(),Login_page.class);
          startActivity(intent);
            }
        });

        sigUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            String user_name=editText_userNeme.getText().toString();
            String gmail=editText_userGmail.getText().toString();
            String passw=  editText_password.getText().toString();
            String comfrom_pass= editText_comfromPassword.getText().toString();

                StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/User_sing_up.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
             Toast.makeText(SingUp_page.this, response, Toast.LENGTH_SHORT).show();
              editText_comfromPassword.setText("");
             editText_password.setText("");
             editText_userGmail.setText("");
             editText_userNeme.setText("");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
             Toast.makeText(SingUp_page.this, "Your SignUp Faild.."+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams()   {

                        HashMap hashMap=new HashMap();
                        hashMap.put("UserName",user_name);
                        hashMap.put("user_gmail",gmail);
                        hashMap.put("password",passw);
                        hashMap.put("Confrom_password",comfrom_pass);
                        return hashMap;
                    }
                };
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);

            }
        });

    }
}