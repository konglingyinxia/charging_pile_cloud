package com.suda.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suda.platform.VO.chargeStation.ChargingStationSelAppVO;
import com.suda.platform.VO.chargeStation.ChargingStationsAppVO;
import com.suda.platform.VO.chargeStation.ChargingStationsCountVO;
import com.suda.platform.entity.ChargingStations;

import java.util.List;

/**
 * <p>
 * 充电站 Mapper 接口
 * </p>
 *
 * @author kongling
 * @since 2019-05-30
 */
public interface ChargingStationsMapper extends BaseMapper<ChargingStations> {

    /**
     * 查询app充电站
     * @param vo
     * @return
     */
    List<ChargingStationsAppVO> selectAppChargingStations(ChargingStationSelAppVO vo);

    /**
     * 充电站统计
     *
     * @param vo
     * @return
     */
    List<ChargingStationsCountVO> selectStationsCount(ChargingStationsCountVO vo);
}
