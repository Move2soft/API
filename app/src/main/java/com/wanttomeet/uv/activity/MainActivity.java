package com.wanttomeet.uv.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.wanttomeet.uv.Listener.ResponseListener;
import com.wanttomeet.uv.R;
import com.wanttomeet.uv.adapter.AdapterBanner;
import com.wanttomeet.uv.adapter.AdapterCategory;
import com.wanttomeet.uv.adapter.AdapterMember;
import com.wanttomeet.uv.adapter.NavigationDrawerAdapter;
import com.wanttomeet.uv.adapter.SpinnerAdapter;
import com.wanttomeet.uv.model.MemberList;
import com.wanttomeet.uv.model.Newslist;
import com.wanttomeet.uv.model.Spinner;
import com.wanttomeet.uv.utils.AsyncHttpRequest;
import com.wanttomeet.uv.utils.AsyncResponseHandler;
import com.wanttomeet.uv.utils.BaseActivity;
import com.wanttomeet.uv.utils.Constants;
import com.wanttomeet.uv.utils.Debug;
import com.wanttomeet.uv.utils.RequestParamsUtils;
import com.wanttomeet.uv.utils.ResponseHandler;
import com.wanttomeet.uv.utils.URLs;

import junit.framework.Test;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements ResponseListener {
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer_layout;
    @Bind(R.id.ivDrawer)
    ImageView ivDrawer;
    @Bind(R.id.drawerListView)
    ListView drawerListView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private View listHeaderView;
    private TextView tvMobileNo;
    RecyclerView rv_banner, rv_category, rv_member;
    private ImageView iv_menu;
    LinearLayout layout_search;
    private PopupWindow pw;
    private String method;
    AdapterMember adapterMember;
    AdapterBanner adapterBanner;
    AdapterCategory adapterCategory;
    ImageView iv_search;

    CircleImageView iv_header_image_view;
    TextView tv_name_header, tv_email_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initDrawer();

        //if (isOnline()) {
            Newslistapi();

        //} else {
          //  Toast.makeText(MainActivity.this, "Sorry! Not connected to internet", Toast.LENGTH_SHORT).show();
       // }


        bind();
        setnavigationheaderdata();


    }

    public void bind() {
        layout_search = (LinearLayout) findViewById(R.id.layout_search);
        iv_search = (ImageView) findViewById(R.id.iv_search);

        iv_menu = (ImageView) findViewById(R.id.iv_menu);
        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuiconclick();
            }
        });

        rv_banner = (RecyclerView) findViewById(R.id.rv_banner);
        rv_category = (RecyclerView) findViewById(R.id.rv_category);
        rv_member = (RecyclerView) findViewById(R.id.rv_member);


        adapterMember = new AdapterMember(MainActivity.this);
        RecyclerView.LayoutManager homelayoutmanager = new GridLayoutManager(MainActivity.this, 1);
        rv_member.setLayoutManager(homelayoutmanager);
        rv_member.setItemAnimator(new DefaultItemAnimator());
        rv_member.setAdapter(adapterMember);

        adapterBanner = new AdapterBanner(MainActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv_banner.setLayoutManager(layoutManager);
        rv_banner.setItemAnimator(new DefaultItemAnimator());
        rv_banner.setAdapter(adapterBanner);

        adapterCategory = new AdapterCategory(MainActivity.this);
        LinearLayoutManager layoutManagert = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv_category.setLayoutManager(layoutManagert);
        rv_category.setItemAnimator(new DefaultItemAnimator());
        rv_category.setAdapter(adapterCategory);

        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout_search.setVisibility(View.VISIBLE);
            }
        });
    }

    public void setnavigationheaderdata() {

        String imageuerl = "";

        iv_header_image_view = (CircleImageView) findViewById(R.id.iv_header_image_view);
        tv_name_header = (TextView) findViewById(R.id.tv_name_header);
        tv_email_header = (TextView) findViewById(R.id.tv_email_header);
        if (isMyProfileRegister()) {
            tv_name_header.setText(Constants.PROFILE.result.get(0).mName);
            tv_email_header.setText(Constants.PROFILE.result.get(0).mEmail);
            Picasso.with(this).load(new URLs().IMAGE + Constants.PROFILE.result.get(0).mImage).into(iv_header_image_view);
            Log.e("image=", new URLs().IMAGE + imageuerl);

        } else {
            tv_name_header.setText("AHIR PARIVAAR");
            tv_email_header.setText("ahir.parivaar.community@gmail.com");
            iv_header_image_view.setImageResource(R.drawable.profile_white);
        }


    }

    public void initDrawer() {
        LayoutInflater inflater = getLayoutInflater();
        listHeaderView = inflater.inflate(R.layout.nav_header, null, false);


        drawerListView.addHeaderView(listHeaderView);
        drawerListView.setAdapter(new NavigationDrawerAdapter(this));
        ivDrawer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                drawer_layout.openDrawer(Gravity.LEFT);
            }
        });
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawer_layout, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFragment(position);
            }
        });
    }

    private void selectItemFragment(int position) {

        switch (position) {
            case 1:
                Intent home = new Intent(MainActivity.this, MainActivity.class);
                startActivity(home);
                break;
            case 2:
                Intent news = new Intent(MainActivity.this, News.class);
                startActivity(news);
                break;
            case 3:
                Intent Aboutus = new Intent(MainActivity.this, AboutUs.class);
                startActivity(Aboutus);
                break;
            case 4:
                Intent contact = new Intent(MainActivity.this, Contacts.class);
                startActivity(contact);
                break;
            case 5:
                if (isMyProfileRegister()) {
                    Intent account = new Intent(MainActivity.this, ViewProfile.class);
                    startActivity(account);
                } else {
                    Intent login = new Intent(MainActivity.this, Login.class);
                    startActivity(login);
                }

                break;
            case 6:
                Intent i = new Intent("android.intent.action.SEND");
                i.setType("text/plain");
                i.putExtra("android.intent.extra.SUBJECT", "Vaishnava Guide");
                i.putExtra("android.intent.extra.TEXT", "Download Vaishnava Guide Now To Get information and guideline to follow vaishnavism.\n\nDownload From :\nplay.google.com/store/apps/details?id=" + MainActivity.this.getPackageName());
                MainActivity.this.startActivity(Intent.createChooser(i, "Share via"));
                break;
            case 7:
                try {
                    MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + MainActivity.this.getPackageName())));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case 8:
                Toast.makeText(MainActivity.this, "delete Me", Toast.LENGTH_SHORT).show();
                break;
            case 9:
                Toast.makeText(MainActivity.this, "Logout...", Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor editor = getPreferences().edit();
                editor.remove(RequestParamsUtils.USERPROFILE);
                editor.commit();



                Intent login = new Intent(MainActivity.this, MainActivity.class);
                startActivity(login);
                break;

        }


        drawerListView.setItemChecked(position, true);

