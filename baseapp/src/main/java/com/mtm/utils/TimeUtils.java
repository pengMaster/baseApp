package com.mtm.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.text.TextUtils;
import android.widget.DatePicker;


import com.mtm.listener.ITimeChooseListener;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by MTM on 2017/10/27.
 * description 时间相关
 *
 * @author mzp
 * @version V1.0
 */

public class TimeUtils {

    public static TimeUtils build() {
        return new TimeUtils();
    }

    private ITimeChooseListener timeChooseListener;
    private DatePickerDialog datePickerDialog;

    /**
     * 用于调用系统的时间选择功能
     *
     * @param context context
     * @return TimeUtils
     */
    public TimeUtils initChooseDate(Context context) {
        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if (timeChooseListener != null) {
                    timeChooseListener.onChoose(year + "-" + (monthOfYear + 1) + "-" + (dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth));
                }
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        return this;
    }

    public TimeUtils chooseListener(ITimeChooseListener timeChooseListener) {
        this.timeChooseListener = timeChooseListener;
        return this;
    }

    public TimeUtils minDate(String minDate) {
        if (datePickerDialog != null && !TextUtils.isEmpty(minDate)) {
            DatePicker datePicker = datePickerDialog.getDatePicker();
            datePicker.setMinDate(stringToLong(minDate));
        }
        return this;
    }

    public DatePickerDialog showChooseDate() {
        if (datePickerDialog != null) {
            datePickerDialog.show();
        }
        return datePickerDialog;
    }

    public static long stringToLong(String strTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = format.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
        return date.getTime();
    }

    public static String getYear2Str() {
        return getYear() + "";
    }

    public static String getLastYear() {
        return (getYear() - 1) + "";
    }

    public static int getLastYearInt() {
        return getYear() - 1;
    }


    public static String dateToStr(Date timValue, int style1, int style2) {
        DateFormat df = DateFormat.getDateTimeInstance(style1, style2, new Locale("zh", "CN"));
        return df.format(timValue);
    }

    public static String dateToStr(Date timValue) {
        return dateToStr(timValue, 0, 3);
    }

    public static String dateToStr(int style1, int style2) {
        Timestamp timValue = getCurrentDatetime();
        return dateToStr(timValue, style1, style2);
    }

    public static String dateToStr(Date date, String dateFormat) {
        String rtn = null;
        SimpleDateFormat fmtDate = new SimpleDateFormat(dateFormat, new Locale("zh", "CN"));
        rtn = fmtDate.format(date);
        return rtn;
    }

    public static String dateToStr(String dateFormat) {
        return dateToStr(getCurrentDatetime(), dateFormat);
    }

    public static String dateToStr() {
        return dateToStr(0, 3);
    }

    public static Date strToDate(String str) throws ParseException {
        DateFormat df = DateFormat.getDateTimeInstance(2, 2, new Locale("zh", "CN"));
        Date date = df.parse(str);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return new Date(calendar.getTimeInMillis());
    }

    public static String dateStrToShow(String str) throws ParseException {
        return dateToStr(strToDate(str));
    }

    public static Date convertDatetime(Date definitionDatetime) throws ParseException {
        String currStr = dateToStr(getCurrentDatetime(), "yyyy-MM-dd");
        String oldStr = dateToStr(definitionDatetime, "HH:mm:ss");
        return strToDate(currStr.concat(" ").concat(oldStr));
    }

    public static long getSpaceTime(Date datetime) throws ParseException {
        return convertDatetime(datetime).getTime() - getCurrentDatetime().getTime();
    }

    public static Timestamp getCurrentDatetime() {
        Calendar c = Calendar.getInstance();
        return new Timestamp(c.getTimeInMillis());
    }

    public static String secondConvTime(float sec) {
        int blurSec = Math.round(sec);
        int resSec = blurSec % 60;
        blurSec -= resSec;
        int minute = blurSec / 60 % 60;
        blurSec -= minute;
        int hour = blurSec / 3600;
        return (hour < 10 ? "0" + hour : (Integer.valueOf(hour)).toString()) + ":" + (minute < 10 ? "0" + minute : (Integer.valueOf(minute)).toString()) + ":" + (resSec < 10 ? "0" + resSec : (Integer.valueOf(resSec)).toString());
    }

