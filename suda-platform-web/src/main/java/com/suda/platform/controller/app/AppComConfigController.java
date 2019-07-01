package com.suda.platform.controller.app;

import com.suda.platform.VO.ComConfigAreaVO;
import com.suda.platform.service.IComConfigAreaService;
import com.util.Respons.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 查询公共配置
 * @author kongling
 * @package com.suda.app.controller.shop
 * @date 2019-05-09  17:03
 * @project suda_cloud
 */
@RestController
@RequestMapping(value = "app/comConfig",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AppComConfigController {

    @Autowired
    private IComConfigAreaService comConfigServiceService;

    /**
     * 获取区域信息
     *
     * @return
     */
    @RequestMapping(value = "/getArea", method = RequestMethod.GET)
    @ResponseBody
    @Deprecated
    public Map<String, Object> getService(Integer areaParentId) {
        List<ComConfigAreaVO> lists = comConfigServiceService.selectByAreaParentId(areaParentId);
        return ResponseUtil.getSuccessMap(lists);
    }

    /**
     * 获取区域信息
     *
     * @return
     */
    @RequestMapping(value = "/getAreaLatLog", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAreaLatLog(Integer areaParentId) {
        List<?> lists = comConfigServiceService.selectAreaLatLog(areaParentId);
        return ResponseUtil.getSuccessMap(lists);
    }

}



