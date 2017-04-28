package com.winit.generator.model;


public class ServiceTestInfo {
    
    private String className;
    
    private String packageStr;
    
    private ServiceImplInfo serviceImplInfo;

    
    public String getClassName() {
        return className;
    }

    
    public void setClassName(String className) {
        this.className = className;
    }

    
    public String getPackageStr() {
        return packageStr;
    }

    
    public void setPackageStr(String packageStr) {
        this.packageStr = packageStr;
    }

    
    public ServiceImplInfo getServiceImplInfo() {
        return serviceImplInfo;
    }

    
    public void setServiceImplInfo(ServiceImplInfo serviceImplInfo) {
        this.serviceImplInfo = serviceImplInfo;
    }
}
