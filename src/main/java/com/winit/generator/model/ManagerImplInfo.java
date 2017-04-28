package com.winit.generator.model;

public class ManagerImplInfo {

    private String      className;

    private String      packageStr;

    private String      daoType;

    private String      entityType;

    private String      managerType;

    private String      voType;

    private String      annotationName;

    private String      managerClassName;

    private String      daoClassName;

    private String      daoVar;

    private String      voClassName;

    private String      entityClassName;

    private EntityInfo  entityInfo;

    private VoInfo      voInfo;

    private DaoInfo     daoInfo;

    private ManagerInfo managerInfo;

    public EntityInfo getEntityInfo() {
        return entityInfo;
    }

    public void setEntityInfo(EntityInfo entityInfo) {
        this.entityInfo = entityInfo;
    }

    public VoInfo getVoInfo() {
        return voInfo;
    }

    public void setVoInfo(VoInfo voInfo) {
        this.voInfo = voInfo;
    }

    public DaoInfo getDaoInfo() {
        return daoInfo;
    }

    public void setDaoInfo(DaoInfo daoInfo) {
        this.daoInfo = daoInfo;
    }

    public ManagerInfo getManagerInfo() {
        return managerInfo;
    }

    public void setManagerInfo(ManagerInfo managerInfo) {
        this.managerInfo = managerInfo;
    }

    public String getPackageStr() {
        return packageStr;
    }

    public void setPackageStr(String packageStr) {
        this.packageStr = packageStr;
    }

    public String getDaoType() {
        return daoType;
    }

    public void setDaoType(String daoType) {
        this.daoType = daoType;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getManagerType() {
        return managerType;
    }

    public void setManagerType(String managerType) {
        this.managerType = managerType;
    }

    public String getVoType() {
        return voType;
    }

    public void setVoType(String voType) {
        this.voType = voType;
    }

    public String getAnnotationName() {
        return annotationName;
    }

    public void setAnnotationName(String annotationName) {
        this.annotationName = annotationName;
    }

    public String getManagerClassName() {
        return managerClassName;
    }

    public void setManagerClassName(String managerClassName) {
        this.managerClassName = managerClassName;
    }

    public String getDaoClassName() {
        return daoClassName;
    }

    public void setDaoClassName(String daoClassName) {
        this.daoClassName = daoClassName;
    }

    public String getDaoVar() {
        return daoVar;
    }

    public void setDaoVar(String daoVar) {
        this.daoVar = daoVar;
    }

    public String getVoClassName() {
        return voClassName;
    }

    public void setVoClassName(String voClassName) {
        this.voClassName = voClassName;
    }

    public String getEntityClassName() {
        return entityClassName;
    }

    public void setEntityClassName(String entityClassName) {
        this.entityClassName = entityClassName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
