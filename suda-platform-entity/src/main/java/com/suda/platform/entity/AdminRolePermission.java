package com.suda.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 角色菜单关联表
 * </p>
 *
 * @author kongling
 * @since 2019-05-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AdminRolePermission对象", description="角色菜单关联表")
public class AdminRolePermission extends Model<AdminRolePermission> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "角色ID")
    @TableField("role_id")
    private Long roleId;

    @ApiModelProperty(value = "菜单ID")
    @TableField("permission_id")
    private Long permissionId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
