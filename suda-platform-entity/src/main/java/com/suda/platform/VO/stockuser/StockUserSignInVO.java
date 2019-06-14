package com.suda.platform.VO.stockuser;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author kongling
 * @package com.suda.platform.VO.stockuser
 * @date 2019-05-16  16:07
 * @project suda_cloud
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StockUserSignInVO {

    @ApiModelProperty(value = "密码，使用md5加密")
    @TableField("pswd")
    private String pswd;

    @ApiModelProperty(value = "账号")
    @TableField("account")
    private String account;
    @ApiModelProperty(value = "验证码（微信code码）")
    @TableField(exist = false)
    private  String code;

    @ApiModelProperty(value = "交易密码")
    @TableField("trade_pwd")
    private String tradePwd;

    @ApiModelProperty(value = "微信唯一id")
    @TableField("open_id")
    private String openId;


}
