package com.suda.platform.controller.admin;

import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.log.LogAdminAgentVO;
import com.suda.platform.VO.log.LogAppPcVO;
import com.suda.platform.service.ILogAdminAgentService;
import com.suda.platform.service.ILogAppPcService;
import com.util.Respons.ResponseUtil;
import com.util.pageinfoutil.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 后台日志管理
 * @author kongling
 * @package com.suda.admin.controller
 * @date 2019-05-09  14:16
 * @project suda_cloud
 */
@Controller
@Scope(value = "prototype")
@RequestMapping(value = "admin/log",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdminLogController {

    @Autowired
    private ILogAdminAgentService logAdminAgentService;

    @Autowired
    private ILogAppPcService logAppPcService;
    /**
     * 获取后台日志
     *
     * @param vo
     * @param pageUtil
     * @return
     */
    @RequestMapping(value = "/getAdminAgentLogs")
    @ResponseBody
    public Map<String, Object> getAdminAgentLogs(LogAdminAgentVO vo, PageUtil pageUtil) {
        PageInfo<LogAdminAgentVO> lists = logAdminAgentService.getAdminAgentLogs(vo,pageUtil);
        return ResponseUtil.getSuccessMap(lists);
    }

    /**
     * 获取app日志
     */
    @RequestMapping(value = "/getAppPcLogs")
    @ResponseBody
    public Map<String, Object> getAppPcLogs(LogAppPcVO vo, PageUtil pageUtil) {
        PageInfo<LogAppPcVO> lists = logAppPcService.getAdminAgentLogs(vo,pageUtil);
        return ResponseUtil.getSuccessMap(lists);
    }




}
