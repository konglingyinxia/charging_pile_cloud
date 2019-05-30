package com.suda.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suda.platform.VO.RolePermission.AdminPermissionMenuList;
import com.suda.platform.VO.RolePermission.AdminPermissionVO;
import com.suda.platform.entity.AdminPermission;

import java.util.List;


/**
 * <p>
 * 菜单资源表 Mapper 接口
 * </p>
 *
 * @author kongling
 * @since 2019-05-06
 */
public interface AdminPermissionMapper extends BaseMapper<AdminPermission> {

    /**
     * 查询权限列表
     * @param adminPermissionVO
     * @return
     */
    List<AdminPermission> selectPermissionsBy(AdminPermissionVO adminPermissionVO);

    /**
     * 整体查询组装权限
     * @return
     */
    List<AdminPermissionMenuList> selectPermissionsByAll();
}
