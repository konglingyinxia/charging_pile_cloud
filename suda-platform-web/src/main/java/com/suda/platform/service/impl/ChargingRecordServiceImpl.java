package com.suda.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.chargeStation.ChargingRecordVO;
import com.suda.platform.entity.ChargingRecord;
import com.suda.platform.mapper.ChargingRecordMapper;
import com.suda.platform.service.IChargingRecordService;
import com.util.pageinfoutil.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 充电记录 服务实现类
 * </p>
 *
 * @author kongling
 * @since 2019-05-30
 */
@Service
public class ChargingRecordServiceImpl extends ServiceImpl<ChargingRecordMapper, ChargingRecord> implements IChargingRecordService {

    @Autowired
    private ChargingRecordMapper chargingRecordMapper;

    @Override
    public PageInfo<ChargingRecordVO> selectAllChargingRecords(ChargingRecordVO vo, PageUtil pageUtil) {
        PageUtil.page(pageUtil);
        List<ChargingRecordVO> list = chargingRecordMapper.selectAllChargingRecords(vo);
        return new PageInfo<>(list);
    }
}
