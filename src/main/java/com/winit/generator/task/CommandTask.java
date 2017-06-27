package com.winit.generator.task;


import java.util.ArrayList;
import java.util.List;

import com.winit.generator.Constants;
import com.winit.generator.config.Configuration;
import com.winit.generator.framework.AbstractApplicationTask;
import com.winit.generator.framework.context.ApplicationContext;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.handler.impl.CommandHandler;
import com.winit.generator.model.CommandInfo;
import com.winit.generator.model.EntityInfo;
import com.winit.generator.model.ServiceInfo;

public class CommandTask extends AbstractApplicationTask {
    private static String GET_FTL = "template/GetCommand.ftl";
    private static String QUERY_FTL = "template/QueryCommand.ftl";
    private static String _FTL = "template/Command.ftl";
    private static String BATCH_FTL = "template/BatchCommand.ftl";
    private static String LIST_FTL = "template/ListCommand.ftl";
    
    private List<CommandInfo> commandInfos;

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成command...");
        
        commandInfos = (List<CommandInfo>) context.getAttribute("commandInfos");
        
        BaseHandler<CommandInfo> handler1 = null;
        BaseHandler<CommandInfo> handler2 = null;
        BaseHandler<CommandInfo> handler3 = null;
        BaseHandler<CommandInfo> handler4 = null;
        BaseHandler<CommandInfo> handler5 = null;
        for (CommandInfo commandInfo : commandInfos) {
            handler1 = new CommandHandler(GET_FTL, commandInfo, commandInfo.getGetClassName());
            handler1.execute(context);
            handler2 = new CommandHandler(QUERY_FTL, commandInfo, commandInfo.getQueryClassName());
            handler2.execute(context);
            handler3 = new CommandHandler(_FTL, commandInfo, commandInfo.getClassName());
            handler3.execute(context);
            handler4 = new CommandHandler(BATCH_FTL, commandInfo, commandInfo.getBatchClassName());
            handler4.execute(context);
            handler5 = new CommandHandler(LIST_FTL, commandInfo, commandInfo.getListClassName());
            handler5.execute(context);
        }
        
        logger.info("结束生成command...");
        return false;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);
        
        //组装Service信息
        List<ServiceInfo> serviceInfos = new ArrayList<ServiceInfo>();
        List<EntityInfo> entityInfos = (List<EntityInfo>) context.getAttribute("entityInfos");
        ServiceInfo service = null;
        for (int i = 0; i < commandInfos.size(); i++) {
            EntityInfo entityInfo = entityInfos.get(i);
            CommandInfo commandInfo = commandInfos.get(i);
            service = new ServiceInfo();
            
            service.setBatchCommandType(commandInfo.getPackageStr() + Constants.CHARACTER_POINT + commandInfo.getBatchClassName());
            service.setClassName(entityInfo.getEntityName() + Constants.SERVICE_SUFFIX);
            service.setCommandInfo(commandInfo);
            service.setCommandType(commandInfo.getPackageStr() + Constants.CHARACTER_POINT + commandInfo.getClassName());
            service.setEntityDesc(entityInfo.getEntityDesc());
            service.setEntityName(entityInfo.getEntityName());
            service.setGetCommandType(commandInfo.getPackageStr() + Constants.CHARACTER_POINT + commandInfo.getGetClassName());
            service.setListCommandType(commandInfo.getPackageStr() + Constants.CHARACTER_POINT + commandInfo.getListClassName());
            service.setPackageStr(Configuration.getString("service.package"));
            service.setQueryCommandType(commandInfo.getPackageStr() + Constants.CHARACTER_POINT + commandInfo.getQueryClassName());
            service.setVoClassName(commandInfo.getVoClassName());
            service.setVoType(commandInfo.getVoType());
            
            serviceInfos.add(service);
        }
        
        context.setAttribute("serviceInfos", serviceInfos);
    }

}
