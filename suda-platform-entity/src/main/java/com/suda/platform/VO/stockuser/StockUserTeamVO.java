package com.suda.platform.VO.stockuser;

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
 * 用户团队统计
 * </p>
 *
 * @author kongling
 * @since 2019-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StockUserTeam对象", description="用户晒图记录表")
public class StockUserTeamVO extends Model<StockUserTeamVO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "手机用户id")
    @TableField("stock_user_id")
    private Long stockUserId;

    @ApiModelProperty(value = "总推广人数")
    @TableField("total_valid_member")
    private Integer totalValidMember;

    @ApiModelProperty(value = "累计推广奖励")
    @TableField("total_reward")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal totalReward;

    @ApiModelProperty(value = "当天推广人数")
    @TableField("develop_num")
    private Integer developNum;


    @ApiModelProperty(value = "团队等级 0：没有团队奖  1：一级（传说） 2：二级（神话） 3：三级（不朽）")
    @TableField("team_lever")
    private Integer teamLever;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @TableField("timestamp")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;


}
