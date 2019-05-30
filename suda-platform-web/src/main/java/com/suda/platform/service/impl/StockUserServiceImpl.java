package com.suda.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.stockuser.StockUserLoginVO;
import com.suda.platform.VO.stockuser.StockUserSignInVO;
import com.suda.platform.VO.stockuser.StockUserVO;
import com.suda.platform.entity.StockUser;
import com.suda.platform.mapper.StockUserMapper;
import com.suda.platform.service.IStockUserService;
import com.util.pageinfoutil.PageUtil;
import config.advice.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 手机用户表 服务实现类
 * </p>
 *
 * @author 张龙飞
 * @since 2019-04-16
 */
@Service
public class StockUserServiceImpl extends ServiceImpl<StockUserMapper, StockUser> implements IStockUserService {


    @Autowired
    private StockUserMapper stockUserMapper;


    /**
     * 用户注册
     *
     * @param stockUser tel   invitationCode
     * @return
     */
    @Override
    @Transactional(rollbackFor = {})
    public boolean registerUser(StockUser stockUser) {
       return true;
    }

    /**
     * 登录
     *
     * @param stockUser
     * @return
     */
    @Override
    public StockUser loginUser(StockUser stockUser) {
        // 查询账号信息
        StockUser selUser = stockUserMapper.selectOne(new QueryWrapper<StockUser>().eq("tel", stockUser.getTel()).eq("pswd", stockUser.getPswd()));
        if (null == selUser) {
            throw new CommonException("帐号或密码错误！");
        }
        if (selUser.getIsDeleted()) {
            throw new CommonException("帐号已被冻结");
        }
        return selUser;
    }

    /**
     * 忘记密码（修改）
     *
     * @param tel
     * @param pswd
     * @return
     */
    @Override
    public boolean rewNewPwd(String tel, String pswd) {
        return stockUserMapper.rewNewPwd(tel, pswd) > 0;
    }

    /**
     * 后台查询所有用户
     *
     * @param stockUserVO
     * @param pageUtil
     * @return
     */
    @Override
    public PageInfo<StockUserVO> selectAllStockUser(StockUserVO stockUserVO, PageUtil pageUtil) {
        PageUtil.page(pageUtil);
        List<StockUserVO> lists = stockUserMapper.selectAllStockUser(stockUserVO);
        return new PageInfo<>(lists);
    }

    /**
     * 用户禁用启用
     *
     * @param stockUserVO
     * @return
     */
    @Override
    public int updateDisableUser(StockUser stockUserVO) {
        StockUser user = new StockUser();
        user.setId(stockUserVO.getId());
        user.setIsDisable(stockUserVO.getIsDisable());
        return baseMapper.updateById(user);
    }


    /**
     * 根据账号信息查询登录信息
     *
     * @param account
     * @return
     */
    @Override
    public StockUserLoginVO selectByAccount(String account) {
        StockUserLoginVO vo = stockUserMapper.selectByAccount(account);
        return vo;
    }

    /**
     * app 用户注册
     *
     * @param vo
     */
    @Override
    @Transactional(rollbackFor = {})
    public void telRegister(StockUserSignInVO vo) {

    }


    @Override
    public void updatePswd(Long id, String pswd) {
        StockUser user = new StockUser();
        user.setId(id);
        user.setPswd(pswd);
        stockUserMapper.updateById(user);
    }
}
