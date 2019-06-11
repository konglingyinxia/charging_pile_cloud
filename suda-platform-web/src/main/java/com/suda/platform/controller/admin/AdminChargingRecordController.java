package com.suda.platform.controller.admin;

import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.chargeStation.ChargingRecordVO;
import com.suda.platform.service.IChargingRecordService;
import com.util.Respons.ResponseUtil;
import com.util.pageinfoutil.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 充电记录管理
 *
 * @author kongling
 * @package com.suda.platform.controller.admin
 * @date 2019-06-10  17:26
 * @project charging_pile_cloud
 */
@RestController
@RequestMapping(value = "admin/chargingRecord",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdminChargingRecordController {
    @Autowired
    private IChargingRecordService chargingRecordService;

    /**
     * 查看所有充电记录
     */
    @RequestMapping(value = "/selectAllChargingRecords")
    @ResponseBody
    public Map<String,Object> selectAllChargingRecords(ChargingRecordVO vo, PageUtil pageUtil){
        PageInfo<ChargingRecordVO> list = chargingRecordService.selectAllChargingRecords(vo,  pageUtil);
        return ResponseUtil.getSuccessMap(list);
    }

}
