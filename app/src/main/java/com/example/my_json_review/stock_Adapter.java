package com.example.my_json_review;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

public class stock_Adapter extends RecyclerView.Adapter<stock_Adapter.MyViwHolder> {
    private Context context;
    private List<stockModel>stockModelList;

    public stock_Adapter(Context context, List<stockModel> stockModelList) {
        this.context = context;
        this.stockModelList = stockModelList;
    }

    @NonNull
    @Override
    public MyViwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
       View view= layoutInflater.inflate(R.layout.stock_layout,parent,false);
        return new MyViwHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViwHolder myViwHolder, int position) {
     stockModel item= stockModelList.get(position);
     myViwHolder.textView_bill.setText("Bill No:"+item.getBill_id());
     myViwHolder.textView_pdName.setText("Name:"+item.getProduct_name());
     myViwHolder.textView_pdSize.setText("Size:"+item.getProduct_size());
     myViwHolder.textView_categoryName.setText("Category Name"+item.getProduct_cateogryName());
    myViwHolder.textView_sellQuatity.setText("Sell Quanitiy"+item.getSell_quantity());
    myViwHolder.textView_purchaseQuanitiy.setText("Purchase Quanitiy:"+item.getPurchase_quantity());
    myViwHolder.textView_sellDiscount.setText("Sell Discount:"+item.getSell_discount());
    myViwHolder.textView_deatils.setText("Deatils:"+item.getDetails());
   myViwHolder.textView_sellprice.setText("Sell price:"+item.getSell_price());
        myViwHolder.textView_stock.setText("Sell price:"+item.getStock());
        Picasso.get().load(item.getImage_url()).into(myViwHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return stockModelList.size();
    }

    public void filterListChinge(List<stockModel> filterList) {
        stockModelList=filterList;
        notifyDataSetChanged();
    }

    class MyViwHolder extends RecyclerView.ViewHolder{
   private TextView textView_bill,textView_pdName,textView_categoryName,textView_pdSize,textView_sellprice,
           textView_sellDiscount,textView_purchaseQuanitiy,textView_sellQuatity,textView_stock,textView_deatils;
   private ImageView imageView;

        public MyViwHolder(@NonNull View itemView) {
            super(itemView);

            textView_bill=itemView.findViewById(R.id.stock_pd_bill);
            textView_pdName=itemView.findViewById(R.id.stock_pd_Name);
            textView_categoryName=itemView.findViewById(R.id.stock_pd_categoryName);
            textView_pdSize=itemView.findViewById(R.id.stock_pd_size);
            textView_sellprice=itemView.findViewById(R.id.stock_pd_Sellprice);
            textView_sellDiscount=itemView.findViewById(R.id.stock_pd_sell_discount);
            textView_purchaseQuanitiy=itemView.findViewById(R.id.stock_pd_quantity);
            textView_sellQuatity=itemView.findViewById(R.id.stock_pd_sellQuantity);
            textView_stock=itemView.findViewById(R.id.stock_pd_stock);
            textView_deatils=itemView.findViewById(R.id.stock_pd_deatils);
            imageView=itemView.findViewById(R.id.stock_pd_pdImage);
        }
    }
}
