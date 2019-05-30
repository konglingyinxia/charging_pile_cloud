package com.suda.platform.VO.transfer;

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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 平台币划转记录
 * </p>
 *
 * @author kongling
 * @since 2019-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StockCodeTransferRecord对象", description="平台币划转记录")
public class StockCodeTransferRecordAppVO extends Model<StockCodeTransferRecordAppVO> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "划转币")
    @TableField("from_stock_code")
    private String fromStockCode;

    @ApiModelProperty(value = "划入币")
    @TableField("to_stock_code")
    private String toStockCode;

    @ApiModelProperty(value = "划转汇率（多少个划入币）")
    @TableField("transfer_ratio")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal transferRatio;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @TableField("timestamp")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;

    @ApiModelProperty(value = "划转手续费比例%")
    @TableField("transfer_fee_ratio")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal transferFeeRatio;

    @ApiModelProperty(value = "手续费")
    @TableField("poundage")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal poundage;

    @ApiModelProperty(value = "转账金额")
    @TableField("transfer_from_money")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal transferFromMoney;

    @ApiModelProperty(value = "到账币种金额")
    @TableField("transfer_to_money")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal transferToMoney;

    @ApiModelProperty(value = "用户id")
    @TableField("stock_user_id")
    private Long stockUserId;

    @ApiModelProperty(value = "流水号: 四位项目简称 + yyyyMMddHHmmSS + 4位随机数 + 账号后4位")
    @TableField("swift_no")
    private String swiftNo;

    /**
     * 查询起始日期
     */
    @ApiModelProperty(value = "查询起始日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    /**
     * 查询结束日期
     */
    @ApiModelProperty(value = "查询结束日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
