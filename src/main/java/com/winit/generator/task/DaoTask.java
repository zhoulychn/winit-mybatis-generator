package com.winit.generator.task;

import java.util.ArrayList;
import java.util.List;

import com.winit.generator.Constants;
import com.winit.generator.framework.AbstractApplicationTask;
import com.winit.generator.framework.context.ApplicationContext;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.handler.impl.DaoHandler;
import com.winit.generator.model.DaoInfo;
import com.winit.generator.model.MapperInfo;

public class DaoTask extends AbstractApplicationTask {
    private static String DAO_FTL = "template/Dao.ftl";
    
    private List<DaoInfo> daoInfos;
    
    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成dao");
        
        daoInfos = (List<DaoInfo>) context.getAttribute("daoList");
        
        BaseHandler<DaoInfo> handler = null;
        for (DaoInfo daoInfo : daoInfos) {
            handler = new DaoHandler(DAO_FTL, daoInfo);
            handler.execute();
        }
        
        logger.info("生成dao完成");
        return false;
    }
    
    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);
        
        List<MapperInfo> mapperInfos = new ArrayList<MapperInfo>();
        MapperInfo mapperInfo = null;
        for (DaoInfo daoInfo : daoInfos) {
            mapperInfo = new MapperInfo();
            mapperInfo.setDaoInfo(daoInfo);
            mapperInfo.setEntityInfo(daoInfo.getEntityInfo());
            mapperInfo.setFileName(daoInfo.getEntityInfo().getEntityName() + Constants.MAPPER_XML_SUFFIX);
            mapperInfo.setNamespace(daoInfo.getPackageStr() + "." + daoInfo.getClassName());
            mapperInfos.add(mapperInfo);
        }
        context.setAttribute("mapperInfos", mapperInfos);
    }

}
