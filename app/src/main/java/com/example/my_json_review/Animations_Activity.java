package com.example.my_json_review;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Animations_Activity extends AppCompatActivity {
  Animation animation_top,animation_buttom;
  private TextView textView_title_name;
  private ImageView imageView;
  private int Splash_Screen=5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_animations_);

        animation_top= AnimationUtils.loadAnimation(this,R.anim.top_naim_layout);
        animation_buttom= AnimationUtils.loadAnimation(this,R.anim.buttom_animtion);
        textView_title_name=(TextView)findViewById(R.id.title_name);
         imageView=(ImageView)findViewById(R.id.animation_image);

         imageView.setAnimation(animation_top);
        textView_title_name.setAnimation(animation_buttom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(getApplicationContext(),Login_page.class);
                startActivity(intent);
                finish();
            }
        },Splash_Screen);
    }
}