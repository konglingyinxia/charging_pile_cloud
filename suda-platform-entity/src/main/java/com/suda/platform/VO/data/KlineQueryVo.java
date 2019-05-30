package com.suda.platform.VO.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chengxuan_chen
 */
@Data
@ToString
public class KlineQueryVo implements Serializable {

    /**
     * 查询code码
     */
    private String stockCode;
    /**
     * 查询K线时间周期
     */
    private String timeCycle;

    /**
     * 开始时间  结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate, endDate;

    public String getTimeCycle() {
        return timeCycle;
    }

    public void setTimeCycle(String timeCycle) {
        this.timeCycle = timeCycle;
    }

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
