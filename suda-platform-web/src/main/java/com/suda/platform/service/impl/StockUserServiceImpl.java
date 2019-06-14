package com.suda.platform.service.impl;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.Account;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.stockuser.AdminUpdateAssetVo;
import com.suda.platform.VO.stockuser.StockUserLoginVO;
import com.suda.platform.VO.stockuser.StockUserSignInVO;
import com.suda.platform.VO.stockuser.StockUserVO;
import com.suda.platform.entity.StockUser;
import com.suda.platform.entity.StockUserCapitalFund;
import com.suda.platform.enums.finance.FinancialTypeEnum;
import com.suda.platform.enums.finance.PayTypeEnum;
import com.suda.platform.enums.finance.WaterTypeEnum;
import com.suda.platform.enums.finance.WithdrawStatusEnum;
import com.suda.platform.mapper.StockUserMapper;
import com.suda.platform.service.IStockUserCapitalFundService;
import com.suda.platform.service.IStockUserChargeService;
import com.suda.platform.service.IStockUserMoneyDetailService;
import com.suda.platform.service.IStockUserService;
import com.util.DealDateUtil;
import com.util.Respons.ResponseMsg;
import com.util.pageinfoutil.PageUtil;
import config.advice.CommonException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    @Autowired
    private IStockUserCapitalFundService stockUserCapitalFundService;
    @Autowired
    private IStockUserMoneyDetailService stockUserMoneyDetailService;
    @Autowired
    private IStockUserChargeService stockUserChargeService;


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

    /**
     * 会员后台充值扣款管理
     *
     * @param vo
     * @return
     */
    @Override
    @Transactional(rollbackFor = {})
    public int updateWallet(AdminUpdateAssetVo vo) {
        String stockCode = vo.getStockCode();
        Long id = vo.getId();
        Byte operation = vo.getOperation();
        BigDecimal money = vo.getMoney();

        int status=0;
        StockUserCapitalFund stockUserCapitalFund = stockUserCapitalFundService.upAndSelectFund(id, stockCode,vo.getAgentUserId());
        switch (operation.intValue()) {
            //充值
            case 1:
                //更新账户资产
                int i =  stockUserCapitalFundService.updateRechargeByCodeId(stockCode,id,money);
                //资产流水
                if(i>0){
                    stockUserMoneyDetailService.addUserMoneyDetail(
                            id,
                            money,
                            stockUserCapitalFund.getUsableFund(),
                            WaterTypeEnum.STATUS_1.getCode(),
                            FinancialTypeEnum.TYPE_1,vo.getRemark(),
                            null,
                            stockCode);
                }
                status =i;
                break;
            //扣款
            case 2:
                BigDecimal usableFund = stockUserCapitalFund.getUsableFund();
                if(money.compareTo(usableFund)>0){
                    throw new CommonException(String.format(ResponseMsg.DEAL_COIN_LITTER,stockCode));
                }
                //更新账户资产
                int j =  stockUserCapitalFundService.updateRechargeByCodeId(stockCode,id,money);
                //资产流水
                if(j>0) {
                    stockUserMoneyDetailService.addUserMoneyDetail(
                            id,
                            money.multiply(new BigDecimal(-1)),
                            stockUserCapitalFund.getUsableFund(),
                            WaterTypeEnum.STATUS_1.getCode(),
                            FinancialTypeEnum.TYPE_1, vo.getRemark(),
                            null,
                            stockCode);
                }
                status =j;
                break;
            default:
                throw new CommonException(ResponseMsg.ADMIN_MANAGE_RECHARGE_OPERATION);
        }
        if(status !=0){
            //添加充值记录
            stockUserChargeService.addChargeRecord(vo.getAgentUserId(),id, money,stockCode, PayTypeEnum.STATUS_2, WithdrawStatusEnum.STATUS_2);
        }
        return status;
    }

    /**
     * 微信小程序登陆
     * 绑定微信OpenID
     * @param sessionResult
     * @return
     */
    @Override
    @Transactional(rollbackFor = {})
    public StockUserLoginVO wxLogin(WxMaJscode2SessionResult sessionResult) {
        StockUser stockUser = stockUserMapper.selectOne(new QueryWrapper<StockUser>()
        .eq("open_id",sessionResult.getOpenid()));
        if(stockUser ==null){
            stockUser = new StockUser();
            stockUser.setOpenId(sessionResult.getOpenid());
            stockUser.setCreateTime(DealDateUtil.getNowDate());
           Integer insert = stockUserMapper.insertSelectiveDullUnion(stockUser);
            if (insert == 0) {
                throw new CommonException(ResponseMsg.USER_HAS_EXIST);
            }
            stockUser.setUserUid(Account.getUserUid(stockUser.getId()));
            stockUserMapper.updateById(stockUser);
        }
        StockUserLoginVO loginVO = new StockUserLoginVO();
        BeanUtils.copyProperties(stockUser,loginVO);
        return loginVO;
    }

}
