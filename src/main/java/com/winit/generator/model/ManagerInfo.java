package com.winit.generator.model;


public class ManagerInfo {
    
    private String packageStr;
    
    private String voType;
    
    private String entityDesc;
    
    private String className;
    
    private String voClassName;
    
    private VoInfo voInfo;

    
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

    
    public String getEntityDesc() {
        return entityDesc;
    }

    
    public void setEntityDesc(String entityDesc) {
        this.entityDesc = entityDesc;
    }

    
    public String getClassName() {
        return className;
    }

    
    public void setClassName(String className) {
        this.className = className;
    }

    
    public String getVoClassName() {
        return voClassName;
    }

    
    public void setVoClassName(String voClassName) {
        this.voClassName = voClassName;
    }

    
    public VoInfo getVoInfo() {
        return voInfo;
    }

    
    public void setVoInfo(VoInfo voInfo) {
        this.voInfo = voInfo;
    }
    
}
