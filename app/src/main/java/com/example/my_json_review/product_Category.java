package com.example.my_json_review;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class product_Category extends AppCompatActivity     {

    private TextView textView_pdName,textView_pdCord,textView_pdDrecp,textView_pdstock,textView_pd_price,textView_discount;
    private ImageSlider imageSlider;
    String  pd_image,product_Name,product_Cord,product_Drecpt,size_pd,categoryName;
    private int prodcut_price,pd_discount;
    private int pd_Stock,shipping_Rate;
    private ElegantNumberButton numberButton;
    private TextView textView_select_size,textView_Exit,textView_sizeShow;
    String User_name,User_email,User_area,User_flat_no,User_city,User_phon,order_date,getAll_Order,order_status,regoin_name;
  private Spinner spinner_size;
  private CardView cardView;

    String pd_size,current_date;
    private SharedPreferences sharedPreferences;
  private ArrayList<String>arrayList_pdSize=new ArrayList<>();
  private ArrayAdapter<String>arrayAdapter_pdSize;
  private product_Category_Add_sqlit category_AddSqlit;
  private pd_Category_showModel category_Model;
    String getSize="";
    String getGmail="";
    User_order_showModel addItem = null ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__category);

        textView_pdName=(TextView)findViewById(R.id.category_pdName);
        textView_pdCord=(TextView)findViewById(R.id.category_pdCord);
        textView_discount=(TextView)findViewById(R.id.category_discount);
        textView_pd_price=(TextView)findViewById(R.id.category_Price);
        textView_pdstock=(TextView)findViewById(R.id.category_pdstock);
        textView_pdDrecp=(TextView)findViewById(R.id.category_drecpt);
        //textView_sizeShow=(TextView)findViewById(R.id.category_pd_size);
        spinner_size=(Spinner)findViewById(R.id.edit_pd_size);
        textView_discount=(TextView)findViewById(R.id.category_discount);


        cardView=(CardView)findViewById(R.id.car_size);
        imageSlider=(ImageSlider)findViewById(R.id.product_rateImage);
        numberButton=(ElegantNumberButton) findViewById(R.id.category_Quantity);

        category_AddSqlit=new product_Category_Add_sqlit(product_Category.this);
        category_Model=new pd_Category_showModel();
        getIntent_Data();

    }

    void getIntent_Data(){

        Bundle bundle=getIntent().getExtras();
     if(bundle.getString("pd_size").contains("no")){
         // textView_sizeShow.setVisibility(View.INVISIBLE);
       cardView.setVisibility(View.INVISIBLE);
     // pd_size= spinner_size.getSelectedItem().toString();
        }

        product_Name=bundle.getString("pName");
        product_Cord=bundle.getString("pd_cord");
       int prodcut_price =bundle.getInt("pd_price");
        product_Drecpt=bundle.getString("pd_Desr");
        pd_Stock=bundle.getInt("pd_stock");
        pd_image=bundle.getString("pd_image");
        pd_discount=bundle.getInt("pd_discount");


        textView_pdCord.setText("product Cord:"+product_Cord);
        textView_pdName.setText(product_Name);
        textView_pdstock.setText("Stock:"+pd_Stock);
        textView_pdDrecp.setText(""+product_Drecpt);
        textView_pd_price.setText("Price:"+prodcut_price);
        textView_discount.setText("Discount:"+pd_discount);


        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(pd_image, product_Name, ScaleTypes.FIT));
        slideModels.add(new SlideModel(pd_image, product_Name, ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

  StringRequest string_request_size=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/product_sizeApi.php", new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
      String size_pd[];
          try {
              JSONArray jsonArray=new JSONArray(response);
              arrayList_pdSize.add("Select product Size..");
              for(int i=0; i<jsonArray.length();i++){
                  JSONObject jsonObject=jsonArray.getJSONObject(i);
                  size_pd=jsonObject.getString("product_size").split(",");
                  for (String size : size_pd) {
                      arrayList_pdSize.add(size);
                  }

              }
   arrayAdapter_pdSize=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,arrayList_pdSize);
              spinner_size.setAdapter(arrayAdapter_pdSize);
          } catch (JSONException e) {
              e.printStackTrace();
          }

      }
  }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
          Toast.makeText(product_Category.this, "Porduct Size Not Found..", Toast.LENGTH_SHORT).show();
      }
  }){
      @Override
      protected Map<String, String> getParams()  {
          HashMap hashMap=new HashMap();
          hashMap.put("pd_id",product_Cord);
          return hashMap;
      }
  };
  RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
  requestQueue.add(string_request_size);

    }

    public void Add_cardData(View view) {

        sharedPreferences=getApplicationContext().getSharedPreferences("com.example.my_json_review", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        String us="tarek@gmail.com";
        editor.putString("user_gmail",us).commit();
      String user_gmail=  sharedPreferences.getString("user_gmail","Your Gmail Not Found");
        Bundle bundle=getIntent().getExtras();


        int num_Quantity = Integer.parseInt(numberButton.getNumber().toString());

        product_Name=bundle.getString("pName");
        product_Cord=bundle.getString("pd_cord");
          prodcut_price =bundle.getInt("pd_price");
        pd_Stock=bundle.getInt("pd_stock");
        pd_discount=bundle.getInt("pd_discount");
        product_Drecpt=bundle.getString("pd_Desr");
        pd_image=bundle.getString("pd_image");
        categoryName=bundle.getString("category_name");
        textView_discount.setText("Discount:"+pd_discount);

        final int total_quan,Sell_total_price;
        if (pd_Stock <num_Quantity) {
            Toast.makeText(this, "Your Order Over..", Toast.LENGTH_SHORT).show();
        }else{

            if(bundle.getString("pd_size").contains("no")){
                getSize="no";
              }else {
                getSize = bundle.getString("pd_size");
            }
            //total_quan=pd_Stock-num_Quantity;
           textView_pdstock.setText("Stock :"+pd_Stock);
            category_Model.setPid(product_Cord);
            category_Model.setPdName(product_Name);
            category_Model.setSelling_price(prodcut_price);
            category_Model.setSell_quanitiy(num_Quantity);
            category_Model.setPd_discount(pd_discount);
            category_Model.setPdSize(getSize);
            category_Model.setPdImage(pd_image);
            category_Model.setCategoryName(categoryName);
            category_Model.setUser_gmail(user_gmail);

   if(category_AddSqlit.MyAddData(category_Model)!=-1){
       Toast.makeText(product_Category.this, "Order Card SuccessFull..", Toast.LENGTH_SHORT).show();
    }else{
    Toast.makeText(product_Category.this, "Order Card Failde..", Toast.LENGTH_SHORT).show();
        }


        }

    }
    public void go_toShowCategory(View view) {

        Date date=new Date();
        SimpleDateFormat sm=new SimpleDateFormat("YYYY-MM-dd");
        current_date = sm.format(date.getTime());

        sharedPreferences=getApplicationContext().getSharedPreferences("com.example.my_json_review", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        String user_gmail=  sharedPreferences.getString("user_gmail","Your Gmail Not Found");

        ///user_gmail
        StringRequest stringRequest_match=new StringRequest(Request.Method.POST, "https://my-sotfwar.000webhostapp.com/userAddress_check.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);

               for (int i = 0; i <jsonArray.length() ; i++) {
                  JSONObject jsonObject=jsonArray.getJSONObject(i);
                   getGmail= jsonObject.getString("email");
                   if(getGmail.contains(user_gmail)){
                       Intent intent=new Intent(getApplicationContext(),User_order_Show.class);
                       startActivity(intent);
                   }else{
                       Intent intent=new Intent(getApplicationContext(),User_Address.class);
                       startActivity(intent);
                   }
             }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(product_Category.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                HashMap hashMa=new HashMap();
                hashMa.put("user_gmail",user_gmail);
                return hashMa;
            }
        };
   RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
     requestQueue.add(stringRequest_match);

    }
}