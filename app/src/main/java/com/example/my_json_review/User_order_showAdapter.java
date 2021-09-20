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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.squareup.picasso.Picasso;

import java.util.List;

public class User_order_showAdapter extends RecyclerView.Adapter<User_order_showAdapter.MyviewHolder> {
    private static onItem_ClickLisiner  clickLisiner;
  private Context context;
  private List<User_order_showModel>order_showModelList;
    private static int listposition=-1;

    public User_order_showAdapter(Context context, List<User_order_showModel> order_showModelList) {
        this.context = context;
        this.order_showModelList = order_showModelList;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
      View view=  layoutInflater.inflate(R.layout.user_order_show,parent,false);

        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myViewHolder, int position) {
        User_order_showModel item=order_showModelList.get(position);

    /*    String m[]=item.getGetDataAll().split("/");
        StringBuilder sb=new StringBuilder();
        for (int i = 0,j=0; i <m.length ; i++) {j++;
            sb.append(j+"\t.Order No:"+m[i]+"\n.");

             textView_pd_price=(TextView)itemView.findViewById(R.id.final_show_price);
            textView_pd_discount=(TextView)itemView.findViewById(R.id.final_show_pd_discount);
            textView_pid=(TextView)itemView.findViewById(R.id.final_show_id);
            textView_pdName=(TextView)itemView.findViewById(R.id.final_show_pd_Name);
            TextView_pd_size=(TextView)itemView.findViewById(R.id.final_show_size);
        }*/

    myViewHolder.textView_pid.setText("id:"+item.getPd_id());
        myViewHolder.textView_pdName.setText("Name:"+item.getPd_name());
        myViewHolder.textView_pd_discount.setText("Discount:"+item.getDiscount());
       myViewHolder.textView_quantity.setText("Quanitiy:"+item.getSell_quanitiy());
         myViewHolder.TextView_pd_size.setText("Size:"+item.getPd_size());
        myViewHolder.textView_pd_price.setText("Price:"+item.getPd_price());

        //myViewHolder.textView_pd_all.setText(sb.toString());
        myViewHolder.text_UerName.setText("User Name:"+item.getUser_name());
        myViewHolder.textView_email.setText("Email Address:"+item.getUser_email());
        myViewHolder.textView_currentDate.setText("Order Date:"+item.getCurrent_date());
        myViewHolder.textView_city.setText("City:"+item.getUser_city());
        myViewHolder.textView_flat.setText("Flat No,Building Name:"+item.getUser_flat_no());
        myViewHolder.textView_location.setText("Area:"+item.getUser_location());
        myViewHolder.textView_phon.setText("phon Number:"+ item.getUser_phon());
        myViewHolder.textView_TotalPrice.setText("Total:"+ item.getProd_TotalPrice());
        myViewHolder.textView_orderStatus.setText("Order Status:"+ item.getOrder_status());

        myViewHolder.textView_Shipping.setText("Shipping:"+ item.getShipping_rate());
        myViewHolder.textView_userRegoinName.setText("Regoin:"+ item.getRegion_name());
       Picasso.get().load(item.getImage_url()).into(myViewHolder.imageView);

        setAnimiton(myViewHolder.itemView,position);

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
        return order_showModelList.size();
    }

    class  MyviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
  private TextView textView_Shipping,text_UerName,
   textView_email,textView_location,textView_flat,textView_city,textView_phon,textView_currentDate,textView_TotalPrice;
      private TextView textView_pd_all, textView_orderStatus,textView_userRegoinName,
              textView_pd_price,textView_pd_discount,textView_pid,textView_pdName,
              TextView_pd_size,textView_quantity;
      private ImageView imageView;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);


            textView_orderStatus=(TextView)itemView.findViewById(R.id.final_show_pd_orderStatus);
           // textView_pd_all=(TextView)itemView.findViewById(R.id.final_show_pd_allOrder);
            text_UerName=(TextView)itemView.findViewById(R.id.final_show_pd_userName);
            textView_currentDate=(TextView)itemView.findViewById(R.id.final_show_pd_orderDate);
            textView_TotalPrice=(TextView)itemView.findViewById(R.id.final_show_pd_totalPrice);
            textView_email=(TextView)itemView.findViewById(R.id.final_show_pd_email);
            textView_location=(TextView)itemView.findViewById(R.id.final_show_pd_location);
            textView_flat=(TextView)itemView.findViewById(R.id.final_show_pd_flat);
            textView_city=(TextView)itemView.findViewById(R.id.final_show_pd_city);
            textView_phon=(TextView)itemView.findViewById(R.id.final_show_pd_phon);
            textView_Shipping=(TextView)itemView.findViewById(R.id.final_Flat_Shipping_rate_free);
            textView_userRegoinName=(TextView)itemView.findViewById(R.id.final_show_regoinNaem);

            textView_pd_price=(TextView)itemView.findViewById(R.id.final_show_price);
            textView_pd_discount=(TextView)itemView.findViewById(R.id.final_show_pd_discount);
            textView_pid=(TextView)itemView.findViewById(R.id.final_show_id);
            textView_pdName=(TextView)itemView.findViewById(R.id.final_show_pd_Name);
            TextView_pd_size=(TextView)itemView.findViewById(R.id.final_show_size);
            textView_quantity=(TextView)itemView.findViewById(R.id.final_show_quantity);
            imageView=(ImageView) itemView.findViewById(R.id.order_pdImage);

            itemView.setOnCreateContextMenuListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int position=getAdapterPosition();
            switch (item.getItemId()){
                case 1:
                    clickLisiner.Update(position);
                    break;

                case 2:
                    clickLisiner.Order_CategorySelect(position);
                    break;

                case 3:
                    clickLisiner.on_Delete(position);
                    break;
            }
            return true;
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            clickLisiner.onClick(position);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Do Your Action");
            MenuItem menuItem_update=menu.add(Menu.NONE,1,1,"Update");
            MenuItem menuIte_order_Category=menu.add(Menu.NONE,2,2,"Send Confirmation Order");
            MenuItem menuIte_order_delete=menu.add(Menu.NONE,3,3,"Delete");

            menuItem_update.setOnMenuItemClickListener(this);
            menuIte_order_Category.setOnMenuItemClickListener(this);
            menuIte_order_delete.setOnMenuItemClickListener(this);
        }
    }

    public interface onItem_ClickLisiner {
        void onClick(int position);
        void Update(int position);
        void Order_CategorySelect(int position);
        void on_Delete(int position);
    }

    void  setOnItemClickLisiner(onItem_ClickLisiner clickLisiner){
        this.clickLisiner=clickLisiner;
    }

}
