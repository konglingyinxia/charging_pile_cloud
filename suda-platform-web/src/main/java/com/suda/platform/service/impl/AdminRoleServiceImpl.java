package com.suda.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.suda.platform.entity.AdminRole;
import com.suda.platform.entity.AdminUserRole;
import com.suda.platform.mapper.AdminRoleMapper;
import com.suda.platform.mapper.AdminRolePermissionMapper;
import com.suda.platform.mapper.AdminUserMapper;
import com.suda.platform.mapper.AdminUserRoleMapper;
import com.suda.platform.service.IAdminRoleService;
import com.util.Respons.ResponseMsg;
import com.util.StringUtils;
import com.util.pageinfoutil.PageUtil;
import config.advice.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author kongling
 * @since 2019-05-05
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements IAdminRoleService {

    @Autowired
    private  AdminRoleMapper adminRoleMapper;
    @Autowired
    private AdminUserMapper adminUserMapper;
    @Autowired
    private AdminUserRoleMapper adminUserRoleMapper;
    @Autowired
    private AdminRolePermissionMapper adminRolePermissionMapper;


    /**
     * 分页查询角色
     * @param adminRole
     * @param pageUtil
     * @return
     */
    @Override
    public PageInfo<AdminRole> selectRoles(AdminRole adminRole, PageUtil pageUtil) {
        PageUtil.page(pageUtil);
        List<AdminRole> list = adminRoleMapper.selectRoles(adminRole);
        return  new PageInfo<>(list);
    }

    /**
     * 添加角色
     *
     * @param adminRole
     */
    @Override
    public void addRole(AdminRole adminRole) {
        /**
         *  查询角色是否存在
         */
        List<AdminRole> lists = adminRoleMapper.selectRoles(adminRole);
        if(lists.size()>0){
            throw new CommonException(ResponseMsg.ROLE_HAVE_EXIST);
        }
        adminRole.setCreateTime(new Date());
        adminRole.setRoleValue(StringUtils.getUUID());
        adminRoleMapper.insert(adminRole);
    }
    /**
     * 删除角色
     *
     * @param adminRole
     */
    @Override
    @Transactional(rollbackFor = {})
    public void delRole(AdminRole adminRole) {
        /**
         *  查询是否有用户占用该角色
         */
        Map<String,Object> resultMap = Maps.newHashMap();
        resultMap.put("role_id",adminRole.getId());

        List<AdminUserRole> lists = adminUserRoleMapper.selectByMap(resultMap);
        if(lists.size()>0){
            throw new CommonException(ResponseMsg.ROLE_HAVE_OCCUPY_NO_DEL);
        }
        adminRoleMapper.deleteById(adminRole.getId());
        //删除权限
        adminRolePermissionMapper.deleteByMap(resultMap);

    }

    /**
     * 角色重新授权
     * @param roleId
     * @param permissionIds
     */

    @Override
    @Transactional(rollbackFor = {})
    public void addRolePermission(Long roleId, String permissionIds) {
        List<Long> permissionIdsArry =(List<Long>)(Object) Arrays.asList(permissionIds.split(",|，"));
        /**
         *  查询是否有用户占用该角色
         */
        Map<String,Object> resultMap = Maps.newHashMap();
        resultMap.put("role_id",roleId);
        adminRolePermissionMapper.deleteByMap(resultMap);
        int  insertNum = adminRolePermissionMapper.insertLists(roleId,permissionIdsArry);

    }

    /**
     * 角色编辑
     * @param adminRole
     */
    @Override
    public void updateRole(AdminRole adminRole) {
        /**
         *  查询角色是否存在
         */
        List<AdminRole> lists = adminRoleMapper.selectRoles(adminRole);
        if(lists.size()>1){
            throw new CommonException(ResponseMsg.ROLE_HAVE_EXIST);
        }
        adminRole.setAdminUserId(null);
        adminRole.setRoleValue(null);
        adminRoleMapper.updateById(adminRole);
    }
}
