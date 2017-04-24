/**
 * Project Name:image-util
 * File Name:Application.java
 * Package Name:org.wuqiong.imageutil.framework
 * Date:2015年12月25日下午4:09:37
 * Copyright (c) 2015, CANNIKIN(http://http://code.taobao.org/p/cannikin/src/) All Rights Reserved.
 *
*/

package com.winit.generator.framework;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.winit.generator.framework.context.ApplicationContext;
import com.winit.generator.framework.context.SimpleApplicationContext;
import com.winit.generator.log.LogFactory;
import com.winit.generator.util.ClassUtils;
import com.winit.generator.util.DateUtil;
import com.winit.generator.util.JsonHelper;
import com.winit.generator.util.PropertyUtil;
import com.winit.generator.util.StringUtil;


/**
 * ClassName:Application <br/>
 * Function: 应用程序类. <br/>
 * Date:     2015年12月25日 下午4:09:37 <br/>
 * @author   qiyongkang
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Application {
    /**
     * 日志类
     */
    private Logger logger;
    
    /**
     * 应用程序id
     */
    private String applicationId;
    
    /**
     * applicationName: 应用程序名称.
     * @since JDK 1.6
     */
    private String applicationName;
    
    /**
     * 上下文
     */
    private ApplicationContext context;
    
    /**
     * 应用程序执行开始时间
     */
    private Date startTime;
    
    /**
     * 应用程序执行结束时间
     */
    private Date endTime;
    
    /**
     * 执行耗时
     */
    private long excuteTime;
    
    /**
     * applicationTasks: 此应用程序中所有的应用程序任务.
     * @since JDK 1.6
     */
    private List<ApplicationTask> applicationTasks = new ArrayList<ApplicationTask>();
    
    public Application(String applicationId) {
        this.applicationId = applicationId;
        this.context = new SimpleApplicationContext();
        this.logger = LogFactory.getApplicationLogger(applicationId);
    }
    
    public void work() {
        PropertyUtil.setLogger(logger);
        
        //获取回调url
//        String callbackUrl = PropertyUtil.getValueByKey("callbackUrl");
        //获取任务历史id
//        String taskHistoryId = PropertyUtil.getValueByKey("taskHistoryId");
//        logger.info("回调url:{}, 任务历史id：{}", callbackUrl, taskHistoryId);
        
        try {
//            tryCallback(0, callbackUrl, taskHistoryId);
            workWithoutCallback();
        } finally {
//            tryCallback(1, callbackUrl, taskHistoryId);
        }
    }
    
    @SuppressWarnings("unused")
    private void tryCallback(int flag, String callbackUrl, String taskHistoryId) {
        if (StringUtil.isEmpty(callbackUrl) || StringUtil.isEmpty(taskHistoryId)) {
            logger.info("回调参数为空，不进行回调！");
            return;
        }
        
        Map<String, Object> map = new HashMap<String, Object>();
        if (flag == 0) {
            //执行前回调
            map.put("id", Long.valueOf(taskHistoryId));
            map.put("execStartTime", new Date());
            map.put("runStatus", 1);  //运行中
        } else {
            //执行后回调
            map.put("id", Long.valueOf(taskHistoryId));
            map.put("execEndTime", new Date());
            map.put("runStatus", 2);  //运行完毕
        }
        
        int tryTimes = 0;
        while (tryTimes < 3) {
            logger.info("当前已经尝试次数为：" + tryTimes + "次");
            try {
                String utf8URL = URLDecoder.decode(callbackUrl, "UTF-8");
                logger.info("开始建立回调连接！URL[" + utf8URL + "]");
                URL url = new URL(utf8URL);
                //建立连接
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setUseCaches(false);
                urlConnection.connect();
                //传参数
                OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
              
                String json = JsonHelper.getJSONString(map);
                String param = "param=" + URLEncoder.encode(json, "UTF-8");
                logger.info("发送请求，参数为：" + param);
                out.write(param);
                out.flush();
                out.close();
                logger.info("发送请求完毕，开始关闭连接！");
                //==================Read Response Input Stream=====================
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    logger.info(" [Read Http line] " + line);
                }
                urlConnection.disconnect();
                tryTimes = 3;
            } catch (Exception e) {
                logger.info("回调异常！", e);
                tryTimes++;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ie) {
                }
            }
        }
        
    }

    private void workWithoutCallback() {
        this.startTime = new Date();
        logger.info("应用程序{}执行开始时间：{}", this.applicationName, this.startTime);
        
        if (hasTasks()) {
            ApplicationTask task = applicationTasks.get(0);
            
            boolean perform = false;
            try {
                perform = task.perform(this.context);
                
                while (!perform && task.hasNext()) {
                    task = task.next();
                    perform = task.perform(this.context);
                    if (perform) {
                        logger.info("跳出整个应用程序任务链！跳出之前执行的任务为：" + task.getClass().getName());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("任务{}执行出现异常！", task.getClass().getName(), e);
            }
        } else {
            logger.error("无法启动应用程序，由于应用程序中没有任务！");
        }
        this.endTime = new Date();
        logger.info("应用程序{}执行结束时间：{}", this.applicationName, this.endTime);
        this.excuteTime = DateUtil.calExcuteSecondTime(startTime, endTime);
        logger.info("应用程序{}执行总耗时(秒)：{},总耗时(毫秒):{}", this.applicationName, this.excuteTime, DateUtil.calExcuteMilliTime(startTime, endTime));
    }
    
    public Application addApplicationTask(Class<? extends ApplicationTask> taskClass) {
        ApplicationTask task = ClassUtils.newInstance(taskClass.getName(), ApplicationTask.class);
        if (null != task) {
            //初始化任务类的日志
            task.initLogger(taskClass.getSimpleName(), this.applicationId);
            this.logger.info("添加新的应用程序任务{}", taskClass.getSimpleName());
            return addApplicationTask(task);
        }
        return this;
    }
    
    public Application addApplicationTask(ApplicationTask task) {
        if (null == task) {
            return this;
        }
        if (hasTasks()) {
            //取出最后一个应用程序任务，将其的下一个应用程序任务设置为当前要注册的应用程序任务
            ApplicationTask lastTask = this.applicationTasks.get(this.applicationTasks.size() - 1);
            lastTask.registerNextTask(task);
        }
        this.applicationTasks.add(task);
        return this;
    }
    
    public boolean hasTasks() {
        return !applicationTasks.isEmpty();
    }
    
    public void parseArgs(String[] args) {
        this.logger.info("开始解析应用程序参数：{}", Arrays.toString(args));
        for (String arg : args) {
            //这里我们可以约定以‘=’号分隔,如param1=1
            String[] param = arg.split("=");
            if (param == null || param.length < 2) {
                continue;
            }
            System.setProperty(param[0], param[1]);
            this.logger.info("业务参数名称：{}，业务参数值：{}", param[0], param[1]);
        }
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
    
}

