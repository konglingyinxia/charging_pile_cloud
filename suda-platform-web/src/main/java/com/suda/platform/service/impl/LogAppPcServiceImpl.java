package com.suda.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.log.LogAppPcVO;
import com.suda.platform.entity.LogAppPc;
import com.suda.platform.mapper.LogAppPcMapper;
import com.suda.platform.service.ILogAppPcService;
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
public class LogAppPcServiceImpl extends ServiceImpl<LogAppPcMapper, LogAppPc> implements ILogAppPcService {
    @Autowired
    private LogAppPcMapper logAppPcMapper;

    /**
     * 获取app 操作日志
     * @param vo
     * @param pageUtil
     * @return
     */
    @Override
    public PageInfo<LogAppPcVO> getAdminAgentLogs(LogAppPcVO vo, PageUtil pageUtil) {
        PageUtil.page(pageUtil);
        List<LogAppPcVO> lists = logAppPcMapper.getAdminAgentLogs(vo);
        return new PageInfo<>(lists);
    }
}
