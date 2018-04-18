package com.mtm.user;


import android.content.Context;

import com.google.gson.Gson;
import com.mtm.utils.SPUtils;


/**
 * class
 * Description
 *
 * @author yangcc
 * @date 2017/11/24
 */
public class UserModel {
    private static UserModel userModel;
    private static Context mAppContext;
    private static Gson gson;

    private UserModel() {
    }

    public static UserModel i(Context context) {
        if (userModel == null) {
            userModel = new UserModel();
            mAppContext = context;
            gson = new Gson();
        }
        return userModel;
    }


    public User getUser1() {
        String user1 = SPUtils.i(mAppContext).getStringData("user1");
        return gson.fromJson(user1, User.class);
    }

    public User getUser2() {
        String user2 = SPUtils.i(mAppContext).getStringData("user2");
        return gson.fromJson(user2, User.class);
    }

    /**
     * 保存用户
     * @param user
     */
    public void saveUser(User user,int sort){
        String userGson = gson.toJson(user);
        String key = "user"+sort;
        SPUtils.i(mAppContext).saveStringData(key, userGson);
    }

    /**
     * 删除用户
     * @param user
     */
    public void deleteUser(User user,int sort){
        String key = "user"+sort;
        SPUtils.i(mAppContext).saveStringData(key,"");
    }
    /**
     * 获取用户级别
     *
     * @return用户级别
     */
    public String getLevelCode(){
        String userLevel = getUser1().getUserLevel();
        String busPcode="";
        switch (userLevel) {
            case "1":
                busPcode =  getUser1().getCountryCode();
                break;
            case "2":
                busPcode =  getUser1().getProvinceCode();
                break;
            case "3":
                busPcode =  getUser1().getCityCode();
                break;
            case "4":
                busPcode =  getUser1().getDistrictCode();
                break;
            default:
                break;

        }
        return busPcode;
    }

    /**
     * 监督员1登录:true
     *
     * @return
     */
    public boolean isLogin(){
        if (null == getUser1()) {
            return false;
        }
        return true;
    }

}
