package com.suda.platform.VO.chargeStation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
public class ChargingStationsVO extends Model<ChargingStationsVO> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "经度")
    @TableField(" longitude")
    private BigDecimal  longitude;

    @ApiModelProperty(value = "维度")
    @TableField("dimensionality")
    private BigDecimal dimensionality;

    @ApiModelProperty(value = "充电桩名字")
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


    @ApiModelProperty(value = "有枪状态0:无枪 1：有枪")
    @TableField("has_a_gun_status")
    private Integer hasAGunStatus;


    @ApiModelProperty(value = "0:未删除 1:删除")
    @TableField("is_deleted")
    private Boolean isDeleted;

    @ApiModelProperty(value = "0:未禁用 1：禁用")
    @TableField("is_disable")
    private Boolean isDisable;

    /**
     * 开始时间  结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate, endDate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
