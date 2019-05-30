package com.suda.platform.service;

/**
 * @author kongling
 * @package com.suda.server.service
 * @date 2019-05-13  18:02
 * @project suda_cloud
 */
public interface ICommonService {
    /**
     * 活动商品倒计时结束 设置中奖人员
     *
     * @param id 活动商品id
     */
    void overPrizeCountdown(Long id);

    /**
     * 验证码验证
     *
     * @param account
     * @param code
     */
    void checkTelToken(String account, String code);

}
