package com.winit.generator.config;

import java.util.Map;

public class Connector {

    private String name;

    private String type;

    private String host;

    private String username;

    private String password;

    private Map<String, TableConfig> tables;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, TableConfig> getTables() {
        return tables;
    }

    public void setTables(Map<String, TableConfig> tables) {
        this.tables = tables;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Connector{");
        sb.append("name='").append(name).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", host='").append(host).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", tables=").append(tables);
        sb.append('}');
        return sb.toString();
    }
}
