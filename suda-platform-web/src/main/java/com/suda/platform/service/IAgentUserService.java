package com.suda.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.agentuser.AgentUserVO;
import com.suda.platform.entity.AgentUser;
import com.util.pageinfoutil.PageUtil;

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

    /**
     * 查询代理商
     *
     * @param vo
     * @param pageUtil
     * @return
     */
    PageInfo<AgentUserVO> selectByChoice(AgentUserVO vo, PageUtil pageUtil);
}
