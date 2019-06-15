package com.suda.platform.VO.chargeStation;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.suda.platform.format.CustomBigDecimalSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 *  app 查询条件
 * @author kongling
 * @package com.suda.platform.VO.chargeStation
 * @date 2019-06-15  10:19
 * @project charging_pile_cloud
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChargingStationSelAppVO {

    @ApiModelProperty(value = "经度")
    @TableField(" longitude")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal longitude;

    @ApiModelProperty(value = "维度")
    @TableField("dimensionality")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal dimensionality;

    @ApiModelProperty(value = "充电站名字")
    @TableField("station_name")
    private String stationName;

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

    @ApiModelProperty(value = "0:无停车费 1:有停车费")
    @TableField("have_parking_fee")
    private Integer haveParkingFee;

    @ApiModelProperty(value = "开放开始时间")
    @TableField("open_start_time")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm:ss")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date openStartTime;

    @ApiModelProperty(value = "开放结束时间")
    @TableField("open_end_time")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm:ss")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date openEndTime;

    @ApiModelProperty(value = "有枪状态0:无枪 1：有枪")
    @TableField("has_a_gun_status")
    private Integer hasAGunStatus;

    /**
     * 距离
     */
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal startDistance;

    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal endDistance;

}
