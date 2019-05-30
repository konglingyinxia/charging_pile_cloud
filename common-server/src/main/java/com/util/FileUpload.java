package com.util;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author zhengliangzhang
 */
public class FileUpload {
    // 上传地址路径
    public static String FILE_PATH = CommonConfig.FILE_PATH;
    // 图片路径
    public static String IMGPATH = CommonConfig.IMG_PATH;

    static {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    // 文件上传
    public static File getFile(String fileName) {
        return new File(FILE_PATH, fileName);
    }

    //获取 重组文件名字
    public static String getFileName(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String[] split = fileName.split("\\.");
        fileName = ConstantConfig.PERIOD + split[split.length - 1];
        if (fileName.equals(ConstantConfig.PERIOD)) {
            return "";
        }
        UUID uuid = UUID.randomUUID();
        fileName = System.currentTimeMillis() + uuid.toString() + String.valueOf(fileName);
        return fileName;
    }

    /**
     * 上传 返回文件名称
     */
    public static String uploadFile(MultipartFile file, HttpServletRequest request)
            throws IOException {
        String imgPath = IMGPATH;
        String fileName = getFileName(file);
        if (fileName.equals("")) {
            return "";
        }
        File tempFile =
                new File(FILE_PATH, fileName);
        if (!tempFile.getParentFile().exists()) {
            tempFile.getParentFile().mkdir();
        }
        if (!tempFile.exists()) {
            tempFile.createNewFile();
        }
        file.transferTo(tempFile);
        return "/" + imgPath + tempFile.getName();
    }

    /**
     * 判断上传文件是否为图片
     *
     * @throws IOException
     */
    public static boolean isImage(MultipartFile file) throws IOException {
        BufferedImage bi = ImageIO.read(file.getInputStream());
        if (bi == null) {
            return false;
        }
        return true;
    }
}
