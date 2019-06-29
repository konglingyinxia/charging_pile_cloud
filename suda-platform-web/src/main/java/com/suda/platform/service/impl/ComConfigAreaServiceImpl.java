package com.suda.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suda.platform.VO.ComConfigAreaVO;
import com.suda.platform.entity.ComConfigArea;
import com.suda.platform.mapper.ComConfigAreaMapper;
import com.suda.platform.service.IComConfigAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 区域字典 服务实现类
 * </p>
 *
 * @author 张龙飞
 * @since 2019-04-16
 */
@Service
public class ComConfigAreaServiceImpl extends ServiceImpl<ComConfigAreaMapper, ComConfigArea> implements IComConfigAreaService {
    @Autowired
    private ComConfigAreaMapper comConfigAreaMapper;

    /**
     * 根据区域上级id，查询区域下级
     * @param areaParentId
     * @return
     */
    @Override
    public List<ComConfigAreaVO> selectByAreaParentId(Integer areaParentId) {
        List<ComConfigAreaVO>  lists = comConfigAreaMapper.selectByAreaParentId(areaParentId);
        return lists;
    }
}
