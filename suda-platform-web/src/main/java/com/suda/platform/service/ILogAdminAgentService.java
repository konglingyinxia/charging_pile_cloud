package com.suda.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.log.LogAdminAgentVO;
import com.suda.platform.entity.LogAdminAgent;
import com.util.pageinfoutil.PageUtil;

/**
 * <p>
 * 系统日志 服务类
 * </p>
 *
 * @author kongling
 * @since 2019-05-09
 */
public interface ILogAdminAgentService extends IService<LogAdminAgent> {

    /**
     * 后台日志查看
     *
     * @param vo
     * @param pageUtil
     * @return
     */
    PageInfo<LogAdminAgentVO> getAdminAgentLogs(LogAdminAgentVO vo, PageUtil pageUtil);
}
