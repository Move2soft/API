package com.wanttomeet.uv.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.wanttomeet.uv.R;
import com.wanttomeet.uv.utils.BaseActivity;

import butterknife.ButterKnife;

public class Branch extends BaseActivity {

    ImageView iv_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);
        ButterKnife.bind(this);
        bindToolbar();
        setTitle("Demo");
        showMenuOnly();
        showBack();

        iv_menu= (ImageView) findViewById(R.id.iv_menu);
        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Branch.this, "Menu Cilck", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
