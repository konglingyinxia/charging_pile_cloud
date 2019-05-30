package com.suda.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.RolePermission.AdminPermissionMenuList;
import com.suda.platform.VO.RolePermission.AdminPermissionVO;
import com.suda.platform.entity.AdminPermission;
import com.util.pageinfoutil.PageUtil;

/**
 * <p>
 * 菜单资源表 服务类
 * </p>
 *
 * @author kongling
 * @since 2019-05-06
 */
public interface IAdminPermissionService extends IService<AdminPermission> {

    /**
     * 菜单权限列表
     * @param adminPermissionVO
     * @param pageUtil
     * @return
     */
    PageInfo<AdminPermission> selectPermissionsBy(AdminPermissionVO adminPermissionVO, PageUtil pageUtil);

    /**
     * 删除权限资源
     * @param adminPermission
     */
    void delPermissionsById(AdminPermission adminPermission);

    /**
     * 整体组装查询菜单权限
     * @param pageUtil
     * @return
     */
    PageInfo<AdminPermissionMenuList> selectPermissionsByAll(PageUtil pageUtil);
}
