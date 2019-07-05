package com.suda.platform.format;


import com.constant.CommonConstant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 数字格式化
 * @author 卫星
 * @package config.format
 * @date 2019-04-23  11:01
 * @project niuwan_cloud
 */
public class CustomBigDecimalSerializer extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException{
        if (value == null) {
            value = BigDecimal.ZERO;
        }
        String valueStr = value.setScale(CommonConstant.DECIMAL_PLACE_EIGHT, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
        gen.writeNumber(valueStr);
    }
}
