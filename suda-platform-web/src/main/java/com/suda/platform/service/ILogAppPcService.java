package com.suda.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.log.LogAppPcVO;
import com.suda.platform.entity.LogAppPc;
import com.util.pageinfoutil.PageUtil;

/**
 * <p>
 * 系统日志 服务类
 * </p>
 *
 * @author kongling
 * @since 2019-05-09
 */
public interface ILogAppPcService extends IService<LogAppPc> {

    /**
     * 获取app客户操作日志
     *
     * @param vo
     * @param pageUtil
     * @return
     */
    PageInfo<LogAppPcVO> getAdminAgentLogs(LogAppPcVO vo, PageUtil pageUtil);
}
