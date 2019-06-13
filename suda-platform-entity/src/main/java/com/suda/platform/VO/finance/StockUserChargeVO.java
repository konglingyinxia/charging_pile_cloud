package com.suda.platform.VO.finance;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * 用户出入金表
 * </p>
 *
 * @author kongling
 * @since 2019-06-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StockUserCharge对象", description="用户出入金表")
public class StockUserChargeVO extends Model<StockUserChargeVO> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "流水号: 四位项目简称 + yyyyMMddHHmmSS + 4位随机数 + 账号后4位")
    @TableField("swift_no")
    private String swiftNo;

    @ApiModelProperty(value = "手机用户id")
    @TableField("stock_user_id")
    private Long stockUserId;

    @ApiModelProperty(value = "IC卡号")
    @TableField("stock_code")
    private String stockCode;

    @ApiModelProperty(value = "人民币金额")
    @TableField("fee")
    private BigDecimal fee;

    @ApiModelProperty(value = "支付状态:1:未支付，2：支付成功 3：支付失败 4:处理中")
    @TableField("withdraw_status")
    private Integer withdrawStatus;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "1:删除，0:未删除")
    @TableField("is_deleted")
    private Boolean isDeleted;

    @TableField("timestamp")
    private Date timestamp;

    @ApiModelProperty(value = "三方支付平台的流水号")
    @TableField("tran_id")
    private String tranId;

    @ApiModelProperty(value = "支付类型：1.微信 2.线下")
    @TableField("pay_type")
    private Byte payType;

    @ApiModelProperty(value = "充值代理商ID")
    @TableField("agent_user_id")
    private Long agentUserId;


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

    /**
     * 开始时间  结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(exist = false)
    private Date startDate, endDate;




    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
