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
 * 手机用户表
 * </p>
 *
 * @author kongling
 * @since 2019-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StockUser对象", description="手机用户表")
public class StockUserVO extends Model<StockUserVO> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty(value = "真实姓名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "登录帐号")
    @TableField("user_uid")
    private String userUid;

    @ApiModelProperty(value = "手机号")
    @TableField("tel")
    private String tel;

    @ApiModelProperty(value = "邮箱|帐号")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "设备号")
    @TableField("device_no")
    private String deviceNo;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "最后登录时间")
    @TableField("last_login_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;

    @ApiModelProperty(value = "0:未禁用 1：禁用")
    @TableField("is_disable")
    private Boolean isDisable;

    @ApiModelProperty(value = "0:未删除 1:删除")
    @TableField("is_deleted")
    private Boolean isDeleted;

    @TableField("timestamp")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;

    @ApiModelProperty(value = "头像")
    @TableField("head_url")
    private String headUrl;

    @ApiModelProperty(value = "微信唯一id")
    @TableField("open_id")
    private String openId;

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



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
