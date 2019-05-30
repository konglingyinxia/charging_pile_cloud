package com;

import com.constant.CommonConstant;

import java.math.BigDecimal;

/**
 * 用户账号信息
 *
 * @author kongling
 * @package com
 * @date 2019-05-16  17:55
 * @project suda_cloud
 */
public class Account {
    /**
     * 获取用户uid
     */
    public  static String getUserUid(Long id){
        return CommonConstant.project+(Long.valueOf(CommonConstant.ACCOUNT_PREFIX) + id);
    }

    /**
     * 获取邀请码
     *
     * @param id
     * @return
     */
    public  static String getInvitationCode(Long id){
        return CommonConstant.project+(new BigDecimal(id).add(new BigDecimal(1000))).toString();
    }

}
