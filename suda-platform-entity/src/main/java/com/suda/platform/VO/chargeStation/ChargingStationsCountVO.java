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

import java.io.Serializable;

/**
 * <p>
 * 充电站 统计
 * </p>
 *
 * @author kongling
 * @since 2019-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ChargingStations对象", description="充电站")
public class ChargingStationsCountVO extends Model<ChargingStationsCountVO> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @ApiModelProperty(value = "充电站名字")
    @TableField("station_name")
    private String stationName;

    @ApiModelProperty(value = "充电站月订单")
    @TableField("station_month_order")
    private String stationMonthOrder;

    @ApiModelProperty(value = "充电站日订单")
    @TableField("station_day_order")
    private String stationDayOrder;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
