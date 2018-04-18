package com.mtm.listener;

/**
 * Created by MTM on 2017/10/27.
 * description 用于时间选择工具类的回调
 *
 * @author mzp
 * @version V1.0
 */

public interface ITimeChooseListener {
    /**
     * 完成
     * @param date yyyy-mm-dd
     */
    void onChoose(String date);

}