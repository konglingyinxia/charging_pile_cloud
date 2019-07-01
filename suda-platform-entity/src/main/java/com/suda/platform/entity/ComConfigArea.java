package com.suda.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.suda.platform.format.CustomBigDecimalSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

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
public class ComConfigArea extends Model<ComConfigArea> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "区域主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "区域名称")
    @TableField("area_name")
    private String areaName;

    @ApiModelProperty(value = "区域代码")
    @TableField("area_code")
    private String areaCode;

    @ApiModelProperty(value = "区域简称")
    @TableField("area_short")
    private String areaShort;

    @ApiModelProperty(value = "是否热门(0:否、1:是)")
    @TableField("area_is_hot")
    private String areaIsHot;

    @ApiModelProperty(value = "区域序列")
    @TableField("area_sequence")
    private Integer areaSequence;

    @ApiModelProperty(value = "上级主键")
    @TableField("area_parent_id")
    private Integer areaParentId;

    @ApiModelProperty(value = "首字母")
    @TableField("letter")
    private String letter;

    @ApiModelProperty(value = "初始时间")
    @TableField("init_date")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date initDate;

    @ApiModelProperty(value = "初始地址")
    @TableField("init_addr")
    private String initAddr;

    @ApiModelProperty(value = "经度")
    @TableField(" longitude")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal longitude;

    @ApiModelProperty(value = "维度")
    @TableField("dimensionality")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal dimensionality;


}
