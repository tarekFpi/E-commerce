package com.example.my_json_review;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class json_Recyclery_show extends AppCompatActivity {
  private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json__recyclery_show);

       recyclerView=(RecyclerView)findViewById(R.id.recylcer);
    }
}