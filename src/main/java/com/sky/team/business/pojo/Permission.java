package com.sky.team.business.pojo;

import java.io.Serializable;

public class Permission implements Serializable {
    private String permissionId;
    private String permissionName;
    private String permissionznName;

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionznName() {
        return permissionznName;
    }

    public void setPermissionznName(String permissionznName) {
        this.permissionznName = permissionznName;
    }
}
