package com.winit.generator.config;

import java.util.Map;

public class Config {

    private Map<String, Connector> connectors;

    public Map<String, Connector> getConnectors() {
        return connectors;
    }

    public void setConnectors(Map<String, Connector> connectors) {
        this.connectors = connectors;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Config{");
        sb.append("connectors=").append(connectors);
        sb.append('}');
        return sb.toString();
    }
}
