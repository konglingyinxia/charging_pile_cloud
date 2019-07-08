package com.suda.platform.service.impl;


import com.suda.platform.service.ICommonService;
import config.advice.CommonException;
import config.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kongling
 * @package com.suda.server.service.impl
 * @date 2019-05-13  18:02
 * @project suda_cloud
 */

@Component
@Slf4j
public class CommonServiceImpl implements ICommonService {
    @Autowired
    RedisUtils redisUtils;

    @Autowired
    private TaskExecutor taskExecutor;


    @Override
    @Transactional(rollbackFor = {})
    public void overPrizeCountdown(Long id) {

    }

    @Override
    public void checkTelToken(String account, String code) {
        // 数据库中查出帐号验证码
        Object saveCode = redisUtils.getSmsRedisMessage(account, code);

        if (null == saveCode) {
            throw new CommonException("请先获取验证码");
        }
        if (!saveCode.toString().equalsIgnoreCase(code)) {
            throw new CommonException("验证码错误");
        }
    }

}
