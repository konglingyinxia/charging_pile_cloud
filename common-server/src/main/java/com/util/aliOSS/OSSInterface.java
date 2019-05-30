package com.util.aliOSS;

import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.PutObjectRequest;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public class OSSInterface extends OSSFather {
    public OSSInterface() {
        super();
    }

    /**
     * 上传文件
     *
     * @param bucketName 要上传到哪个存储区域中   hrex
     * @param object     要上传的文件 或是 字符串
     * @param fileName   文件上传路径
     * @return 返回存储的区域 和存储的名字
     */
    public static String upload(Object object, String bucketName, String fileName) {
        // 把字符串存入OSS，Object的名称为firstKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
        //判断是否传进bucketName 如果没有传 那就引用父类的参数
        if (StringUtils.isBlank(bucketName)) {
            bucketName = bucketName_user;
        }
        fileName = folder + fileName;
        //判断object是什么类型 调用什么多态方法
        if (object instanceof String) {
            InputStream is = new ByteArrayInputStream(((String) object).getBytes());
            OSSFather.getOSSClient().putObject(bucketName, fileName, is);
        }
        if (object instanceof InputStream) {
            OSSFather.getOSSClient().putObject(bucketName, fileName, (InputStream) object);
        }
        if (object instanceof File) {
            File file = (File) object;
            OSSFather.getOSSClient().putObject(bucketName, fileName, file);
        }
        if (object instanceof Byte[]) {
            byte[] content = (byte[]) object;
            OSSFather.getOSSClient().putObject(bucketName, fileName, new ByteArrayInputStream(content));
        }
        OSSFather.getOSSClient().shutdown();
        return "/" + fileName;
    }

    //上传图片 返回图片url
    public static String uploadImage(String fileName, InputStream inputStream, String bucketName) {
        try {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String[] names = fileName.split("[.]");
            String name = uuid + "." + names[names.length - 1];
            OSSFather.getOSSClient().putObject(new PutObjectRequest(bucketName, folder.substring(1) + name, inputStream));
            return key + folder + name ;// last;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } finally {
            ossClient.shutdown();
        }
        return null;

    }

    /**
     * 查看某个存储区域的所有文件
     *
     * @param bucketName 要查看的存储区域
     * @return
     */
    public List<OSSObjectSummary> findObject(String bucketName) {
        if (bucketName == null || bucketName.equals("")) {
            bucketName = bucketName_user;
        }
        ObjectListing objectListing = ossClient.listObjects(bucketName);
        List<OSSObjectSummary> list = objectListing.getObjectSummaries();
        ossClient.shutdown();
        return list;
    }

    /**
     * 删除某个文件
     *
     * @param bucketName 存储区域
     * @param url    文件key
     */
    public void delFile(String bucketName, String url) {
        String[] key = url.split(folder + "");
        String[] urls = key[1].split("\\?");
        String newUrl = folder + urls[0];
        ossClient.deleteObject(bucketName, newUrl);
        ossClient.shutdown();
    }

    /**
     * 获取图片
     *
     * @param response   服务端
     * @param bucketName 存储区域
     * @param firstKey   文件key
     * @throws IOException
     */
    public void getPhoto(HttpServletResponse response, String bucketName, String firstKey) throws IOException {
        if (bucketName == null || bucketName.equals("")) {
            bucketName = bucketName_user;
        }
        try {
            InputStream fis = ossClient.getObject(bucketName, firstKey).getObjectContent();
            // 设置输出的格式
            response.reset();
            response.setContentType("image/png");
            // 循环取出流中的数据
            byte[] b = new byte[100];
            int len;
            while ((len = fis.read(b)) > 0) {
                response.getOutputStream().write(b, 0, len);
            }
            fis.close();
            ossClient.shutdown();
        } catch (IOException c) {
            c.printStackTrace();
        }

    }

    /**
     * 上传图片
     *
     * @param fileName    文件名
     * @param inputStream 流
     */
    public String uploadImageToOSS(String fileName, InputStream inputStream) {
        /**
         * 创建OSS客户端
         */
        try {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String[] names = fileName.split("[.]");
            String name = uuid + "." + names[names.length - 1];
            ossClient.putObject(new PutObjectRequest(bucketName_user, folder + name, inputStream));
            return key + folder + name;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } finally {
            ossClient.shutdown();
        }
        return null;
    }


    public static void main(String[] args) {
        OSSInterface ossInterface = new OSSInterface();
        //  System.err.println(ossInterface.uploadString("你好",null));
  /*      for (OSSObjectSummary object : ossInterface.findObject("ziyitest")) {
            System.out.println("\t" + object.getKey());
        }
        ossInterface.delFile(bucketName,"README.md");*/
//       System.err.println(ossInterface.uploadString(ossInterface.image2byte(),null));
        /*for (OSSObjectSummary object : ossInterface.findObject("ziyitest")) {
            System.out.println("\t" + object.getKey());
        }*/


    }

}
