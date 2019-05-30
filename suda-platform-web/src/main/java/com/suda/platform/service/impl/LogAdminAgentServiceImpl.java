package com.suda.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.log.LogAdminAgentVO;
import com.suda.platform.entity.LogAdminAgent;
import com.suda.platform.mapper.LogAdminAgentMapper;
import com.suda.platform.service.ILogAdminAgentService;
import com.util.pageinfoutil.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author kongling
 * @since 2019-05-09
 */
@Service
public class LogAdminAgentServiceImpl extends ServiceImpl<LogAdminAgentMapper, LogAdminAgent> implements ILogAdminAgentService {

    @Autowired
    private  LogAdminAgentMapper logAdminAgentMapper;
    /**
     * 后台日志查看
     * @param vo
     * @param pageUtil
     * @return
     */
    @Override
    public PageInfo<LogAdminAgentVO> getAdminAgentLogs(LogAdminAgentVO vo, PageUtil pageUtil) {
        PageUtil.page(pageUtil);
        List<LogAdminAgentVO> lists = logAdminAgentMapper.getAdminAgentLogs(vo);

        return new PageInfo<>(lists);
    }
}
