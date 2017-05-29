package com.wanttomeet.uv.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.wanttomeet.uv.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by UV on 20-Feb-17.
 */
public class SelectImageAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater infalter;
    private List<String> data = new ArrayList<String>();

    public SelectImageAdapter(Context c) {
        infalter = (LayoutInflater) c
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = c;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public String getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addAll(List<String> files) {

        try {

            this.data.clear();
            this.data.addAll(files);

        } catch (Exception e) {
            Log.e("Exception is", e.getMessage());
        }

        notifyDataSetChanged();
    }

    public void add(String files) {

        try {
            this.data.add(files);
        } catch (Exception e) {
            Log.e("Exception is", e.getMessage());
        }

        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();

            convertView = infalter.inflate(R.layout.item_select_image, null);
            holder.tvMenuTitle = (TextView) convertView.findViewById(R.id.tvSPinner);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        try {
            holder.tvMenuTitle.setText(data.get(position));

        } catch (Exception e) {
            Log.e("spinner error", e.toString());
        }

        return convertView;
    }

    public class ViewHolder {
        TextView tvMenuTitle;
        ImageView ivImageview;
    }

    boolean isEnable = true;

    public void setParentCategEnabled(boolean isEnable) {
        this.isEnable = isEnable;
    }

    @Override
    public boolean isEnabled(int position) {
        return super.isEnabled(position);
    }
}
