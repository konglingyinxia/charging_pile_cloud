package com.suda.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suda.platform.VO.log.LogAdminAgentVO;
import com.suda.platform.entity.LogAdminAgent;

import java.util.List;

/**
 * <p>
 * 系统日志 Mapper 接口
 * </p>
 *
 * @author kongling
 * @since 2019-05-09
 */
public interface LogAdminAgentMapper extends BaseMapper<LogAdminAgent> {

    /**
     * 获取logs
     *
     * @param vo
     * @return
     */
    List<LogAdminAgentVO> getAdminAgentLogs(LogAdminAgentVO vo);
}
