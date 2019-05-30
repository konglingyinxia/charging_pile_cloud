package com.suda.platform.VO.transfer;

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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户虚拟币转入转出记录
 * </p>
 *
 * @author kongling
 * @since 2019-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StockUserCoinCharge对象", description="用户虚拟币转入转出记录")
public class StockUserCoinChargeAppVO extends Model<StockUserCoinChargeAppVO> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "流水号: 四位项目简称 + yyyyMMddHHmmSS + 4位随机数 + 账号后4位")
    @TableField("swift_no")
    private String swiftNo;

    @ApiModelProperty(value = "手机用户id")
    @TableField("stock_user_id")
    private Long stockUserId;

    @ApiModelProperty(value = "出入金类型 1:入金，2:出金")
    @TableField("charge_type")
    private Byte chargeType;

    @ApiModelProperty(value = "审核原因/备注")
    @TableField("inspect_reason")
    private String inspectReason;

    @ApiModelProperty(value = "审核/确认时间")
    @TableField("inspect_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inspectTime;

    @ApiModelProperty(value = "审核状态 0:待审核 1:已审核 2:拒绝 3:审核中")
    @TableField("inspect_status")
    private Byte inspectStatus;

    @ApiModelProperty(value = "手续费")
    @TableField("poundage")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal poundage;

    @ApiModelProperty(value = "手续费比例")
    @TableField("poundage_ratio")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal poundageRatio;

    @ApiModelProperty(value = "转账金额")
    @TableField("transfer_money")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal transferMoney;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "1:删除，0:未删除")
    @TableField("is_deleted")
    private Boolean isDeleted;

    @ApiModelProperty(value = "交易hash")
    @TableField("transaction_hash")
    private String transactionHash;

    @ApiModelProperty(value = "转出地址")
    @TableField("from_addr")
    private String fromAddr;

    @ApiModelProperty(value = "转入地址")
    @TableField("to_addr")
    private String toAddr;

    @ApiModelProperty(value = "币种")
    @TableField("stock_code")
    private String stockCode;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "转入用户id")
    @TableField("to_stock_user_id")
    private Long toStockUserId;

    @ApiModelProperty(value = "是否是平台地址0：不是 1：是")
    @TableField("platform_is")
    private Byte platformIs;
    @ApiModelProperty(value = "0:转帐中 1：转账成功 2：转账失败")
    @TableField("transfer_status")
    private Byte transferStatus;

    @ApiModelProperty(value = "转出用户id")
    @TableField("from_stock_user_id")
    private Long fromStockUserId;

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
