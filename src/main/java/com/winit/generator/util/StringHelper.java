/**
 * Project Name:qyk-util-base
 * File Name:StringHelper.java
 * Package Name:org.qyk.base.util
 * Date:2017年2月16日下午6:37:22
 * Copyright (c) 2017, Thinkive(http://www.thinkive.com/) All Rights Reserved.
 *
*/

package com.winit.generator.util;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ClassName:StringHelper <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年2月16日 下午6:37:22 <br/>
 * 
 * @author qiyongkang
 * @version
 * @since JDK 1.6
 * @see
 */
public class StringHelper {

    /**
     * StringHelper 日志
     */
    private static Logger logger = LogManager.getLogger(StringHelper.class);

    /**
     * 描述： 构造方法
     */
    private StringHelper(){
    }

    /**
     * 空字符串
     */
    public static final String EMPTY_STRING = "";

    /**
     * 点
     */
    public static final char   DOT          = '.';

    /**
     * 下划线
     */
    public static final char   UNDERSCORE   = '_';

    /**
     * 逗点及空格
     */
    public static final String COMMA_SPACE  = ", ";

    /**
     * 逗点
     */
    public static final String COMMA        = ",";

    /**
     * 开始括号
     */
    public static final String OPEN_PAREN   = "(";

    /**
     * 结束括号
     */
    public static final String CLOSE_PAREN  = ")";

    /**
     * 单引号
     */
    public static final char   SINGLE_QUOTE = '\'';

    /**
     * 回车
     */
    public static final String CRLF         = "\r\n";

    /**
     * 常量 12
     */
    public static final int    FIANL_TWELVE = 12;

    /**
     * 十六进制常量 0x80
     */
    public static final int    HEX_80       = 0x80;

    /**
     * 十六进制常量 0xff
     */
    public static final int    HEX_FF       = 0xff;

    /**
     * 把字符数组，转化为一个字符
     *
     * @param seperator 字符分隔符
     * @param strings 数组对象
     * @return 字符串
     */
    public static String join(String seperator, String[] strings) {
        int length = strings.length;
        if (length == 0) {
            return EMPTY_STRING;
        }
        StringBuffer buf = new StringBuffer(length * strings[0].length()).append(strings[0]);
        for (int i = 1; i < length; i++) {
            buf.append(seperator).append(strings[i]);
        }
        return buf.toString();
    }

    /**
     * 把迭代对象转化为一个字符串
     *
     * @param seperator 分隔符
     * @param objects 迭代器对象
     * @return 字符串
     */
    @SuppressWarnings("rawtypes")
    public static String join(String seperator, Iterator objects) {
        StringBuffer buf = new StringBuffer();
        if (objects.hasNext()) {
            buf.append(objects.next());
        }
        while (objects.hasNext()) {
            buf.append(seperator).append(objects.next());
        }
        return buf.toString();
    }

    /**
     * 把两个字符串数组的元素用分隔符连接，生成新的数组，生成的数组以第一个字符串数组为参照，与其长度相同。
     *
     * @param x 字符串数组
     * @param seperator 分隔符
     * @param y 字符串数组
     * @return 组合后的字符串数组
     */
    public static String[] add(String[] x, String seperator, String[] y) {
        String[] result = new String[x.length];
        for (int i = 0; i < x.length; i++) {
            result[i] = x[i] + seperator + y[i];
        }
        return result;
    }

    /**
     * 生成一个重复的字符串，如需要重复*10次，则生成：**********。
     *
     * @param string 重复元素
     * @param times 重复次数
     * @return 生成后的字符串
     */
    public static String repeat(String string, int times) {
        StringBuffer buf = new StringBuffer(string.length() * times);
        for (int i = 0; i < times; i++) {
            buf.append(string);
        }
        return buf.toString();
    }

    /**
     * 字符串替换处理，把旧的字符串替换为新的字符串，主要是通过字符串查找进行处理
     *
     * @param source 需要进行替换的字符串
     * @param old 需要进行替换的字符串
     * @param replace 替换成的字符串
     * @return 替换处理后的字符串
     */
    public static String replace(String source, String old, String replace) {
        StringBuffer output = new StringBuffer();

        int sourceLen = source.length();
        int oldLen = old.length();

        int posStart = 0;
        int pos;

        // 通过截取字符串的方式，替换字符串
        while ((pos = source.indexOf(old, posStart)) >= 0) {
            output.append(source.substring(posStart, pos));

            output.append(replace);
            posStart = pos + oldLen;
        }

        // 如果还有没有处理的字符串，则都添加到新字符串后面
        if (posStart < sourceLen) {
            output.append(source.substring(posStart));
        }

        return output.toString();
    }

