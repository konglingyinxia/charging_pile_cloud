package config.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 卫星
 * @package com.sskj.common.filter
 * @date 2018-12-13  09:38
 * @project C2C
 */
public class FilterComm {


    public static boolean handleExcludeURL(HttpServletRequest request, HttpServletResponse response, List<String> excludes) {

        if (excludes == null || excludes.isEmpty()) {
            return false;
        }

        String url = request.getServletPath();
        for (String pattern : excludes) {
            Pattern p = Pattern.compile("^" + pattern);
            Matcher m = p.matcher(url);
            if (m.find()) {
                return true;
            }
        }

        return false;
    }

}
