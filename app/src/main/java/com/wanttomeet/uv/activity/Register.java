package com.wanttomeet.uv.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wanttomeet.uv.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Register extends AppCompatActivity {

    @Bind(R.id.tv_caldender)
    TextView tv_caldender;

    @Bind(R.id.tv_female)
    TextView tv_female;

    @Bind(R.id.tv_male)
    TextView tv_male;

    @Bind(R.id.etName)
    EditText etName;

    @Bind(R.id.etMobileNo)
    EditText etMobileNo;

    @Bind(R.id.etEmailId)
    EditText etEmailId;

    @Bind(R.id.etPassword)
    EditText etPassword;

    @Bind(R.id.etCity)
    EditText etCity;

    @Bind(R.id.etState)
    EditText etState;

    @Bind(R.id.etCountry)
    EditText etCountry;

    private int mYear;
    private int mMonth;
    private int mDay;
    private String gender;

    private String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.tv_next)
    public void tv_next() {
        isValidData();
    }

    @OnClick(R.id.tv_male)
    public void tv_male() {
        tv_male.setBackgroundColor(Color.parseColor("#2E3F51"));
        tv_female.setBackgroundColor(Color.parseColor("#b6b5b5"));
        tv_male.setTextColor(Color.parseColor("#FFFFFF"));
        tv_female.setTextColor(Color.parseColor("#2E3F51"));
        gender = "Male";

    }

    @OnClick(R.id.tv_female)
    public void tv_female() {
        tv_female.setBackgroundColor(Color.parseColor("#2E3F51"));
        tv_male.setBackgroundColor(Color.parseColor("#b6b5b5"));
        tv_female.setTextColor(Color.parseColor("#FFFFFF"));
        tv_male.setTextColor(Color.parseColor("#2E3F51"));
        gender = "Female";
    }

    @OnClick(R.id.tv_caldender)
    public void tv_caldender() {
        Calendar mcurrentDate = Calendar.getInstance();

        mYear = mcurrentDate.get(Calendar.YEAR);
        mMonth = mcurrentDate.get(Calendar.MONTH);
        mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker = new DatePickerDialog(Register.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                // TODO Auto-generated method stub

                tv_caldender.setText(selectedyear + "-" + selectedmonth + "-" + selectedday);
                tv_caldender.setTextColor(Color.BLACK);
            }
        }, mYear, mMonth, mDay);
        mDatePicker.setTitle("Select date");
        String string_date = "12-December-1920";

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MMM-dd");
        long milliseconds = 0;
        try {
            Date d = f.parse(string_date);
            milliseconds = d.getTime();
        } catch (ParseException e) {
            Log.e("ParseException", e.getMessage());
        }
        mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
        if (milliseconds != 0) {
            mDatePicker.getDatePicker().setMinDate(milliseconds);
        }


        mDatePicker.show();
    }

    @OnClick(R.id.tv_skip)
    public void tv_skip() {
        Intent main = new Intent(Register.this, MainActivity.class);
        startActivity(main);
        finish();
    }

    private void isValidData() {

        String varemail=etEmailId.getText().toString().trim();

        if (etName.getText().toString().length() == 0) {
            etName.setError("Enter Name!");
        } else if (etMobileNo.getText().toString().length() == 0) {
            etMobileNo.setError("Enter MobileNo!");
        } else if (gender == null) {
            Toast.makeText(this, "Select Gender .....!", Toast.LENGTH_LONG).show();
        } else if (tv_caldender.getText().toString().length() == 0) {
            tv_caldender.setText("Select Your Birthdate!");
        } else if (etEmailId.getText().toString().length() == 0) {
            etEmailId.setText("Enter EmailId!");
        }    else if (!varemail.matches(EMAIL_PATTERN)) {
            etEmailId.setError("Invalid Email");
            etEmailId.requestFocus();
        } else if (etPassword.getText().toString().equals("")) {
            etPassword.setError("Enter Password!");
        } else {
            Intent regtwo = new Intent(Register.this, RegisterTwo.class);
            Bundle bundle = new Bundle();
            bundle.putString("name", etName.getText().toString());
            bundle.putString("mobileno", etMobileNo.getText().toString());
            bundle.putString("gender", gender);
            bundle.putString("dob", tv_caldender.getText().toString());
            bundle.putString("email", etEmailId.getText().toString());
            bundle.putString("password", etPassword.getText().toString());
            bundle.putString("city", etCity.getText().toString());
            bundle.putString("state", etState.getText().toString());
            bundle.putString("country", etCountry.getText().toString());
            regtwo.putExtras(bundle);
            startActivity(regtwo);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
