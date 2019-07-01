package com.suda.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.suda.platform.format.CustomBigDecimalSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 充电桩
 * </p>
 *
 * @author kongling
 * @since 2019-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ChargingPileInfo对象", description="充电桩")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChargingPileInfo extends Model<ChargingPileInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "充电站id")
    @TableField("charging_stations_id")
    private Long chargingStationsId;

    @ApiModelProperty(value = "编号（长度 5）")
    @TableField("serial_number")
    private String serialNumber;

    @ApiModelProperty(value = "车位号")
    @TableField("parking_lot_no")
    private String parkingLotNo;

    @ApiModelProperty(value = "功率 KW")
    @TableField("rate_of_work")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal rateOfWork;

    @ApiModelProperty(value = "1:直流 2:交流")
    @TableField("ac_dc")
    private Integer acDc;

    @ApiModelProperty(value = "有枪状态0:无枪 1：有枪")
    @TableField("has_a_gun_status")
    private Boolean hasAGunStatus;

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

    @ApiModelProperty(value = "价格单位元度")
    @TableField("price")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal price;

    @ApiModelProperty(value = "服务费 价格单位元度")
    @TableField("service_charge")
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal serviceCharge;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @TableField("timestamp")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;


    @ApiModelProperty(value = "0未使用 1：正在使用")
    @TableField("use_status")
    private Boolean useStatus;

    @ApiModelProperty(value = "0 未离线 1：离线")
    @TableField("off_line_is")
    private Boolean offLineIs;
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
