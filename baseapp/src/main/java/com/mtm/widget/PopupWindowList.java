package com.mtm.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mtm.baseapp.R;
import com.mtm.config.UIUtils;
import com.mtm.listener.OnPopListSuccessListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.INPUT_METHOD_SERVICE;


/**
 * ***    ***     **********   ***    ***
 * ****   ****    **********   ****   ****
 * *** *  * ***       ***      *** *  * ***
 * ***  * *  ***      ***      ***  * *  ***
 * ***   **   ***     ***      ***   **   ***
 * ******************************************************************
 * Created by MTM on 2017-8-10.
 * Description: PopupWindowList
 * Android:minSdkVersion: API
 * Author:mzp
 * Version:V1.0
 * ******************************************************************
 */
public class PopupWindowList {

    public static PopupWindow window;
    private static OnPopListSuccessListener onPopListSuccessListener;

    public static void setOnPopListSuccessListener(OnPopListSuccessListener onPopListSuccessListener) {
        PopupWindowList.onPopListSuccessListener = onPopListSuccessListener;
    }

    public static void close() {
        if (window != null) {
            window.dismiss();
            window = null;
        }
    }

    /**
     * 用于显示选择 地市 区县 街道 的窗体
     *
     * @param relyView 依赖那个View显示
     * @param title    窗体标题名称
     * @return List<RelativeLayout>
     * @autor
     */
    public static List<RelativeLayout> show3LineList(Context context,View relyView, String title) {
        View view = UIUtils.inflate(context, R.layout.layout_pop_3line_item);
        View mainLessColorView = view.findViewById(R.id.layout_3line_title);
        if (mainLessColorView != null) {
//            mainLessColorView.setBackgroundResource(ChameleonUtils.i().getMainLessColorId());
        }
        //设置title
        ((TextView) view.findViewById(R.id.title_bar_title_tv)).setText(title);
        //返回键
        view.findViewById(R.id.title_bar_back_rl).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onPopListSuccessListener != null) {
                    onPopListSuccessListener.onSuccess();
                }
                close();
            }
        });
        //设置头部按钮
        TextView mRightBtn = (TextView) view.findViewById(R.id.btn_query);
        mRightBtn.setVisibility(View.VISIBLE);
        mRightBtn.setText("完成");
        mRightBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPopListSuccessListener != null) {
                    onPopListSuccessListener.onSuccess();
                }
                close();
            }
        });
        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        // 这是响应返回键让弹出消失
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));

        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        window.showAtLocation(relyView, Gravity.BOTTOM, 0, 0);
        List<RelativeLayout> mRlList = new ArrayList<RelativeLayout>();
        mRlList.add((RelativeLayout) view.findViewById(R.id.layout_pop_3ling_left_rl));
        mRlList.add((RelativeLayout) view.findViewById(R.id.layout_pop_3ling_mid_rl));
        mRlList.add((RelativeLayout) view.findViewById(R.id.layout_pop_3ling_right_rl));
        return mRlList;
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 屏幕透明度0.0-1.0 1表示完全不透明
     */
    public void setBackgroundAlpha(float bgAlpha, Activity activity) {
        Window winManager = activity.getWindow();
        WindowManager.LayoutParams lp = winManager.getAttributes();
        lp.alpha = bgAlpha;
        winManager.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        winManager.setAttributes(lp);
    }

    /**
     * 模拟对话框
     *
     * @param con        Context
     * @param anyOneView 任意的view
     * @param title      对话框标题
     * @param content    对话框内容
     */
    public static TextView showPopDialog(Context con, View anyOneView, String title, String content) {
        View contentView = View.inflate(con, R.layout.dialog_pop_info, null);
        TextView progressTitle = (TextView) contentView.findViewById(R.id.pop_pro_title);
        TextView contentTv = (TextView) contentView.findViewById(R.id.pop_pro_content);
        Button btn_text = (Button) contentView.findViewById(R.id.btn_text);
        progressTitle.setText(title);
        contentTv.setText(content);

        window = new PopupWindow(contentView, -1, -1);
        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);

        // 设置可以使用焦点
        window.setFocusable(false);
        // 设置popWindow点击外部 不可以被关闭
//        pw.setOutsideTouchable(false);
        // 设置一个popupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#50000000")));
        window.showAtLocation(anyOneView, Gravity.CENTER, 0, 0);

        //关闭popWindow
        btn_text.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
            }
        });
        return contentTv;
    }
}