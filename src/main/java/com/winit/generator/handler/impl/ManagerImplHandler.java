package com.winit.generator.handler.impl;

import java.io.File;

import com.winit.generator.config.Configuration;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.model.ManagerImplInfo;

public class ManagerImplHandler extends BaseHandler<ManagerImplInfo> {
    public ManagerImplHandler(String ftlName, ManagerImplInfo info) {
        this.ftlName = ftlName;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir") 
                + File.separator + Configuration.getString("managerImpl.path")
                + File.separator + info.getClassName() + ".java";
        
    }
    
    @Override
    public void combileParams(ManagerImplInfo info) {
        this.param.put("packageStr", info.getPackageStr());
        this.param.put("daoType", info.getDaoType());
        this.param.put("entityType", info.getEntityType());
        this.param.put("managerType", info.getManagerType());
        this.param.put("voType", info.getVoType());
        this.param.put("entityDesc", info.getEntityInfo().getEntityDesc());
        this.param.put("annotationName", info.getAnnotationName());
        this.param.put("className", info.getClassName());
        this.param.put("managerClassName", info.getManagerClassName());
        this.param.put("daoClassName", info.getDaoClassName());
        this.param.put("daoVar", info.getDaoVar());
        this.param.put("entityClassName", info.getEntityClassName());
        this.param.put("voClassName", info.getVoClassName());
    }

}
