package com.winit.generator.task;

import java.util.List;

import com.winit.generator.framework.AbstractApplicationTask;
import com.winit.generator.framework.context.ApplicationContext;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.handler.impl.ServiceTestHandler;
import com.winit.generator.model.ServiceTestInfo;

public class ServiceTestTask extends AbstractApplicationTask  {
    private static String SERVICETEST_FTL = "template/ServiceTest.ftl";

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成service单元测试类。。。");
        
        List<ServiceTestInfo> list = (List<ServiceTestInfo>) context.getAttribute("serviceTestInfos");
        
        BaseHandler<ServiceTestInfo> baseHandler = null;
        for (ServiceTestInfo serviceTestInfo : list) {
            baseHandler = new ServiceTestHandler(SERVICETEST_FTL, serviceTestInfo);
            baseHandler.execute(context);
        }
        
        logger.info("生成service单元测试类完成。。。");
        return false;
    }

}
