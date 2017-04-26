package com.winit.generator.task;

import java.util.List;

import com.winit.generator.framework.AbstractApplicationTask;
import com.winit.generator.framework.context.ApplicationContext;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.handler.impl.VoHandler;
import com.winit.generator.model.VoInfo;

public class VoTask extends AbstractApplicationTask {
    private static String VO_FTL = "template/Vo.ftl";
    
    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成vo");
        List<VoInfo> voList = (List<VoInfo>) context.getAttribute("voList");
        
        BaseHandler<VoInfo> handler = null;
        for (VoInfo voInfo : voList) {
            handler = new VoHandler(VO_FTL, voInfo);
            handler.execute();
        }
        logger.info("结束生成vo");
        return false;
    }

}
