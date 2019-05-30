package com.suda.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suda.platform.entity.AdminRolePermission;
import com.suda.platform.mapper.AdminRolePermissionMapper;
import com.suda.platform.service.IAdminRolePermissionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色菜单关联表 服务实现类
 * </p>
 *
 * @author kongling
 * @since 2019-05-06
 */
@Service
public class AdminRolePermissionServiceImpl extends ServiceImpl<AdminRolePermissionMapper, AdminRolePermission> implements IAdminRolePermissionService {

}
