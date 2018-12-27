package com.fingertrip.innervoicefortraining.guide;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fingertrip.innervoicefortraining.R;
import com.fingertrip.innervoicefortraining.base.BaseActivity;
import com.fingertrip.innervoicefortraining.login.LoginActivity;

import java.util.ArrayList;

/**
 * 管理新手引导模块中的视图界面
 */
public class GuideActivity extends BaseActivity implements GuideContract.View {
    private GuideContract.Presenter mPresenter;
    private Button btnGuideStart;
    private ViewPager vp;
    private ArrayList<View> mViews;
    @Override
    protected int setLayout() {
        return R.layout.activity_guide;
    }

    @Override
    protected void init() {
        mPresenter = new GuidePresenter(this);
        vp=findViewById(R.id.vp);
        mViews=new ArrayList<>();
        View guide01=LayoutInflater.from(getApplicationContext()).inflate(R.layout.guide01,vp,false);
        View guide02=LayoutInflater.from(getApplicationContext()).inflate(R.layout.guide02,vp,false);
        View guide03=LayoutInflater.from(getApplicationContext()).inflate(R.layout.guide03,vp,false);
        View guide04=LayoutInflater.from(getApplicationContext()).inflate(R.layout.guide04,vp,false);
        btnGuideStart=guide04.findViewById(R.id.btnGuideStart);
        mViews.add(guide01);
        mViews.add(guide02);
        mViews.add(guide03);
        mViews.add(guide04);
        vp.setAdapter(new GuideAdapter());
    }

    @Override
    protected void setListener() {
        btnGuideStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.saveFirstRunState();
                finish();
            }
        });
    }

    @Override
    public void toLoginView() {
        toNewView(LoginActivity.class);
    }

    private class GuideAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(mViews.get(position));
            return mViews.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(mViews.get(position));
        }
    }
}
