package com.suda.platform.VO.stockuser;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
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

/**
 * <p>
 * 手机用户表
 * </p>
 *
 * @author kongling
 * @since 2019-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StockUser对象", description="手机用户表")
public class StockUserLoginVO extends Model<StockUserLoginVO> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @ApiModelProperty(value = "登录帐号")
    @TableField("user_uid")
    private String userUid;

    @ApiModelProperty(value = "手机号")
    @TableField("tel")
    private String tel;

    @TableField(exist = false)
    private String sessionId;
    @TableField(exist = false)
    private WxMaJscode2SessionResult  sessionResult;





    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
