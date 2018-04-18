package com.mtm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mtm.baseapp.R;
import com.mtm.bean.PopListBean;
import com.mtm.config.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MtmWp
 * @date 2018-4-12 09:06.
 */

public class RegionAdapter extends BaseAdapter {
    private List<PopListBean>  list;
    private Context context;

    public RegionAdapter( Context context,List<PopListBean> list) {
        super();
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
         view =  UIUtils.inflate(context, R.layout.item_pop_select);
        TextView mContentTv = (TextView) view.findViewById(R.id.type);
        mContentTv.setText(list.get(i).getName());
        if(list.get(i).isSelect()){
            view.setBackgroundResource(R.color.main_bg);
        }else{
            view.setBackgroundResource(R.color.white);
        }
        return view;
    }
    private class RegionHolder{
      private TextView mTitleTv,mInfoTv;
    }
}
