package com.suda.platform.VO.stockuser;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 我的团队列表查询
 *
 * @author kongling
 * @package com.suda.platform.VO.stockuser
 * @date 2019-05-20  17:01
 * @project suda_cloud
 */
@Data
@ToString
public class MyTeamSelVO {
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

    @ApiModelProperty(value = "总推广人数")
    @TableField("total_valid_member")
    private Integer totalValidMember;


    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "手机用户id")
    @TableField("stock_user_id")
    private Long stockUserId;

    @ApiModelProperty(value = "上级经纪人id")
    @TableField("invitation_id")
    private Long invitationId;

    @ApiModelProperty(value = "头像")
    @TableField("head_url")
    private String headUrl;


}
