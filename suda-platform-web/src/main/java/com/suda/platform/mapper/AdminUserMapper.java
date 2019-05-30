package com.suda.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suda.platform.VO.AdminUserVO;
import com.suda.platform.entity.AdminUser;

import java.util.List;

;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 张龙飞
 * @since 2019-04-16
 */
public interface AdminUserMapper extends BaseMapper<AdminUser> {

    /**
     * 管理员查询
     * @param adminUserVO
     * @return
     */
    List<AdminUserVO> selectByChoice(AdminUserVO adminUserVO);
}
