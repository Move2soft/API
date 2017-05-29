package com.wanttomeet.uv.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.wanttomeet.uv.Listener.ResponseListener;
import com.wanttomeet.uv.R;
import com.wanttomeet.uv.model.Profile;
import com.wanttomeet.uv.utils.AsyncHttpRequest;
import com.wanttomeet.uv.utils.BaseActivity;
import com.wanttomeet.uv.utils.Constants;
import com.wanttomeet.uv.utils.Debug;
import com.wanttomeet.uv.utils.RequestParamsUtils;
import com.wanttomeet.uv.utils.ResponseHandler;
import com.wanttomeet.uv.utils.URLs;

import butterknife.ButterKnife;

public class Login extends BaseActivity implements ResponseListener {

    LinearLayout layout_new_register;
    TextView tv_login_here;



    EditText et_email, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        bind();
    }

    public void bind() {

        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);

        layout_new_register = (LinearLayout) findViewById(R.id.layout_new_register);
        layout_new_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reg = new Intent(Login.this, Register.class);
                startActivity(reg);
            }
        });

        tv_login_here = (TextView) findViewById(R.id.tv_login_here);
        tv_login_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isValidData();
            }
        });
    }

    private void isValidData() {



        if (et_email.getText().toString().length() == 0) {
            et_email.setError("Enter Email OR Mobile No!");
        } else if (et_password.getText().toString().length() == 0) {
            et_password.setError("Enter Password!");
        } else {
            doLogin();
        }
    }

    private void doLogin() {
        try {
            showProgress("");
            RequestParams params = new RequestParams();

            params.put(RequestParamsUtils.LOGIN_MOBILE_KEY, et_email.getText().toString());
            params.put(RequestParamsUtils.PASS_LOGIN, et_password.getText().toString());

            Debug.e("dologin", params.toString());

            AsyncHttpClient clientPhotos = AsyncHttpRequest.newRequest();

           clientPhotos.post(new URLs().LOGIN, params, new ResponseHandler(Login.this, this));

        } catch (Exception e) {
            Debug.e("doRegistration Exception", e.getMessage());
        }
    }


    @Override
    public void onResponse(String response) {
        dismissProgress();
        if (response != null && response.length() > 0) {
            try {
                dismissProgress();
                Log.e("doRegistration", response);

                if (response != null && response.length() > 0) {

                    Profile pt = new Gson().fromJson(
                            response, new TypeToken<Profile>() {
                            }.getType());

                    if (pt.st == 1) {
                        Constants.PROFILE = pt;
                        SharedPreferences.Editor editor = getPreferences().edit();
                        editor.putString(RequestParamsUtils.USERPROFILE, response);
                        editor.commit();

                        Toast.makeText(getActivity(), "Login Successfully...", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getActivity(), "Invild Data !...Try Again...!", Toast.LENGTH_LONG).show();
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
