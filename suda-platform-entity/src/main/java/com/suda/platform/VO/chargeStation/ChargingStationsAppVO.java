package com.suda.platform.VO.chargeStation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 充电站
 * </p>
 *
 * @author kongling
 * @since 2019-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ChargingStations对象", description="充电站")
public class ChargingStationsAppVO extends Model<ChargingStationsAppVO> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "经度")
    @TableField(" longitude")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal  longitude;

    @ApiModelProperty(value = "维度")
    @TableField("dimensionality")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal dimensionality;

    @ApiModelProperty(value = "充电站名字")
    @TableField("station_name")
    private String stationName;

    @ApiModelProperty(value = "详情")
    @TableField("station_details")
    private String stationDetails;

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

    @ApiModelProperty(value = "价格单位元")
    @TableField("price")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal price;

    @ApiModelProperty(value = "停车费")
    @TableField("parking_fee_details")
    private String parkingFeeDetails;

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

    @ApiModelProperty(value = "产品封面图")
    @TableField("cover_img")
    private String coverImg;

    @ApiModelProperty(value = "产品轮播图")
    @TableField("banner_img")
    private String bannerImg;

    @ApiModelProperty(value = "有枪状态0:无枪 1：有枪")
    @TableField("has_a_gun_status")
    private Integer hasAGunStatus;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    @ApiModelProperty(value = "0:未禁用 1：禁用")
    @TableField("is_disable")
    private Boolean isDisable;

    /**
     * 距离
     */
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal distance;

    /**
     * 直流在线
     */
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    @TableField("ac_online_num")
    private BigDecimal acOnlineNum;
    /**
     * 直流离线
     */
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    @TableField("ac_offline_num")
    private BigDecimal acOfflineNum;

    /**
     * 交流在线
     */
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    @TableField("dc_online_num")
    private BigDecimal dcOnlineNum;
    /**
     * 交流离线
     */
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    @TableField("dc_offline_num")
    private BigDecimal dcOfflineNum;






    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
