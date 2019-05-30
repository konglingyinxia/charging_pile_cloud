package com.suda.platform.VO;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 区域字典
 * </p>
 *
 * @author 张龙飞
 * @since 2019-04-16
 */
@ApiModel(value="ComConfigArea对象", description="区域字典")
@Data
public class ComConfigAreaVO extends Model<ComConfigAreaVO> {

    private static final long serialVersionUID = 1L;

    /**
     * 省
     */
    private String province ;

    private Integer id;

    /**
     * 市
     */
    private List<CityVO> citys;
}
