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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 个人可提现资产
 * </p>
 *
 * @author 张龙飞
 * @since 2019-04-16
 */
@Data
@ApiModel(value="StockUserCapitalFund对象", description="个人可提现资产")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockUserCapitalFund extends Model<StockUserCapitalFund> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    @TableField("stock_user_id")
    private Long stockUserId;

    @ApiModelProperty(value = "卡号")
    @TableField("stock_code")
    private String stockCode;

    @ApiModelProperty(value = "可用")
    @TableField("usable_fund")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal usableFund;

    @ApiModelProperty(value = "总充值金额")
    @TableField("in_all_fee")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal inAllFee;

    @TableField("create_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * agent_user_id
     * 代理商id
     */
    @TableField("agent_user_id")
    private Long agentUserId;

    @ApiModelProperty(value = "钱包账号 卡号")
    @TableField("card_num")
    private String cardNum;
}
