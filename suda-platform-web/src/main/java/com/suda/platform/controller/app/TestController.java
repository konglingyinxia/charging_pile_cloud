package com.suda.platform.controller.app;

import io.swagger.annotations.Api;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author kongling
 * @package com.suda.platform.controller.app
 * @date 2019-06-01  10:48
 * @project charging_pile_cloud
 */
@Controller
@RequestMapping(value = {"test/status"},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(description = "app用户登录注册及密码修改 和重置", tags = "app/user")
public class TestController {

    @RequestMapping("test")
    public void test(HttpServletRequest request, HttpServletResponse response){
        PrintWriter out = null;
        System.out.println(response.getHeaderNames());
        try {
            String data = request.getParameter("data");
            System.out.println("接收参数："+data);
            out = response.getWriter();
            out.print(data);
        } catch (Exception e) {

        } finally {
            if (out != null) {
                IOUtils.closeQuietly(out);
            }
        }

    }
}
