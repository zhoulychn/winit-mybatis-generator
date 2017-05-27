package com.winit.generator.config;

public class TableConfig {

    private String name;

    private String database;

    private String entityName;

    private RealPath realPath;

    private InterfacePath interfacePath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public RealPath getRealPath() {
        return realPath;
    }

    public void setRealPath(RealPath realPath) {
        this.realPath = realPath;
    }

    public InterfacePath getInterfacePath() {
        return interfacePath;
    }

    public void setInterfacePath(InterfacePath interfacePath) {
        this.interfacePath = interfacePath;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TableConfig{");
        sb.append("name='").append(name).append('\'');
        sb.append(", database='").append(database).append('\'');
        sb.append(", entityName='").append(entityName).append('\'');
        sb.append(", realPath=").append(realPath);
        sb.append(", interfacePath=").append(interfacePath);
        sb.append('}');
        return sb.toString();
    }
}
