package com.meihua.advanceapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.meihua.advanceapp.app.BaseApplication;
import com.meihua.advanceapp.presenter.BasePresenter;

import butterknife.Unbinder;

/**
 * Created by : lgz
 * Time : 2017/4/17.
 * Desc :
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    private static BaseActivity mForegroundActivity;
    protected Unbinder unbinder;
    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BaseApplication.getInstance().registerActivity(this);
        initView();
    }

    protected abstract void initView() ;

    @Override
    protected void onResume() {
        super.onResume();
        this.mForegroundActivity = this;
        if (unbinder == null) {
            unbinder.unbind();
        }
        mPresenter = null;

    }

    //activity销毁的时候，移除set中activity
    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApplication.getInstance().unregisterActivity(this);

    }

    //获取当前显示的activity的上下文对象
    public static BaseActivity getForegroundActivity(){
        return mForegroundActivity;
    }

}
