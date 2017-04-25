package com.winit.generator.application;

import com.winit.generator.framework.Application;
import com.winit.generator.task.CombineInfoTask;
import com.winit.generator.task.DaoTask;
import com.winit.generator.task.EntityTask;
import com.winit.generator.task.InitTask;
import com.winit.generator.task.MapperTask;

/**
 * 
 * 程序入口
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * qiyongkang 	1.0  		2017年4月25日 	Created
 *
 * </pre>
 * @since 1.
 */
public class WinitGeneratorApplication {
    public static void main(String[] args) {
        //程序入口
        Application application = new Application(WinitGeneratorApplication.class.getSimpleName());
        application.parseArgs(args);
        application.setApplicationName(WinitGeneratorApplication.class.getName());
        application.addApplicationTask(InitTask.class)
        .addApplicationTask(CombineInfoTask.class)
        .addApplicationTask(EntityTask.class)
        .addApplicationTask(DaoTask.class)
        .addApplicationTask(MapperTask.class)
        .work();
    }
}
