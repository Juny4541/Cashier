package com.juny.cashiersystem.business.loginreg;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.juny.cashiersystem.R;
import com.juny.cashiersystem.base.AbstractCSActivity;
import com.juny.cashiersystem.business.homepage.main.HomeActivity;
import com.juny.cashiersystem.widget.TitleBarCommon;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 登录页面
 */

public class LoginActivity extends AbstractCSActivity {

    @BindView(R.id.title_bar)
    TitleBarCommon mTitleBar;
    @BindView(R.id.login_name_edit)
    EditText mNameEdit;
    @BindView(R.id.login_psd_edit)
    EditText mPsdEdit;
    @BindView(R.id.login_btn)
    Button mLoginBtn;
    @BindView(R.id.login_register_text)
    TextView mRegisterText;
    @BindView(R.id.login_lose_psd_text)
    TextView mLosePsdText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        // 初始化UI
        mTitleBar.setTitle("登 录");

        // 监听
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeActivity.startActivity(LoginActivity.this);
            }
        });

        mRegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity.startActivity(LoginActivity.this);
            }
        });
    }
}
