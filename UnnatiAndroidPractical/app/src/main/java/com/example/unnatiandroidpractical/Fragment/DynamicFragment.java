package com.example.unnatiandroidpractical.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unnatiandroidpractical.Adapter.SubCategoryAdapter;
import com.example.unnatiandroidpractical.Model.GetSubCategory;
import com.example.unnatiandroidpractical.NetworkUtility.ApiHandler;
import com.example.unnatiandroidpractical.R;
import com.example.unnatiandroidpractical.Utility.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.unnatiandroidpractical.Utility.Util.AlertDialog;
import static com.example.unnatiandroidpractical.Utility.Util.GetJsonRequestBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class DynamicFragment extends Fragment {

    @BindView(R.id.tvCatName)
    TextView tvCatName;
    @BindView(R.id.rootLayout)
    RelativeLayout rootLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tvNoDataFound)
    TextView tvNoDataFound;

    int position;
    String CategoryName;
    private Activity activity;
    private ArrayList<GetSubCategory.SubCategory> subCategoriesList = new ArrayList<>();
    private SubCategoryAdapter subCategoryAdapter;
    public ProgressDialog progessDialog;

    public static DynamicFragment newInstance(int val, String CategoryName) {
        DynamicFragment fragment = new DynamicFragment();
        Bundle args = new Bundle();
        args.putInt("position", val);
        args.putString("CategoryName", CategoryName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dynamic, container, false);
        ButterKnife.bind(this, view);

        activity = getActivity();

        position = getArguments().getInt("position", 0);
        CategoryName = getArguments().getString("CategoryName", "Ceramic");
        tvCatName.setText(CategoryName);

        Init();
        return view;
    }

    private void Init() {
        if (CategoryName.equalsIgnoreCase("Ceramic")){
            rootLayout.setVisibility(View.VISIBLE);
            tvCatName.setVisibility(View.GONE);
        } else {
            rootLayout.setVisibility(View.GONE);
            tvCatName.setVisibility(View.VISIBLE);
        }

        getSubCategory();
    }

    private void getSubCategory() {
        showProgressDialog();

        JSONObject params = new JSONObject();
        try {
            params.accumulate("CategoryId", "56");
            params.accumulate("PageIndex", "2");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiHandler.getApiService().getSubCategory(GetJsonRequestBody(params)).enqueue(new Callback<GetSubCategory>() {
            @Override
            public void onResponse(Call<GetSubCategory> call, Response<GetSubCategory> response) {
                if (response.isSuccessful()) {
                    progessDialog.dismiss();
                    try {
                        GetSubCategory getSubCategory = response.body();
                        if (getSubCategory != null) {
                            if (getSubCategory.getStatus() == 200) {
                                subCategoriesList = getSubCategory.getResult().getCategory().get(0).getSubCategories();
                                if (subCategoriesList == null) subCategoriesList = new ArrayList<>();

                                if (subCategoriesList.size() > 0) {
                                    if (subCategoryAdapter == null) {
                                        SetAdapter(subCategoriesList);
                                    } else {
                                        subCategoryAdapter.notifyDataSetChanged();
                                    }
                                } else {
                                    recyclerView.setVisibility(View.GONE);
                                    tvNoDataFound.setVisibility(View.VISIBLE);
                                }
                            } else {
                                AlertDialog(getString(R.string.something_went_wrong), activity);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    AlertDialog(activity.getString(R.string.server_error_msg), activity);
                }
            }

            @Override
            public void onFailure(Call<GetSubCategory> call, Throwable t) {
                progessDialog.dismiss();
                Log.e("Failure", t.getMessage());
//                AlertDialog(getString(R.string.server_error_msg), MainActivity.this);
            }
        });

    }

    private void SetAdapter(ArrayList<GetSubCategory.SubCategory> subCategoriesList) {
        subCategoryAdapter = new SubCategoryAdapter(subCategoriesList, activity);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(subCategoryAdapter);
        subCategoryAdapter.notifyDataSetChanged();
    }

    public void showProgressDialog() {
        progessDialog = new ProgressDialog(activity);
        progessDialog.setTitle(activity.getString(R.string.app_name));
        progessDialog.setMessage(activity.getString(R.string.please_wait));
        progessDialog.setCancelable(false);
        progessDialog.show();
    }
}
