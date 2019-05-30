package com.suda.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suda.platform.VO.stockuser.StockUserLoginVO;
import com.suda.platform.VO.stockuser.StockUserVO;
import com.suda.platform.entity.StockUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 手机用户表 Mapper 接口
 * </p>
 *
 * @author 张龙飞
 * @since 2019-04-16
 */
public interface StockUserMapper extends BaseMapper<StockUser> {

    int updateInviAndAccountByStockUserId(@Param("invitationCode") String invitationCode, @Param("userUid") String account, @Param("id") Long stockUserId);

    int rewNewPwd(@Param("tel") String tel, @Param("pswd") String pswd);

    /**
     * 后台查询所有用户
     * @param stockUserVO
     * @return
     */
    List<StockUserVO> selectAllStockUser(StockUserVO stockUserVO);

    /**
     * 查询登录账号信息
     *
     * @param account
     * @return
     */
    StockUserLoginVO selectByAccount(@Param("account") String account);

    /**
     * 用户注册保存
     *
     * @param user
     * @return
     */
    int insertSelectiveDullUnion(StockUser user);
}
