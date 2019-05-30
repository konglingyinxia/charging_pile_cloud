package com.suda.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suda.platform.entity.StockUser;
import com.suda.platform.entity.StockUserInfo;

/**
 * <p>
 * 手机用户信息表 服务类
 * </p>
 *
 * @author kongling
 * @since 2019-05-18
 */
public interface IStockUserInfoService extends IService<StockUserInfo> {

    /**
     * 用户实名认证
     * @param userinfo
     * @param stockUser
     * @return
     */
    boolean openAccount(StockUserInfo userinfo, StockUser stockUser);
}
