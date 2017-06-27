package com.winit.generator.handler.impl;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import com.winit.generator.Constants;
import com.winit.generator.config.Configuration;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.model.EntityInfo;
import com.winit.generator.model.VoInfo;
import com.winit.generator.util.StringUtil;

public class VoHandler extends BaseHandler<VoInfo> {
    
    
    public VoHandler(String ftlName, VoInfo info){
        this.ftlName = ftlName;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir") 
                + File.separator + Configuration.getString("vo.path")
                + File.separator + info.getClassName() + Constants.FILE_SUFFIX_JAVA;
    }

    @Override
    public void combileParams(VoInfo info) {
        EntityInfo entityInfo = info.getEntityInfo();
        this.param.put("packageStr", info.getPackageStr());
        StringBuilder sb = new StringBuilder();
        for (String str : entityInfo.getImports()) {
            sb.append("import ").append(str).append(";\r\n");
        }
        this.param.put("importStr", sb.toString());
        this.param.put("entityDesc", entityInfo.getEntityDesc());
        this.param.put("className", info.getClassName());
        
        //生成属性，getter,setter方法
        sb = new StringBuilder();
        StringBuilder sbMethods = new StringBuilder();
        Map<String, String> propRemarks = entityInfo.getPropRemarks();
        for (Entry<String, String> entry : entityInfo.getPropTypes().entrySet()) {
            String propName = entry.getKey();
            String propType = entry.getValue();
            
            //注释、类型、名称
            sb.append("    /*").append(propRemarks.get(propName)).append("*/\r\n")
            .append("    private ").append(propType).append(" ").append(propName)
            .append(";\r\n");
            
            sbMethods.append("    public ").append(propType).append(" get")
            .append(propName.substring(0, 1).toUpperCase())
            .append(propName.substring(1)).append("() {\r\n")
            .append("        return ").append(propName).append(";\r\n")
            .append("    }\r\n")
            .append("    public void set").append(propName.substring(0, 1).toUpperCase())
            .append(propName.substring(1)).append("(").append(propType).append(" ")
            .append(propName).append(") {\r\n")
            .append("        this.").append(propName).append(" = ").append(propName)
            .append(";\r\n    }\r\n").append("\r\n");
        }
        
        this.param.put("propertiesStr", sb.toString());
        this.param.put("methodStr", sbMethods.toString());
        this.param.put("serialVersionNum", StringUtil.generate16LongNum());
    }

}
