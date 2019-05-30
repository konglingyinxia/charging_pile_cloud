package com.suda.platform.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.AdminUserVO;
import com.suda.platform.entity.AdminUser;
import com.util.pageinfoutil.PageUtil;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张龙飞
 * @since 2019-04-16
 */
public interface IAdminUserService extends IService<AdminUser> {

    /**
     * 登录方法
     * @param account
     * @param password
     * @return
     */
    AdminUser selectByAccountLogin(String account, String password);

    /**
     * 分页查询管理员列表
     * @param adminUserVO
     * @param pageUtil
     * @return
     */
    PageInfo<AdminUserVO> selectByChoice(AdminUserVO adminUserVO, PageUtil pageUtil);

    /**
     * 禁用启用管理员
     * @param adminUserVO
     */
    void adminUserDisable(AdminUserVO adminUserVO);

    /**
     * 角色编辑
     * @param adminUserVO
     */
    void editUserRole(AdminUserVO adminUserVO);
}
