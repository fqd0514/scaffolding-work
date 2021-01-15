package com.tf.smart.community.wechat.common.utils;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件工具类
 *
 * @author 翟晶
 */
public class FileUtil {
    public static String FILE_SIZE_UNIT_B = "B";
    public static String FILE_SIZE_UNIT_K = "K";
    public static String FILE_SIZE_UNIT_M = "M";
    public static String FILE_SIZE_UNIT_G = "G";

    /**
     * 校验文件大小
     *
     * @param file 文件
     * @param size 文件大小
     * @param unit 文件大小单位
     * @return
     */
    public static boolean checkFileSize(MultipartFile file, int size, String unit) {
        double fileSize = 0;

        switch (unit.toUpperCase()) {
            case "B":
                fileSize = (double) file.getSize();
                break;
            case "K":
                fileSize = (double) file.getSize() / 1024;
                break;
            case "M":
                fileSize = (double) file.getSize() / 1048576;
                break;
            case "G":
                fileSize = (double) file.getSize() / 1073741824;
                break;
        }

        return fileSize > size;
    }
}
