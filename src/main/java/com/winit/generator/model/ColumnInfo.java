package com.winit.generator.model;

public class ColumnInfo {
    private String name;
    private String type;
    private String remark;
    private int len;
    private int precision;
    
    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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

}
