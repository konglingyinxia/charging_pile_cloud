package com.suda.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.suda.platform.VO.ComConfigAreaVO;
import com.suda.platform.entity.ComConfigArea;
import com.suda.platform.mapper.ComConfigAreaMapper;
import com.suda.platform.service.IComConfigAreaService;
import org.springframework.beans.BeanUtils;
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


    @Override
    public List<?> selectAreaLatLog(Integer areaParentId) {
        List<ComConfigArea> list = comConfigAreaMapper.selectList(new QueryWrapper<ComConfigArea>()
        .eq("area_parent_id",0));
        List<ComConfigAreaVO> vos = Lists.newArrayList();
        list.forEach((area)->{
            ComConfigAreaVO vo = ComConfigAreaVO.builder().build();
            BeanUtils.copyProperties(area,vo);
            vos.add(vo);
        });
        //递归查询下级
        recursionSelArea(vos);
        return vos;
    }

    private List<ComConfigAreaVO> recursionSelArea(List<ComConfigAreaVO> vos) {
        vos.forEach((vo)->{
            List<ComConfigArea> list = comConfigAreaMapper.selectList(new QueryWrapper<ComConfigArea>()
                    .eq("area_parent_id",vo.getId()));
            List<ComConfigAreaVO> voTemps = Lists.newArrayList();
            list.forEach((area)->{
                ComConfigAreaVO voTemp = ComConfigAreaVO.builder().build();
                BeanUtils.copyProperties(area,voTemp);
                voTemps.add(voTemp);
            });
            vo.setLists(recursionSelArea(voTemps));
        });
        return vos;

    }
}
