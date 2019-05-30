package com.suda.platform.VO.finance;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.ToString;

/**
 * 保存表名 及 id
 * @author kongling
 * @package com.shop.VO.finance
 * @date 2019-05-08  10:17
 * @project suda_cloud
 */
@Data
@ToString
public class TypeIdTableNameIdVO {

   private  String tableName ;
   private Long tableId;


   public static String getJSONString(String tableName,Long tableId){
       TypeIdTableNameIdVO tableNameIdVO = new TypeIdTableNameIdVO();
       tableNameIdVO.setTableId(tableId);
       tableNameIdVO.setTableName(tableName);
       String jsonObject = JSONObject.toJSONString(tableNameIdVO);
       return jsonObject;
   }

}
