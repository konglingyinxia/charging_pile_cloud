package com.suda.platform.VO.finance;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * 资产走势图
 * </p>
 *
 * @author kongling
 * @since 2019-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StockUserCapitalFundTrend对象", description="资产走势图")
public class StockUserCapitalFundTrendVO extends Model<StockUserCapitalFundTrendVO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "手机用户id")
    @TableField("stock_user_id")
    private Long stockUserId;

    @ApiModelProperty(value = "币种类型")
    @TableField("stock_code")
    private String stockCode;

    @ApiModelProperty(value = "发生金额")
    @TableField("money")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal money;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "0：支出 1：收入")
    @TableField("income")
    private Boolean income;

    @ApiModelProperty(value = "日期")
    @TableField("date")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

}
