package com.suda.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 张龙飞
 * @since 2019-04-16
 */
@ApiModel(value="AdminUser对象", description="")
public class AdminUser extends Model<AdminUser> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "帐号")
    @TableField("account")
    private String account;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "添加时间")
    @TableField("create_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Long createTime;

    @ApiModelProperty(value = "最后登录时间")
    @TableField("last_login_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;

    @TableField("timestamp")
    private Date timestamp;

    @ApiModelProperty(value = "其他状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "0:有效 1:禁止登录")
    @TableField("is_disable")
    private Boolean isDisable;

    @ApiModelProperty(value = "1:删除，0:未删除")
    @TableField("is_delete")
    private Boolean isDelete;

    @ApiModelProperty(value = "父级管理员id")
    @TableField("admin_parent_id")
    private Long adminParentId;

    @ApiModelProperty(value = "父级分销代理商id")
    @TableField("agent_parent_id")
    private Long agentParentId;

    @ApiModelProperty(value = "代理商邀请码")
    @TableField("user_invite_code")
    private String userInviteCode;

    @ApiModelProperty(value = "推荐邀请关系树")
    @TableField("invitation_path")
    private String invitationPath;

    @ApiModelProperty(value = "审核状态(1-待审核;2-已审核;3-拒绝)")
    @TableField("inspect_status")
    private Integer inspectStatus;

    @TableField(exist = false)
    private String sessionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Boolean getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(Boolean isDisable) {
        this.isDisable = isDisable;
    }
    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
    public Long getAdminParentId() {
        return adminParentId;
    }

    public void setAdminParentId(Long adminParentId) {
        this.adminParentId = adminParentId;
    }
    public Long getAgentParentId() {
        return agentParentId;
    }

    public void setAgentParentId(Long agentParentId) {
        this.agentParentId = agentParentId;
    }
    public String getUserInviteCode() {
        return userInviteCode;
    }

    public void setUserInviteCode(String userInviteCode) {
        this.userInviteCode = userInviteCode;
    }
    public String getInvitationPath() {
        return invitationPath;
    }

    public void setInvitationPath(String invitationPath) {
        this.invitationPath = invitationPath;
    }
    public Integer getInspectStatus() {
        return inspectStatus;
    }

    public void setInspectStatus(Integer inspectStatus) {
        this.inspectStatus = inspectStatus;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "AdminUser{" +
        "id=" + id +
        ", account=" + account +
        ", password=" + password +
        ", nickname=" + nickname +
        ", username=" + username +
        ", createTime=" + createTime +
        ", lastLoginTime=" + lastLoginTime +
        ", timestamp=" + timestamp +
        ", status=" + status +
        ", isDisable=" + isDisable +
        ", isDelete=" + isDelete +
        ", adminParentId=" + adminParentId +
        ", agentParentId=" + agentParentId +
        ", userInviteCode=" + userInviteCode +
        ", invitationPath=" + invitationPath +
        ", inspectStatus=" + inspectStatus +
        "}";
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
