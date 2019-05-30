package com.util.aliOSS;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.BucketInfo;
import com.util.CommonConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This sample demonstrates how to upload/download an object to/from
 * Aliyun OSS using the OSS SDK for Java.
 */
public class OSSFather {
    static Logger logger = LoggerFactory.getLogger(OSSFather.class);
    static String endpoint = CommonConfig.endpoint;
    static String accessKeyId = CommonConfig.accessKeyId;
    static String accessKeySecret = CommonConfig.accessKeySecret;
    public static String bucketName_user = CommonConfig.bucketName_user;

    /**
     * OSS的文件夹名
     */
    static String folder = CommonConfig.IMG_PATH;
    //这里是你存放图片的文件夹名
    static String key = "";
    static String last = "?x-oss-process=image/resize,w_300,h_300/quality,q_100";
    static OSSClient ossClient;
    static ClientConfiguration conf;

    static {
        conf = new ClientConfiguration();
        conf.setConnectionTimeout(10000);
    }


    public static OSSClient getOSSClient() {
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);
        if (ossClient.doesBucketExist(bucketName_user)) {
            logger.debug("您已经创建Bucket：" + bucketName_user + "。");
        } else {
            logger.debug("您的Bucket不存在，创建Bucket：" + bucketName_user + "。");
            ossClient.createBucket(bucketName_user);
        }
        BucketInfo info = ossClient.getBucketInfo(bucketName_user);
        logger.debug("Bucket " + bucketName_user + "的信息如下：");
        logger.debug("\t数据中心：" + info.getBucket().getLocation());
        logger.debug("\t创建时间：" + info.getBucket().getCreationDate());
        logger.debug("\t用户标志：" + info.getBucket().getOwner());
        return ossClient;
    }

    public OSSFather() {

    }
}