    public static String addTime(String datetimeStr, String timeStr) {
        String[] datetime = datetimeStr.split(":");
        String[] date = datetime[0].split("-");
        String taskYear = date[0];
        String taskMonth = date[1];
        String taskDate = date[2].split(" ")[0];
        String taskHour = date[2].split(" ")[1];
        String taskMinute = datetime[1];
        String taskSecond = datetime[2];
        String[] time = timeStr.split(":");
        String hour = time[0];
        String minute = time[1];
        String second = time[2];
        Calendar c = Calendar.getInstance(new Locale("zh", "CN"));
        c.set(Integer.parseInt(taskYear), Integer.parseInt(taskMonth), Integer.parseInt(taskDate), Integer.parseInt(taskHour), Integer.parseInt(taskMinute), Integer.parseInt(taskSecond));
        c.add(13, Integer.parseInt(second));
        c.add(12, Integer.parseInt(minute));
        c.add(10, Integer.parseInt(hour));
        c.add(2, -1);
        return dateToStr(new Date(c.getTimeInMillis()), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 年月日时分秒
     *
     * @return 2017-11-28 13:31:38
     */
    public static String getYYYYMMDD_HHMMSS() {
        return getYYYY_MM_DD() + " " + getHH_MM_SS();
    }

    /**
     * 年月日时分秒 - 连起来
     *
     * @return 20171128133138
     */
    public static String getYYYYMMDD_HHMMSS_CONNECT() {
        return getYYYYMMDD() + getHHMMSS();
    }

    public static Date getDate(int field, int amount) {
        Calendar c = Calendar.getInstance();
        c.add(field, amount);
        return new Date(c.getTimeInMillis());
    }

    /**
     * 年月日
     *
     * @return 2017-11-28
     */
    public static String getYYYY_MM_DD() {
        Calendar c = Calendar.getInstance(new Locale("zh", "CN"));
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        return year + "-" + (month >= 10 ? "" + month : "0" + month) + "-" + (day >= 10 ? "" + day : "0" + day);
    }

    /**
     * 年月日
     *
     * @return 20171108
     */
    public static String getYYYYMMDD() {
        Calendar c = Calendar.getInstance(new Locale("zh", "CN"));
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        return year + (month >= 10 ? "" + month : "0" + month) + (day >= 10 ? "" + day : "0" + day);
    }

    public static String getYYYYMMDD(int field, int amount) {
        Calendar c = Calendar.getInstance(new Locale("zh", "CN"));
        c.add(field, amount);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        return year + (month >= 10 ? "" + month : "0" + month) + (day >= 10 ? "" + day : "0" + day);
    }

    /**
     * 时分秒
     *
     * @return 13:31:38
     */
    public static String getHH_MM_SS() {
        Calendar c = Calendar.getInstance(new Locale("zh", "CN"));
        int hour = c.get(11);
        int minute = c.get(12);
        int second = c.get(13);
        return (hour >= 10 ? "" + hour : "0" + hour) + ":" + (minute >= 10 ? "" + minute : "0" + minute) + ":" + (second >= 10 ? "" + second : "0" + second);
    }

    /**
     * 时分秒
     *
     * @return 133138
     */
    public static String getHHMMSS() {
        Calendar c = Calendar.getInstance(new Locale("zh", "CN"));
        int hour = c.get(11);
        int minute = c.get(12);
        int second = c.get(13);
        return (hour >= 10 ? "" + hour : "0" + hour) + (minute >= 10 ? "" + minute : "0" + minute) + (second >= 10 ? "" + second : "0" + second);
    }

    public static String getHHMMSS(int field, int amount) {
        Calendar c = Calendar.getInstance(new Locale("zh", "CN"));
        c.add(field, amount);
        int hour = c.get(11);
        int minute = c.get(12);
        int second = c.get(13);
        return (hour >= 10 ? "" + hour : "0" + hour) + ":" + (minute >= 10 ? "" + minute : "0" + minute) + ":" + (second >= 10 ? "" + second : "0" + second);
    }

    public static Calendar getCurrWeekInOneDay(int dayOfWeek) {
        GregorianCalendar gc = new GregorianCalendar(new Locale("zh", "CN"));
        gc.add(5, -gc.get(7) + 1 + dayOfWeek);
        return gc;
    }

    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getYear(int field, int amount) {
        Calendar c = Calendar.getInstance();
        c.add(field, amount);
        return c.get(Calendar.YEAR);
    }

    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    public static int getMonth(int field, int amount) {
        Calendar c = Calendar.getInstance();
        c.add(field, amount);
        return c.get(Calendar.MONTH) + 1;
    }

    public static int getDay(int field, int amount) {
        Calendar c = Calendar.getInstance();
        c.add(field, amount);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static int getDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public static int getWeek() {
        return Calendar.getInstance().get(7) - 1;
    }

    public static int getWeek(int field, int amount) {
        Calendar c = Calendar.getInstance();
        c.add(field, amount);
        return c.get(7) - 1;
    }

    public static int getHour() {
        return Calendar.getInstance().get(11);
    }

    public static int getMinute() {
        return Calendar.getInstance().get(12);
    }

    public static int getSecond() {
        return Calendar.getInstance().get(13);
    }


    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return long[] 返回值为：{天, 时, 分, 秒}
     */
    public static long[] getDistanceTimes(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long[] times = {day, hour, min, sec};
        return times;
    }

    public static String getDate(Date d, String s) {
        DateFormat df = new SimpleDateFormat(s);
        return df.format(d);
    }

    public static String getDateFormat(String datestr, String format) {
        DateFormat df = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = df.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return df.format(date);
    }

    public static Date getDate(String datestr, String format) {
        DateFormat df = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = df.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 验证许可证是否过期
     *
     * @param licenseEnd true 过期
     */
    public static boolean isOutLicense(String licenseEnd) {
        if (!CheckUtils.isEmpty(licenseEnd)) {
            String old = getDateFormat(licenseEnd.replace("-", ""), "yyyyMMdd");
            String now = getYYYYMMDD();
            return compare(old, now);
        }
        return false;
    }

    public static boolean compare(String old, String now) {
        int oldInt = 0;
        int nowInt = 0;
        try {
            oldInt = Integer.valueOf(old);
            nowInt = Integer.valueOf(now);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return oldInt < nowInt;
    }

    public static String getCurrentDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate =  new Date(System.currentTimeMillis());
        //获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

    public static String getCurrentTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date curDate =  new Date(System.currentTimeMillis());
        //获取当前时间
        String str = formatter.format(curDate);
        return str;
    }
}