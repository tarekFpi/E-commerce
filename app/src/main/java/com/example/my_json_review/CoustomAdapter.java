package com.example.my_json_review;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CoustomAdapter extends BaseAdapter {

    private Context context;
    private int lay;
    private List<productModel>models;
    private LayoutInflater layoutInflater;

    public CoustomAdapter(Context context, int lay, List<productModel> models) {
        this.context = context;
        this.lay = lay;
        this.models = models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int position) {
        return  models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        TextView textView_id,textView_name,textView_price;
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view =layoutInflater.inflate(R.layout.simple_layout,viewGroup,false);

        textView_id=view.findViewById(R.id.pd_id);
        textView_name=view.findViewById(R.id.pd_name);
        textView_price=view.findViewById(R.id.pd_price);

        textView_id.setText(models.get(position).getId());
        textView_name.setText(models.get(position).getName());
        textView_price.setText(models.get(position).getPrice());
        return view;
    }
}
