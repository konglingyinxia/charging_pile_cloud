package com.suda.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.suda.platform.entity.AdminRole;
import com.util.pageinfoutil.PageUtil;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author kongling
 * @since 2019-05-05
 */
public interface IAdminRoleService extends IService<AdminRole> {

    /**
     * 分页查询角色
     * @param adminRole
     * @param pageUtil
     * @return
     */
    PageInfo<AdminRole> selectRoles(AdminRole adminRole, PageUtil pageUtil);

    /**
     * 添加角色
     * @param adminRole
     */
    void addRole(AdminRole adminRole);

    /**
     * 删除角色
     * @param adminRole
     */
    void delRole(AdminRole adminRole);

    /**
     * 角色重新授权
     * @param roleId
     * @param permissionIds
     */
    void addRolePermission(Long roleId, String permissionIds);

    /**
     * 角色编辑
     * @param adminRole
     */
    void updateRole(AdminRole adminRole);
}
