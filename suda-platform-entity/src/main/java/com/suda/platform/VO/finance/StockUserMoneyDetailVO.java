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
 * 用户资金明细表
 * </p>
 *
 * @author kongling
 * @since 2019-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StockUserMoneyDetail对象", description="用户资金明细表")
public class StockUserMoneyDetailVO extends Model<StockUserMoneyDetailVO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "币种类型")
    @TableField("stock_code")
    private String stockCode;

    @ApiModelProperty(value = "手机用户id")
    @TableField("stock_user_id")
    private Long stockUserId;

    @ApiModelProperty(value = "发生金额")
    @TableField("money")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal money;

    @ApiModelProperty(value = "发生前金额")
    @TableField("money_before")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal moneyBefore;

    @ApiModelProperty(value = "发生后金额")
    @TableField("money_after")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal moneyAfter;

    @ApiModelProperty(value = "备注")
    @TableField("detail")
    private String detail;

    @TableField("type")
    private Integer type;
    @TableField(exist = false)
    private String typeStr;


    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "1:删除，0:未删除")
    @TableField("is_deleted")
    private Boolean isDeleted;

    @TableField("timestamp")
    private Date timestamp;
    @ApiModelProperty(value = "1:后台操作，2:正常流水")
    @TableField("water_type")
    private Byte waterType;

    @ApiModelProperty(value = "0：支出 1：收入")
    @TableField("income")
    private Byte income;


    @ApiModelProperty(value = "用户昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty(value = "真实姓名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "登录UID")
    @TableField("user_uid")
    private String userUid;

    @ApiModelProperty(value = "手机号")
    @TableField("tel")
    private String tel;



}
