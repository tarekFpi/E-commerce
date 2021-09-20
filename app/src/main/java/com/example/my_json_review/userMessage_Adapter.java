package com.example.my_json_review;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class userMessage_Adapter extends RecyclerView.Adapter<userMessage_Adapter.MyviewHolder> {
    private Context context;
    private List<Order_Except_Model>modelList;
     private  OnItemListener onItemClick;
    public userMessage_Adapter(Context context, List<Order_Except_Model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
       View view= layoutInflater.inflate(R.layout.admin_user_order_except,parent,false);

        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myviewHolder, int position) {
        Order_Except_Model item=modelList.get(position);

         myviewHolder.textView_pid.setText("product id:"+item.getPd_id());
        myviewHolder.textView_pd_price.setText("product Price:"+item.getPd_price());
        myviewHolder.textView_pdName.setText("product Name:"+item.getPd_name());

        myviewHolder.textView_quantity.setText("Sell Quantity:"+item.getSell_quanitiy());
        myviewHolder.textView_pd_size.setText("Size:"+item.getPd_size());
        myviewHolder.textView_categoryName.setText("Category Name:"+item.getCategory_name());

        myviewHolder.textView_pd_discount.setText("Discount:"+item.getDiscount());
        myviewHolder.text_UerName.setText("User Name:"+item.getUser_name());
        myviewHolder.textView_email.setText("Gmail:"+item.getUser_email());
        myviewHolder.textView_userRegoinName.setText("Region:"+item.getRegion_name());
        myviewHolder.textView_phon.setText("Phone:"+item.getUser_phon());
        myviewHolder.textView_orderDate.setText("Order Date:"+item.getOrder_date());
        myviewHolder.textView_execptDate.setText("Except Date:"+item.getExcept_date());
        myviewHolder.textView_Shipping.setText("Shipping:"+item.getShipping_rate());
        myviewHolder.textView_messagess.setText("Messages:"+item.getMessages());
        myviewHolder.textView_orderStatus.setText("Order Satuts:"+item.getOrder_status());
    }

    @Override
    public int getItemCount() {
    return modelList.size();
    }

    public void filtetListChane(List<Order_Except_Model> filterList) {
        modelList=filterList;
        notifyDataSetChanged();
    }

    class MyviewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener,View.OnClickListener, MenuItem.OnMenuItemClickListener {
        private TextView textView_Shipping,text_UerName,
                textView_email,textView_phon,
                textView_orderDate,textView_TotalPrice,textView_execptDate;
        private TextView  textView_orderStatus,textView_userRegoinName,
                textView_pd_price,textView_pd_discount,textView_pid,textView_pdName,
                textView_pd_size,textView_quantity,textView_categoryName,textView_messagess;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            text_UerName=itemView.findViewById(R.id.Admin_message_pd_userName);
            textView_email=itemView.findViewById(R.id.Admin_messages_pd_email);
            textView_phon=itemView.findViewById(R.id.Admin_message_pd_userName);
            textView_userRegoinName=itemView.findViewById(R.id.Admin_messagess_regoinNaem);
            textView_orderDate=itemView.findViewById(R.id.Admin_message_orderDate);
            textView_execptDate=itemView.findViewById(R.id.Admin_message_except_Date);
            textView_pdName=itemView.findViewById(R.id.Admin_message_pd_Name);

            textView_pd_size=itemView.findViewById(R.id.Admin_messages_size);
            textView_orderStatus=itemView.findViewById(R.id.Admin_message_pd_orderStatus);
            textView_pd_price=itemView.findViewById(R.id.Admin_messages_price);
            textView_pd_discount=itemView.findViewById(R.id.Admin_messages_pd_discount);
            textView_pid=itemView.findViewById(R.id.Admin_show_messages_id);
            textView_Shipping=itemView.findViewById(R.id.Admin_messages_rate_free);

            textView_quantity=itemView.findViewById(R.id.Admin_messagess_quantity);
            textView_categoryName=itemView.findViewById(R.id.Admin_message_categoryName);//textView_messagess
            textView_messagess=itemView.findViewById(R.id.Admin_final_messages);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
          int position=getAdapterPosition();

          switch (item.getItemId()){
              case 1:
                  onItemClick.OnDelete(position);
                  break;
          }
            return true;
        }

        @Override
        public void onClick(View v) {
          int position=getAdapterPosition();
          onItemClick.OnDelete(position);
          onItemClick.OnItmclick(position);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Do Your Any Action..");
            MenuItem menuItem_delete=menu.add(Menu.NONE,1,1,"Delete");
            menuItem_delete.setOnMenuItemClickListener(this);
        }
    }
    public  interface OnItemListener{
        void OnItmclick(int position);
        void OnDelete(int position);
    }

    void setOnItemClickListener(OnItemListener onItemClick){
      this.onItemClick=onItemClick;
    }
}
