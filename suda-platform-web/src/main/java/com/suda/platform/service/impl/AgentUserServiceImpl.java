package com.suda.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.agentuser.AgentUserVO;
import com.suda.platform.entity.AgentUser;
import com.suda.platform.mapper.AgentUserMapper;
import com.suda.platform.service.IAgentUserService;
import com.util.pageinfoutil.PageUtil;
import config.advice.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private AgentUserMapper agentUserMapper;

    @Override
    public AgentUser selectByAccountLogin(String account, String password) {
        AgentUser  vo = baseMapper.selectOne(new QueryWrapper<AgentUser>()
                .eq("account",account)
                .eq("password",password)
        .eq("is_delete",0));
        if(vo==null){
            throw new CommonException("用户账号或密码错误");
        }
        return vo;
    }

    /**
     * 查询代理商用户
     *
     * @param vo
     * @param pageUtil
     * @return
     */
    @Override
    public PageInfo<AgentUserVO> selectByChoice(AgentUserVO vo, PageUtil pageUtil) {
        PageUtil.page(pageUtil);
        List<AgentUserVO> vos = agentUserMapper.selectByChoice(vo);
        return new PageInfo<>(vos);
    }

}
