package com.winit.generator.task;

import java.util.ArrayList;
import java.util.List;

import com.winit.generator.Constants;
import com.winit.generator.config.Configuration;
import com.winit.generator.framework.AbstractApplicationTask;
import com.winit.generator.framework.context.ApplicationContext;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.handler.impl.VoHandler;
import com.winit.generator.model.EntityInfo;
import com.winit.generator.model.ManagerInfo;
import com.winit.generator.model.VoInfo;

public class VoTask extends AbstractApplicationTask {
    private static String VO_FTL = "template/Vo.ftl";
    private List<VoInfo> voList;
    
    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成vo");
        voList = (List<VoInfo>) context.getAttribute("voList");
        
        BaseHandler<VoInfo> handler = null;
        for (VoInfo voInfo : voList) {
            handler = new VoHandler(VO_FTL, voInfo);
            handler.execute(context);
        }
        logger.info("结束生成vo");
        return false;
    }
    
    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);
        
        //组装ManagerInfo
        List<ManagerInfo> managerInfos = new ArrayList<ManagerInfo>();
        
        ManagerInfo managerInfo = null;
        for (VoInfo voInfo : voList) {
            managerInfo = new ManagerInfo();
            
            EntityInfo entityInfo = voInfo.getEntityInfo();
            
            managerInfo.setClassName(entityInfo.getEntityName() + Constants.MANAGER_SUFFIX);
            managerInfo.setEntityDesc(entityInfo.getEntityDesc());
            managerInfo.setPackageStr(Configuration.getString("manager.package"));
            managerInfo.setVoClassName(voInfo.getClassName());
            managerInfo.setVoType(voInfo.getPackageStr() + "." + voInfo.getClassName());
            managerInfo.setVoInfo(voInfo);
            
            managerInfos.add(managerInfo);
        }
        context.setAttribute("managerInfos", managerInfos);
    }

}
