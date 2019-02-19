package com.example.dell.appracadog.home.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.appracadog.R;
import com.example.dell.appracadog.adapter.RecyclerViewAdapter;
import com.example.dell.appracadog.databinding.ActivityHomeBinding;
import com.example.dell.appracadog.home.model.CategoryResponse;
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
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private List<String> imageUrls = new ArrayList<>();
    private HomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        initAssets();

        viewModel.getCategory();

        viewModel.objectLiveData.observe(this, new Observer<CategoryResponse>() {
            @Override
            public void onChanged(@Nullable CategoryResponse response) {
                adapter.update(response.getList());
            }
        });

        button1.setOnClickListener(v -> {
            hideImage();
            category = button1.getText().toString().toLowerCase();
            viewModel.getCategory(category);
        });

        button2.setOnClickListener(v -> {
            hideImage();
            category = button2.getText().toString().toLowerCase();
            viewModel.getCategory(category);
        });

        button3.setOnClickListener(v -> {
            hideImage();
            category = button3.getText().toString().toLowerCase();
            viewModel.getCategory(category);
        });

        button4.setOnClickListener(v -> {
            hideImage();
            category = button4.getText().toString().toLowerCase();
            viewModel.getCategory(category);
        });

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
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);


        adapter = new RecyclerViewAdapter(imageUrls, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        button1 = mBinding.btnHusky;
//                findViewById(R.id.btn_husky);
        button2 = mBinding.btnHound;
        button3 = mBinding.btnPug;
        button4 = mBinding.btnLabrador;
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
}