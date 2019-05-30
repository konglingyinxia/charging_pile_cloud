package com.util.sms.networdbuild;

import java.util.HashMap;
import java.util.Map;

public class ChinaToBuild {

	@SuppressWarnings("unchecked")
	public static Map<String, String> map=new HashMap();
	
	static {
		
			map.put("-1", "短信接口没有该用户账户");
			map.put("-2", "接口密钥不正确");
			map.put("-21", "MD5接口密钥加密不正确");
			map.put("-3", "短信数量不足");
			map.put("-11", "该用户被禁用");
			map.put("14","短信内容出现非法字符");
			map.put("-4", "手机号格式不正确");
			map.put("-41", "手机号码为空");
			map.put("-42", "短信内容为空");
			map.put("-51", "短信签名格式不正确");
			map.put("-52", "短信签名太长");
			map.put("-6", "IP限制");
		
	}
	
}
