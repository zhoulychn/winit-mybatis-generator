/**
 * Project Name:image-util
 * File Name:SysoutUtil.java
 * Package Name:org.wuqiong.imageutil.util
 * Date:2015年12月28日下午2:00:00
 * Copyright (c) 2015, CANNIKIN(http://http://code.taobao.org/p/cannikin/src/) All Rights Reserved.
 *
*/

package com.winit.generator.util;

import java.text.MessageFormat;

/**
 * ClassName:SysoutUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015年12月28日 下午2:00:00 <br/>
 * 
 * @author qiyongkang
 * @version
 * @since JDK 1.6
 * @see
 */
public class SysoutUtil {

    public static void printInfo(String str, Object... objects) {
        System.out.println(MessageFormat.format(str, objects));
    }
}
