package com.winit.generator.task;

import java.util.List;

import com.winit.generator.Constants;
import com.winit.generator.config.Configuration;
import com.winit.generator.framework.AbstractApplicationTask;
import com.winit.generator.framework.context.ApplicationContext;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.handler.impl.MapperHandler;
import com.winit.generator.model.MapperInfo;

public class MapperTask extends AbstractApplicationTask {
    private static String MAPPER_FTL = Configuration.getString("base.database").equals(Constants.DB_ORACLE) ? "template/Mapper_oracle.ftl" : "template/Mapper.ftl";

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成Mapper");
        
        List<MapperInfo> list = (List<MapperInfo>) context.getAttribute("mapperInfos");
        
        BaseHandler<MapperInfo> handler = null;
        for (MapperInfo mapperInfo : list) {
            handler = new MapperHandler(MAPPER_FTL, mapperInfo);
            handler.execute(context);
        }
        
        logger.info("生成Mapper完成");
        return false;
    }
    
    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);
    }
}
