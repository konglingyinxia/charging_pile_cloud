package com.suda.platform.VO.stockcode;

import lombok.Data;
import lombok.ToString;

/**
 * @author kongling
 * @package com.suda.platform.VO.stockcode
 * @date 2019-05-23  09:22
 * @project suda_cloud
 */
@Data
@ToString
public class StockCodeRealTimeVO {
    private String stockCode="";
    private String name="";
    private String date="";
    private String time="";
    private String price="1";
    private String openPrice="1";
    private String closePrice="1";
    private String high="1";
    private String low="1";
    private String prevClose="0";
    private String volume="0";
    private String change="0";
    private String changeRate="0%";
    private String buy="0";
    private String sell="0";
    /**
     * 人民币汇率
     */
    private String cnyPrice="1";
    /**
     * 时间戳
     */
    private String timestamp=System.currentTimeMillis()+"";
}
