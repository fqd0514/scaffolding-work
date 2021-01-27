package com.tf.smart.community.wechat.common.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @author zlx
 * @date 2019/11/6 14:00
 * @description
 */
public class UuidUtil {

    public static String PASS_KEY = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String getUUID32() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String getUUID36() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取长度为num的随机数
     * @param num
     * @return
     */
    public static String getRandomStr(final int num) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= num; i++) {
            stringBuilder.append(PASS_KEY.charAt(new Random().nextInt(PASS_KEY.length())));
        }
        return stringBuilder.toString();
    }
}
