package com.suda.platform.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.suda.platform.format.CustomBigDecimalSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 区域字典
 * </p>
 *
 * @author 张龙飞
 * @since 2019-04-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ComConfigArea对象", description="区域字典")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComConfigAreaVO extends Model<ComConfigAreaVO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "区域主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "区域名称")
    @TableField("area_name")
    private String areaName;

    @ApiModelProperty(value = "上级主键")
    @TableField("area_parent_id")
    private Integer areaParentId;

    @ApiModelProperty(value = "经度")
    @TableField(" longitude")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal longitude;

    @ApiModelProperty(value = "维度")
    @TableField("dimensionality")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal dimensionality;

    List<ComConfigAreaVO> lists;


}
