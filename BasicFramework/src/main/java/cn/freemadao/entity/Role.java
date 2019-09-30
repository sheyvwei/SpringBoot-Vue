package cn.freemadao.entity;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {
    private Integer id;
    private String roleName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private Char Status;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", RoleName='" + roleName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", Status=" + Status +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        roleName = roleName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Char getStatus() {
        return Status;
    }

    public void setStatus(Char status) {
        Status = status;
    }
}
