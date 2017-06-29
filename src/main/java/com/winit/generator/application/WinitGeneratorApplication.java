package com.winit.generator.application;

import com.winit.generator.framework.Application;
import com.winit.generator.task.CombineInfoTask;
import com.winit.generator.task.CommandTask;
import com.winit.generator.task.DaoTask;
import com.winit.generator.task.EntityTask;
import com.winit.generator.task.InitTask;
import com.winit.generator.task.ManagerImplTask;
import com.winit.generator.task.ManagerTask;
import com.winit.generator.task.MapperTask;
import com.winit.generator.task.ServiceImplTask;
import com.winit.generator.task.ServiceTask;
import com.winit.generator.task.ServiceTestTask;
import com.winit.generator.task.VoTask;

/**
 * 程序入口
 * 
 * @version
 * 
 * <pre>
 * Author	Version		Date		Changes
 * qiyongkang 	1.0  		2017年4月25日 	Created
 * </pre>
 * 
 * @since 1.
 */
public class WinitGeneratorApplication {

    public static void main(String[] args) {
        // 程序入口
        Application application = new Application(WinitGeneratorApplication.class.getSimpleName());
        application.parseArgs(args);
        application.setApplicationName(WinitGeneratorApplication.class.getName());
        application.addApplicationTask(InitTask.class) // 获取数据库表以及字段相关信息
            .addApplicationTask(CombineInfoTask.class) // 基本信息封装
            .addApplicationTask(EntityTask.class) // 生成Entity
            .addApplicationTask(DaoTask.class) // 生成Dao
            .addApplicationTask(MapperTask.class) // 生成Mapper.xml
            .addApplicationTask(VoTask.class) // 生成Vo
            .addApplicationTask(ManagerTask.class) // 生成Manager
            .addApplicationTask(ManagerImplTask.class) // 生成ManagerImpl
            .addApplicationTask(CommandTask.class) // 生成Command
            .addApplicationTask(ServiceTask.class) // 生成Service
            .addApplicationTask(ServiceImplTask.class) // 生成ServiceImpl
            .addApplicationTask(ServiceTestTask.class) // 生成Service单元测试类
            .work();
    }
}
