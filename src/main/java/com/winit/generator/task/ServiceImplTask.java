package com.winit.generator.task;

import java.util.List;

import com.winit.generator.framework.AbstractApplicationTask;
import com.winit.generator.framework.context.ApplicationContext;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.handler.impl.ServiceImplHandler;
import com.winit.generator.model.ServiceImplInfo;

public class ServiceImplTask extends AbstractApplicationTask {
    private static String SERVICEIMPL_FTL = "template/ServiceImpl.ftl";

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成serviceImpl...");
        List<ServiceImplInfo> list = (List<ServiceImplInfo>) context.getAttribute("serviceImplInfos");
        
        BaseHandler<ServiceImplInfo> baseHandler = null;
        for (ServiceImplInfo info : list) {
            baseHandler = new ServiceImplHandler(SERVICEIMPL_FTL, info);
            baseHandler.execute(context);
        }
        
        logger.info("结束生成serviceImpl。。。");
        return false;
    }

}
