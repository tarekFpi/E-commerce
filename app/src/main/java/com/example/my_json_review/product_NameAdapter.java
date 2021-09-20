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

public class product_NameAdapter extends RecyclerView.Adapter<product_NameAdapter.MyviewHolder> {
    private Context context;
    private List<product_nameModel>modelList;
    private onItemClickLisiner clickLisiner;
    private static int listposition=-1;
    public product_NameAdapter(Context context, List<product_nameModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
       View view=  layoutInflater.inflate(R.layout.product_name_layout,parent,false);

        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myviewHolder, int position) {
        product_nameModel item= modelList.get(position);
        myviewHolder.textView_serial_id.setText("Serial id:"+item.getSerial_id());
        myviewHolder.textView_productName.setText("Name:"+item.getSerial_id());
        myviewHolder.textView_date.setText("Date:"+item.getAdd_date());

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

    public void filterListChange(List<product_nameModel> filterList) {
        modelList=filterList;
        notifyDataSetChanged();
    }

    public  class MyviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
      private TextView textView_serial_id,textView_productName,textView_date;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            textView_serial_id=itemView.findViewById(R.id.product_id);
            textView_productName=itemView.findViewById(R.id.prodcut_name);
            textView_date=itemView.findViewById(R.id.prodcut_date);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int position=getAdapterPosition();

            switch (item.getItemId()){
                case 1:
                    clickLisiner.OnDelete(position);
                  break;
            }
            return true;
        }

        @Override
        public void onClick(View v) {
     int position =getAdapterPosition();
       clickLisiner.Onclick(position);
     //  clickLisiner.OnDelete(position);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Do Your Any Action.");
          MenuItem menuItem_delete=menu.add(Menu.NONE,1,1,"Delete");
          menuItem_delete.setOnMenuItemClickListener(this);
        }
    }
    public  interface  onItemClickLisiner{
         void Onclick(int position);
         void OnDelete(int position);
    }

    void  setOnItemClickLisiner(onItemClickLisiner clickLisiner){
        this.clickLisiner=clickLisiner;
    }
}
