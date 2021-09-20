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

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.squareup.picasso.Picasso;

import java.util.List;

public class image_Adapter  extends RecyclerView.Adapter<image_Adapter.MyViewHolder> {
    private  static onItemClickLisiner clickLisiner;
    private static int listposition=-1;
    private Context context;
    private List<Purchase_Model>modelList;
   private View view;
     String mystatus="";

    public image_Adapter(Context context, List<Purchase_Model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
   LayoutInflater layoutInflater=LayoutInflater.from(context);
  View view =layoutInflater.inflate(R.layout.simple_image_pd,parent,false);

  return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Purchase_Model itemPosition = modelList.get(position);


        myViewHolder.textView_name.setText("Name:" + itemPosition.getPdName());
        myViewHolder.textView_price.setText("Price:" + itemPosition.getPd_Price());
        myViewHolder.textView_discount.setText("Discount:" + itemPosition.getPd_discount());

        Picasso.get().load(itemPosition.getPdImage()).into(myViewHolder.imageView);

      //Glide.with(context).load(itemPosition.getPdImage()).into(myViewHolder.imageView);

        /*myViewHolder.imageView_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             DialogPlus dialogPlus=DialogPlus.newDialog(context)
                        .setContentHolder(new ViewHolder(R.layout.update_layout))
                        .setExpanded(true,1100)
                        .create();

                View mview=dialogPlus.getHeaderView();
               TextView textView_id=(TextView)  mview.findViewById(R.id.text_update_Id);
                EditText editText_name=(EditText)  mview.findViewById(R.id.edit_Upd_Name);
                EditText editText_price=(EditText)  mview.findViewById(R.id.edit_upd_price);

               //textView_id.setText("Id:"+itemPosition.getPid());
              //  Toast.makeText(context, ""+itemPosition.getPdName(), Toast.LENGTH_SHORT).show();
               // editText_name.setText(itemPosition.getPdName());
               // editText_price.setText(itemPosition.getPdPrice());
          dialogPlus.show();
            }
        });*/
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
    return modelList.size();
    }

    public void filterList(List<Purchase_Model> filterList) {
        modelList=filterList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener

         {

    private ImageView imageView,imageView_delete,imageView_update;
    private TextView textView_name,textView_price,textView_discount;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.show_pd_Image);
            textView_name=itemView.findViewById(R.id.text_name);
            textView_price=itemView.findViewById(R.id.text_price);
            textView_discount=itemView.findViewById(R.id.text_discout);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
     int position =getAdapterPosition();


     clickLisiner.onClick(position);

     }



         }
    public interface onItemClickLisiner{
        void onClick(int position);

    }

    void setOnItemClick(onItemClickLisiner clickLisiner){
        this. clickLisiner=clickLisiner;
    }


}
