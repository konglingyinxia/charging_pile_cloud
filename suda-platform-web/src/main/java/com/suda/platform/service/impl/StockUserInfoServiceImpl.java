package com.suda.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suda.platform.entity.StockUser;
import com.suda.platform.entity.StockUserInfo;
import com.suda.platform.mapper.StockUserInfoMapper;
import com.suda.platform.service.IStockUserInfoService;
import com.suda.platform.service.IStockUserService;
import com.util.DealDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 手机用户信息表 服务实现类
 * </p>
 *
 * @author kongling
 * @since 2019-05-18
 */
@Service
public class StockUserInfoServiceImpl extends ServiceImpl<StockUserInfoMapper, StockUserInfo> implements IStockUserInfoService {

    @Autowired
    private IStockUserService stockUserService;
    @Override
    @Transactional(rollbackFor = {})
    public boolean openAccount(StockUserInfo userinfo, StockUser stockUser) {

        StockUserInfo oldUserInfo =
                baseMapper.selectOne(new QueryWrapper<StockUserInfo>()
                .eq("stock_user_id",userinfo.getStockUserId()));
        if(oldUserInfo==null){
            userinfo.setCreateTime(DealDateUtil.getNowDate());
            baseMapper.insert(userinfo) ;
        }else {
            userinfo.setId(oldUserInfo.getId());
            baseMapper.updateById(userinfo);
        }
        stockUser.setUsername(userinfo.getUsername());
        stockUser.setId(stockUser.getId());
        stockUserService.updateById(stockUser);
        return true;
    }
}
