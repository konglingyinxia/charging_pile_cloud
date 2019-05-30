package com.suda.platform.controller.common;

import com.suda.platform.VO.StatusVo;
import com.suda.platform.enums.PayWayPayTypeEnum;
import com.suda.platform.enums.TelCodeTypeEnum;
import com.suda.platform.enums.version.AppVersionEnum;
import com.util.Respons.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 公共状态值
 * @author kongling
 * @package com.suda.common.controller
 * @date 2019-05-09  15:18
 * @project suda_cloud
 */
@Controller
@Scope(value = "prototype")
@RequestMapping(value = "common/status",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CommonStatusController {


    /**
     * 获取短信类别TelCodeTypeEnum
     */
    @RequestMapping(value = "getTelCodeType", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "短信类别")
    public Map<String, Object> getTelCodeType() {
        List<StatusVo> list = new ArrayList<>();
        for (TelCodeTypeEnum o : TelCodeTypeEnum.values()) {
            StatusVo vo = new StatusVo();
            vo.setCode(o.getCode().intValue());
            vo.setName(o.getMessage());
            list.add(vo);
        }
        return ResponseUtil.getSuccessMap(list);
    }


    @RequestMapping(value = "getPayWayPayType", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "用户支付方式类型")
    public Map<String, Object> getPayWayPayType() {
        List<StatusVo> list = new ArrayList<>();
        for (PayWayPayTypeEnum o : PayWayPayTypeEnum.values()) {
            StatusVo vo = new StatusVo();
            vo.setCode(o.getCode().intValue());
            vo.setName(o.getMessage());
            list.add(vo);
        }
        return ResponseUtil.getSuccessMap(list);
    }



    /**
     *  移动 前端 系统类型
     */
    @RequestMapping(value = "projectSystemTypes", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(notes = "移动 前端 系统类型 \n" +
            "      ", value = "移动 前端 系统类型")
    @ResponseBody
    public Map<String, Object> projectSystemTypes() {

        List<StatusVo> list = new ArrayList<>();
        for (AppVersionEnum o : AppVersionEnum.values()) {
            StatusVo vo = new StatusVo();
            vo.setCode(o.getCode().intValue());
            vo.setName(o.getMessage());
            list.add(vo);
        }
        return ResponseUtil.getSuccessMap(list);
    }









}
