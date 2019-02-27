package com.example.dell.appracadog.adapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dell.appracadog.R;
import com.example.dell.appracadog.interfaces.ItemOnClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<String> imageUrls;
    private ItemOnClickListener listener;

    public RecyclerViewAdapter(List<String> imageUrls, ItemOnClickListener listener) {
        this.imageUrls = imageUrls;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        String url = imageUrls.get(position);
        holder.bind(url);
        holder.itemView.setOnClickListener(view -> listener.onClick(url));
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView dogImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

           dogImage = itemView.findViewById(R.id.imagem_dog_id);
        }

        public void bind(String url) {
            Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.img_loading)
                    .error(android.R.drawable.stat_notify_error)
                    .into(dogImage);
        }
    }

    public void update(List<String> imageUrls) {
        this.imageUrls.clear();
        this.imageUrls = imageUrls;
        notifyDataSetChanged();
    }
}