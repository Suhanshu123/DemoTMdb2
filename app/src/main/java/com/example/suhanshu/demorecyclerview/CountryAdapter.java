package com.example.suhanshu.demorecyclerview;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.net.URL;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryHolder> {

    public interface OnClickListenerDemo {
        void OnClick(Result result);
    }

    OnClickListenerDemo onClickListenerDemo;


    Context context;
    List<Result> results;


    public CountryAdapter(List<Result> results, Context context, OnClickListenerDemo onClickListenerDemo) {
        this.results = results;
        this.context = context;
        this.onClickListenerDemo = onClickListenerDemo;

    }

    @Override
    public CountryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_row_layout, parent, false);
        return new CountryHolder(view);
    }

    @Override
    public void onBindViewHolder(CountryHolder holder, int position) {

//        List<Result> results=user.Result();

        Result result = results.get(position);
        holder.ratings.setText(result.getVoteAverage() + " ");
        Glide.with(context).load("https://image.tmdb.org/t/p/w500" + result.getPosterPath()).into(holder.imageView);
        holder.title.setText(result.getTitle());

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class CountryHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageView;
        TextView ratings;


        public CountryHolder(final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_view);
            imageView = itemView.findViewById(R.id.image_view);
            ratings = itemView.findViewById(R.id.ratings);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListenerDemo.OnClick(results.get(getAdapterPosition()));
                }
            });


        }
    }
}
