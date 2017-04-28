package com.winit.generator.model;


public class CommandInfo {
    private String packageStr;
    
    private String voType;
    
    private String entityName;
    
    private String voClassName;
    
    private String className;
    
    private String getClassName;
    
    private String queryClassName;
    
    private String batchClassName;
    
    private String listClassName;
    
    public String getPackageStr() {
        return packageStr;
    }

    public void setPackageStr(String packageStr) {
        this.packageStr = packageStr;
    }

    public String getVoType() {
        return voType;
    }

    
    public void setVoType(String voType) {
        this.voType = voType;
    }

    
    public String getEntityName() {
        return entityName;
    }

    
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    
    public String getVoClassName() {
        return voClassName;
    }

    
    public void setVoClassName(String voClassName) {
        this.voClassName = voClassName;
    }


    
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }


    
    public String getGetClassName() {
        return getClassName;
    }


    
    public void setGetClassName(String getClassName) {
        this.getClassName = getClassName;
    }


    
    public String getQueryClassName() {
        return queryClassName;
    }


    
    public void setQueryClassName(String queryClassName) {
        this.queryClassName = queryClassName;
    }

    
    public String getBatchClassName() {
        return batchClassName;
    }

    
    public void setBatchClassName(String batchClassName) {
        this.batchClassName = batchClassName;
    }

    
    public String getListClassName() {
        return listClassName;
    }

    
    public void setListClassName(String listClassName) {
        this.listClassName = listClassName;
    }
}
