package com.suda.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.suda.platform.VO.AdminUserVO;
import com.suda.platform.entity.AdminUser;
import com.suda.platform.entity.AdminUserRole;
import com.suda.platform.mapper.AdminUserMapper;
import com.suda.platform.mapper.AdminUserRoleMapper;
import com.suda.platform.service.IAdminUserService;
import com.util.pageinfoutil.PageUtil;
import config.advice.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 张龙飞
 * @since 2019-04-16
 */
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements IAdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;
    @Autowired
    private AdminUserRoleMapper adminUserRoleMapper;



    @Override
    public AdminUser selectByAccountLogin(String account, String password) {
        AdminUser  vo = adminUserMapper.selectOne(new QueryWrapper<AdminUser>()
                .eq("account",account)
                .eq("password",password));
        if(vo==null){
            throw new CommonException("用户账号或密码错误");
        }
        return vo;
    }

    @Override
    public PageInfo<AdminUserVO> selectByChoice(AdminUserVO adminUserVO, PageUtil pageUtil) {
        PageUtil.page(pageUtil);
        List<AdminUserVO> lists = adminUserMapper.selectByChoice(adminUserVO);
        return new PageInfo<>(lists);
    }

    @Override
    public void adminUserDisable(AdminUserVO adminUserVO) {
        AdminUser adminUser = new AdminUser();
        adminUser.setId(adminUserVO.getId());
        adminUser.setIsDisable(adminUserVO.getIsDisable());
        adminUserMapper.updateById(adminUser);
    }

    /**
     * 角色编辑
     * @param adminUserVO
     */
    @Override
    @Transactional(rollbackFor = {})
    public void editUserRole(AdminUserVO adminUserVO) {
        //删除原有用户角色
        Map<String,Object> resultMap = Maps.newHashMap();
        resultMap.put("user_id",adminUserVO.getId());
        adminUserRoleMapper.deleteByMap(resultMap);
        adminUserRoleMapper.insert(new AdminUserRole(adminUserVO.getRoleId(),adminUserVO.getId()));
    }
}
