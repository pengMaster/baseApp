package com.mtm.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 类转换初始化
 */
public class TUtil {
    public static <T> T getT(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 对象之间拷贝属性
     *
     * @param source
     * @param target
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static void copyProperties(Object source, Object target)
            throws IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        Map<String, Method> mapMethodWrite = new HashMap<String, Method>();
        Map<String, Method> mapMethodRead = new HashMap<String, Method>();

        Method[] methodTarget = target.getClass().getMethods();
        for (Method mt : methodTarget) {
            if (mt.getName().startsWith("set")) {
                String propertyName = mt.getName().substring(3, 4)
                        .toLowerCase()
                        + mt.getName().substring(4);
                mapMethodWrite.put(propertyName, mt);
            }
        }

        Method[] methodSource = source.getClass().getMethods();
        for (Method ms : methodSource) {
            if (ms.getName().startsWith("get")) {
                mapMethodRead.put(ms.getName().substring(3, 4).toLowerCase()
                        + ms.getName().substring(4), ms);
            } else if (ms.getName().startsWith("is")) {
                mapMethodRead.put(ms.getName().substring(2, 3).toLowerCase()
                        + ms.getName().substring(3), ms);
            }
        }

        Iterator<String> iter = mapMethodWrite.keySet().iterator();
        while (iter.hasNext()) {
            String propertyName = iter.next();
            if (mapMethodRead.get(propertyName) != null) {
                Object[] obj = new Object[1];
                obj[0] = mapMethodRead.get(propertyName).invoke(source,
                        new Object[0]);
                mapMethodWrite.get(propertyName).invoke(target, obj);
            }
        }

    }
    public static String getDate(Date d, String s) {
        DateFormat df = new SimpleDateFormat(s);
        return df.format(d);
    }
    public static String getDate(String datestr, String format)
            throws ParseException {
        DateFormat df = new SimpleDateFormat(format);
        Date date = df.parse(datestr);
        return df.format(date);
    }
    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1
     *            时间参数 1 格式：1990-01-01 12:00:00
     * @param str2
     *            时间参数 2 格式：2009-01-01 12:00:00
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
        long[] times = { day, hour, min, sec };
        return times;
    }
}
