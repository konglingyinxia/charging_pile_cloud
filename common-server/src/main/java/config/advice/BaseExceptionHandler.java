package config.advice;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import static com.util.Respons.ResponseUtil.RequestException;
import static com.util.Respons.ResponseUtil.getExceptionMap;

/**
 * 统一异常处理类
 */
@ControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public Map<String, Object> serverException(Throwable e) {
        log.error("日志记录错误:\n{}", ExceptionUtils.getStackTrace(e));
        if (e instanceof CommonException) {
            return RequestException(e.getMessage());
        }else if(e instanceof HttpRequestMethodNotSupportedException){
            return RequestException(e.getMessage());
        }else if(e instanceof WxErrorException){
            JSONObject jsonObject = JSONObject.parseObject(e.getMessage());
            return RequestException(jsonObject.getString("errmsg"));
        }
        return getExceptionMap();
    }

}
