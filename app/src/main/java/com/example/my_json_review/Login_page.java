package com.example.my_json_review;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login_page extends AppCompatActivity {
  private EditText editText_userGmail,editText_password;
private TextView textView_singup;
private Button button_log;
private String Admin_gmail="";
private String Admin_password="";
private String status="Admin";

    String getGmail="";
    String getPassword="";
  private String user_status="User";
    @Override
    protected void onStart() {

        StringRequest stringRequest_admin=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/Admin_login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        Admin_gmail= jsonObject.getString("Gmail");
                        Admin_password=  jsonObject.getString("password");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Your Login Faild.."+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue_admin= Volley.newRequestQueue(getApplicationContext());
        requestQueue_admin.add(stringRequest_admin);

        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
         textView_singup=(TextView)findViewById(R.id.sing_up);
         button_log=(Button)findViewById(R.id.login_btn);
        editText_userGmail=(EditText)findViewById(R.id.lguser_email);
        editText_password=(EditText)findViewById(R.id.lg_edit_password);

         textView_singup.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

               Intent intent=new Intent(getApplicationContext(),SingUp_page.class);
               startActivity(intent);
             }
         });



        button_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String gmail = editText_userGmail.getText().toString();
                String passw = editText_password.getText().toString();

                if ((gmail.contains(Admin_gmail) && passw.contains(Admin_password))) {
                    Intent admin_intent = new Intent(getApplicationContext(), Admin_Homepage.class);
                    admin_intent.putExtra("status", status);
                    Toast.makeText(Login_page.this, "Login" + status, Toast.LENGTH_SHORT).show();
                    startActivity(admin_intent);
                }else {
                    editText_password.setText("");
                    editText_userGmail.setText("");

                StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/Login_page.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(Login_page.this, ""+response, Toast.LENGTH_SHORT).show();
                        if (response.contains("1")) {

                            Toast.makeText(Login_page.this, "Login SuccessFull..:", Toast.LENGTH_LONG).show();
                            editText_password.setText("");
                            editText_userGmail.setText("");
                            Intent intent = new Intent(getApplicationContext(), product_show.class);
                        } else {
                            editText_password.setText("");
                            editText_userGmail.setText("");
                            Toast.makeText(Login_page.this, "Gmail password Not Match", Toast.LENGTH_LONG).show();
                        }


                       /* try {
                            JSONArray jsonArray=new JSONArray(response);
                            for (int i = 0; i <jsonArray.length() ; i++) {
                          JSONObject Object=jsonArray.getJSONObject(i);
                            getGmail=Object.getString("Gmail");
                            getPassword=Object.getString("password");

                          if(getGmail.contains(gmail) && getPassword.contains(passw)){
                              Toast.makeText(Login_page.this, "Login SuccessFull..", Toast.LENGTH_SHORT).show();
                              editText_password.setText("");
                              editText_userGmail.setText("");
                          *//*    Intent intent=new Intent(getApplicationContext(),product_show.class);
                              startActivity(intent);*//*


                         *//*   if(gmail.contains(Admin_gmail) && passw.contains(Admin_password)){
                                  Intent admin_intent=new Intent(getApplicationContext(),Admin_Homepage.class);
                                  admin_intent.putExtra("status",status);
                                  Toast.makeText(Login_page.this, "Login"+status, Toast.LENGTH_SHORT).show();
                                  startActivity(admin_intent);
                              }else{
                                  Toast.makeText(Login_page.this, "Admin password Not Match..", Toast.LENGTH_SHORT).show();
                              }*//*
                          }else{
                              editText_password.setText("");
                              editText_userGmail.setText("");
                              Toast.makeText(Login_page.this, "Email password Not Match", Toast.LENGTH_SHORT).show();
                          }
                        }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
*/
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
  Toast.makeText(getApplicationContext(), "Your Data Not Found.." + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        HashMap hashMap = new HashMap();
                        hashMap.put("user_gamil", gmail);
                        hashMap.put("user_password", passw);
                        return hashMap;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }
            }
        });
    }


}