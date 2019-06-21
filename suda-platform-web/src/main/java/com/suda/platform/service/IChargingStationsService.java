package com.suda.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.chargeStation.ChargingStationSelAppVO;
import com.suda.platform.VO.chargeStation.ChargingStationsAppVO;
import com.suda.platform.VO.chargeStation.ChargingStationsCountVO;
import com.suda.platform.VO.chargeStation.ChargingStationsVO;
import com.suda.platform.entity.ChargingStations;
import com.util.pageinfoutil.PageUtil;

/**
 * <p>
 * 充电站 服务类
 * </p>
 *
 * @author kongling
 * @since 2019-05-30
 */
public interface IChargingStationsService extends IService<ChargingStations> {

    /**
     * 查询所有充电站
     *
     * @param vo
     * @param pageUtil
     * @return
     */
    PageInfo<ChargingStations> selectAllChargingStations(ChargingStationsVO vo, PageUtil pageUtil);

    /**
     * app查询充电站
     * @param vo
     * @param pageUtil
     * @return
     */
    PageInfo<ChargingStationsAppVO> selectAppChargingStations(ChargingStationSelAppVO vo, PageUtil pageUtil);

    /**
     * 充电站统计
     *
     * @param vo
     * @param pageUtil
     * @return
     */
    PageInfo<ChargingStationsCountVO> chargingStationsCount(ChargingStationsCountVO vo, PageUtil pageUtil);
}
