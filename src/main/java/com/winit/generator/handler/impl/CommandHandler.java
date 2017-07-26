package com.winit.generator.handler.impl;

import java.io.File;

import com.winit.generator.Constants;
import com.winit.generator.config.Configuration;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.model.CommandInfo;
import com.winit.generator.util.StringUtil;

public class CommandHandler extends BaseHandler<CommandInfo> {

    private String className;

    public CommandHandler(String ftlName, CommandInfo info, String className){
        this.ftlName = ftlName;
        this.className = className;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir") + File.separator
                        + Configuration.getString("command.path") + File.separator + this.className
                        + Constants.FILE_SUFFIX_JAVA;
    }

    @Override
    public void combileParams(CommandInfo info) {
        this.param.put("packageStr", info.getPackageStr());
        this.param.put("voType", info.getVoType());
        this.param.put("entityName", info.getEntityName());
        this.param.put("voClassName", info.getVoClassName());
        this.param.put("className", this.className);
        this.param.put("serialVersionNum", StringUtil.generate16LongNum());
    }

}