    /**
     * 替换字符，如果指定进行全替换，必须设wholeWords=true，否则只替换最后出现的字符。
     *
     * @param template 字符模板
     * @param placeholder 需要替换的字符
     * @param replacement 新的字符
     * @param wholeWords 是否需要全替换，true为需要，false为不需要。如果不需要，则只替换最后出现的字符。
     * @return 替换后的新字符
     */
    public static String replace(String template, String placeholder, String replacement, boolean wholeWords) {
        int loc = template.indexOf(placeholder);
        if (loc < 0) {
            return template;
        } else {
            final boolean actuallyReplace = wholeWords || loc + placeholder.length() == template.length() || !Character
                .isJavaIdentifierPart(template.charAt(loc + placeholder.length()));
            String actualReplacement = actuallyReplace ? replacement : placeholder;
            return new StringBuffer(template.substring(0, loc)).append(actualReplacement)
                .append(replace(template.substring(loc + placeholder.length()), placeholder, replacement, wholeWords))
                .toString();
        }
    }

    /**
     * 替换字符，只替换第一次出现的字符串。
     *
     * @param template 字符模板
     * @param placeholder 需要替换的字符串
     * @param replacement 新字符串
     * @return 替换后的字符串
     */
    public static String replaceOnce(String template, String placeholder, String replacement) {
        int loc = template.indexOf(placeholder);
        if (loc < 0) {
            return template;
        } else {
            return new StringBuffer(template.substring(0, loc)).append(replacement)
                .append(template.substring(loc + placeholder.length()))
                .toString();
        }
    }

    /**
     * 把字符串，按指字的分隔符分隔为字符串数组
     *
     * @param seperators 分隔符
     * @param list 字符串
     * @return 字符串数组
     */
    public static String[] split(String list, String seperators) {
        return split(list, seperators, false);
    }

    /**
     * 把字符串，按指字的分隔符分隔为字符串数组
     *
     * @param seperators 分隔符
     * @param list 字符串
     * @param include 是否需要把分隔符也返回
     * @return 字符串数组
     */
    public static String[] split(String list, String seperators, boolean include) {
        StringTokenizer tokens = new StringTokenizer(list, seperators, include);
        String[] result = new String[tokens.countTokens()];
        int i = 0;
        while (tokens.hasMoreTokens()) {
            result[i++] = tokens.nextToken();
        }
        return result;
    }

    /**
     * 提取字符串中，以.为分隔符后的所有字符，如string.exe，将返回exe。
     *
     * @param qualifiedName 字符串
     * @return 提取后的字符串
     */
    public static String unqualify(String qualifiedName) {
        return unqualify(qualifiedName, ".");
    }

    /**
     * 提取字符串中，以指定分隔符后的所有字符，如string.exe，将返回exe。
     *
     * @param qualifiedName 字符串
     * @param seperator 分隔符
     * @return 提取后的字符串
     */
    public static String unqualify(String qualifiedName, String seperator) {
        return qualifiedName.substring(qualifiedName.lastIndexOf(seperator) + 1);
    }

    /**
     * 提取字符串中，以.为分隔符以前的字符，如string.exe，则返回string
     *
     * @param qualifiedName 字符串
     * @return 提取后的字符串
     */
    public static String qualifier(String qualifiedName) {
        int loc = qualifiedName.lastIndexOf(".");
        if (loc < 0) {
            return EMPTY_STRING;
        } else {
            return qualifiedName.substring(0, loc);
        }
    }

    /**
     * 向字符串数组中的所有元素添加上后缀
     *
     * @param columns 字符串数组
     * @param suffix 后缀
     * @return 添加后缀后的数组
     */
    public static String[] suffix(String[] columns, String suffix) {
        if (suffix == null) {
            return columns;
        }
        String[] qualified = new String[columns.length];
        for (int i = 0; i < columns.length; i++) {
            qualified[i] = suffix(columns[i], suffix);
        }
        return qualified;
    }

    /**
     * 向字符串加上后缀
     *
     * @param name 需要添加后缀的字符串
     * @param suffix 后缀
     * @return 添加后缀的字符串
     */
    public static String suffix(String name, String suffix) {
        return (suffix == null) ? name : name + suffix;
    }

