package com.example.my_json_review;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Admin_Homepage extends AppCompatActivity {
  private DrawerLayout drawerLayout;
  private NavigationView navigationView;
  private ActionBarDrawerToggle toggle;
 private LayoutInflater layoutInflater;

 private TextView textView_status;
 private View view;
private Toolbar toolbar;
private CircleImageView circleImageView;
private ImageSlider imageSlider;
private CardView cardView_product_Name_Add,cardView_category_Add,cardView_RegionAdd,
    cardView_pruchase_order,cardView_user_orderShow,cardView_uerOderExcept,
        cardView_stock,cardView_purchase_oderShow,cardView_singup,cardView_admin;
//https://my-sotfwar.000webhostapp.com/AllSign_up.php carview_user
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__homepage);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout_id);
        navigationView=(NavigationView)findViewById(R.id.navigation_id);
        toolbar=(Toolbar)findViewById(R.id.toolbar_id);
        imageSlider=(ImageSlider) findViewById(R.id.e_comm_home_imageSlider);

        cardView_product_Name_Add=(CardView)findViewById(R.id.card_pd_nameAdd);
        cardView_category_Add=(CardView)findViewById(R.id.card_catgory_nameAdd);
        cardView_RegionAdd=(CardView)findViewById(R.id.card_userArea);
        cardView_pruchase_order=(CardView)findViewById(R.id.Card_purchase_prodcut);
        cardView_stock=(CardView)findViewById(R.id.card_stock);
        cardView_user_orderShow=(CardView)findViewById(R.id.card_user_order);
        cardView_uerOderExcept=(CardView)findViewById(R.id.cardview_exceptOrder);
        cardView_purchase_oderShow=(CardView)findViewById(R.id.cardview_purchase_ordershow);
        cardView_singup=(CardView)findViewById(R.id.carview_user);
        cardView_admin=(CardView)findViewById(R.id.admin_information);
        textView_status=(TextView)findViewById(R.id.status_id);

        ///admin_information
         setSupportActionBar(toolbar);
  toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
  drawerLayout.addDrawerListener(toggle);
  toggle.syncState();

  navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
          switch (item.getItemId()){
              case R.id.home_id:
           Toast.makeText(Admin_Homepage.this, "Home", Toast.LENGTH_SHORT).show();
                  drawerLayout.closeDrawer(GravityCompat.START);
           break;
              case R.id.my_order:
          Toast.makeText(Admin_Homepage.this, "MyOrder", Toast.LENGTH_SHORT).show();
       drawerLayout.closeDrawer(GravityCompat.START);
          break;
              case  R.id.setting_id:
                  Toast.makeText(Admin_Homepage.this, "Setting", Toast.LENGTH_SHORT).show();
                  break;
              case R.id.exit_id:
                  drawerLayout.closeDrawer(GravityCompat.START);
                  break;

          }
          return true;
      }
  });

    view= navigationView.inflateHeaderView(R.layout.header_layout);
    circleImageView=view.findViewById(R.id.user_image);

        List<SlideModel>sliderList=new ArrayList<>();
         sliderList.add(new SlideModel(R.drawable.dress2s,"Image", ScaleTypes.FIT));
        sliderList.add(new SlideModel(R.drawable.keybord,"keybord", ScaleTypes.FIT));
        sliderList.add(new SlideModel(R.drawable.hp,"Loptop", ScaleTypes.FIT));
        sliderList.add(new SlideModel(R.drawable.mouse1,"Mouse", ScaleTypes.FIT));
        imageSlider.setImageList(sliderList,ScaleTypes.FIT);

        cardView_product_Name_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),product_name_Add.class);
                startActivity(intent);
            }
        });

        cardView_category_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Category_nameAdd.class);
                startActivity(intent);
            }
        });


        cardView_pruchase_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),product_purchase.class);
                startActivity(intent);
            }
        });

        cardView_RegionAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),User_areaAdd.class);
                startActivity(intent);
            }
        });


        cardView_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Stock_product.class);
                startActivity(intent);
            }
        });

        cardView_uerOderExcept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Messages_page.class);
                startActivity(intent);
            }
        });


        cardView_user_orderShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),User_all_order_showAdmin.class);
                startActivity(intent);
            }
        });

        cardView_singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),AllUser_show.class);
                startActivity(intent);
            }
        });

        cardView_purchase_oderShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),purchsase_orderShow.class);
                startActivity(intent);
            }
        });

        cardView_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Admin_informationShow.class);
                startActivity(intent);
            }
        });

       getData();
    }

    void getData(){
      Bundle bundle=getIntent().getExtras();
      String mystatus=bundle.getString("status");
      textView_status.setText(""+mystatus);
    }
}