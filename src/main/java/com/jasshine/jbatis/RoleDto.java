package com.jasshine.jbatis;

/**
 * Created by ecuser on 2016/2/25.
 */
public class RoleDto {
    private int id;
    private String roleName;
    private String resid;
    private String createTime;

    public int getId() {
        return id;
    }

    public RoleDto setId(int id) {
        this.id = id;
        return this;
    }

    public String getRoleName() {
        return roleName;
    }

    public RoleDto setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public String getResid() {
        return resid;
    }

    public RoleDto setResid(String resid) {
        this.resid = resid;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public RoleDto setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }
}