    /**
     * 向字符串数组中的所有元素，添加上前缀
     *
     * @param columns 需要添加前缀的字符串数组
     * @param prefix prefix
     * @return
     */
    public static String[] prefix(String[] columns, String prefix) {
        if (prefix == null) {
            return columns;
        }
        String[] qualified = new String[columns.length];
        for (int i = 0; i < columns.length; i++) {
            qualified[i] = prefix + columns[i];
        }
        return qualified;
    }

    /**
     * 向字符串添加上前缀
     *
     * @param name 需要添加前缀的字符串
     * @param prefix 前缀
     * @return 添加前缀后的字符串
     */
    public static String prefix(String name, String prefix) {
        return (prefix == null) ? name : prefix + name;
    }

    /**
     * 判断字符串是否为"true"、"t"，如果是，返回true，否则返回false
     *
     * @param tfString 需要进行判断真/假的字符串
     * @return true/false
     */
    public static boolean booleanValue(String tfString) {
        String trimmed = tfString.trim().toLowerCase();
        return trimmed.equals("true") || trimmed.equals("t");
    }

    /**
     * 把对象数组转化为字符串
     *
     * @param array 对象数组
     * @return 字符串
     */
    public static String toString(Object[] array) {
        int len = array.length;
        if (len == 0) {
            return StringHelper.EMPTY_STRING;
        }
        StringBuffer buf = new StringBuffer(len * FIANL_TWELVE);
        for (int i = 0; i < len - 1; i++) {
            buf.append(array[i]).append(StringHelper.COMMA_SPACE);
        }
        return buf.append(array[len - 1]).toString();
    }

    /**
     * 描述：把数组中的所有元素出现的字符串进行替换，把旧字符串替换为新字符数组的所有元素，只替换第一次出现的字符。 作者： 时间：Oct 29,
     * 2008 4:30:21 PM
     * 
     * @param string 需要替换的数组
     * @param placeholders 需要替换的字符串
     * @param replacements 新字符串数组
     * @return 替换后的字符串数组
     */
    @SuppressWarnings("rawtypes")
    public static String[] multiply(String string, Iterator placeholders, Iterator replacements) {
        String[] result = new String[] { string };
        while (placeholders.hasNext()) {
            result = multiply(result, (String) placeholders.next(), (String[]) replacements.next());
        }
        return result;
    }

    /**
     * 把数组中的所有元素出现的字符串进行替换，把旧字符串替换为新字符数组的所有元素，只替换第一次出现的字符。
     *
     * @param strings 需要替换的数组
     * @param placeholder 需要替换的字符串
     * @param replacements 新字符串数组
     * @return 替换后的字符串数组
     */
    private static String[] multiply(String[] strings, String placeholder, String[] replacements) {
        String[] results = new String[replacements.length * strings.length];
        int n = 0;
        for (int i = 0; i < replacements.length; i++) {
            for (int j = 0; j < strings.length; j++) {
                results[n++] = replaceOnce(strings[j], placeholder, replacements[i]);
            }
        }
        return results;
    }

