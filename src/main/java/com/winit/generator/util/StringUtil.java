package com.winit.generator.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StringUtil {

    /**
     * isEmpty:判断字符串是否为空. <br/>
     *
     * @author qiyongkang
     * @param str
     * @return
     * @since JDK 1.6
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * subBySplit:根据分隔符截取字符串. <br/>
     *
     * @author qiyongkang
     * @param split
     * @return
     * @since JDK 1.6
     */
    public static String subBySplit(String str, String split) {
        String sub = null;
        if (!isEmpty(str) && str.lastIndexOf(split) > -1) {
            sub = str.substring(str.lastIndexOf(split) + 1);
        }

        return sub;
    }

    /**
     * upperFirst: 将字符串的首字母转换为大写. <br/>
     * 
     * @author qiyongkang
     * @param str 要转换的字符串
     * @return 转换之后的字符串
     * @since JDK 1.6
     */
    public static String upperFirst(String str) {
        if (isEmpty(str)) {
            return null;
        }
        if (1 == str.length()) {
            return str.toUpperCase();
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 将字符串拆分为list
     * 
     * @param str
     * @param regex
     * @return
     */
    public static List<String> splitStr2List(String str, String regex) {
        List<String> list = new ArrayList<String>();
        String[] strs = str.split(regex);
        for (String s : strs) {
            list.add(s.trim());
        }
        return list;
    }

    /**
     * 将数据库字段名，转为属性名 TODO Add comments here.
     * 
     * @param fieldName
     * @return
     */
    public static String convertFieldName2PropName(String fieldName) {
        String propName = "";
        String[] strs = fieldName.split("_");
        for (int i = 0; i < strs.length; i++) {
            if (i == 0) {
                propName = strs[0];
            } else {
                propName += strs[i].substring(0, 1).toUpperCase() + strs[i].substring(1);
            }
        }
        return propName;
    }

    /**
     * 生成随机序列值
     * 
     * @return
     */
    public static String generate16LongNum() {
        Random ran = new Random();
        int a = ran.nextInt(99999999);
        int b = ran.nextInt(99999999);
        long l = a * 100000000L + b;
        String num = String.valueOf(l) + "L";
        return num;
    }

    public static void main(String[] args) {
        System.out.println(subBySplit("BirthdayReminder\\生日名单.xls", "/"));
        System.out.println(convertFieldName2PropName("qyk_yong_kang"));
        for (int i = 0; i < 1000; i++) {
            System.out.println(generate16LongNum());
        }

    }
}
