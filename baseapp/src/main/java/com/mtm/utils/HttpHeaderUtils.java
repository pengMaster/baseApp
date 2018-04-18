package com.mtm.utils;

import android.content.Context;

import com.jess.arms.utils.DeviceUtils;


import java.util.HashMap;
import java.util.Map;


/**
 * ******************************************************************
 * Created by
 * MTM on 2017/6/1.
 * Description: 获取手机状态的工具类
 * Android:minSdkVersion:
 * API Author:wp
 * Version:V1.0
 * ******************************************************************
 */
public class HttpHeaderUtils {
    /**
     * 接口入口中的分支方法
     */
    public final static String DOWNLOAD_PRO_RANDOM_EXDOC = "DOWNLOAD_PRO_RANDOM_EXDOC";

    private static HttpHeaderUtils mInstance;

    /**
     * 传header参数
     */
    private static final String IMEI = "imei";
    private static final String VERSION_CODE = "versionCode";
    private static final String VERSION_NAME = "versionName";
    private static final String SYSTEM_MODEL = "systemModel";
    private static final String SYSTEM_VERSION = "systemVersion";
    private static final String METHOD = "method";

    private static final int IMEI_INT = 0;
    private static final int VERSION_CODE_INT = 1;
    private static final int VERSION_NAME_INT = 2;
    private static final int SYSTEM_MODEL_INT = 3;
    private static final int SYSTEM_VERSION_INT = 4;
    private static final int METHOD_INT = 5;

    private static Context mAppContext;

    /**
     * header 传参方法
     *
     * @return map
     */
    public Map<String, String> addHeaders() {
        return getHeaderMap("");
    }

    /**
     * header 传参方法
     *
     * @return map
     */
    public Map<String, String> addHeaders(String method) {
        return getHeaderMap(method);
    }


    public static HttpHeaderUtils i(Context context) {
        if (mInstance == null) {
            mAppContext =context;
            mInstance = new HttpHeaderUtils();
            initHeaderMap(mAppContext);
        }
        return mInstance;
    }

    private static Map<String, String> mHeaderMap = new HashMap<>();
    private static String[] mHeaderStrArray = {};

    private static void initHeaderMap(Context context) {
        mHeaderMap = new HashMap<>(5);
        mHeaderMap.put(IMEI, Md5Util.getMD5Str(getDeviceId(context)));
        // BaseApplication.getApplication() 自己实体类中获取上下文的方法
        // 软件内部版本号
        mHeaderMap.put(VERSION_CODE, DeviceUtils.getVersionCode(mAppContext) + "");
        // 软件外部版本号
        mHeaderMap.put(VERSION_NAME, DeviceUtils.getVersionName(mAppContext));
        // 手机型号
        mHeaderMap.put(SYSTEM_MODEL, getSystemModel());
        // 系统版本
        mHeaderMap.put(SYSTEM_VERSION, HttpHeaderUtils.getSystemVersion());
        mHeaderStrArray = new String[]{
                IMEI + ":" + mHeaderMap.get(IMEI)
                , VERSION_CODE + ":" + mHeaderMap.get(VERSION_CODE)
                , VERSION_NAME + ":" + mHeaderMap.get(VERSION_NAME)
                , SYSTEM_MODEL + ":" + mHeaderMap.get(SYSTEM_MODEL)
                , SYSTEM_VERSION + ":" + mHeaderMap.get(SYSTEM_VERSION)
                , ""
        };
    }

    public Map<String, String> getHeaderMap(String method) {
        if (mHeaderMap.get(METHOD) == null) {
            mHeaderMap.put(METHOD, method);// 要访问的方法
        } else {
            mHeaderMap.remove(METHOD);
            mHeaderMap.put(METHOD, method);// 要访问的方法
        }
        return mHeaderMap;
    }

    public static String[] getHeaderStrArray(String method) {
        mHeaderStrArray[METHOD_INT] = METHOD + ":" + method;
        return mHeaderStrArray;
    }

    public static String getRetorfitHead(int key) {
        return mHeaderStrArray[key];
    }

    public static String getHeadMethod(String method) {
        return METHOD + ":" + method;
    }

    public static String getDeviceId(Context context) {
        return DeviceUtils.getIMEI(context);
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

}
