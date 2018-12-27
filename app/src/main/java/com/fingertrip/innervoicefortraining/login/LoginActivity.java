package com.fingertrip.innervoicefortraining.login;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fingertrip.innervoicefortraining.R;
import com.fingertrip.innervoicefortraining.base.BaseActivity;
import com.fingertrip.innervoicefortraining.home.HomeActivity;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    private LoginContract.Presenter mPresenter;
    private ImageView imgLoginIcon;
    private TextView tvLoginTitle, tvSignUp;
    private EditText edtUsername, edtPassword, edtConfirm;
    private Button btnOk;
    private ConstraintLayout constraintLayout;
    private ConstraintSet loginSet, signUpSet;
    private boolean login = true;

    private ProgressBar progressBar;

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        //初始化所有属性
        mPresenter = new LoginPresenter(this);
        imgLoginIcon = findViewById(R.id.imgLoginIcon);

        imgLoginIcon.setImageResource(R.drawable.login);

        tvLoginTitle = findViewById(R.id.tvLoginTitle);
        tvSignUp = findViewById(R.id.tvSignUp);

        tvLoginTitle.setTextSize(30);
        tvSignUp.setTextSize(15);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirm = findViewById(R.id.edtConfirm);
        loginSet = new ConstraintSet();
        btnOk = findViewById(R.id.btnOk);
        constraintLayout = findViewById(R.id.constraintLayout);

        progressBar = findViewById(R.id.progressBar);

        signUpSet = new ConstraintSet();
        loginSet.clone(constraintLayout); //把布局文件中的约束信息存储在约束集中
        signUpSet.clone(getApplicationContext(), R.layout.activity_sign_up);
    }

    @Override
    protected void setListener() {
        //设置监听器
        tvLoginTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!login) {
                    //约束布局实施动画
                    TransitionManager.beginDelayedTransition(constraintLayout);
                    tvLoginTitle.setTextSize(30);
                    tvSignUp.setTextSize(15);
                    btnOk.setText("登录");
                    imgLoginIcon.setImageResource(R.drawable.login);
                    loginSet.applyTo(constraintLayout); //实施对应的约束动画
                    login = !login;
                }
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login) {
                    TransitionManager.beginDelayedTransition(constraintLayout);
                    tvLoginTitle.setTextSize(15);
                    tvSignUp.setTextSize(30);
                    btnOk.setText("注册");
                    imgLoginIcon.setImageResource(R.drawable.signup);
                    signUpSet.applyTo(constraintLayout);
                    login = !login;
                }
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtUsername.getText().toString().trim();
                String pw = edtPassword.getText().toString().trim();
                String pc = edtConfirm.getText().toString().trim();
                if (login) {
                    mPresenter.login(name, pw);
                } else {
                    if (pw.equals(pc)) {
                        mPresenter.signUp(name, pw);
                    } else {
                        showError("密码不一致");
                    }
                }
            }
        });
    }

    @Override
    public void toHomeView() {
        //跳转到主界面
        toNewView(HomeActivity.class);
        finish();
    }

    @Override
    public void showError(String message) {
        //显示错误信息
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingView() {
        //显示加载界面
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingView() {
        //隐藏加载界面
        progressBar.setVisibility(View.GONE);
    }
}
