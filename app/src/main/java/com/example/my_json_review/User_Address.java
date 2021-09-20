package com.example.my_json_review;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class User_Address extends AppCompatActivity {
    private EditText editText_UserName,editText_user_email,editText_location,editText_flat_no,editText_city,editText_phon;
    private TextView text_current_date;
    private  int Total_pric;
    static int Shipping_Rate=100;
    private TextView textView_marque;
    private ProgressDialog progressDialog;
   private String current_date;
   private String muLink="https://my-sotfwar.000webhostapp.com/user_dataOrder_Add.php";
    private int pd_sellprice,purchase_price,pd_discount,pd_stock,purchase_quanitiy;
    private String name,image_url,deatils,pd_Id,pd_Size;

    private int sell_quanitiy;
    pd_Category_showModel get_Item;
    private Spinner spinner_regionName,spinner_cityName,spinner_areaName,spinner_Shipping;
    private ArrayList<String>arrayList_regionName=new ArrayList<>();
    private ArrayAdapter<String>arrayAdapter_region;

    private ArrayList<String>arrayList_cityname=new ArrayList<>();
    private ArrayAdapter<String>arrayAdapter_cityName;

    private ArrayList<String>arrayList_Areaname=new ArrayList<>();
    private ArrayAdapter<String>arrayAdapter_AreaName;

    private ArrayList<String>arrayList_shipping=new ArrayList<>();
    private ArrayAdapter<String>arrayAdapter_shippping;
    private RadioGroup radioGroup;
    private RadioButton Select_radioButton;
    private   String select_orderStatus="";
    private product_Category_Add_sqlit category_AddSqlit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__address);

        category_AddSqlit=new product_Category_Add_sqlit(User_Address.this);
        get_Item=new pd_Category_showModel();

        editText_UserName=(EditText)findViewById(R.id.user_name);
        editText_user_email=(EditText)findViewById(R.id.user_email);

        editText_flat_no=(EditText)findViewById(R.id.user_flat_no_id);
        spinner_regionName=(Spinner)findViewById(R.id.spinner_regionName);
        spinner_cityName=(Spinner)findViewById(R.id.spinner_cityName);
        spinner_areaName=(Spinner)findViewById(R.id.spinner_areaName);
        spinner_Shipping=(Spinner)findViewById(R.id.spinner_shipping);
        editText_phon=(EditText)findViewById(R.id.user_phon);
        textView_marque=(TextView)findViewById(R.id.marquee_id);
        radioGroup=(RadioGroup)findViewById(R.id.radi_group);
        textView_marque.setSelected(true);

        arrayList_shipping.add("Select----Shipping---Area");
        arrayList_shipping.add("Dhaka");
        arrayList_shipping.add("Outside");
        arrayAdapter_shippping=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,arrayList_shipping);
        spinner_Shipping.setAdapter(arrayAdapter_shippping);

        text_current_date=(TextView) findViewById(R.id.deatils_current_Date);
        Date date=new Date();
        SimpleDateFormat sm=new SimpleDateFormat("YYYY-MM-dd");
        current_date= sm.format(date.getTime());
        text_current_date.setText("Date:"+current_date);


        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/UserRegion_ShowApi.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray  jsonArray = new JSONArray(response);
                    arrayList_regionName.add("Select---Region--Name");
                    for(int i=0; i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                arrayList_regionName.add(jsonObject.getString("Region_name").toString());
                    }
                    arrayAdapter_region=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,arrayList_regionName);
                    spinner_regionName.setAdapter(arrayAdapter_region);

                    spinner_regionName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ///onItem click action
                            String getRegionName=arrayList_regionName.get(position).toString();

                      StringRequest stringCityName=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/UserCityName_Api.php", new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONArray  jsonArray2 = new JSONArray(response);
                                        arrayList_cityname.clear();
                                        arrayList_cityname.add("Select---City---Name");
                                        for(int i=0; i<jsonArray2.length();i++){
                                     JSONObject jsonObject=jsonArray2.getJSONObject(i);
                                     arrayList_cityname.add(jsonObject.getString("City_name").toString());
                                        }
                                        arrayAdapter_cityName=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,arrayList_cityname);
                                        spinner_cityName.setAdapter(arrayAdapter_cityName);

                                        spinner_cityName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                String getCityName=arrayList_cityname.get(position).toString();
                                           ///     https://my-sotfwar.000webhostapp.com/UserAreaNameShow.php

                                                StringRequest stringRequest_AreaName=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/UserAreaNameShow.php", new Response.Listener<String>() {
                                                    @Override
                                                    public void onResponse(String response) {
                                                        try {
                                                            arrayList_Areaname.clear();
                                                            arrayList_Areaname.add("Select----Area---Name");
                                                            JSONArray jsonArray_area=new JSONArray(response);
                                                            for(int i=0; i<jsonArray_area.length();i++){
                                                          JSONObject jsonObject_area=jsonArray_area.getJSONObject(i);
                                                        arrayList_Areaname.add(jsonObject_area.getString("Aare_name").toString());
                                                            }
                                                            arrayAdapter_AreaName=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line,arrayList_Areaname);
                                                            spinner_areaName.setAdapter(arrayAdapter_AreaName);
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }

                                                    }
                                                }, new Response.ErrorListener() {
                                                    @Override
                                                    public void onErrorResponse(VolleyError error) {
                                               Toast.makeText( getApplicationContext(), "Area Name is Not Found..", Toast.LENGTH_SHORT).show();
                                                    }
                                                }){
                                                    @Override
                                                    protected Map<String, String> getParams()  {
                                                        HashMap hashMap_area=new HashMap();
                                                        hashMap_area.put("region_Name",getRegionName);
                                                        hashMap_area.put("city_Name",getCityName);
                                                        return hashMap_area;
                                                    }
                                                };
                                                RequestQueue requestAreaName=Volley.newRequestQueue(getApplicationContext());
                                                requestAreaName.add(stringRequest_AreaName);
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
                                    Toast.makeText(User_Address.this, "City Name Not Funde.."+error.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }){
                                @Override
                                protected Map<String, String> getParams() {
                                    HashMap hashMap=new HashMap();
                                   hashMap.put("region_Name",getRegionName);
                                    return hashMap;
                                }
                            };
                            RequestQueue requestCity=Volley.newRequestQueue(getApplicationContext());
                            requestCity.add(stringCityName);


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
   Toast.makeText(User_Address.this, "Region Name Not Funde.."+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

     //   CityNameSet();
    }


    public void Use_Adress_Data(View view) {
        Bundle bundle=getIntent().getExtras();
       // String order_pid= bundle.getString("pid_all");

     /*   String mm []=order_pid.split(",");
         //https://my-sotfwar.000webhostapp.com/product_pidAdd.php
        for (String product_id : mm) {
            StringRequest stringRequest_pdcord=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/product_pidAdd.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "product Name is Not Found..", Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams()  {
                    HashMap hashMap=new HashMap();
                     hashMap.put("pid",product_id);
                    hashMap.put("User_gmail","tarek@gmail.com");
                    return hashMap;
                }
            };
            RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest_pdcord);

        }
*/
        if (editText_UserName.getText().toString().isEmpty()) {
            editText_UserName.setError("please Your User Name");
            editText_UserName.requestFocus();
        } else if (editText_user_email.getText().toString().isEmpty()) {
            editText_user_email.setError("please Your Email ");
            editText_user_email.requestFocus();

        } else if (!Patterns.EMAIL_ADDRESS.matcher(editText_user_email.getText().toString()).matches()) {
            editText_user_email.setError("Your Email Address Invlide ");
            editText_user_email.requestFocus();

        } else if (spinner_regionName.getSelectedItem().toString().contains("Select---Region--Name")) {
   Toast.makeText(this, "Please Select Your Region Name..", Toast.LENGTH_SHORT).show();
            spinner_regionName.requestFocus();
        }else if (spinner_cityName.getSelectedItem().toString().contains("Select---City---Name")) {
            Toast.makeText(this, "Please Select Your City Name..", Toast.LENGTH_SHORT).show();
            spinner_cityName.requestFocus();
        }
        else if (spinner_areaName.getSelectedItem().toString().contains("Select----Area---Name")) {
            Toast.makeText(this, "Please Select Your City Name..", Toast.LENGTH_SHORT).show();
            spinner_areaName.requestFocus();
        } else if (editText_flat_no.getText().toString().isEmpty()) {
            editText_flat_no.setError("please Your Flat no,Building Name ");
            editText_flat_no.requestFocus();
        }  else if (editText_phon.getText().toString().isEmpty()) {
            editText_phon.setError("please Your phonNumber ");
            editText_phon.requestFocus();
        }   else if (spinner_Shipping.getSelectedItem().toString().contains("Select----Shipping---Area")) {
            Toast.makeText(this, "Please Select Your Shipping Status..", Toast.LENGTH_SHORT).show();
            spinner_Shipping.requestFocus();
        }  else {
           int selected_RadioButtonId =radioGroup.getCheckedRadioButtonId();
            if (selected_RadioButtonId != -1) {
                Select_radioButton=findViewById(selected_RadioButtonId);
               select_orderStatus = Select_radioButton.getText().toString();
            }else{
           Toast.makeText(this, "Please Select Your Delivery Status", Toast.LENGTH_LONG).show();
            }

           //String order_data_key= bundle.getString("data_all_key");
            //Total_pric= bundle.getInt("pd_totalPrice");

            String user_name=editText_UserName.getText().toString().trim();
            String user_email=editText_user_email.getText().toString().trim();
            String user_flat_no=editText_flat_no.getText().toString().trim();
            String user_city=spinner_cityName.getSelectedItem().toString();
            String user_area=spinner_areaName.getSelectedItem().toString();
            String user_RegionName=spinner_regionName.getSelectedItem().toString();
           // String shippping_order=spinner_Shipping.getSelectedItem().toString();
            String user_phon=editText_phon.getText().toString().trim();


             if(spinner_Shipping.getSelectedItem().toString().contains("Dhaka")){
                 Shipping_Rate=200;
                 Toast.makeText(this, "Shipping :"+Shipping_Rate, Toast.LENGTH_SHORT).show();
             }else if(spinner_Shipping.getSelectedItem().toString().contains("Outside")){
                 Shipping_Rate=500;
                 Toast.makeText(this, "Shipping :"+Shipping_Rate, Toast.LENGTH_SHORT).show();
             }

           StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/user_Order_Add.php", new Response.Listener<String>() {
               @Override
               public void onResponse(String response) {
               Toast.makeText(User_Address.this, response , Toast.LENGTH_SHORT).show();
               editText_UserName.setText("");
               editText_user_email.setText("");
               editText_flat_no.setText("");
               spinner_cityName.setSelection(0);
               spinner_areaName.setSelection(0);
               spinner_regionName.setSelection(0);
              spinner_Shipping.setSelection(0);
              editText_phon.setText("");
              radioGroup.clearCheck();

           Intent intent=new Intent(getApplicationContext(),User_order_Show.class);
             startActivity(intent);
                  // Toast.makeText(User_Address.this, "", Toast.LENGTH_SHORT).show();
               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
             Toast.makeText(User_Address.this, "User Address Save Faild:"+error.getMessage(), Toast.LENGTH_LONG).show();
               }
           }){

            @Override
               protected Map<String, String> getParams()  {
                Map<String,String>hasmp=new HashMap<String,String>();
                hasmp.put("Delivery_status",select_orderStatus);
                 hasmp.put("region_name",user_RegionName);
                 hasmp.put("us_name",user_name);
                    hasmp.put("us_email",user_email);
                    hasmp.put("us_area",user_area);
                    hasmp.put("flat_no",user_flat_no);
                    hasmp.put("city",user_city);
                    hasmp.put("phon",user_phon);
                    hasmp.put("order_date",current_date);
                  hasmp.put("shipping_rate", String.valueOf(Shipping_Rate));
                   return  hasmp;
                }
           };
           RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
           requestQueue.add(stringRequest);
        }




    }
}