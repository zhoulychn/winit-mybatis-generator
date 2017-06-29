package com.winit.generator.model;

import java.util.List;

public class TableInfo {

    private String           name;
    private String           type;
    private String           remark;
    private List<ColumnInfo> columnList;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<ColumnInfo> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<ColumnInfo> columnList) {
        this.columnList = columnList;
    }

    @Override
    public String toString() {
        return "TableInfo [name=" + name + ", type=" + type + ", remark=" + remark + ", columnList=" + columnList + "]";
    }
}
