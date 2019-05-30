package com.suda.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suda.platform.entity.AdminRole;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author kongling
 * @since 2019-05-05
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    /**
     * 分页查询角色
     * @param adminRole
     * @return
     */
    List<AdminRole> selectRoles(AdminRole adminRole);
}
