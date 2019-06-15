package com.suda.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.chargeStation.ChargingStationSelAppVO;
import com.suda.platform.VO.chargeStation.ChargingStationsAppVO;
import com.suda.platform.VO.chargeStation.ChargingStationsVO;
import com.suda.platform.entity.ChargingStations;
import com.suda.platform.mapper.ChargingStationsMapper;
import com.suda.platform.service.IChargingStationsService;
import com.util.pageinfoutil.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private  ChargingStationsMapper chargingStationsMapper;
    @Override
    public PageInfo<ChargingStations> selectAllChargingStations(ChargingStationsVO vo, PageUtil pageUtil) {
        PageUtil.page(pageUtil);
        List<ChargingStations> lists = baseMapper.selectList(new QueryWrapper<ChargingStations>()
        .eq(vo.getId() !=null,"id",vo.getId())
                .eq("is_deleted",0)
                .between(vo.getStartDate()!=null && vo.getEndDate()!=null,
                        "create_time",vo.getStartDate(),vo.getEndDate())
        .like(vo.getStationName() !=null,"station_name",vo.getStationName())
        .orderByDesc("create_time"));
        return new PageInfo<>(lists);
    }

    /**
     * app 查询充电站
     * @param vo
     * @param pageUtil
     * @return
     */
    @Override
    public PageInfo<ChargingStationsAppVO> selectAppChargingStations(ChargingStationSelAppVO vo, PageUtil pageUtil) {
        PageUtil.page(pageUtil);
        List<ChargingStationsAppVO> stationsAppVOS = chargingStationsMapper.selectAppChargingStations(vo);
        return new PageInfo<>(stationsAppVOS);
    }
}
