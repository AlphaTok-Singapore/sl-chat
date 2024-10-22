package io.pisceshub.muchat.server.util;

import java.util.Random;

/**
 * @author muxiaohui
 * @desc 随机生成速聊号
 */
public class CodeUtils {
    public static String getRandomCode(int length) {
        String context = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefg0123456789";
        StringBuilder stringBuilder = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(context.length());
            char randomChar = context.charAt(index);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }
}
