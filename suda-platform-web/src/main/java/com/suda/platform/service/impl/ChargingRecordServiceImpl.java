package com.suda.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suda.platform.entity.ChargingRecord;
import com.suda.platform.mapper.ChargingRecordMapper;
import com.suda.platform.service.IChargingRecordService;
import org.springframework.stereotype.Service;

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

}
