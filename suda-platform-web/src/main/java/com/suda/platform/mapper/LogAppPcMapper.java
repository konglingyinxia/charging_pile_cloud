package com.suda.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suda.platform.VO.log.LogAppPcVO;
import com.suda.platform.entity.LogAppPc;

import java.util.List;

/**
 * <p>
 * 系统日志 Mapper 接口
 * </p>
 *
 * @author kongling
 * @since 2019-05-09
 */
public interface LogAppPcMapper extends BaseMapper<LogAppPc> {

    /**
     * 获取app 操作日志
     *
     * @param vo
     * @return
     */
    List<LogAppPcVO> getAdminAgentLogs(LogAppPcVO vo);
}
