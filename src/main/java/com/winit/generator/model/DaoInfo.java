package com.winit.generator.model;

public class DaoInfo {

    /**
     * 包路径
     */
    private String     packageStr;

    /**
     * 需要导入的包
     */
    private String     importStr;

    /**
     * 类名
     */
    private String     className;

    /**
     * 实体信息
     */
    private EntityInfo entityInfo;

    public String getPackageStr() {
        return packageStr;
    }

    public void setPackageStr(String packageStr) {
        this.packageStr = packageStr;
    }

    public String getImportStr() {
        return importStr;
    }

    public void setImportStr(String importStr) {
        this.importStr = importStr;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public EntityInfo getEntityInfo() {
        return entityInfo;
    }

    public void setEntityInfo(EntityInfo entityInfo) {
        this.entityInfo = entityInfo;
    }
}
