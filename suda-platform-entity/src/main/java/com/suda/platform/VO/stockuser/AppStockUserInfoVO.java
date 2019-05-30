package com.suda.platform.VO.stockuser;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @author kongling
 * @package com.suda.platform.VO.stockuser
 * @date 2019-05-17  10:35
 * @project suda_cloud
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StockUser对象", description="手机用户表")
public class AppStockUserInfoVO {


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

    @ApiModelProperty(value = "邀请码")
    @TableField("invitation_code")
    private String invitationCode;

    @ApiModelProperty(value = "上级代理id")
    @TableField("agent_id")
    private Long agentId;

    @ApiModelProperty(value = "审核者ID")
    @TableField("inspector_id")
    private Long inspectorId;

    @ApiModelProperty(value = "审核原因")
    @TableField("inspect_auth_reason")
    private String inspectAuthReason;

    @ApiModelProperty(value = "审核时间")
    @TableField("inspect_auth_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inspectAuthTime;

    @ApiModelProperty(value = "1:未认证 2:待审核 3:已认证 4:审核未通过")
    @TableField("inspect_auth_status")
    private Integer inspectAuthStatus;

    @ApiModelProperty(value = " 0:模拟用户 false 1:真实用户 true")
    @TableField("is_real")
    private Boolean isReal;


    @ApiModelProperty(value = "是否是经纪人 1:是，0:否")
    @TableField("is_staff")
    private Boolean isStaff;

    @ApiModelProperty(value = "是否是商家 0：false 不是商家  1：true 是商家")
    @TableField("is_shop")
    private Boolean isShop;

    @TableField("timestamp")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;

    @ApiModelProperty(value = "上级经纪人id")
    @TableField("invitation_id")
    private Long invitationId;

    @ApiModelProperty(value = "推荐邀请关系树")
    @TableField("invitation_path")
    private String invitationPath;

    @ApiModelProperty(value = "申请经济人拒绝原因")
    @TableField("staff_inspect_reason")
    private String staffInspectReason;

    @ApiModelProperty(value = "经济人审核时间")
    @TableField("staff_inspect_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date staffInspectTime;

    @ApiModelProperty(value = "经纪人申请状态1.未申请 2.已申请(待审核）3.同意 4.拒绝")
    @TableField("staff_apply_status")
    private Integer staffApplyStatus;

    @ApiModelProperty(value = "申请商家拒绝原因")
    @TableField("shop_inspect_reason")
    private String shopInspectReason;

    @ApiModelProperty(value = "商家审核时间")
    @TableField("shop_inspect_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date shopInspectTime;

    @ApiModelProperty(value = "商家申请状态1.未申请 2.已申请(待审核）3.同意 4.拒绝 5.已解绑")
    @TableField("shop_inspect_status")
    private Integer shopInspectStatus;

    @ApiModelProperty(value = "成为商家冻结保证金")
    @TableField("shop_frost_fund")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal shopFrostFund;

    @ApiModelProperty(value = "用户单独设置手续费比例")
    @TableField("user_charge_rate")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal userChargeRate;

    @ApiModelProperty(value = "头像")
    @TableField("head_url")
    private String headUrl;

}
