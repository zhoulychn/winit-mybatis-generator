package com.winit.generator.log;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.zip.Deflater;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.appender.rolling.DefaultRolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.RolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.SizeBasedTriggeringPolicy;
import org.apache.logging.log4j.core.appender.rolling.TriggeringPolicy;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.layout.PatternLayout;

import com.winit.generator.framework.Application;
import com.winit.generator.util.PropertyUtil;
import com.winit.generator.util.StringUtil;

/**
 * ClassName:LogFactory. <br/>
 * 日志工厂.
 * <p/>
 * 调用示例：在应用程序任务中获取日志的方法
 * 
 * <pre>
 * protected boolean doInternal(ApplicationTaskContext context) throws ApplicationException {
 *     context.getLogger().info("====================Demo Task Perform===========================");
 *     return false;
 * }
 * </pre>
 * 
 * Date: 2013年10月16日 下午4:12:02 <br/>
 * 
 * @author qiyongkang
 * @version 1.0.0
 * @since JDK 1.6
 */
public abstract class LogFactory {

    /**
     * APPLICATION_LOG_CONFIG: 日志的配置文件.
     * 
     * @since JDK 1.6
     */
    private final static String APPLICATION_LOG_CONFIG = "application.log.properties";

    /**
     * ROOT_DIR: 日志的根目录.
     * 
     * @since JDK 1.6
     */
    private final static String ROOT_DIR = "logs/";

    /**
     * DEFAULT_LAYOUT: 日志默认的输出样式.
     * 
     * @since JDK 1.6
     */
    private final static String DEFAULT_LAYOUT = "[%level] %d{yyyy-MM-dd HH:mm:ss} [Line %L] %class{36}.%M - %msg%xEx%n";

    /**
     * DEFAULT_APPLICATION_LOG_SIZE: 默认的应用程序日志文件大小.
     * 
     * @since JDK 1.6
     */
    private final static String DEFAULT_APPLICATION_LOG_SIZE = "5M";

    /**
     * DEFAULT_APPLICATION_TASK_LOG_SIZE: 默认的应用程序任务日志文件大小.
     * 
     * @since JDK 1.6
     */
    private final static String DEFAULT_APPLICATION_TASK_LOG_SIZE = "5M";

    /**
     * props: 日志属性对象.
     * 
     * @since JDK 1.6
     */
    private static Properties props = new Properties();