//        drawerLayout.closeDrawer(drawerListView);
    }

    private void menuiconclick() {
        LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //get the pop-up window i.e.  drop-down layout
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.filter_dialog_dealerlist, (ViewGroup) findViewById(R.id.PopUpView));

        //get the view to which drop-down layout is to be anchored
        pw = new PopupWindow(layout, 350, ViewGroup.LayoutParams.WRAP_CONTENT, true);


        //Pop-up window background cannot be null if we want the pop-up to listen touch events outside its window
        pw.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pw.setElevation(10);
        pw.setTouchable(true);

        //let pop-up be informed about touch events outside its window. This  should be done before setting the content of pop-up
        pw.setOutsideTouchable(false);
        pw.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pw.setContentView(layout);

        //anchor the drop-down to bottom-left corner of 'layout1'
        pw.showAsDropDown(iv_menu);

        //populate the drop-down list
        final ListView list = (ListView) layout.findViewById(R.id.dropDownList_dealer);
        final ArrayList<Spinner> data = new ArrayList<>();
        data.add(new Spinner("0", "About Us"));
        data.add(new Spinner("1", "Share"));
        data.add(new Spinner("2", "Rate Us"));
        SpinnerAdapter adapter = new SpinnerAdapter(this);
        adapter.addAll(data);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pw.dismiss();

                if (position == 0) {
                    Intent Aboutusss = new Intent(MainActivity.this, AboutUs.class);
                    startActivity(Aboutusss);

                } else if (position == 1) {

                    Intent i = new Intent("android.intent.action.SEND");
                    i.setType("text/plain");
                    i.putExtra("android.intent.extra.SUBJECT", "Vaishnava Guide");
                    i.putExtra("android.intent.extra.TEXT", "Download Vaishnava Guide Now To Get information and guideline to follow vaishnavism.\n\nDownload From :\nplay.google.com/store/apps/details?id=" + MainActivity.this.getPackageName());
                    MainActivity.this.startActivity(Intent.createChooser(i, "Share via"));
                } else if (position == 2) {
                    try {
                        MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + MainActivity.this.getPackageName())));

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    private void Newslistapi() {
        method = "Newslistapi";
        try {
            showProgress("");
            RequestParams params = new RequestParams();
            Debug.e("Newslistapi", params.toString());

            AsyncHttpClient clientPhotos = AsyncHttpRequest.newRequest();

            clientPhotos.post(new URLs().Newslist, params, new ResponseHandler(MainActivity.this, MainActivity.this));

        } catch (Exception e) {
            Debug.e("Newslistapi Exception", e.getMessage());
        }
    }

    private void getmemberList() {
        method = "getmemberList";
        try {
            showProgress("");
            RequestParams params = new RequestParams();
            params.put(RequestParamsUtils.MID, 26 + "");
            params.put(RequestParamsUtils.MLAT, "");
            params.put(RequestParamsUtils.MLONG, "");
            Debug.e("getmemberList", params.toString());

            AsyncHttpClient clientPhotos = AsyncHttpRequest.newRequest();

            clientPhotos.post(new URLs().MEMBER, params, new ResponseHandler(MainActivity.this, MainActivity.this));

        } catch (Exception e) {
            Debug.e("getmemberList Exception", e.getMessage());
        }
    }

    @Override
    public void onResponse(String response) {
        if (method.equals("Newslistapi")) {

            try {

                Log.e("responce", response);

                if (response != null && response.length() > 0) {

                    Newslist pt = new Gson().fromJson(
                            response, new TypeToken<Newslist>() {
                            }.getType());
                    adapterBanner.addAll(pt.result);
                    getmemberList();

                }

            } catch (Exception e) {
                Debug.e("RetryHandler Sucess Exception", e.getMessage());
            }
        } else if (method.equals("getmemberList")) {
            dismissProgress();
            try {

                Log.e("responce", response);

                if (response != null && response.length() > 0) {

                    MemberList pt = new Gson().fromJson(
                            response, new TypeToken<MemberList>() {
                            }.getType());

                    if (pt.st == 1) {
                        adapterMember.addAll(pt.result);
                    }

                }

            } catch (Exception e) {
                Debug.e("RetryHandler Sucess Exception", e.getMessage());
            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
