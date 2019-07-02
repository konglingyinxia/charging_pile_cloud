package com.suda.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suda.platform.VO.ComConfigAreaVO;
import com.suda.platform.entity.ComConfigArea;

import java.util.List;

/**
 * <p>
 * 区域字典 服务类
 * </p>
 *
 * @author 张龙飞
 * @since 2019-04-16
 */
public interface IComConfigAreaService extends IService<ComConfigArea> {

    /**
     * 根据区域上级id，查询区域下级
     * @param areaParentId
     * @return
     */
    @Deprecated
    List<ComConfigAreaVO> selectByAreaParentId(Integer areaParentId);


    /**
     * 查询地址包含经纬度
     * @param areaParentId
     * @return
     */
    List<?> selectAreaLatLog(Integer areaParentId);
}
