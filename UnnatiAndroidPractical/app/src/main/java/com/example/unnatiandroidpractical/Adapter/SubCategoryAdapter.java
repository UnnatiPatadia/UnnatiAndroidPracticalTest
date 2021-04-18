package com.example.unnatiandroidpractical.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unnatiandroidpractical.Model.GetSubCategory;
import com.example.unnatiandroidpractical.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder> {
    Activity activity;
    RecyclerView recyclerViewProduct;
    ProductListAdapter productListAdapter;
    private ArrayList<GetSubCategory.SubCategory> subCategoriesList;

    public SubCategoryAdapter(ArrayList<GetSubCategory.SubCategory> subCategoriesList, Activity activity) {
        this.subCategoriesList = subCategoriesList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.raw_subcategory_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final GetSubCategory.SubCategory list = subCategoriesList.get(position);
        holder.tvSubCatName.setText(list.getName());

        if (list.getProduct().size() > 0) {
            for (int i = 0; i < list.getProduct().size(); i++) {
                setProductAdapter(list.getProduct());
            }
        }
    }

    @Override
    public int getItemCount() {
        return subCategoriesList.size();
    }

    private void setProductAdapter(ArrayList<GetSubCategory.Product> productList) {
        productListAdapter = new ProductListAdapter(productList, activity);
        recyclerViewProduct.setAdapter(productListAdapter);
        productListAdapter.notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvSubCatName)
        TextView tvSubCatName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            recyclerViewProduct = itemView.findViewById(R.id.recyclerViewProduct);
        }
    }
}
