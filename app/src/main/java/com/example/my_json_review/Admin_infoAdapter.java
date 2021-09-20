package com.example.my_json_review;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Admin_infoAdapter extends RecyclerView.Adapter<Admin_infoAdapter.MyviewHolder> {
    List<Admin_infoModel>admin_infoModels;
    Context context;
    private static int listposition=-1;

    public Admin_infoAdapter(List<Admin_infoModel> admin_infoModels, Context context) {
        this.admin_infoModels = admin_infoModels;
        this.context = context;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=  layoutInflater.inflate(R.layout.admin_layout,parent,false);

        return new MyviewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myviewHolder, int position) {
      Admin_infoModel Item=admin_infoModels.get(position);
        myviewHolder.textView_serial_id.setText("Serial Id:"+Item.getSerial_id());
        myviewHolder.textView_gamil.setText("Gmail Id:"+Item.getGmail());
        myviewHolder.textView_password.setText("Password Id:"+Item.getPassword());
        myviewHolder.textView_admin_userName.setText("User Name:"+Item.getUser_name());

        setAnimiton(myviewHolder.itemView,position);
    }

    private void setAnimiton(View viewAnimition,int position){
        if(position>listposition){
            ScaleAnimation scaleAnimation=new ScaleAnimation(0.0f, 0.1f, 0.0f, 0.1f, Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnimation.setDuration(2000);
            viewAnimition.startAnimation(scaleAnimation);
            listposition = position;
        }
    }

    @Override
    public int getItemCount() {
        return admin_infoModels.size();
    }

    class MyviewHolder extends RecyclerView.ViewHolder{
      private TextView textView_serial_id,textView_admin_userName,textView_password,textView_gamil;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            textView_serial_id=(TextView)itemView.findViewById(R.id.admin_serial_id);
            textView_admin_userName=(TextView)itemView.findViewById(R.id.admin_name);
            textView_password=(TextView)itemView.findViewById(R.id.admin_password);
            textView_gamil=(TextView)itemView.findViewById(R.id.admin_gmail);
        }
    }
}
