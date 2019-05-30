package com.suda.platform.VO.stockuserinfo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 手机用户信息表
 * </p>
 *
 * @author kongling
 * @since 2019-05-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StockUserInfo对象", description="手机用户信息表")
public class StockUserInfoVO extends Model<StockUserInfoVO> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "手机用户id")
    @TableField("stock_user_id")
    private Long stockUserId;

    @ApiModelProperty(value = "银行卡号")
    @TableField("bank_card_no")
    private String bankCardNo;

    @ApiModelProperty(value = "银联号")
    @TableField("bank_card_union_no")
    private String bankCardUnionNo;

    @ApiModelProperty(value = "银行卡类型")
    @TableField("bank_card_type")
    private String bankCardType;

    @ApiModelProperty(value = "银行卡类型编码")
    @TableField("bank_card_type_code")
    private String bankCardTypeCode;

    @ApiModelProperty(value = "开户行")
    @TableField("bank_card_open_bank")
    private String bankCardOpenBank;

    @ApiModelProperty(value = "银行卡图片")
    @TableField("bank_card_img")
    private String bankCardImg;

    @ApiModelProperty(value = "银行卡过期时间")
    @TableField("bank_card_expired_time")
    private Date bankCardExpiredTime;

    @ApiModelProperty(value = "省")
    @TableField("province")
    private String province;

    @ApiModelProperty(value = "市")
    @TableField("city")
    private String city;

    @ApiModelProperty(value = "县")
    @TableField("county")
    private String county;

    @ApiModelProperty(value = "详细地点")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "身份证号")
    @TableField("id_card_no")
    private String idCardNo;

    @ApiModelProperty(value = "身份证正面照")
    @TableField("id_card_front_img")
    private String idCardFrontImg;

    @ApiModelProperty(value = "身份证背面照")
    @TableField("id_card_back_img")
    private String idCardBackImg;

    @ApiModelProperty(value = "身份证有效期开始时间")
    @TableField("id_card_start_time")
    private Date idCardStartTime;

    @ApiModelProperty(value = "身份证有效期结束时间")
    @TableField("id_card_end_time")
    private Date idCardEndTime;

    @ApiModelProperty(value = "自拍照")
    @TableField("selfie_img")
    private String selfieImg;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "1:删除，0:未删除")
    @TableField("is_deleted")
    private Boolean isDeleted;

    @TableField("timestamp")
    private Date timestamp;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "性别 0:男 ，1女")
    @TableField("sex")
    private Integer sex;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
