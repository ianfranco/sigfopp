package com.areatecnica.sigf.util;


import java.io.Serializable;

/**
 *
 * author Omer Faruk KURT e-mail:kurtomerfaruk@gmail.com
 */

public class ColumnSettings implements Serializable {

    private static final long serialVersionUID = 1L;
    private Short columnSettingsId;
    private String tableName;
    private String columnName;
    private boolean visible;
    private short columnQueue;
    private Integer width;

    public ColumnSettings() {
    }

    public ColumnSettings(Short columnSettingsId) {
        this.columnSettingsId = columnSettingsId;
    }

    public ColumnSettings(Short columnSettingsId, String columnName, boolean visible, short columnQueue) {
        this.columnSettingsId = columnSettingsId;
        this.columnName = columnName;
        this.visible = visible;
        this.columnQueue = columnQueue;
    }

    public Short getColumnSettingsId() {
        return columnSettingsId;
    }

    public void setColumnSettingsId(Short columnSettingsId) {
        this.columnSettingsId = columnSettingsId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public short getColumnQueue() {
        return columnQueue;
    }

    public void setColumnQueue(short columnQueue) {
        this.columnQueue = columnQueue;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (columnSettingsId != null ? columnSettingsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ColumnSettings)) {
            return false;
        }
        ColumnSettings other = (ColumnSettings) object;
        return (this.columnSettingsId != null || other.columnSettingsId == null) && (this.columnSettingsId == null || this.columnSettingsId.equals(other.columnSettingsId));
    }

    @Override
    public String toString() {
        return "com.kurtomerfaruk.mavenproject1.ColumnSettings[ columnSettingsId=" + columnSettingsId + " ]";
    }

}
