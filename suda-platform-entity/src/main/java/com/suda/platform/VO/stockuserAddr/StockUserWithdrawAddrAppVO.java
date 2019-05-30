package com.suda.platform.VO.stockuserAddr;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户提现币种地址表
 * </p>
 *
 * @author kongling
 * @since 2019-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StockUserWithdrawAddr对象", description="用户提现币种地址表")
public class StockUserWithdrawAddrAppVO extends Model<StockUserWithdrawAddrAppVO> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    @TableField("stock_user_id")
    private Long stockUserId;

    @ApiModelProperty(value = "地址")
    @TableField("addr")
    private String addr;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;


    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "币种code")
    @TableField("stock_code")
    private String stockCode;

    @ApiModelProperty(value = "好友id")
    @TableField("friend_id")
    private Long friendId;

    /******************好友*******************************/
    @ApiModelProperty(value = "用户昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty(value = "真实姓名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "头像")
    @TableField("head_url")
    private String headUrl;

    @ApiModelProperty(value = "手机号")
    @TableField("tel")
    private String tel;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
