package com.suda.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suda.platform.VO.ComConfigAreaVO;
import com.suda.platform.entity.ComConfigArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

;

/**
 * <p>
 * 区域字典 Mapper 接口
 * </p>
 *
 * @author 张龙飞
 * @since 2019-04-16
 */
public interface ComConfigAreaMapper extends BaseMapper<ComConfigArea> {

    /**
     * 根据区域上级id 查询区域下级信息
     * @param areaParentId
     * @return
     */
    List<ComConfigAreaVO> selectByAreaParentId(@Param("areaParentId") Integer areaParentId);

}
