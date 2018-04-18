package com.mtm.config;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.IComponent;

/**
 * @author MtmWp
 * @date 2018-3-20 13:26.
 */

public class BaseIComponent implements IComponent {
    @Override
    public String getName() {
        //组件的名称，调用此组件的方式：
        // CC.obtainBuilder("ComponentA")...build().callAsync()
        return "BaseIComponent";
    }

    /**
     * 组件被调用时的入口
     * 要确保每个逻辑分支都会调用到CC.sendCCResult，
     * 包括try-catch,if-else,switch-case-default,startActivity
     *
     * @param cc 组件调用对象，可从此对象中获取相关信息
     * @return true:将异步调用CC.sendCCResult(...),用于异步实现相关功能，例如：文件加载、网络请求等
     * false:会同步调用CC.sendCCResult(...),即在onCall方法return之前调用，否则将被视为不合法的实现
     */
    @Override
    public boolean onCall(CC cc) {
        String actionName = cc.getActionName();
        String callId = cc.getCallId();
        String moduleType = cc.getParamItem("moduleType");
        String data = cc.getParamItem("data");
        Context context = cc.getContext();
        Intent intent = new Intent();
        switch (actionName) {
            case "baseDetail":
//                intent.setClass(context, BaseDetailAct.class);
                break;
            default:
                break;
        }
        if (!(context instanceof Activity)) {
            //调用方没有设置context或app间组件跳转，context为application
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("callId", callId);
            intent.putExtra("data", data);
            intent.putExtra("moduleType", moduleType);
        }
        context.startActivity(intent);
        return true;
    }
}
