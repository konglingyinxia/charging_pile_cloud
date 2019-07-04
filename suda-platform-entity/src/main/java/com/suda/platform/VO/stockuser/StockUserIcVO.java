package com.suda.platform.VO.stockuser;

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
 *  ic  卡用户记录
 * </p>
 *
 * @author kongling
 * @since 2019-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StockUser对象", description="手机用户表")
public class StockUserIcVO extends Model<StockUserIcVO> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "登录帐号")
    @TableField("user_uid")
    private String userUid;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "充电总金额")
    @TableField("charge_total_money")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal chargeTotalMoney;

    @ApiModelProperty(value = "充电总度数")
    @TableField("charge_num")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal chargeNum;

    @ApiModelProperty(value = "充电次数")
    @TableField("charge_count")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal chargeCount;

    @ApiModelProperty(value = "用户类型 1：小程序用户  2：ic卡用户")
    @TableField("user_type")
    private Integer userType;

    //====== ic 卡 余额==========================

    @ApiModelProperty(value = "可用")
    @TableField("usable_fund")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal usableFund;


    @ApiModelProperty(value = "钱包账号 卡号")
    @TableField("card_num")
    private String cardNum;

    // =================================== 代理账号 名字

    @ApiModelProperty(value = "帐号")
    @TableField("agent_account")
    private String agentAccount;

    @ApiModelProperty(value = "用户名")
    @TableField("agent_username")
    private String agentUsername;

    @ApiModelProperty(value = "添加代理商id")
    @TableField("agent_user_id")
    private Long agentUserId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
