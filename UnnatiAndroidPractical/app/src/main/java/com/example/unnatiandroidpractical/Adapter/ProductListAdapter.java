package com.example.unnatiandroidpractical.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.unnatiandroidpractical.Model.GetSubCategory;
import com.example.unnatiandroidpractical.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyViewHolder>  {

    private ArrayList<GetSubCategory.Product> productList;
    Activity activity;

    public ProductListAdapter(ArrayList<GetSubCategory.Product> productList, Activity activity) {
        this.productList = productList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.raw_product_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final GetSubCategory.Product list = productList.get(position);
        holder.tvPriceCode.setText(list.getPriceCode());
        holder.tvProdName.setText(list.getName());

        Glide.with(activity).load(list.getImageName()).into(holder.ImageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.myImageView)
        ImageView ImageView;
        @BindView(R.id.tvPriceCode)
        TextView tvPriceCode;
        @BindView(R.id.tvProdName)
        TextView tvProdName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
