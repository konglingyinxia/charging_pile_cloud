package com.suda.platform.service;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.stockuser.AdminUpdateAssetVo;
import com.suda.platform.VO.stockuser.StockUserLoginVO;
import com.suda.platform.VO.stockuser.StockUserSignInVO;
import com.suda.platform.VO.stockuser.StockUserVO;
import com.suda.platform.entity.StockUser;
import com.util.pageinfoutil.PageUtil;

/**
 * <p>
 * 手机用户表 服务类
 * </p>
 *
 * @author 张龙飞
 * @since 2019-04-16
 */
public interface IStockUserService extends IService<StockUser> {

    /**
     * 用户注册
     *
     * @param stockUser
     */
    public boolean registerUser(StockUser stockUser);

    /**
     * 用户登录
     *
     * @param stockUser
     * @return
     */
    public StockUser loginUser(StockUser stockUser);

    /**
     * 忘记密码（修改）
     *
     * @param tel
     * @param pswd
     * @return
     */
    public boolean rewNewPwd(String tel, String pswd);

    /**
     * 后台查询所有用户
     *
     * @param stockUserVO
     * @param pageUtil
     * @return
     */
    PageInfo<StockUserVO> selectAllStockUser(StockUserVO stockUserVO, PageUtil pageUtil);

    /**
     * 禁用启用用户
     *
     * @param stockUserVO
     * @return
     */
    int updateDisableUser(StockUser stockUserVO);


    /**
     * 根据账号查询登录信息
     * @param account
     * @return
     */
    StockUserLoginVO selectByAccount(String account);

    /**
     * 用户手机号注册
     * 
     * @param vo
     */
    void telRegister(StockUserSignInVO vo);

    /**
     * 修改更新用户密码
     *
     * @param id
     * @param pswd
     */
    void updatePswd(Long id, String pswd);


    /**
     * 后台会员充值扣款
     * @param vo
     * @return
     */
    int updateWallet(AdminUpdateAssetVo vo);

    /**
     * 微信小程序登陆
     * 绑定微信OpenID
     * @param sessionResult
     * @return
     */
    StockUserLoginVO wxLogin(WxMaJscode2SessionResult sessionResult);
}
