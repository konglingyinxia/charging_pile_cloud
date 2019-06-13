package com.suda.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suda.platform.entity.AgentUser;
import com.suda.platform.mapper.AgentUserMapper;
import com.suda.platform.service.IAgentUserService;
import config.advice.CommonException;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 代理商用户表 服务实现类
 * </p>
 *
 * @author kongling
 * @since 2019-06-13
 */
@Service
public class AgentUserServiceImpl extends ServiceImpl<AgentUserMapper, AgentUser> implements IAgentUserService {


    @Override
    public AgentUser selectByAccountLogin(String account, String password) {
        AgentUser  vo = baseMapper.selectOne(new QueryWrapper<AgentUser>()
                .eq("account",account)
                .eq("password",password));
        if(vo==null){
            throw new CommonException("用户账号或密码错误");
        }
        return vo;
    }

}
