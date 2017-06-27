/**
 * Project Name:testJava
 * File Name:DbUtil.java
 * Package Name:com.qiyongkang.db.util
 * Date:2015年11月13日下午6:00:34
 * Copyright (c) 2015, CANNIKIN(http://http://code.taobao.org/p/cannikin/src/) All Rights Reserved.
 *
*/

package com.winit.generator.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.winit.generator.config.Configuration;

/**
 * ClassName:DbUtil <br/>
 * Function: 数据库连接工具类. <br/>
 * Date: 2015年11月13日 下午6:00:34 <br/>
 * 
 * @author qiyongkang
 * @version
 * @since JDK 1.6
 * @see
 */
public class DbUtil {
    /**
     * 日志类
     */
    private static Logger logger = LogManager.getLogger(DbUtil.class.getName());
    
    /**
     * 加载驱动
     */
    static {
        try {
            String driverName = Configuration.getString("jdbc.driverName");
            Class.forName(driverName);
            
            logger.info("加载驱动成功：{}", driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * getConn:获取连接. <br/>
     *
     * @author qiyongkang
     * @return
     * @since JDK 1.6
     */
    public static Connection getConn() {
        Connection conn = null;
        try {
            String jdbcUrl = Configuration.getString("jdbc.url");
            String userName = Configuration.getString("jdbc.username");
            String password = Configuration.getString("jdbc.password");
            Properties props = new Properties();  
            props.put("remarksReporting", "true");  
            props.put("user", userName);  
            props.put("password", password);
            conn = DriverManager.getConnection(jdbcUrl, props);
        } catch (SQLException e) {
            logger.error("数据连接异常", e);
            e.printStackTrace();
        }
        return conn;
    }
    
    /**
     * 
     * closeConn:关闭连接. <br/>
     *
     * @author qiyongkang
     * @param conn
     * @since JDK 1.6
     */
    public static void closeReso(Connection conn, Statement stat, ResultSet resultSet) {
        try {
            if (conn != null)
                conn.close();
            if (stat != null)
                stat.close();
            if (resultSet != null)
                resultSet.close();
            logger.info("关闭资源成功。。。");
        } catch (SQLException e) {
            logger.error("关闭连接异常", e);
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        System.out.println(getConn());
    }
}