    static {
        InputStream is = ClassLoader.getSystemResourceAsStream(APPLICATION_LOG_CONFIG);
        if (null != is) {
            try {
                props.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String path = PropertyUtil.getConfigurationPath();
        System.setProperty("logDirPath", path);
    }

    /**
     * getApplicationSysLogger: 获取一个应用程序系统级日志记录对象. <br/>
     * 
     * @author qiyongkang
     * @return 应用程序系统级日志记录对象
     * @since JDK 1.6
     */
    public static org.apache.logging.log4j.Logger getSystemLogger() {
        return LogManager.getLogger(Application.class.getName());
    }

    /**
     * getApplicationLogger: 获取一个应用程序日志记录对象. <br/>
     * 
     * @author qiyongkang
     * @param applicationId
     *            应用程序ID字符串描述
     * @return 应用程序日志记录对象
     * @since JDK 1.6
     */
    public static org.apache.logging.log4j.Logger getApplicationLogger(String applicationId) {
        String applicationLogPath = getApplicationLogDirPath(applicationId);
        String fileName = applicationLogPath + applicationId + ".log";
        String filePattern = applicationLogPath + "$${date:yyyy-MM}/" + applicationId + "-%d{MM-dd-yyyy}-%i.log.gz";
        org.apache.logging.log4j.Logger logger = createLogger(applicationId, "ApplicationRollingFile", fileName,
                filePattern, getLogApplicationFileSize());
        logger.info("创建一个应用程序日志记录对象成功：{}", applicationLogPath + fileName);
        return logger;
    }

    /**
     * getApplicationTaskLogger: 获取一个应用程序任务日志记录对象. <br/>
     * 
     * @author qiyongkang
     * @param applicationTaskId
     *            应用程序任务ID字符串描述
     * @param applicationId
     *            应用程序ID字符串描述
     * @return 应用程序任务日志记录对象
     * @since JDK 1.6
     */
    public static org.apache.logging.log4j.Logger getApplicationTaskLogger(String applicationTaskId,
            String applicationId) {
        String applicationLogPath = getApplicationLogDirPath(applicationId);
        String fileName = applicationLogPath + applicationTaskId + ".log";
        String filePattern = applicationLogPath + "$${date:yyyy-MM}/" + applicationTaskId + "-%d{MM-dd-yyyy}-%i.log.gz";
        org.apache.logging.log4j.Logger logger = createLogger(applicationTaskId, "ApplicationTaskRollingFile", fileName,
                filePattern, getLogApplicationTaskFileSize());
        logger.info("创建一个应用程序任务日志记录对象成功：{}", applicationLogPath + fileName);
        return logger;
    }

    /**
     * getApplicationLogDirPath: 获取应用程序日志文件夹路径. <br/>
     * 
     * @author qiyongkang
     * @param applicationId
     *            应用程序ID字符串描述
     * @return 应用程序日志文件夹路径
     * @since JDK 1.6
     */
    public static String getApplicationLogDirPath(String applicationId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateDir = sdf.format(new Date()) + "/";
        return getLogRootDir() + dateDir + applicationId + "/";
    }

    /**
     * getApplicationLogDirPath: 获取应用程序日志文件夹路径. <br/>
     * 
     * @author qiyongkang
     * @param applicationId
     *            应用程序ID字符串描述
     * @return 应用程序日志文件夹路径
     * @since JDK 1.6
     */
    public static String getApplicationSysLogDirPath() {
        return getLogRootDir() + "sys/";
    }

    /**
     * getLogRootDir: 日志根目录. <br/>
     * 
     * @author qiyongkang
     * @return
     * @since JDK 1.6
     */
    public static String getLogRootDir() {
        String logRootDir = props.getProperty("log.root.dir");
        String dir = StringUtil.isEmpty(logRootDir) ? ROOT_DIR : logRootDir;
        return System.getProperty("logDirPath") + dir;
    }

    /**
     * getLogApplicationLayout: 获取应用程序日志的输出格式，应用程序和应用程序任务公用一种日志输出格式. <br/>
     * 
     * @author qiyongkang
     * @return 应用程序日志输出格式
     * @since JDK 1.6
     */
    public static String getLogApplicationLayout() {
        String logApplicationLayout = props.getProperty("log.application.layout");
        return StringUtil.isEmpty(logApplicationLayout) ? DEFAULT_LAYOUT : logApplicationLayout;
    }

    /**
     * getLogApplicationFileSize: 获取应用程序日志的文件大小. <br/>
     * 
     * @author qiyongkang
     * @return 应用程序日志的文件大小
     * @since JDK 1.6
     */
    public static String getLogApplicationFileSize() {
        String logApplicationFileSize = props.getProperty("log.application.file.size");
        return StringUtil.isEmpty(logApplicationFileSize) ? DEFAULT_APPLICATION_LOG_SIZE : logApplicationFileSize;
    }

    /**
     * getLogApplicationTaskFileSize: 获取应用程序任务日志的文件大小. <br/>
     * 
     * @author qiyongkang
     * @return 应用程序任务日志的文件大小
     * @since JDK 1.6
     */
    public static String getLogApplicationTaskFileSize() {
        String logApplicationTaskFileSize = props.getProperty("log.application.task.file.size");
        return StringUtil.isEmpty(logApplicationTaskFileSize) ? DEFAULT_APPLICATION_TASK_LOG_SIZE
                : logApplicationTaskFileSize;
    }

    /**
     * createLogger: 创建一个log4j的日志对象. <br/>
     * 
     * @author qiyongkang
     * @param name
     *            日志对象名称
     * @param fileName
     *            日志文件名称
     * @param filePattern
     *            日志文件表达式
     * @param fileSize
     *            日志大小
     * @return 日志对象
     * @since JDK 1.6
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected static Logger createLogger(String loggerName, String appenderName, String fileName, String filePattern,
            String fileSize) {
        Logger logger = (Logger) LogManager.getLogger(loggerName);
        TriggeringPolicy policy = SizeBasedTriggeringPolicy.createPolicy(fileSize);
        Configuration config = logger.getContext().getConfiguration();
        RolloverStrategy strategy = DefaultRolloverStrategy.createStrategy(null, null, null,
                String.valueOf(Deflater.DEFAULT_COMPRESSION), config);
        Layout layout = PatternLayout.createLayout(getLogApplicationLayout(), null, config, null,
                Charset.forName("UTF-8"), true, false, "", "");
        Appender appender = RollingFileAppender.createAppender(fileName, filePattern, "false", appenderName, null, null,
                "true", policy, strategy, layout, null, null, null, null, config);
        appender.start();
        logger.addAppender(appender);
        return logger;
    }
}
