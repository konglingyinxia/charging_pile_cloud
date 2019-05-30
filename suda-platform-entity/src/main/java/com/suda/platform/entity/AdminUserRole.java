package com.suda.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 角色用户关联表
 * </p>
 *
 * @author 张龙飞
 * @since 2019-04-16
 */
@ApiModel(value="AdminUserRole对象", description="角色用户关联表")
public class AdminUserRole extends Model<AdminUserRole> {

    public AdminUserRole() {
    }

    /**
     *
     * @param roleId 角色
     * @param userId 用户
     */
    public AdminUserRole(Long roleId, Long userId) {
        this.roleId = roleId;
        this.userId = userId;
    }

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色id")
    @TableField("role_id")
    private Long roleId;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "AdminUserRole{" +
        "roleId=" + roleId +
        ", userId=" + userId +
        "}";
    }
}
