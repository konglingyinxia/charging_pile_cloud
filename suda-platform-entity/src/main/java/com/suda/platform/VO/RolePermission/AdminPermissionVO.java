package com.suda.platform.VO.RolePermission;

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

/**
 * <p>
 * 菜单资源表
 * </p>
 *
 * @author kongling
 * @since 2019-05-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AdminPermission对象", description="菜单资源表")
public class AdminPermissionVO extends Model<AdminPermissionVO> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "菜单或按钮名字")
    @TableField("menu_name")
    private String menuName;


    @ApiModelProperty(value = "菜单级别  1 一级菜单  2 二级菜单")
    @TableField("menu_lev")
    private Integer menuLev;

    @ApiModelProperty(value = "菜单类型  1 菜单  2按钮")
    @TableField("menu_type")
    private Integer menuType;

    @ApiModelProperty(value = "上级菜单")
    @TableField("parent_id")
    private Long parentId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
