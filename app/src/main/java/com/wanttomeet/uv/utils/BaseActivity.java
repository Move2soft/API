package com.wanttomeet.uv.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wanttomeet.uv.R;
import com.wanttomeet.uv.model.Profile;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by root on 21/4/16.
 */
public class BaseActivity extends AppCompatActivity {

    AsyncProgressDialog ad;
    private Toast toast;
    public Toolbar toolbar;
    TextView tv_title;
    ImageView ivDrawer, iv_menu, iv_search;
    private SharedPreferences sharedpreferences;




    public void bindToolbar() {
        ivDrawer = (ImageView) findViewById(R.id.ivDrawer);
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_menu = (ImageView) findViewById(R.id.iv_menu);
        iv_search = (ImageView) findViewById(R.id.iv_search);



    }

    public void setTitle(String titlea) {
        tv_title.setText(titlea + "");
    }

    public void showBack() {
        ivDrawer.setImageResource(R.drawable.ic_back);
        ivDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void showEmptyOnly() {
        iv_menu.setVisibility(View.GONE);
        iv_search.setVisibility(View.GONE);

    }

    public void showMenuOnly() {
        iv_menu.setImageResource(R.drawable.ic_settings);

    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        toast = Toast.makeText(getActivity(), "", Toast.LENGTH_LONG);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    public void showToast(final String text, final int duration) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                toast.setText(text);
                toast.setDuration(duration);
                toast.show();
            }
        });
    }

    public void showToast(final int text, final int duration) {

        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                toast.setText(getResources().getString(text));
                toast.setDuration(duration);
                toast.show();
            }
        });

    }

    public void showProgress(String msg) {

        try {
            if (ad != null && ad.isShowing()) {
                return;
            }

            ad = AsyncProgressDialog.getInstant(getActivity());
            ad.show(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public BaseActivity getActivity() {
        return this;
    }

    public void dismissProgress() {
        try {
            if (ad != null) {
                ad.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SharedPreferences getPreferences() {
        sharedpreferences = getSharedPreferences(
                Constants.MyPREFERENCES, Context.MODE_PRIVATE);
        return sharedpreferences;
    }

    public void hideKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    public boolean isMyProfileRegister() {
        String response = getPreferences().getString(RequestParamsUtils.USERPROFILE, "");
        if (response != null && !response.equals("")) {
            Profile pt = new Gson().fromJson(
                    response, new TypeToken<Profile>() {
                    }.getType());

            if (pt.st == 1) {
                Constants.PROFILE = pt;
            }
            return true;
        } else {
            Toast.makeText(this, "Please Login Or Register!", Toast.LENGTH_LONG).show();
            return false;
        }

    }

    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }



}
