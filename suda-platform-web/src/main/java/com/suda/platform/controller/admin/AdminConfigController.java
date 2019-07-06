package com.suda.platform.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suda.platform.entity.ComConfigArea;
import com.suda.platform.service.IComConfigAreaService;
import com.util.Respons.ResponseMsg;
import com.util.Respons.ResponseUtil;
import config.annotation.LogMenthodName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 *
 * 配置信息管理
 * @author kongling
 * @package com.suda.platform.controller.admin
 * @date 2019-06-25  16:14
 * @project charging_pile_cloud
 */
@RestController
@RequestMapping(value = "admin/config",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdminConfigController {

    @Autowired
    private IComConfigAreaService comConfigServiceService;

    /**
     * 区域地址管理查看
     * @return
     */
    @RequestMapping(value = "/getAreaLatLog", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAreaLatLog(Integer areaParentId) {
        List<?> lists = comConfigServiceService.selectAreaLatLog(areaParentId);
        return ResponseUtil.getSuccessMap(lists);
    }

    /**
     * 编辑/添加区域地址
     */
    @RequestMapping(value = "/editAreaLatLog", method = RequestMethod.GET)
    @ResponseBody
    @LogMenthodName(name = "编辑/添加区域地址")
    public Map<String, Object> editAreaLatLog(ComConfigArea area) {
        if(area.getId()==null){
            if(area.getLongitude()==null||area.getDimensionality()==null){
                return ResponseUtil.getNotNormalMap(ResponseMsg.ERROR_PARAM);
            }
            if(area.getAreaParentId()==null){
                area.setAreaParentId(0);
            }
            comConfigServiceService.save(area);
        } else {
            comConfigServiceService.updateById(area);
        }
        return ResponseUtil.getSuccessMap();
    }

    /**
     * 删除区域地址
     */
    @RequestMapping(value = "/delAreaLatLog", method = RequestMethod.GET)
    @ResponseBody
    @LogMenthodName(name = "删除区域地址")
    public Map<String, Object> delAreaLatLog(ComConfigArea area) {
        if(area.getId()==null){
           return ResponseUtil.getNotNormalMap(ResponseMsg.ID_IS_EMPTY);
        }
        //查询是否有下级
        List<Object> areas = comConfigServiceService.listObjs(new QueryWrapper<ComConfigArea>()
                .eq("area_parent_id",area.getId())
        );
        if(areas.size()>0){
            return ResponseUtil.getNotNormalMap("请先删除下级区域！");
        }
        comConfigServiceService.removeById(area.getId());
        return ResponseUtil.getSuccessMap();
    }






}
