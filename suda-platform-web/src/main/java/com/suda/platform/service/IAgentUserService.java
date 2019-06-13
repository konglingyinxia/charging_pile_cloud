package com.suda.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suda.platform.entity.AgentUser;

/**
 * <p>
 * 代理商用户表 服务类
 * </p>
 *
 * @author kongling
 * @since 2019-06-13
 */
public interface IAgentUserService extends IService<AgentUser> {

    AgentUser selectByAccountLogin(String account, String password);
}
