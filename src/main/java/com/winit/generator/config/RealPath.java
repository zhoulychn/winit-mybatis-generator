package com.winit.generator.config;

public class RealPath {

    private String projectHome;

    private String entity;

    private String dao;

    private String mapper;

    private String manager;

    private String managerImpl;

    private String serviceImpl;

    private String test;

    public String getProjectHome() {
        return projectHome;
    }

    public void setProjectHome(String projectHome) {
        this.projectHome = projectHome;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getDao() {
        return dao;
    }

    public void setDao(String dao) {
        this.dao = dao;
    }

    public String getMapper() {
        return mapper;
    }

    public void setMapper(String mapper) {
        this.mapper = mapper;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getManagerImpl() {
        return managerImpl;
    }

    public void setManagerImpl(String managerImpl) {
        this.managerImpl = managerImpl;
    }

    public String getServiceImpl() {
        return serviceImpl;
    }

    public void setServiceImpl(String serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RealPath{");
        sb.append("projectHome='").append(projectHome).append('\'');
        sb.append(", entity='").append(entity).append('\'');
        sb.append(", dao='").append(dao).append('\'');
        sb.append(", mapper='").append(mapper).append('\'');
        sb.append(", manager='").append(manager).append('\'');
        sb.append(", managerImpl='").append(managerImpl).append('\'');
        sb.append(", serviceImpl='").append(serviceImpl).append('\'');
        sb.append(", test='").append(test).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
