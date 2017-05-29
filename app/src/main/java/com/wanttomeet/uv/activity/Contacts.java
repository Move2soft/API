package com.wanttomeet.uv.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wanttomeet.uv.R;
import com.wanttomeet.uv.utils.BaseActivity;
import com.wanttomeet.uv.utils.RequestParamsUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Contacts extends BaseActivity {


    LinearLayout layout_baground_shape;

    @Bind(R.id.iv_send_email)
    ImageView iv_send_email;

    @Bind(R.id.iv_call)
    ImageView iv_call;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        ButterKnife.bind(this);
        bindToolbar();
        showEmptyOnly();
        setTitle("Contact");
        showBack();
        bind();
    }

    public void bind() {
        layout_baground_shape = (LinearLayout) findViewById(R.id.layout_baground_shape);
        layout_baground_shape.setBackgroundResource(R.drawable.baground_contact1);

        iv_send_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Contacts.this, "SEND EMAIL(AHIR PARIVAAR)...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:ahir.parivaar.community@gmail.com")));



            }
        });

        iv_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String mobileno = "+919979958715";

                if (mobileno.equals("")) {
                    Toast.makeText(Contacts.this, "No Contact Number Found", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Contacts.this, "Call Now...", Toast.LENGTH_SHORT).show();
                    String no = mobileno.toString().trim();
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + no));
                    if (ActivityCompat.checkSelfPermission(Contacts.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        // ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        // public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        // int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}