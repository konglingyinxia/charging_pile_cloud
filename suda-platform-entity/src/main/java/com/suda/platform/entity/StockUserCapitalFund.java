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
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
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
public class StockUserCapitalFund extends Model<StockUserCapitalFund> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    @TableField("stock_user_id")
    private Long stockUserId;

    @ApiModelProperty(value = "币种编码 SDT ，TC，ETH，")
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

    @ApiModelProperty(value = "总提现金额")
    @TableField("out_all_fee")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal outAllFee;

    @TableField("create_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @TableField("timestamp")
    private Date timestamp;
    /**
     * agent_user_id
     * 代理商id
     */
    @TableField("agent_user_id")
    private Long agentUserId;

    @TableField(exist = false)
    @ApiModelProperty(value = "人民币金额")
    private String cnyPrice="6.816";
    @TableField(exist = false)
    private String change="0";
    @TableField(exist = false)
    private String changeRate="0%";

    public StockUserCapitalFund() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getStockUserId() {
        return stockUserId;
    }

    public void setStockUserId(Long stockUserId) {
        this.stockUserId = stockUserId;
    }
    public BigDecimal getUsableFund() {
        return usableFund;
    }

    public void setUsableFund(BigDecimal usableFund) {
        this.usableFund = usableFund;
    }
    public BigDecimal getInAllFee() {
        return inAllFee;
    }

    public void setInAllFee(BigDecimal inAllFee) {
        this.inAllFee = inAllFee;
    }
    public BigDecimal getOutAllFee() {
        return outAllFee;
    }

    public void setOutAllFee(BigDecimal outAllFee) {
        this.outAllFee = outAllFee;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "StockUserCapitalFund{" +
        "id=" + id +
        ", stockUserId=" + stockUserId +
        ", usableFund=" + usableFund +
        ", inAllFee=" + inAllFee +
        ", outAllFee=" + outAllFee +
        ", createTime=" + createTime +
        ", timestamp=" + timestamp +
        "}";
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }
}
