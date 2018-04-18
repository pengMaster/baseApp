package com.mtm.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by mzp on 2014/6/8.
 * 一些基本使用
 *
 * @author mzp
 * @version 1.0
 */
public class StringUtils {
//    public final static String UTF_8 = "utf-8";

    /**
     * 返回一个高亮spannable
     *
     * @param content 文本内容
     * @param color   高亮颜色
     * @param start   起始位置
     * @param end     结束位置
     * @return 高亮spannable
     */
    public static CharSequence getHighLightText(String content, int color, int start, int end) {
        if (TextUtils.isEmpty(content)) {
            return "";
        }
        start = start >= 0 ? start : 0;
        end = end <= content.length() ? end : content.length();
        SpannableString spannable = new SpannableString(content);
        CharacterStyle span = new ForegroundColorSpan(color);
        spannable.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    /**
     * 格式化文件大小，不保留末尾的0
     */
    public static String formatFileSize(long len) {
        return formatFileSize(len, false);
    }

    /**
     * 格式化文件大小，保留末尾的0，达到长度一致
     */
    public static String formatFileSize(long len, boolean keepZero) {
        String size;
        DecimalFormat formatKeepTwoZero = new DecimalFormat("#.00");
        DecimalFormat formatKeepOneZero = new DecimalFormat("#.0");
        if (len < 1024) {
            size = String.valueOf(len + "B");
        } else if (len < 10 * 1024) {
            // [0, 10KB)，保留两位小数
            size = String.valueOf(len * 100 / 1024 / (float) 100) + "KB";
        } else if (len < 100 * 1024) {
            // [10KB, 100KB)，保留一位小数
            size = String.valueOf(len * 10 / 1024 / (float) 10) + "KB";
        } else if (len < 1024 * 1024) {
            // [100KB, 1MB)，个位四舍五入
            size = String.valueOf(len / 1024) + "KB";
        } else if (len < 10 * 1024 * 1024) {
            // [1MB, 10MB)，保留两位小数
            if (keepZero) {
                size = String.valueOf(formatKeepTwoZero.format(len * 100 / 1024 / 1024 / (float) 100)) + "MB";
            } else {
                size = String.valueOf(len * 100 / 1024 / 1024 / (float) 100) + "MB";
            }
        } else if (len < 100 * 1024 * 1024) {
            // [10MB, 100MB)，保留一位小数
            if (keepZero) {
                size = String.valueOf(formatKeepOneZero.format(len * 10 / 1024 / 1024 / (float) 10)) + "MB";
            } else {
                size = String.valueOf(len * 10 / 1024 / 1024 / (float) 10) + "MB";
            }
        } else if (len < 1024 * 1024 * 1024) {
            // [100MB, 1GB)，个位四舍五入
            size = String.valueOf(len / 1024 / 1024) + "MB";
        } else {
            // [1GB, ...)，保留两位小数
            size = String.valueOf(len * 100 / 1024 / 1024 / 1024 / (float) 100) + "GB";
        }
        return size;
    }

    /**
     * 单位信息 set 数据 处理
     * 1.去空 去null
     * 2.添加2个空格
     */
    public static String getDisStr(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.trim().replace("null", "");
        } else {
            return "";
        }
    }
    public static Map<String, String> getNationMap() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("01", "汉族");
        map.put("02", "蒙古族");
        map.put("03", "回族");
        map.put("04", "藏族");
        map.put("05", "维吾尔族");
        map.put("06", "苗族");
        map.put("07", "彝族");
        map.put("08", "壮族");
        map.put("09", "布依族");
        map.put("10", "朝鲜族");
        map.put("11", "满族");
        map.put("12", "侗族");
        map.put("13", "瑶族");
        map.put("14", "白族");
        map.put("15", "土家族");
        map.put("16", "哈尼族");
        map.put("17", "哈萨克族");
        map.put("18", "傣族");
        map.put("19", "黎族");
        map.put("20", "傈僳族");
        map.put("21", "佤族");
        map.put("22", "畲族");
        map.put("23", "高山族");
        map.put("24", "拉祜族");
        map.put("25", "水族");
        map.put("26", "东乡族");
        map.put("27", "纳西族");
        map.put("28", "景颇族");
        map.put("29", "柯尔克孜族");
        map.put("30", "土族");
        map.put("31", "达斡尔族");
        map.put("32", "仫佬族");
        map.put("33", "羌族");
        map.put("34", "布朗族");
        map.put("35", "撒拉族");
        map.put("36", "毛难族");
        map.put("37", "仡佬族");
        map.put("38", "锡伯族");
        map.put("39", "阿昌族");
        map.put("40", "普米族");
        map.put("41", "塔吉克族");
        map.put("42", "怒族");
        map.put("43", "乌孜别克族");
        map.put("44", "俄罗斯族");
        map.put("45", "鄂温克族");
        map.put("46", "崩龙族");
        map.put("47", "保安族");
        map.put("48", "裕固族");
        map.put("49", "京族");
        map.put("50", "塔塔尔族");
        map.put("51", "独龙族");
        map.put("52", "鄂伦春族");
        map.put("53", "赫哲族");
        map.put("54", "门巴族");
        map.put("55", "珞巴族");
        map.put("56", "基诺族");
        map.put("99", "其他");
        return map;
    }
    /**
     * 数据库可以识别的不带 '-' 的uuid字符串
     *
     * @return String
     */
    public static String getUUIDString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    public static String getView2Str(View mView) {
        if (mView instanceof Button) {
            return getBtn2Str((Button) mView);
        } else if (mView instanceof EditText) {
            return getEdit2Str((EditText) mView);
        } else if (mView instanceof TextView) {
            return getText2Str((TextView) mView);
        } else {
            return "";
        }
    }

    /**
     * 获取 EditText 输入内容 去空
     */
    public static String getEdit2Str(EditText mEdit) {
        return getDisStr(mEdit.getText() + "");
    }

    public static String getText2Str(TextView mText) {
        return getDisStr(mText.getText() + "");
    }

    public static String getBtn2Str(Button mText) {
        return getDisStr(mText.getText() + "");
    }
    /**
     * 获取 EditText 输入内容 去空
     */
    public static String getEditText(EditText mEdit) {
        return mEdit.getText().toString().trim();
    }

    public static String getText(TextView mText) {
        return mText.getText().toString().trim();
    }

    public static SpannableString generateCenterSpannableText(String title, String megInfo) {
        SpannableString s = new SpannableString(title + "\n" + megInfo);
        int titleLength = title.length();
        s.setSpan(new RelativeSizeSpan(1.0f), 0, titleLength, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 0, titleLength, 0);
        s.setSpan(new ForegroundColorSpan(Color.parseColor("#0E7AC2")), 0, titleLength, 0);

//        if (!CheckUtils.isEmpty(megInfo)) {
//            s.setSpan(new RelativeSizeSpan(.8f), titleLength + 2, s.length(), 0);
//            s.setSpan(new StyleSpan(Typeface.NORMAL), titleLength + 2, s.length(), 0);
//            s.setSpan(new ForegroundColorSpan(Color.GRAY), titleLength + 2, s.length(), 0);
//        }
        return s;
    }

    public static int toInt(String str, int val) {
        if (!CheckUtils.isInt(str)) {
            return val;
        } else {
            int tmp = Integer.parseInt(str);
            return tmp > val ? tmp : val;
        }
    }

    public static int toInt(Object obj, int val) {
        if (CheckUtils.isNull(obj)) {
            return val;
        } else if (obj instanceof Integer) {
            int tmp = (Integer) obj;
            return tmp > val ? tmp : val;
        } else {
            return toInt(obj.toString(), val);
        }
    }

    public static int toInt(String str) {
        if (CheckUtils.isEmpty(str) || !CheckUtils.isInt(str)) {
            return 0;
        }
        return Integer.parseInt(str);
    }

    public static float toFloat(String str) {
        if (CheckUtils.isEmpty(str)) {
            return 0;
        }
        return Float.parseFloat(str);
    }

    /**
     * 利用 case 穿透返回需要的 color 个数
     *
     * @param size 返回的color 个数
     * @return int[] colors
     */
    public static int[] getColors(int size) {
        int[] colors = new int[size];
        switch (size) {
            case 11:
//                colors[9] = ContextCompat.getColor(context, R.color.chart_10);
                colors[10] = Color.rgb(225, 242, 172);
            case 10:
//                colors[8] = ContextCompat.getColor(context, R.color.chart_9);
                colors[9] = Color.rgb(254, 149, 7);
            case 9:
//                colors[7] = ContextCompat.getColor(context, R.color.chart_8);
                colors[8] = Color.rgb(217, 80, 138);
            case 8:
//                colors[6] = ContextCompat.getColor(context, R.color.chart_7);
                colors[7] = Color.rgb(128, 161, 210);
            case 7:
//                colors[5] = ContextCompat.getColor(context, R.color.chart_6);
                colors[6] = Color.rgb(255, 140, 157);
            case 6:
//                colors[4] = ContextCompat.getColor(context, R.color.chart_5);
                colors[5] = Color.rgb(252, 162, 200);
            case 5:
//                colors[3] = ContextCompat.getColor(context, R.color.chart_4);
                colors[4] = Color.rgb(227, 207, 174);
            case 4:
//                colors[2] = ContextCompat.getColor(context, R.color.chart_3);
                colors[3] = Color.rgb(131, 223, 182);
            case 3:
//                colors[1] = ContextCompat.getColor(context, R.color.chart_2);
                colors[2] = Color.rgb(250, 214, 128);
            case 2:
//                colors[0] = ContextCompat.getColor(context, R.color.chart_1);
                colors[1] = Color.rgb(142, 205, 246);
            case 1:
//                colors[0] = ContextCompat.getColor(context, R.color.chart_1);
//                colors[0] = Color.rgb(195, 179, 228);
                colors[0] = Color.rgb(38, 79, 109);//29, 60, 78
        }
        return colors;
    }

//    public static int gettColorTansparent(Context context) {
//        return ContextCompat.getColor(context, R.color.transparent);
//    }

//    public static void saveUserPwd(String username, String password) {
//        SPUtils.i("userData").saveStringData("username", username);
//        SPUtils.i("userData").saveStringData("password", password);
//    }


    /**
     * 获取申报状态
     *
     * @param state 01 02 03...
     * @return 状态
     */
    public static String getReportState(String state) {
        String str = "";
        switch (state) {
            case "01":
                str = "新办";
                break;
            case "02":
                str = "上报";
                break;
            case "03":
                str = "审核通过";
                break;
            case "04":
                str = "审核未通过";
                break;
            case "05":
                str = "处理中";
                break;
            case "06":
                str = "已完成现场审核";
                break;
            case "07":
                str = "核对上传文件";
                break;
            case "08":
                str = "核对上传文件退回";
                break;
            case "09":
                str = "已公布";
                break;
            default:
        }
        return str;
    }

    public static boolean isBlank(String obj) {
        return (TextUtils.isEmpty(obj) || "null".equalsIgnoreCase(obj));
    }

}