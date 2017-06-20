package com.winit.generator.handler.impl;

import java.io.File;

import com.winit.generator.config.Configuration;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.model.ManagerInfo;

public class ManagerHandler extends BaseHandler<ManagerInfo> {
    
    public ManagerHandler(String ftlName, ManagerInfo info) {
        this.ftlName = ftlName;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir") 
                + File.separator + Configuration.getString("manager.path")
                + File.separator + info.getClassName() + ".java";
        
    }
    
    @Override
    public void combileParams(ManagerInfo info) {
        this.param.put("packageStr", info.getPackageStr());
        this.param.put("voType", info.getVoType());
        this.param.put("entityDesc", info.getEntityDesc());
        this.param.put("className", info.getClassName());
        this.param.put("voClassName", info.getVoClassName());
        this.param.put("entityName", info.getVoInfo().getEntityInfo().getEntityName());
    }

}
