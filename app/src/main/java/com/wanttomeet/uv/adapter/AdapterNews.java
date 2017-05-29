package com.wanttomeet.uv.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wanttomeet.uv.R;
import com.wanttomeet.uv.model.Newslist;
import com.wanttomeet.uv.model.Spinner;
import com.wanttomeet.uv.utils.URLs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by UV on 5/16/2017.
 */
public class AdapterNews extends RecyclerView.Adapter<AdapterNews.News> {

    private Activity activity;
    private List<Newslist.Result> newslist = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Context c;

    private AlertDialog alertDialog;

    private TextView tv_titile, tv_body;
    private ImageView iv_baneer;
    private String imagevar = "", titlevar = "", bodyvar = "";


    public AdapterNews(Activity activity) {
        this.activity = activity;
        layoutInflater = LayoutInflater.from(activity);
        c = activity.getApplicationContext();
       /* for (int i = 0; i < 10; i++) {
            templelist.add("");
        }*/
    }


    public void addAll(List<Newslist.Result> templelist) {
        this.newslist = templelist;
        notifyDataSetChanged();
    }
    @Override
    public News onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news_list, parent, false);

        return new News(itemView);
    }

    @Override
    public void onBindViewHolder(final News holder, final int position) {

        holder.imageurl=newslist.get(position).nwImage;

        if(holder.imageurl.equals("")){
            holder.iv_banner_all.setImageResource(R.drawable.ic_nw);
        }
        else {
            Picasso.with(activity).load(new URLs().ImageUrlnewsimage+holder.imageurl).into(holder.iv_banner_all);

        }
        holder.tv_titile_all.setText(newslist.get(position).nwTitle);
        holder.tv_body_all.setText(newslist.get(position).nwBody);

        holder.layout_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imagevar = holder.imageurl = newslist.get(position).nwImage;
                titlevar = holder.titile = newslist.get(position).nwTitle;
                bodyvar = holder.body = newslist.get(position).nwBody;
                newsdialognews();
            }
        });

    }

    @Override
    public int getItemCount() {
        return newslist.size();
    }

    class News extends RecyclerView.ViewHolder {
        private TextView tv_titile_all,tv_body_all;
        private ImageView iv_banner_all;
        private LinearLayout layout_cart;
        private String imageurl;

        String  titile, body;

        public News(View itemView) {
            super(itemView);

            tv_body_all= (TextView) itemView.findViewById(R.id.tv_body_all);
            tv_titile_all= (TextView) itemView.findViewById(R.id.tv_titile_all);
            iv_banner_all= (ImageView) itemView.findViewById(R.id.iv_banner_all);
            layout_cart= (LinearLayout) itemView.findViewById(R.id.layout_cart);


        }
    }

    public void newsdialognews() {

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
