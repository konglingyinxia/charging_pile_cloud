package com.suda.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author kongling
 * @since 2019-05-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AdminRole对象", description="角色表")
public class AdminRole extends Model<AdminRole> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("role_parent_id")
    private Long roleParentId;

    @ApiModelProperty(value = "角色名")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty(value = "角色编码")
    @TableField("role_value")
    private String roleValue;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "角色描述")
    @TableField("details")
    private String details;

    @ApiModelProperty(value = "1:最后一级代理，可创建会员，0:不是最后一级")
    @TableField("last")
    private Boolean last;

    @ApiModelProperty(value = "所属管理员下级管理员角色")
    @TableField("admin_user_id")
    private Long adminUserId;

    @TableField("timestamp")
    private Date timestamp;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
