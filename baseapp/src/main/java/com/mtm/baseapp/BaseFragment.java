package com.mtm.baseapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;


/**
 * BaseFragment
 *
 * @author yangcc
 * @date 2017/11/15
 */
public abstract class BaseFragment extends android.support.v4.app.Fragment {
    protected String TAG = this.getClass().getSimpleName();
    public View view;
    public Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = initView(inflater);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        initData(savedInstanceState);
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 当前Fragment 退出时,取消所有网络请求
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        //TODO 需要完成:    当前Fragment 退出时,取消所有网络请求
    }

    /**
     * 子类必须实现初始化界面
     */
    public abstract View initView(LayoutInflater inflater);

    /**
     * 子类实现初始化数据
     */
    public abstract void initData(Bundle savedInstanceState);


}
