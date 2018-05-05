package com.juny.cashiersystem.business.loginreg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.juny.cashiersystem.R;
import com.juny.cashiersystem.base.AbstractCSActivity;
import com.juny.cashiersystem.widget.TitleBarCommon;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 注册页面
 * Created by Junny on 2018/3/5.
 */

public class RegisterActivity extends AbstractCSActivity {

    @BindView(R.id.title_bar)
    TitleBarCommon mTitleBar;
    @BindView(R.id.register_name_edit)
    EditText mNameEdit;
    @BindView(R.id.register_psd_edit)
    EditText mPsdEdit;
    @BindView(R.id.register_psd_two_edit)
    EditText mPsdTwoEdit;
    @BindView(R.id.login_btn)
    Button mRegisterBtn;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, RegisterActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        // 初始化UI
        mTitleBar.setTitle("登 录");

        // 监听
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}
