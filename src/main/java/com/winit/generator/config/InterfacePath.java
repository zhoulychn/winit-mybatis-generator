package com.winit.generator.config;

public class InterfacePath {

    private String projectHome;

    private String service;

    private String vo;

    private String command;

    public String getProjectHome() {
        return projectHome;
    }

    public void setProjectHome(String projectHome) {
        this.projectHome = projectHome;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getVo() {
        return vo;
    }

    public void setVo(String vo) {
        this.vo = vo;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InterfacePath{");
        sb.append("projectHome='").append(projectHome).append('\'');
        sb.append(", service='").append(service).append('\'');
        sb.append(", vo='").append(vo).append('\'');
        sb.append(", command='").append(command).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
