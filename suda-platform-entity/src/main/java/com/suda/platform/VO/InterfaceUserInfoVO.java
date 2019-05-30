package com.suda.platform.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author kongling
 * @package com.shop.VO
 * @date 2019-05-14  09:23
 * @project suda_cloud
 */
@ToString
@Data
public class InterfaceUserInfoVO implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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

    @ApiModelProperty(value = "邮箱|帐号")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "0:未禁用 1：禁用")
    @TableField("is_disable")
    private Boolean isDisable;

    @ApiModelProperty(value = "头像")
    @TableField("head_url")
    private String headUrl;
}
