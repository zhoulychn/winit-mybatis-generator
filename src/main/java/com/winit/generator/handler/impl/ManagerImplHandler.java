package com.winit.generator.handler.impl;

import java.io.File;
import java.util.Set;

import com.winit.generator.Constants;
import com.winit.generator.config.Configuration;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.model.ManagerImplInfo;

public class ManagerImplHandler extends BaseHandler<ManagerImplInfo> {
    public ManagerImplHandler(String ftlName, ManagerImplInfo info) {
        this.ftlName = ftlName;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir") 
                + File.separator + Configuration.getString("managerImpl.path")
                + File.separator + info.getClassName() + Constants.FILE_SUFFIX_JAVA;
        
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
        this.param.put("entityName", info.getEntityInfo().getEntityName());
        
        StringBuilder entityToVos = new StringBuilder();
        StringBuilder voToEntitys = new StringBuilder();
        
        Set<String> keys = info.getEntityInfo().getPropTypes().keySet();
        for (String key : keys) {
            String prop = key.substring(0, 1).toUpperCase() + key.substring(1);
            
            entityToVos.append("        vo.set")
            .append(prop).append("(entity.get").append(prop).append("());\r\n");
            voToEntitys.append("        entity.set")
            .append(prop).append("(vo.get").append(prop).append("());\r\n");
        }
        this.param.put("entityToVos", entityToVos.substring(0, entityToVos.length() - 2));
        this.param.put("voToEntitys", voToEntitys.substring(0, voToEntitys.length() - 2));
    }

}
