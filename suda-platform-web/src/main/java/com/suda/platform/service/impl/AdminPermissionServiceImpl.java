package com.suda.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.suda.platform.VO.RolePermission.AdminPermissionMenuList;
import com.suda.platform.VO.RolePermission.AdminPermissionVO;
import com.suda.platform.entity.AdminPermission;
import com.suda.platform.mapper.AdminPermissionMapper;
import com.suda.platform.mapper.AdminRolePermissionMapper;
import com.suda.platform.service.IAdminPermissionService;
import com.util.pageinfoutil.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 菜单资源表 服务实现类
 * </p>
 *
 * @author kongling
 * @since 2019-05-06
 */
@Service
public class AdminPermissionServiceImpl extends ServiceImpl<AdminPermissionMapper, AdminPermission> implements IAdminPermissionService {

    @Autowired
    private   AdminPermissionMapper   adminPermissionMapper;
    @Autowired
    private AdminRolePermissionMapper adminRolePermissionMapper;

    /**
     * 菜单权限列表
     * @param adminPermissionVO
     * @param pageUtil
     * @return
     */
    @Override
    public PageInfo<AdminPermission> selectPermissionsBy(AdminPermissionVO adminPermissionVO, PageUtil pageUtil) {
        PageUtil.page(pageUtil);
        List<AdminPermission>  lists = adminPermissionMapper.selectPermissionsBy(adminPermissionVO);
        return new PageInfo<>(lists);
    }

    /**
     * 删除权限资源
     * @param adminPermission
     */
    @Override
    @Transactional(rollbackFor = {})
    public void delPermissionsById(AdminPermission adminPermission) {
        Map<String,Object> resultMap = Maps.newHashMap();
        resultMap.put("permission_id",adminPermission.getId());
        //删除权限角色表中的内容
        adminRolePermissionMapper.deleteByMap(resultMap);
        //删除权限表资源
        adminPermissionMapper.deleteById(adminPermission.getId());
    }

    /**
     * 整体组装查询菜单权限列表
     * @param pageUtil
     * @return
     */
    @Override
    public PageInfo<AdminPermissionMenuList> selectPermissionsByAll(PageUtil pageUtil) {
        PageUtil.page(pageUtil);
        List<AdminPermissionMenuList>  lists = adminPermissionMapper.selectPermissionsByAll();
        return new PageInfo<>(lists);
    }
}
