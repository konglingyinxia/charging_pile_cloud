package com.suda.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suda.platform.VO.agentuser.AgentUserVO;
import com.suda.platform.entity.AgentUser;

import java.util.List;

/**
 * <p>
 * 代理商用户表 Mapper 接口
 * </p>
 *
 * @author kongling
 * @since 2019-06-13
 */
public interface AgentUserMapper extends BaseMapper<AgentUser> {

    /**
     * 代理商查询
     *
     * @param vo
     * @return
     */
    List<AgentUserVO> selectByChoice(AgentUserVO vo);
}
