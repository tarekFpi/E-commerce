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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class pd_categoryShowAdapter extends RecyclerView.Adapter<pd_categoryShowAdapter.MyViewholder> {
    private  static  onItemClickLisiner clickLisiner;
    private Context context;
    private List<pd_Category_showModel>productModelList;
       String size_key="test";
    private static int listposition=-1;

    public pd_categoryShowAdapter(Context context, List<pd_Category_showModel> productModelList) {
        this.context = context;
        this.productModelList = productModelList;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
      View view= layoutInflater.inflate(R.layout.category_pd_show,parent,false);

        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder myViewholder, int position) {
        pd_Category_showModel item_position=productModelList.get(position);

         myViewholder.textView_pd_Name.setText("Name:"+item_position.getPdName());
         myViewholder.textView_pdCord.setText("Id:"+item_position.getPid());
         myViewholder.textView_quantity.setText("Quanitty:"+item_position.getSell_quanitiy());
         myViewholder.textView_pd_price.setText("Itme Price:"+item_position.getSelling_price());
         myViewholder.textView_discount.setText("Discount:"+item_position.getPd_discount());

         if(item_position.getPdSize().contains("no")){
             myViewholder.textView_pd_size.setVisibility(View.INVISIBLE);
         }else{
           myViewholder.textView_pd_size.setText("Size:"+item_position.getPdSize());
         }

        Picasso.get().load(item_position.getPdImage()).into(myViewholder.imageView_pd);

        setAnimiton(myViewholder.itemView,position);
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
        return productModelList.size();
    }

   public class  MyViewholder extends RecyclerView.ViewHolder implements View.OnClickListener,
           View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{

       private TextView textView_pdCord,textView_pd_Name,textView_quantity,textView_pd_price,textView_pd_size,textView_discount;
       private ImageView imageView_pd;

       public MyViewholder(@NonNull View itemView) {
       super(itemView);

           textView_pdCord=itemView.findViewById(R.id.cate_show_pd_cord);
           textView_pd_Name=itemView.findViewById(R.id.cate_show_pd_name);
           textView_quantity=itemView.findViewById(R.id.cate_show_pd_quan);
           textView_pd_price=itemView.findViewById(R.id.cate_show_pd_totoalPrice);
           textView_pd_size=(TextView)itemView.findViewById(R.id.cate_show_pd_size);
           textView_discount=(TextView)itemView.findViewById(R.id.cate_show_pd_discount);
           imageView_pd=(ImageView)itemView.findViewById(R.id.category_pd_image);

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
        this.clickLisiner=clickLisiner;
    }
}
