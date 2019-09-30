package cn.freemadao.entity;

import java.io.Serializable;

public class Permission implements Serializable {
    private Integer id;
    private String menuCode;
    private String menuName;
    private String permissionCode;
    private String permissionName;
    private String requiredPermission;

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", menuCode='" + menuCode + '\'' +
                ", menuName='" + menuName + '\'' +
                ", permissionCode='" + permissionCode + '\'' +
                ", permissionName='" + permissionName + '\'' +
                ", requiredPermission='" + requiredPermission + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getRequiredPermission() {
        return requiredPermission;
    }

    public void setRequiredPermission(String requiredPermission) {
        this.requiredPermission = requiredPermission;
    }
}
