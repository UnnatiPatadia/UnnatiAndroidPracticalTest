package com.example.unnatiandroidpractical.NetworkUtility;

import com.example.unnatiandroidpractical.Model.GetCategory;
import com.example.unnatiandroidpractical.Model.GetProductList;
import com.example.unnatiandroidpractical.Model.GetSubCategory;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WebServices {
    @POST("DashBoard")
    Call<GetCategory> getCategory(@Body RequestBody jsonObject);

    @POST("DashBoard")
    Call<GetSubCategory> getSubCategory(@Body RequestBody jsonObject);

    @POST("ProductList")
    Call<GetProductList> getProductList(@Body RequestBody jsonObject);
}
