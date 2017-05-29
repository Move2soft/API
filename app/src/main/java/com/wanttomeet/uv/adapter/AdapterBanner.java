package com.wanttomeet.uv.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wanttomeet.uv.R;
import com.wanttomeet.uv.model.Newslist;
import com.wanttomeet.uv.utils.URLs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by UV on 5/11/2017.
 */
public class AdapterBanner extends RecyclerView.Adapter<AdapterBanner.Banner> {

    private Activity activity;
    private List<Newslist.Result> Bannerlist = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Context c;
    AlertDialog alertDialog;

    TextView tv_titile, tv_body;
    ImageView iv_baneer;
    String imagevar = "", titlevar = "", bodyvar = "";


    public AdapterBanner(Activity activity) {
        this.activity = activity;
        layoutInflater = LayoutInflater.from(activity);
        c = activity.getApplicationContext();
    }


    public void addAll(List<Newslist.Result> templelist) {
        this.Bannerlist = templelist;
        notifyDataSetChanged();
    }


    @Override
    public Banner onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_banner, parent, false);

        return new Banner(itemView);
    }

    @Override
    public void onBindViewHolder(final Banner holder, final int position) {

        holder.imageurl = Bannerlist.get(position).nwImage;

        if (holder.imageurl.equals("")) {
            holder.iv_banner_image.setImageResource(R.drawable.ic_nw);
        } else {
            Picasso.with(activity).load(new URLs().ImageUrlnewsimage + holder.imageurl).into(holder.iv_banner_image);

        }
        holder.iv_banner_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagevar = holder.imageurl = Bannerlist.get(position).nwImage;
                titlevar = holder.titile = Bannerlist.get(position).nwTitle;
                bodyvar = holder.body = Bannerlist.get(position).nwBody;
                newsdialog();
            }
        });

    }

    @Override
    public int getItemCount() {
        return Bannerlist.size();
    }

    class Banner extends RecyclerView.ViewHolder {

        ImageView iv_banner_image;
        String imageurl, titile, body;

        public Banner(View itemView) {
            super(itemView);
            iv_banner_image = (ImageView) itemView.findViewById(R.id.iv_banner_image);
        }
    }

    public void newsdialog() {

        LayoutInflater li = LayoutInflater.from(activity);

        View confirmDialog = li.inflate(R.layout.dialognews, null);
        tv_titile = (TextView) confirmDialog.findViewById(R.id.tv_titile);
        tv_body = (TextView) confirmDialog.findViewById(R.id.tv_body);
        iv_baneer = (ImageView) confirmDialog.findViewById(R.id.iv_baneer);

        if (imagevar.equals("")) {
            iv_baneer.setVisibility(View.GONE);
        } else {
            Picasso.with(activity).load(new URLs().ImageUrlnewsimage + imagevar).into(iv_baneer);

        }
        tv_titile.setText(titlevar);
        tv_body.setText(bodyvar);

        android.support.v7.app.AlertDialog.Builder alert = new android.support.v7.app.AlertDialog.Builder(activity);

        alert.setView(confirmDialog);

        alertDialog = alert.create();

        alertDialog.show();

        alertDialog.setCanceledOnTouchOutside(false);


    }
}
