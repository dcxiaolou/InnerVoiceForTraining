package com.fingertrip.innervoicefortraining.splash;
/**
 * Splash模块中的View
 * 管理启动模块中的视图界面
 */
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import com.fingertrip.innervoicefortraining.R;
import com.fingertrip.innervoicefortraining.base.BaseActivity;
import com.fingertrip.innervoicefortraining.guide.GuideActivity;
import com.fingertrip.innervoicefortraining.home.HomeActivity;
import com.fingertrip.innervoicefortraining.login.LoginActivity;


public class SplashActivity extends BaseActivity implements SplashContract.View {

    private TextView tvSplash;
    private TimeTask mTimeTask;
    private SplashContract.Presenter mPresenter;

    @Override
    protected int setLayout() {
        return R.layout.activity_splash;
    }
    @Override
    protected void init(){
        tvSplash = findViewById(R.id.tvSplash);
        mTimeTask = new TimeTask();
        mPresenter = new SplashPresenter(this);
        mTimeTask.execute();
    }

    @Override
    protected void setListener() {
        tvSplash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //把异步任务标记为停止
                mTimeTask.cancel(true);
                mPresenter.isFirstRun();
            }
        });
    }

    @Override
    public void toGuideView() {
        toNewView(GuideActivity.class);
        finish();
    }

    @Override
    public void toLoginView() {
        toNewView(LoginActivity.class);
        finish();
    }

    @Override
    public void toHomeView() {
        toNewView(HomeActivity.class);
        finish();
    }

    /**
     * 异步任务类
     * 第一个泛型，限定任务执行时是否需要传入外部数据
     * 第二个泛型，限定任务执行过程时发布任务进度的数据类型
     * 第三个泛型，限定任务执行完成时，返回的数据类型
     */
    private class TimeTask extends AsyncTask<Void, String, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            for(int i = 3; i >= 0; i--){
                //判断异步任务是否标记为结束
                if(isCancelled()){
                    break;
                }
                try{
                    //发布进度
                    publishProgress(i+"");
                    //线程休眠1秒
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            tvSplash.setText(values[0] + "s 跳过");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mPresenter.isFirstRun();
        }
    }
}
