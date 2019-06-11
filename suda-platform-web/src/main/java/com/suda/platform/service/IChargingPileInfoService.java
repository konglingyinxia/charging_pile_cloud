package com.suda.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.chargeStation.ChargingPileInfoVO;
import com.suda.platform.entity.ChargingPileInfo;
import com.util.pageinfoutil.PageUtil;

/**
 * <p>
 * 充电桩 服务类
 * </p>
 *
 * @author kongling
 * @since 2019-05-30
 */
public interface IChargingPileInfoService extends IService<ChargingPileInfo> {

    /**
     * 查看充电站下的充电桩
     *
     * @param vo
     * @param pageUtil
     * @return
     */
    PageInfo<ChargingPileInfo> selectAllChargingPileInfos(ChargingPileInfoVO vo, PageUtil pageUtil);
}
