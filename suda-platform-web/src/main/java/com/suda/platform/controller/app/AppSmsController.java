package com.suda.platform.controller.app;

import com.suda.platform.VO.stockuser.StockUserLoginVO;
import com.suda.platform.common.RedisUtils;
import com.suda.platform.entity.TelCodeTypeVO;
import com.suda.platform.enums.TelCodeTypeEnum;
import com.suda.platform.service.IStockUserService;
import com.util.Respons.ResponseMsg;
import com.util.Respons.ResponseUtil;
import com.util.sms.networdbuild.ChinaToBuild;
import com.util.sms.networdbuild.NetWordBuildParam;
import config.advice.CommonException;
import config.com.MyConfiguration;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *
 * app短信服务
 *
 * @author kongling
 * @package com.suda.platform.app
 * @date 2019-05-15  11:21
 * @project suda_cloud
 */
@RestController
@RequestMapping(value = "app/sms",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AppSmsController {
    @Autowired
    private MyConfiguration  myConfiguration;
    @Autowired
    private IStockUserService stockUserService;
    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation(notes = "发送验证码 ：\n" +
            "\n       account账号," +
            "         codeType:1 注册 2 重置密码 3 安全验证 4 支付密码设置 5 提币 6手机号绑定 7手机号验证", value = "发送验证码")
    @RequestMapping(value = "getTelCode", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getTelVcode(TelCodeTypeVO telCodeTypeVO, HttpServletRequest request, HttpServletResponse response) {
        String account = telCodeTypeVO.getAccount();
        Byte codeType = telCodeTypeVO.getCodeType();
        if (StringUtils.isBlank(account) ||!TelCodeTypeEnum.CODES.contains(codeType) ) {
            throw new CommonException(ResponseMsg.MISS_PARAM);
        }
        //短信存储到redis
        if(!redisUtils.isCanSendTwoSms(account,codeType)){
            throw new CommonException(ResponseMsg.SMS_NO_TIME_MINUTE_1);
        }
        //检查验证码类型
        checkTelCodeType(telCodeTypeVO);
        // 获取随机码
        String telVcode = RandomStringUtils.randomNumeric(myConfiguration.getCodeLength());
        telVcode = "123456";
        try {
            NetWordBuildParam netWordBuildParam = new NetWordBuildParam();
            netWordBuildParam.setMobile(account);
            netWordBuildParam.setCode(telVcode);
            // 发送短信
            String yzm = "1";//NetWordBuildSendUtil.send(netWordBuildParam);
            // 判断短信发送状态
            if (Integer.parseInt(yzm) == 1) {
                redisUtils.setSmsRedisHashValue(codeType,account,telVcode);
                return ResponseUtil.getSuccessMap("验证码发送成功");
            } else {
                return ResponseUtil.getNotNormalMap(ChinaToBuild.map.get(yzm));
            }
        } catch (Exception e) {
            throw new CommonException("短信发送失败：" + e.getMessage());
        }
    }


    private void checkTelCodeType(TelCodeTypeVO telCodeTypeVO) {
        if (telCodeTypeVO.getCodeType() != null) {
            if(telCodeTypeVO.getCodeType().byteValue() == TelCodeTypeEnum.CHANGE_PHONE.getCode()){
                return;
            }
            //查询账号
            StockUserLoginVO selUser = stockUserService.selectByAccount(telCodeTypeVO.getAccount());
            //注册短信
            if (telCodeTypeVO.getCodeType().byteValue() == TelCodeTypeEnum.REGISTER_TYPE.getCode()
                    || telCodeTypeVO.getCodeType().byteValue() == TelCodeTypeEnum.BIND_PHONE.getCode()) {
                if (selUser != null) {
                    throw new CommonException("该手机号已存在！");
                }
            }
            if (telCodeTypeVO.getCodeType().byteValue() == TelCodeTypeEnum.RESET_PSWD.getCode()
                    || telCodeTypeVO.getCodeType().byteValue() == TelCodeTypeEnum.TRADE_PSWD.getCode()
                    || telCodeTypeVO.getCodeType().byteValue() == TelCodeTypeEnum.COIN_TYPE.getCode()
                    || telCodeTypeVO.getCodeType().byteValue() == TelCodeTypeEnum.CHECK_PHONE.getCode()) {
                if (selUser == null) {
                    throw new CommonException(ResponseMsg.NOUSER);
                }
            }
        }
    }

}