    /**
     * 统计Char在字符串中出现在次数，如"s"在字符串"string"中出现的次数
     *
     * @param string 字符串
     * @param character 需要进行统计的char
     * @return 数量
     */
    public static int count(String string, char character) {
        int n = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == character) {
                n++;
            }
        }
        return n;
    }

    /**
     * 描述：计算字符串中未引用的字符 作者： 时间：Oct 29, 2008 4:32:58 PM
     * 
     * @param string 字符串
     * @param character 字符
     * @return 未引用的字符数
     */
    public static int countUnquoted(String string, char character) {
        if (SINGLE_QUOTE == character) {
            throw new IllegalArgumentException("Unquoted count of quotes is invalid");
        }

        int count = 0;
        int stringLength = string == null ? 0 : string.length();
        boolean inQuote = false;
        for (int indx = 0; indx < stringLength; indx++) {
            if (inQuote) {
                if (SINGLE_QUOTE == string.charAt(indx)) {
                    inQuote = false;
                }
            } else if (SINGLE_QUOTE == string.charAt(indx)) {
                inQuote = true;
            } else if (string.charAt(indx) == character) {
                count++;
            }
        }
        return count;
    }

    /**
     * 描述：描述：判断字符串是否为空，如果为true则为空。与isEmpty不同，如果字符为" "也视为空字符 作者：兰磊
     * lanlei@thinkive.com 2008-9-25 下午09:40:42 修改人：李建 修改时间：2012-9-7
     * 修正buger：空值传入会空指针
     * 
     * @param str 字符串
     * @return
     */
    public static boolean isBlank(String str) {
        boolean b = true;// 20140507 modify by liwei 修复对" "为false的bug
        if (str == null) {
            b = true;
        } else {
            int strLen = str.length();
            if (strLen == 0) {
                b = true;
            }

            for (int i = 0; i < strLen; i++) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    b = false;
                    break;
                }
            }
        }

        return b;
    }

    /**
     * 描述：描述：判断字符串是否为空，如果为true则不为空。与isNotEmpty不同，如果字符为" "也视为空字符 作者：兰磊
     * lanlei@thinkive.com 2008-9-25 下午09:40:42
     * 
     * @param str 字符串
     * @return
     */
    public static boolean isNotBlank(String str) {
        int strLen = 0;
        if (str != null) strLen = str.length();
        if (str == null || strLen == 0) {
            return false;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断字符串是否非空，如果为true则不为空
     *
     * @param string 字符串
     * @return true/false
     */
    public static boolean isNotEmpty(String string) {
        return string != null && string.length() > 0;
    }

    /**
     * 判断字符串是否空，如果为true则为空
     *
     * @param str 字符串
     * @return true/false
     */

    public static boolean isEmpty(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 向字符串添加上前缀，并以.作为分隔符
     *
     * @param name 需要添加前缀的字符串
     * @param prefix 前缀
     * @return 添加前缀后的字符串
     */
    public static String qualify(String name, String prefix) {
        if (name.startsWith("'")) {
            return name;
        }

        return new StringBuffer(prefix.length() + name.length() + 1).append(prefix).append(DOT).append(name).toString();
    }

    /**
     * 向字符串数组中的所有字符添加上前缀，前以点作为分隔符
     *
     * @param names 字符串数组
     * @param prefix 前缀
     * @return 添加前缀后的字符串数组
     */
    public static String[] qualify(String[] names, String prefix) {
        if (prefix == null) {
            return names;
        }
        int len = names.length;
        String[] qualified = new String[len];
        for (int i = 0; i < len; i++) {
            qualified[i] = qualify(prefix, names[i]);
        }
        return qualified;
    }

    /**
     * 在字符串中，查找字符第一次出现的位置
     *
     * @param sqlString 原字符串
     * @param string 需要查找到字符串
     * @param startindex 开始位置
     * @return 第一个出现的位置
     */
    public static int firstIndexOfChar(String sqlString, String string, int startindex) {
        int matchAt = -1;
        for (int i = 0; i < string.length(); i++) {
            int curMatch = sqlString.indexOf(string.charAt(i), startindex);
            if (curMatch >= 0) {
                if (matchAt == -1) {
                    matchAt = curMatch;
                } else {
                    matchAt = Math.min(matchAt, curMatch);
                }
            }
        }
        return matchAt;
    }

    /**
     * 从字符串中提取指字长度的字符。区分中英文。<br>
     * 如果需要加省略号，则将在指定长度上少取3个字符宽度，末尾加上"......"。
     *
     * @param string 字符串
     * @param length 要取的字符长度，此为中文长度，英文仅当作半个字符。
     * @param appendSuspensionPoints 是否需要加省略号
     * @return 提取后的字符串
     */
    public static String truncate(String string, int length, boolean appendSuspensionPoints) {
        if (isEmpty(string) || length < 0) {
            return string;
        }

        if (length == 0) {
            return "";
        }

        int strLength = string.length(); // 字符串字符个数
        int byteLength = byteLength(string); // 字符串字节长度
        length *= 2; // 换成字节长度

        // 判断是否需要加省略号
        boolean needSus = false;
        if (appendSuspensionPoints && byteLength >= length) {
            needSus = true;

            // 如果需要加省略号，则要少取2个字节用来加省略号
            length -= 2;
        }

        StringBuffer result = new StringBuffer();
        int count = 0;
        for (int i = 0; i < strLength; i++) {
            if (count >= length) { // 取完了
                break;
            }

            char c = string.charAt(i);

            if (isLetter(c)) { // Ascill字符
                result.append(c);
                count += 1;
            } else { // 非Ascill字符
                if (count == length - 1) { // 如果只要取1个字节了，而后面1个是汉字，就放空格
                    result.append(" ");
                    count += 1;
                } else {
                    result.append(c);
                    count += 2;
                }
            }
        }

        if (needSus) {
            result.append("...");
        }

        return result.toString();
    }

    /**
     * 描述：判断一个字符是Ascill字符还是其它字符（如汉，日，韩文字符） 作者： 时间：Oct 29, 2008 4:39:11 PM
     * 
     * @param c 需要判断的字符
     * @return
     */
    public static boolean isLetter(char c) {
        int k = HEX_80;
        return c / k == 0 ? true : false;
    }

    /**
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
     *
     * @param s ,需要得到长度的字符串
     * @return int, 得到的字符串长度
     */
    public static int byteLength(String s) {
        char[] c = s.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++) {
            if (isLetter(c[i])) {
                len++;
            } else {
                len += 2;
            }
        }
        return len;
    }

    /**
     * 从字符串中提取指字长度的字符
     *
     * @param string 字符串
     * @param length 字符长度
     * @return 提取后的字符串
     */
    public static String truncate(String string, int length) {
        if (isEmpty(string)) {
            return string;
        }

        if (string.length() <= length) {
            return string;
        } else {
            return string.substring(0, length);
        }
    }

    /**
     * 去丢字符的左侧空格
     *
     * @param value 字符串
     * @return 去丢左侧空格后的字符串
     */
    public static String leftTrim(String value) {
        String result = value;
        if (result == null) {
            return result;
        }
        char ch[] = result.toCharArray();
        int index = -1;
        for (int i = 0; i < ch.length; i++) {
            if (!Character.isWhitespace(ch[i])) {
                break;
            }
            index = i;
        }

        if (index != -1) {
            result = result.substring(index + 1);
        }
        return result;
    }

    /**
     * 去丢字符的右侧空格
     *
     * @param value 字符串
     * @return 去右侧空格后的字符串
     */
    public static String rightTrim(String value) {
        String result = value;
        if (result == null) {
            return result;
        }
        char ch[] = result.toCharArray();
        int endIndex = -1;
        for (int i = ch.length - 1; i > -1; i--) {
            if (!Character.isWhitespace(ch[i])) {
                break;
            }
            endIndex = i;
        }

        if (endIndex != -1) {
            result = result.substring(0, endIndex);
        }
        return result;
    }

    /**
     * 把null字符串转化为""
     *
     * @param source 空字符串
     * @return 转化后的字符串
     */
    public static String n2s(String source) {
        return source != null ? source : "";
    }

    /**
     * 如果字符串为空，则返回默认字符串
     *
     * @param source 源字符串
     * @param defaultStr 默认字符串
     * @return 转换后的字符串
     */
    public static String n2s(String source, String defaultStr) {
        return source != null ? source : defaultStr;
    }

    /**
     * 把字符串转换为gb2312编码
     *
     * @param source 需要进行转换的字符串
     */
    public static final String toGb2312(String source) {
        String temp = null;
        if (source == null || source.equals("")) {
            return source;
        }
        try {
            temp = new String(source.getBytes("8859_1"), "GB2312");
        } catch (Exception e) {
            logger.error("转换字符串为gb2312编码出错", e);
        }
        return temp;
    }

    /**
     * 把字符串转换为GBK编码
     *
     * @param source 需要进行转换的字符串
     */
    public static final String toGBK(String source) {
        String temp = null;
        if (source == null || source.equals("")) {
            return source;
        }
        try {
            temp = new String(source.getBytes("8859_1"), "GBK");
        } catch (Exception e) {
            logger.error("Convert code Error", e);
        }
        return temp;
    }

    /**
     * 把字符串转换为UTF8859编码
     *
     * @param source 需要进行转换的字符串
     */
    public static final String to8859(String source) {
        String temp = null;
        if (source == null || source.equals("")) {
            return source;
        }
        try {
            temp = new String(source.getBytes("GBK"), "8859_1");
        } catch (Exception e) {
            logger.error("Convert code Error", e);
        }
        return temp;
    }

    /**
     * 把中文字符串，转换为unicode字符串
     *
     * @param source 需要进行转换的字符串
     * @return 转换后的unicode字符串
     */
    public static String chineseToUnicode(String source) {
        if (isEmpty(source)) {
            return source;
        }

        String unicode = null;
        String temp = null;
        for (int i = 0; i < source.length(); i++) {
            temp = "\\u" + Integer.toHexString((int) source.charAt(i));
            unicode = unicode == null ? temp : unicode + temp;
        }
        return unicode;
    }

    /**
     * 将字符串格式化成 HTML 以SCRIPT变量 主要是替换单,双引号，以将内容格式化输出，适合于 HTML 中的显示输出
     *
     * @param str 要格式化的字符串
     * @return 格式化后的字符串
     */
    public static String toScript(String str) {
        if (str == null) {
            return null;
        }

        String html = new String(str);

        html = replace(html, "\"", "\\\"");
        html = replace(html, "\r\n", "\n");
        html = replace(html, "\n", "\\n");
        html = replace(html, "\t", "    ");
        html = replace(html, "\'", "\\\'");

        html = replace(html, "  ", " &nbsp;");

        html = replace(html, "</script>", "<\\/script>");
        html = replace(html, "</SCRIPT>", "<\\/SCRIPT>");

        return html;
    }

    /**
     * 同于String#trim()，但是检测null，如果原字符串为null，则仍然返回null
     *
     * @param s s
     * @return
     */
    public static String trim(String s) {
        return s == null ? s : s.trim();
    }

    /**
     * 对字符串进行空格处理，如果字符串为null呀是空字符串， 则返回默认的数字。
     *
     * @param source 需要进行处理的字符串
     * @param defaultValue 缺省值
     * @return 字符串的数字值
     */
    public static int strTrim(String source, int defaultValue) {
        if (isEmpty(source)) {
            return defaultValue;
        }
        try {
            source = source.trim();
            int value = (new Integer(source)).intValue();
            return value;
        } catch (Exception ex) {
            logger.error("数字转换出错，请检查数据来源。返回默认值", ex);
            return defaultValue;
        }
    }

    /**
     * 对字符串进行过滤处理，如果字符串是null或为空字符串， 返回默认值。
     *
     * @param source 需要进行处理的字符串
     * @param defaultValue 缺省值
     * @return 过滤后的字符串
     */
    public static String strTrim(String source, String defaultValue) {
        if (StringHelper.isEmpty(source)) {
            return defaultValue;
        }
        try {
            source = source.trim();
            return source;
        } catch (Exception ex) {
            logger.error("这个地方永远也不可能出错啊，这代码。。。。", ex);
            return defaultValue;
        }
    }

    /**
     * 描述：为了防止跨站脚本攻击，转换<>这种尖括号。 作者：岳知之 时间：2009-4-1 下午02:03:28
     * 
     * @param source
     * @return
     */
    public static String encodeURL(String source) {
        if (source == null) {
            return null;
        }
        String html = new String(source);
        html = replace(html, "<", "&lt;");
        html = replace(html, ">", "&gt;");
        html = replace(html, "\"", "&quot;");
        html = replace(html, " ", "&nbsp;");
        html = replace(html, "\'", "&acute;");
        html = replace(html, "\\", "&#092;");
        html = replace(html, "&", "&amp;");
        html = replace(html, "\r", "");
        html = replace(html, "\n", "");
        html = replace(html, "(", "&#40;");
        html = replace(html, ")", "&#41;");
        html = replace(html, "[", "&#91;");
        html = replace(html, "]", "&#93;");
        html = replace(html, ";", "&#59;");
        html = replace(html, "/", "&#47;");

        return html;
    }

    /**
     * 把字符串中一些特定的字符转换成html字符，如&、<、>、"号等
     *
     * @param source 需要进行处理的字符串
     * @return 处理后的字符串
     */
    public static String encodeHtml(String source) {
        if (source == null) {
            return null;
        }

        String html = new String(source);

        html = replace(html, "&", "&amp;");
        html = replace(html, "<", "&lt;");
        html = replace(html, ">", "&gt;");
        html = replace(html, "\"", "&quot;");
        html = replace(html, " ", "&nbsp;");
        html = replace(html, "\'", "&acute;");
        return html;
    }

    /**
     * 把一些html的字符串还原
     *
     * @param source 需要进行处理的字符串
     * @return 处理后的字符串
     */
    public static String decodeHtml(String source) {
        if (source == null) {
            return null;
        }

        String html = new String(source);

        html = replace(html, "&amp;", "&");
        html = replace(html, "&lt;", "<");
        html = replace(html, "&gt;", ">");
        html = replace(html, "&quot;", "\"");
        html = replace(html, " ", "&nbsp;");

        html = replace(html, "\r\n", "\n");
        html = replace(html, "\n", "<br>\n");
        html = replace(html, "\t", "    ");
        html = replace(html, "  ", " &nbsp;");

        return html;
    }

    /**
     * 判断字符串是否为布尔值，如true/false等
     *
     * @param source 需要进行判断的字符串
     * @return 返回字符串的布尔值
     */
    public static boolean isBoolean(String source) {
        if (source.equalsIgnoreCase("true") || source.equalsIgnoreCase("false")) {
            return true;
        }
        return false;
    }

    /**
     * 去除字符串中的最后字符
     *
     * @param str 原字符串
     * @param strMove 要去除字符 比如","
     * @return 去除后的字符串
     */
    public static String lastCharTrim(String str, String strMove) {
        if (isEmpty(str)) {
            return "";
        }

        String newStr = "";
        if (str.lastIndexOf(strMove) != -1 && str.lastIndexOf(strMove) == str.length() - 1) {
            newStr = str.substring(0, str.lastIndexOf(strMove));
        }
        return newStr;
    }

    /**
     * 清除字符串里的html代码
     *
     * @param html 需要进行处理的字符串
     * @return 清除html后的代码
     */
    public static String clearHtml(String html) {
        if (isEmpty(html)) {
            return "";
        }

        String patternStr = "(<[^>]*>)";
        Pattern pattern = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
        Matcher matcher = null;
        StringBuffer bf = new StringBuffer();
        try {
            matcher = pattern.matcher(html);
            boolean first = true;
            int start = 0;
            int end = 0;
            while (matcher.find()) {
                start = matcher.start(1);
                if (first) {
                    bf.append(html.substring(0, start));
                    first = false;
                } else {
                    bf.append(html.substring(end, start));
                }

                end = matcher.end(1);
            }
            if (end < html.length()) {
                bf.append(html.substring(end));
            }
            html = bf.toString();
            return html;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        } finally {
            pattern = null;
            matcher = null;
        }
        return html;
    }

    /**
     * 把文杯格式转换为html格式
     *
     * @param content 转换的内容
     * @return
     */
    public static String textFmtToHtmlFmt(String content) {
        content = StringHelper.replace(content, " ", "&nbsp;");
        content = StringHelper.replace(content, "\r\n", "<br>");
        content = StringHelper.replace(content, "\n", "<br>");

        return content;
    }

    /**
     * 描述：大写英文字母转换成小写 作者：兰磊 lanlei@thinkive.com 2009-1-5 下午07:05:37
     * 
     * @param strIn 字符串参数
     * @return
     */
    public static String toLowerStr(String strIn) {
        String strOut = new String(); // 输出的字串
        int len = strIn.length(); // 参数的长度
        int i = 0; // 计数器
        char ch; // 存放参数的字符

        while (i < len) {
            ch = strIn.charAt(i);

            if (ch >= 'A' && ch <= 'Z') {
                ch = (char) (ch - 'A' + 'a');
            }

            strOut += ch;
            i++;
        }
        return strOut;
    }

    /**
     * 描述：小写英文字母转换成大写 作者：兰磊 lanlei@thinkive.com 2009-1-5 下午07:07:46
     * 
     * @param strIn 字符串参数
     * @return
     */
    public static String toUpperStr(String strIn) {
        String strOut = new String(); // 输出的字串
        int len = strIn.length(); // 参数的长度
        int i = 0; // 计数器
        char ch; // 存放参数的字符

        while (i < len) {
            ch = strIn.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                ch = (char) (ch - 'a' + 'A');
            }

            strOut += ch;
            i++;
        }
        return strOut;
    }

    public static boolean isEmail(String str) {
        if (isEmpty(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile(
            "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");

        // Pattern pattern =
        // Pattern.compile("^([a-zA-Z0-9_-])+@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}");
        Matcher matcher = pattern.matcher(str);

        return matcher.matches();

    }

    public static boolean isMoblie(String str) {
        if (isEmpty(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^(13|14|15|17|18)[0-9]{9}$");
        Matcher matcher = pattern.matcher(str);

        return matcher.matches();

    }

    /**
     * 货币缩写,提供亿和万两个单位，并精确到小数点2位 TODO:切换到新的算法:对数算法
     * 
     * @param original
     * @return
     */
    public static String currencyShortFor(String original) {
        if (StringHelper.isBlank(original)) {
            return "";
        } else {
            String shortFor = "";
            double shortForValue = 0;
            DecimalFormat df = new DecimalFormat("#.00");

            try {
                double account = Double.parseDouble(original);
                if (account / 100000000 > 1) {
                    shortForValue = account / 100000000;
                    shortFor = df.format(shortForValue) + "亿";
                } else if (account / 10000 > 1) {
                    shortForValue = account / 10000;
                    shortFor = df.format(shortForValue) + "万";
                } else {
                    shortFor = original;
                }
            } catch (NumberFormatException e) {
                logger.error("字符串[" + original + "]转换成数字出错", e);
            }

            return shortFor;
        }
    }

    /**
     * 将日期格式由yyyyMMdd装换为yyyy-MM-dd
     * 
     * @param date Date string whose format is yyyyMMdd.
     * @return
     */
    public static String formatDate(String date) {
        if (isBlank(date) || date.length() < 8) {
            return "";
        }
        StringBuffer dateBuf = new StringBuffer();
        dateBuf.append(date.substring(0, 4));
        dateBuf.append("-");
        dateBuf.append(date.substring(4, 6));
        dateBuf.append("-");
        dateBuf.append(date.substring(6, 8));
        return dateBuf.toString();
    }

    /**
     * 判断是否为整数
     * 
     * @param str 传入的字符串
     * @return 是整数返回true,否则返回false
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^\\d+(\\.0)?$", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(str).matches();

    }

    /**
     * 用于=中英文混排标题中限定字符串长度。保证显示长度最多只相差一个全角字符。
     * 
     * @param string 需要截取的字符串
     * @param byteCount
     * 字节数（度量标准为中文为两个字节，ASCII字符为一个字节,这样子，刚好匹配ASCII为半角字符，而中文为全角字符，
     * 保证在网页上中英文混合的句子长度一致）
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String substring(String string, int byteCount) throws UnsupportedEncodingException {
        if (isBlank(string)) {
            return string;
        }
        byte[] bytes = string.getBytes("Unicode");// 使用UCS-2编码.
        int viewBytes = 0; // 表示当前的字节数(英文为单字节，中文为双字节的表示方法)
        int ucs2Bytes = 2; // 要截取的字节数，从第3个字节开始,前两位为位序。（UCS-2的表示方法）
        // UCS-2每个字符使用两个字节来编码。
        // ASCII n+=1,i+=2
        // 中文 n+=2,i+=2
        for (; ucs2Bytes < bytes.length && viewBytes < byteCount; ucs2Bytes++) {
            // 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
            if (ucs2Bytes % 2 == 1) {
                viewBytes++; // 低字节，无论中英文，都算一个字节。
            } else {
                // 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
                // 高位时，仅中文的高位算一字节。
                if (bytes[ucs2Bytes] != 0) {
                    viewBytes++;
                }
            }
        }
        // 截一半的汉字要保留
        if (ucs2Bytes % 2 == 1) {
            ucs2Bytes = ucs2Bytes + 1;
        }
        String result = new String(bytes, 0, ucs2Bytes, "Unicode");// 将字节流转换为java默认编码UTF-8的字符串
        if (bytes.length > ucs2Bytes) {
            result += "...";
        }
        return result;
    }

    /**
     * 描述：根据长度截断字串 作者：李建 时间：Oct 13, 2010 9:01:02 PM
     * 
     * @param str 字串
     * @param length 截取长度
     * @return
     */
    public static String[] splite(String str, int length) {
        if (StringHelper.isEmpty(str)) {
            return null;
        }
        String[] strArr = new String[(str.length() + length - 1) / length];
        for (int i = 0; i < strArr.length; i++) {
            if (str.length() > i * length + length - 1) {
                strArr[i] = str.substring(i * length, i * length + length - 1);
            } else {
                strArr[i] = str.substring(i * length);
            }
        }
        return strArr;
    }

    /**
     * 描述：把某一个字符变成大写 作者：李建 时间：Dec 17, 2010 9:42:26 PM
     * 
     * @param str str 字串
     * @param index 第几个字符
     * @return
     */
    public static String toUpOneChar(String str, int index) {
        return toUpOrLowOneChar(str, index, 1);
    }

    /**
     * 描述：把某一个字符变成小写 作者：李建 时间：Dec 17, 2010 9:42:32 PM
     * 
     * @param str str 字串
     * @param index 第几个字符
     * @return
     */
    public static String toLowOneChar(String str, int index) {
        return toUpOrLowOneChar(str, index, 0);
    }

    /**
     * 描述：把某一个字符变成大写或小写 作者：李建 时间：Dec 17, 2010 9:39:32 PM
     * 
     * @param str 字串
     * @param index 第几个字符
     * @param upOrLow 大小写 1：大写；0小写
     * @return
     */
    public static String toUpOrLowOneChar(String str, int index, int upOrLow) {
        if (StringHelper.isNotEmpty(str) && index > -1 && index < str.length()) {
            char[] chars = str.toCharArray();
            if (upOrLow == 1) {
                chars[index] = Character.toUpperCase(chars[index]);
            } else {
                chars[index] = Character.toLowerCase(chars[index]);
            }
            return new String(chars);
        }
        return str;
    }
}
