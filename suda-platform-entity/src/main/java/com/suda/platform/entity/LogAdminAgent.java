package com.suda.platform.entity;

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
 * 系统日志
 * </p>
 *
 * @author kongling
 * @since 2019-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="LogAdminAgent对象", description="系统日志")
public class LogAdminAgent extends Model<LogAdminAgent> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "请求地址")
    @TableField("request_url")
    private String requestUrl;

    @ApiModelProperty(value = "请求方式")
    @TableField("request_way")
    private String requestWay;

    @ApiModelProperty(value = "IP")
    @TableField("ip")
    private String ip;

    @ApiModelProperty(value = "方法地址")
    @TableField("method_url")
    private String methodUrl;

    @ApiModelProperty(value = "方法别名")
    @TableField("method_name")
    private String methodName;

    @ApiModelProperty(value = "请求参数")
    @TableField("request_param")
    private String requestParam;

    @ApiModelProperty(value = "返回参数")
    @TableField("return_param")
    private String returnParam;

    @ApiModelProperty(value = "耗时")
    @TableField("time")
    private String time;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "操作人id")
    @TableField("operator_id")
    private Long operatorId;

    @ApiModelProperty(value = "1,admin，2，agent")
    @TableField("operator_platform")
    private Integer operatorPlatform;

    @ApiModelProperty(value = "登录设备")
    @TableField("login_facility")
    private String loginFacility;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
