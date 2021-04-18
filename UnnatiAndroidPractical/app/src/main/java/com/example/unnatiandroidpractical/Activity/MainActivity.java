package com.example.unnatiandroidpractical.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.unnatiandroidpractical.Adapter.ViewPagerAdapter;
import com.example.unnatiandroidpractical.Model.GetCategory;
import com.example.unnatiandroidpractical.NetworkUtility.ApiHandler;
import com.example.unnatiandroidpractical.R;
import com.google.android.material.tabs.TabLayout;

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

public class MainActivity extends AppCompatActivity {

    public ProgressDialog progessDialog;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.frameLayout)
    ViewPager viewPager;
    private ArrayList<GetCategory.Category> categoriesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        GetCategoryList();
    }

    private void GetCategoryList() {
        showProgressDialog();

        JSONObject params = new JSONObject();
        try {
            params.accumulate("CategoryId", "0");
            params.accumulate("DeviceManufacturer", "Google");
            params.accumulate("DeviceModel", "Android SDK built for x86");
            params.accumulate("DeviceToken", " ");
            params.accumulate("PageIndex", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiHandler.getApiService().getCategory(GetJsonRequestBody(params)).enqueue(new Callback<GetCategory>() {
            @Override
            public void onResponse(Call<GetCategory> call, Response<GetCategory> response) {
                if (response.isSuccessful()) {
                    progessDialog.dismiss();
                    try {
                        GetCategory getCategory = response.body();
                        if (getCategory != null) {
                            if (getCategory.getStatus() == 200) {
                                categoriesList = getCategory.getResult().getCategory();
                                for (int i = 0; i < categoriesList.size(); i++) {
                                    tabLayout.addTab(tabLayout.newTab().setText(categoriesList.get(i).getName()));
                                }

                                ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), categoriesList);
                                viewPager.setAdapter(adapter);
                                viewPager.setOffscreenPageLimit(1);
                                viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
                                if (tabLayout.getTabCount() == 2) {
                                    tabLayout.setTabMode(TabLayout.MODE_FIXED);
                                } else {
                                    tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                                }
                            } else {
                                AlertDialog(getString(R.string.something_went_wrong), MainActivity.this);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    AlertDialog(getString(R.string.server_error_msg), MainActivity.this);
                }
            }

            @Override
            public void onFailure(Call<GetCategory> call, Throwable t) {
                progessDialog.dismiss();
                Log.e("Failure", t.getMessage());
//                AlertDialog(getString(R.string.server_error_msg), MainActivity.this);
            }
        });

    }

    public void showProgressDialog() {
        progessDialog = new ProgressDialog(MainActivity.this);
        progessDialog.setTitle(getString(R.string.app_name));
        progessDialog.setMessage(getString(R.string.please_wait));
        progessDialog.setCancelable(false);
        progessDialog.show();
    }
}
