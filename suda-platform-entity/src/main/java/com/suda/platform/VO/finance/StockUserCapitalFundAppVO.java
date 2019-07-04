package com.suda.platform.VO.finance;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.suda.platform.format.CustomBigDecimalSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 个人可提现资产
 * </p>
 *
 * @since 2019-04-16
 */
@Data
@ApiModel(value="StockUserCapitalFund对象", description="个人可提现资产")
public class StockUserCapitalFundAppVO extends Model<StockUserCapitalFundAppVO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "钱包类型")
    @TableField("stock_code")
    private String stockCode;

    @ApiModelProperty(value = "可用")
    @TableField("usable_fund")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal usableFund;

    @TableField("create_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "钱包账号 卡号")
    @TableField("card_num")
    private String cardNum;


    public StockUserCapitalFundAppVO() {
    }

}
