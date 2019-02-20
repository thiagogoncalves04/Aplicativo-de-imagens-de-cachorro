package com.example.dell.appracadog.home.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.appracadog.R;
import com.example.dell.appracadog.adapter.RecyclerViewAdapter;
import com.example.dell.appracadog.databinding.ActivityHomeBinding;
import com.example.dell.appracadog.home.viewmodel.HomeViewModel;
import com.example.dell.appracadog.interfaces.ItemOnClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements ItemOnClickListener {

    ActivityHomeBinding mBinding;
    private String category;
    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private ImageView expandedImage;
    private View bgView;
    private BottomNavigationView navigationView;
    private List<String> imageUrls = new ArrayList<>();
    private HomeViewModel viewModel;
    private TextView textCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        navigationView = mBinding.bottomNavigationView;

        initAssets();

        viewModel.getCategory();

        viewModel.objectLiveData.observe(this, response -> adapter.update(response.getList()));

//        button1.setOnClickListener(v -> {
//            hideImage();
//            category = button1.getText().toString().toLowerCase();
//            viewModel.getCategory(category);

        bgView.setOnClickListener(v -> {
            hideImage();
        });
    }

    private void initAssets() {
        recyclerView = mBinding.recyclerId;
//        recyclerView = findViewById(R.id.recycler_id);
        expandedImage = mBinding.imageDog;
        bgView = mBinding.containerId;
//        bgView = findViewById(R.id.container_id);
        textCategory = mBinding.textCategory;
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        selectedCategory();

        adapter = new RecyclerViewAdapter(imageUrls, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

//        button1 = mBinding.btnHusky;
////                findViewById(R.id.btn_husky);
    }

    @Override
    public void onClick(String url) {
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.img_loading)
                .error(android.R.drawable.stat_notify_error)
                .into(expandedImage);
        showImage();
    }

    private void showImage() {
        bgView.setVisibility(View.VISIBLE);
        expandedImage.setVisibility(View.VISIBLE);
    }

    public void hideImage() {
        expandedImage.setVisibility(View.GONE);
        bgView.setVisibility(View.GONE);
    }

    private void selectedCategory() {
        navigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.navigation_labrador: {
                    hideImage();
                    category = "labrador";
                    textCategory.setText(category.toLowerCase());
                    viewModel.getCategory(category.toLowerCase());
                    break;
                }
                case R.id.navigation_hound: {
                    hideImage();
                    category = "hound";
                    textCategory.setText(category);
                    viewModel.getCategory(category.toLowerCase());
                    break;
                }
                case R.id.navigation_pug: {
                    hideImage();
                    category = "pug";
                    textCategory.setText(category);
                    viewModel.getCategory(category.toLowerCase());

                    break;
                }
                case R.id.navigation_husky: {
                    hideImage();
                    category = "husky";
                    textCategory.setText(category);
                    viewModel.getCategory(category.toLowerCase());
                    break;
                }
            }
            return true;
        });
    }
}