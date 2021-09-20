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

import com.squareup.picasso.Picasso;

import java.util.List;

public class purchase_orderShow_Adapter extends RecyclerView.Adapter<purchase_orderShow_Adapter.MyViewHolder> {
    private  static   onItemClickLisiner clickLisiner;
  private   List<purchase_orderShow_Model>purchase_order_list;
    private Context context;
    private static int listposition=-1;

    public purchase_orderShow_Adapter(List<purchase_orderShow_Model> purchase_order_list, Context context) {
        this.purchase_order_list = purchase_order_list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
      View view=   layoutInflater.inflate(R.layout.purchase_order_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        purchase_orderShow_Model item=purchase_order_list.get(position);

        myViewHolder.textView_serial_no.setText("Serial No:"+item.getSerial_id());
        myViewHolder.textView_bill_No.setText("Bill No:"+item.getBuill_no());
        myViewHolder.textView_pdName.setText("Name:"+item.getProduct_name());
        myViewHolder.textView_cateogryName.setText("Category Name:"+item.getCategoryName());
        myViewHolder.textView_purchase_quant.setText("Purchase Quanitiy:"+item.getPurchase_quantity());
        myViewHolder.textView_purchase_discount.setText("Purchase Discount:"+item.getPuchase_discount());
        myViewHolder.textView_sell_price.setText("sell price:"+item.getSell_price());
        myViewHolder.textView_sell_discount.setText("sell Discount:"+item.getSell_discount());
        myViewHolder.textView_pd_deatis.setText("Deatils:"+item.getProduct_deatils());
        myViewHolder.textView_supplayer_name.setText("Supplayer Name:"+item.getSupplayer_name());
        myViewHolder.textView_pdSize.setText("Size:"+item.getProduct_size());
        myViewHolder.textView_purchse_price.setText("purchse price:"+item.getPurchase_price());

        Picasso.get().load(item.getImage_url()).into(myViewHolder.imageView);
        setAnimiton(myViewHolder.imageView,position);
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
        return purchase_order_list.size();
    }

    public void filter_ListChange(List<purchase_orderShow_Model> filter_list) {
        purchase_order_list=filter_list;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
       private TextView textView_serial_no,textView_bill_No,textView_pdName,textView_cateogryName,
               textView_purchase_quant,
      textView_purchase_discount,textView_sell_price,textView_sell_discount,textView_pdSize,
               textView_pd_deatis,textView_supplayer_name,textView_purchse_price;

       private ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//
            textView_serial_no=itemView.findViewById(R.id.purch_pd_serial);
            textView_bill_No=itemView.findViewById(R.id.purch_pd_bill);
            textView_pdName=itemView.findViewById(R.id.purch_pd_Name);
            textView_cateogryName=itemView.findViewById(R.id.purch_categoryName);
            textView_pdSize=itemView.findViewById(R.id.purch_size);
            textView_purchase_quant=itemView.findViewById(R.id.purch_pd_quantity);
            textView_purchase_discount=itemView.findViewById(R.id.purch_purchaseDiscount);
            textView_sell_price=itemView.findViewById(R.id.purch_Sellprice);
            textView_sell_discount=itemView.findViewById(R.id.purch_sell_discount);
            textView_supplayer_name=itemView.findViewById(R.id.purch_supplayerName);
            textView_pd_deatis=itemView.findViewById(R.id.purch_pd_deatils);
            imageView=itemView.findViewById(R.id.purch_pd_pdImage);
            textView_purchse_price=itemView.findViewById(R.id.purch_pd_price);

            itemView.setOnCreateContextMenuListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int position=getAdapterPosition();

            switch (item.getItemId()){
                case 1:
                    clickLisiner.OnUpdate(position);
                    break;

                case 2:
                    clickLisiner.OnDelete(position);
                    break;
            }
            return true;
        }

        @Override
        public void onClick(View v) {
            int position =getAdapterPosition();
            clickLisiner.onClick(position);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Do your Any Action");
            MenuItem menuIte_Update=menu.add(Menu.NONE,1,1,"Update");
            MenuItem menuIte_Delete=menu.add(Menu.NONE,2,2,"Delete");

            menuIte_Update.setOnMenuItemClickListener(this);
            menuIte_Delete.setOnMenuItemClickListener(this);
        }
    }

    public interface onItemClickLisiner{
        void onClick(int position);
        void OnUpdate(int position);
        void OnDelete(int position);

    }

    void setOnItemClick(onItemClickLisiner clickLisiner){
        this. clickLisiner=clickLisiner;
    }
}

