package com.example.my_json_review;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserSignUp_Adapter extends RecyclerView.Adapter<UserSignUp_Adapter.MyviewHolder> {
    private Context context;
    private List<AllUser_singUp_Model>modelList;
    private  OnItemListener clickItem;
    private static int listposition=-1;

    public UserSignUp_Adapter(Context context, List<AllUser_singUp_Model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=  layoutInflater.inflate(R.layout.sign_up_user,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myviewHolder, int position) {
        AllUser_singUp_Model getItem=modelList.get(position);

        myviewHolder.textView_serial_id.setText("Serial Id:"+getItem.getUser_serial());
        myviewHolder.textView_gmail.setText("Gmail:"+getItem.getUser_gmail());
        myviewHolder.textView_userName.setText("User Name:"+getItem.getUser_name());
        myviewHolder.textView_password.setText("password:"+getItem.getUser_password());

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
        return modelList.size();
    }

  class MyviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{
     private TextView textView_serial_id,textView_userName,textView_gmail,textView_password;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            textView_serial_id=(TextView) itemView.findViewById(R.id.sing_user_serial_id);
            textView_userName=(TextView)itemView.findViewById(R.id.sing_user_name);
            textView_gmail=(TextView)itemView.findViewById(R.id.sign_user_gmail);
            textView_password=(TextView)itemView.findViewById(R.id.user_password);

            itemView.setOnCreateContextMenuListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int position=getAdapterPosition();
            switch(item.getItemId()){
                case 1:
                    clickItem.Order_Delete(position);
                    break;
            }

            return true;
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            clickItem.Onclick(position);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Do Your Aany Action..");
            MenuItem menuItem_update= menu.add(Menu.NONE,1,1,"Delete");
            MenuItem menuItem_message=menu.add(Menu.NONE,2,2,"Send Messages");
            menuItem_update.setOnMenuItemClickListener(this);
            menuItem_message.setOnMenuItemClickListener(this);
        }
    }

    public  interface OnItemListener{
        void Onclick(int position);
        void Order_Delete(int position);

    }

    void  setOnItemClickListener(OnItemListener clickItem){
        this.clickItem=clickItem;
    }
}

