package com.suda.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suda.platform.VO.chargeStation.ChargingRecordVO;
import com.suda.platform.entity.ChargingRecord;

import java.util.List;

/**
 * <p>
 * 充电记录 Mapper 接口
 * </p>
 *
 * @author kongling
 * @since 2019-05-30
 */
public interface ChargingRecordMapper extends BaseMapper<ChargingRecord> {

    /**
     *
     * 查询充电桩充电站交易记录
     * @param vo
     * @return
     */
    List<ChargingRecordVO> selectAllChargingRecords(ChargingRecordVO vo);
}
