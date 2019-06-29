package com.suda.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 区域字典
 * </p>
 *
 * @author 张龙飞
 * @since 2019-04-16
 */
@ApiModel(value="ComConfigArea对象", description="区域字典")
public class ComConfigArea extends Model<ComConfigArea> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "区域主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "区域名称")
    @TableField("area_name")
    private String areaName;

    @ApiModelProperty(value = "区域代码")
    @TableField("area_code")
    private String areaCode;

    @ApiModelProperty(value = "区域简称")
    @TableField("area_short")
    private String areaShort;

    @ApiModelProperty(value = "是否热门(0:否、1:是)")
    @TableField("area_is_hot")
    private String areaIsHot;

    @ApiModelProperty(value = "区域序列")
    @TableField("area_sequence")
    private Integer areaSequence;

    @ApiModelProperty(value = "上级主键")
    @TableField("area_parent_id")
    private Integer areaParentId;

    @ApiModelProperty(value = "首字母")
    @TableField("letter")
    private String letter;

    @ApiModelProperty(value = "初始时间")
    @TableField("init_date")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date initDate;

    @ApiModelProperty(value = "初始地址")
    @TableField("init_addr")
    private String initAddr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
    public String getAreaShort() {
        return areaShort;
    }

    public void setAreaShort(String areaShort) {
        this.areaShort = areaShort;
    }
    public String getAreaIsHot() {
        return areaIsHot;
    }

    public void setAreaIsHot(String areaIsHot) {
        this.areaIsHot = areaIsHot;
    }
    public Integer getAreaSequence() {
        return areaSequence;
    }

    public void setAreaSequence(Integer areaSequence) {
        this.areaSequence = areaSequence;
    }
    public Integer getAreaParentId() {
        return areaParentId;
    }

    public void setAreaParentId(Integer areaParentId) {
        this.areaParentId = areaParentId;
    }
    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }
    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }
    public String getInitAddr() {
        return initAddr;
    }

    public void setInitAddr(String initAddr) {
        this.initAddr = initAddr;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ComConfigArea{" +
        "id=" + id +
        ", areaName=" + areaName +
        ", areaCode=" + areaCode +
        ", areaShort=" + areaShort +
        ", areaIsHot=" + areaIsHot +
        ", areaSequence=" + areaSequence +
        ", areaParentId=" + areaParentId +
        ", letter=" + letter +
        ", initDate=" + initDate +
        ", initAddr=" + initAddr +
        "}";
    }
}
