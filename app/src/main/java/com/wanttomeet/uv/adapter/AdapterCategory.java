package com.wanttomeet.uv.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wanttomeet.uv.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by UV on 5/11/2017.
 */
public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.Category> {

    private Activity activity;
    private List<String> CategoryList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Context c;



    public AdapterCategory(Activity activity) {
        this.activity = activity;
        layoutInflater = LayoutInflater.from(activity);
        c = activity.getApplicationContext();
        for (int i = 0; i < 10; i++) {
            CategoryList.add("");
        }
    }

    @Override
    public Category onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);

        return new Category(itemView);
    }

    @Override
    public void onBindViewHolder(Category holder, int position) {

    }

    @Override
    public int getItemCount() {
        return CategoryList.size();
    }

        class Category extends RecyclerView.ViewHolder {


        public Category(View itemView) {
            super(itemView);

        }
    }

}
