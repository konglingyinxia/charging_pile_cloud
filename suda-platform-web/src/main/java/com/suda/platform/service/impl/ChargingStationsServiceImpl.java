package com.suda.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.chargeStation.ChargingStationsVO;
import com.suda.platform.entity.ChargingStations;
import com.suda.platform.mapper.ChargingStationsMapper;
import com.suda.platform.service.IChargingStationsService;
import com.util.pageinfoutil.PageUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 充电站 服务实现类
 * </p>
 *
 * @author kongling
 * @since 2019-05-30
 */
@Service
public class ChargingStationsServiceImpl extends ServiceImpl<ChargingStationsMapper, ChargingStations> implements IChargingStationsService {

    @Override
    public PageInfo<ChargingStations> selectAllChargingStations(ChargingStationsVO vo, PageUtil pageUtil) {
        PageUtil.page(pageUtil);
        List<ChargingStations> lists = baseMapper.selectList(new QueryWrapper<ChargingStations>()
        .eq(vo.getId() !=null,"id",vo.getId())
        .like(vo.getStationName() !=null,"station_name",vo.getStationName()));
        return new PageInfo<>(lists);
    }
}
