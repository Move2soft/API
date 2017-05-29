package com.wanttomeet.uv.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.wanttomeet.uv.R;
import com.wanttomeet.uv.activity.ViewProfile;
import com.wanttomeet.uv.model.MemberList;
import com.wanttomeet.uv.utils.Constants;
import com.wanttomeet.uv.utils.RequestParamsUtils;
import com.wanttomeet.uv.utils.URLs;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by UV on 5/11/2017.
 */
public class AdapterMember extends RecyclerView.Adapter<AdapterMember.Member> {

    private Activity activity;
    private List<MemberList.Result> Memberlist = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Context c;
    int likeimafe=0;
    int likecount=121;



    public AdapterMember(Activity activity) {
        this.activity = activity;
        layoutInflater = LayoutInflater.from(activity);
        c = activity.getApplicationContext();
    }

    public void addAll(List<MemberList.Result> Memberlist) {
        this.Memberlist = Memberlist;
        notifyDataSetChanged();
    }


    @Override
    public Member onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_member, parent, false);

        return new Member(itemView);
    }

    @Override
    public void onBindViewHolder(final Member holder, final int position) {
        holder.layout_cart_memebers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profile = new Intent(activity, ViewProfile.class);
                Bundle bundle = new Bundle();
                bundle.putString(RequestParamsUtils.MNAME, Memberlist.get(position).mName);
                bundle.putString(RequestParamsUtils.MEMAIL, Memberlist.get(position).mEmail);
                bundle.putString(RequestParamsUtils.MCONTACT, Memberlist.get(position).mContact);
                bundle.putString(RequestParamsUtils.MGENDER, Memberlist.get(position).mGender);
                bundle.putString(RequestParamsUtils.MDOB, Memberlist.get(position).mDob);
                bundle.putString(RequestParamsUtils.MEDUCTION, Memberlist.get(position).mEducation);
                bundle.putString(RequestParamsUtils.MCOLLAGE, Memberlist.get(position).mCollage);
                bundle.putString(RequestParamsUtils.MEMPLOUEDIN, Memberlist.get(position).mEmployedin);
                bundle.putString(RequestParamsUtils.MOCCUPTION, Memberlist.get(position).mOccupation);
                bundle.putString(RequestParamsUtils.MOCCUPTIONDETAIL, Memberlist.get(position).mOccupationDetail);
                bundle.putString(RequestParamsUtils.MCITY, Memberlist.get(position).mCity);
                bundle.putString(RequestParamsUtils.MSTATE, Memberlist.get(position).mState);
                bundle.putString(RequestParamsUtils.MCOUNTRY, Memberlist.get(position).mContry);
                bundle.putString(RequestParamsUtils.MABOUT, Memberlist.get(position).mAbout);
                bundle.putString(RequestParamsUtils.MIMAGE, Memberlist.get(position).mImage);
                profile.putExtras(bundle);
                activity.startActivity(profile);
            }
        });
        holder.tvCity.setText(Memberlist.get(position).mCity);
        holder.tvName.setText(Memberlist.get(position).mName);
        holder.tvEmailId.setText(Memberlist.get(position).mEmail);
        holder.tvGender.setText(Memberlist.get(position).mGender);
        holder.tvOccuption.setText(Memberlist.get(position).mOccupation);

        holder.llCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mobileno = Memberlist.get(position).mContact;

                if (mobileno.equals("")) {
                    Toast.makeText(activity, "No Contact Number Found", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity, "Call Now...", Toast.LENGTH_SHORT).show();
                    String no = mobileno.toString().trim();
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + no));
                    if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        // ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        // public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        // int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    activity.startActivity(intent);

                }



            }
        });

        holder.llLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (likeimafe == 0) {
                    Toast.makeText(activity, "Like", Toast.LENGTH_SHORT).show();
                    holder.iv_like_thamb.setImageResource(R.drawable.ic_likefill);
                    int liketotal=likecount+1;
                    holder.tv_like_count.setText(String.valueOf(liketotal));
                    likeimafe = 1;

                } else if (likeimafe == 1) {
                    Toast.makeText(activity, "Unlike", Toast.LENGTH_SHORT).show();
                    holder.iv_like_thamb.setImageResource(R.drawable.ic_like1);
                    int liketotal=likecount-1;
                    holder.tv_like_count.setText(String.valueOf(liketotal));
                    likeimafe = 0;
                } else {

                }







            }
        });

        String image=Memberlist.get(position).mImage;

        if(image.equals("")){
            holder.ivProfile.setBackgroundResource(R.drawable.ic_profilr);
        }
        else {
            Picasso.with(activity).load(new URLs().IMAGE + Memberlist.get(position).mImage).into(holder.ivProfile);
        }

    }

    @Override
    public int getItemCount() {
        return Memberlist.size();
    }

    class Member extends RecyclerView.ViewHolder {
        @Bind(R.id.layout_cart_memebers)
        LinearLayout layout_cart_memebers;

        @Bind(R.id.tvName)
        TextView tvName;

        @Bind(R.id.tvEmailId)
        TextView tvEmailId;

        @Bind(R.id.tvCity)
        TextView tvCity;

        @Bind(R.id.tvGender)
        TextView tvGender;

        @Bind(R.id.tv_like_count)
        TextView tv_like_count;

        @Bind(R.id.tvOccuption)
        TextView tvOccuption;

        @Bind(R.id.ivProfile)
        ImageView ivProfile;

        @Bind(R.id.tvViewProfile)
        TextView tvViewProfile;

        @Bind(R.id.llCall)
        LinearLayout llCall;

        @Bind(R.id.llLocation)
        LinearLayout llLocation;

        @Bind(R.id.iv_like_thamb)
        ImageView iv_like_thamb;

        public Member(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
