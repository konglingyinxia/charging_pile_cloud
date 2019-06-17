package com.suda.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.chargeStation.ChargingPileInfoVO;
import com.suda.platform.entity.ChargingPileInfo;
import com.suda.platform.mapper.ChargingPileInfoMapper;
import com.suda.platform.service.IChargingPileInfoService;
import com.util.pageinfoutil.PageUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 充电桩 服务实现类
 * </p>
 *
 * @author kongling
 * @since 2019-05-30
 */
@Service
public class ChargingPileInfoServiceImpl extends ServiceImpl<ChargingPileInfoMapper, ChargingPileInfo> implements IChargingPileInfoService {

    @Override
    public PageInfo<ChargingPileInfo> selectAllChargingPileInfos(ChargingPileInfoVO vo, PageUtil pageUtil) {
        PageUtil.page(pageUtil);
        List<ChargingPileInfo> lists = baseMapper.selectList(new QueryWrapper<ChargingPileInfo>()
                .eq(vo.getId() !=null,"id",vo.getId())
                .eq("is_deleted",0)
                .eq(vo.getChargingStationsId()!=null,"charging_stations_id",vo.getChargingStationsId())
                .eq(vo.getAcDc()!=null,"ac_dc",vo.getAcDc())
                .eq(vo.getSerialNumber()!=null,"serial_number",vo.getSerialNumber())
                .between(vo.getStartDate()!=null && vo.getEndDate()!=null,
                        "create_time",vo.getStartDate(),vo.getEndDate())
                .orderByDesc("create_time"));
        return new PageInfo<>(lists);
    }
}
