package com.suda.platform.controller.common;

import com.util.FileUpload;
import com.util.Respons.ResponseMsg;
import com.util.Respons.ResponseUtil;
import com.util.aliOSS.OSSFather;
import com.util.aliOSS.OSSInterface;
import config.advice.CommonException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author 卫星
 * @package com.sskj.common.controller
 * @date 2019-03-08  10:20
 * @project CurrenCy-Cloud
 */


@Controller
@RequestMapping(value = "common/file",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(description = "文件上传(浏览器调用)", tags = "common/file")
public class FileUploadController {

    @RequestMapping(value = "upImg", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "上传图片", value = "上传图片")
    public Map<String, Object> uploadImg(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;
        // 身份证截图
        MultipartFile imgUrl = mhs.getFile("file");
        try {

            String idCardFront = FileUpload.uploadFile(imgUrl, request);
            return ResponseUtil.getSuccessNoMsg(idCardFront);
        } catch (IOException e) {
            throw new CommonException("图片上传失败");
        }
    }

    /**
     * 阿里云上传图片接口
     *
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value = "uploadAliOss", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "上传图片", value = "上传图片")
    public Map<String, Object> uploadAliOss(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;

        MultipartFile file = mhs.getFile("file");
        try {
            String fileName = FileUpload.getFileName(file);
            if (StringUtils.isBlank(fileName)) {
                throw new CommonException("未知的文件格式");
            }
            String url = OSSInterface.uploadImage(file.getOriginalFilename(), file.getInputStream(), OSSFather.bucketName_user);
            if(com.util.StringUtils.isBlank(url)){
                return ResponseUtil.getNotNormalMap(ResponseMsg.UPLOAD_FAIL);
            }else {
                return ResponseUtil.getSuccessNoMsg(url);
            }
        } catch (IOException e) {
            throw new CommonException("图片上传失败");
        }
    }
}
