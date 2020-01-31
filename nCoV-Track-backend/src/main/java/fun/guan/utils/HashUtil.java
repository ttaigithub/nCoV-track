package fun.guan.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @program: encryptutil
 * @description:
 * @author: JingXi
 * @create: 2019-06-19 13:50
 * @Copyright © 2019 JingXi. All rights reserved
 **/
public class HashUtil {

    public static String fileHash(String filePath) throws InterruptedException {

        String method = "MD5";
        //拿到一个MD5转换器,如果想使用SHA-1或SHA-256，则传入SHA-1,SHA-256
        try {
            MessageDigest md = MessageDigest.getInstance(method);
            // TODO: 2019/10/19 解决文件读写占用问题
            Thread.sleep(500);
            FileInputStream fis = new FileInputStream(filePath);
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            fis.close();
            byte[] md5Bytes  = md.digest();
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                //这里借助了Integer类的方法实现16进制的转换
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param decript 要加密的字符串
     * @return 加密的字符串
     * MD5加密
     */
    public static String md5(String decript) {
        char[] hexDigits = { // 用来将字节转换成 16 进制表示的字符
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
                'e', 'f'};
        try {
            byte[] strTemp = decript.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            // MD5 的计算结果是一个 128 位的长整数，
            byte[] tmp = mdTemp.digest();
            // 用字节表示就是 16 个字节
            // 每个字节用 16 进制表示的话，使用两个字符，
            char[] strs = new char[16 * 2];
            // 所以表示成 16 进制需要 32 个字符
            // 表示转换结果中对应的字符位置
            int k = 0;
            // 从第一个字节开始，对 MD5 的每一个字节
            for (int i = 0; i < 16; i++) {
                // 转换成 16 进制字符的转换
                // 取第 i 个字节
                byte byte0 = tmp[i];
                // 取字节中高 4 位的数字转换,
                strs[k++] = hexDigits[byte0 >>> 4 & 0xf];
                // >>> 为逻辑右移，将符号位一起右移
                // 取字节中低 4 位的数字转换
                strs[k++] = hexDigits[byte0 & 0xf];
            }
            // 换后的结果转换为字符串
            return new String(strs);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * SHA1
     * @param strText
     * @return
     */
    public static String SHA1(final String strText) {
        return SHA(strText, "SHA-1");
    }

    /**
     * 传入文本内容，返回 SHA-256 串
     *
     * @param strText
     * @return
     */
    public static String SHA256(final String strText) {
        return SHA(strText, "SHA-256");
    }

    /**
     * 传入文本内容，返回 SHA-512 串
     *
     * @param strText
     * @return
     */
    public static String SHA512(final String strText) {
        return SHA(strText, "SHA-512");
    }

    /**
     * SHA-224
     * @param strText
     * @return
     */
    public static String SHA224(final String strText) {
        return SHA(strText, "SHA-224");
    }

    /**
     * SHA-384
     * @param strText
     * @return
     */
    public static String SHA384(final String strText) {
        return SHA(strText, "SHA-384");
    }

    /**
     * 字符串 SHA 加密
     * @param strText
     * @param strType
     * @return
     */
    private static String SHA(final String strText, final String strType) {
        // 返回值
        String strResult = null;

        // 是否是有效字符串
        if (strText != null && strText.length() > 0) {
            try {
                // SHA 加密开始
                // 创建加密对象 并傳入加密類型
                MessageDigest messageDigest = MessageDigest
                        .getInstance(strType);
                // 传入要加密的字符串
                messageDigest.update(strText.getBytes());
                // 得到 byte 類型结果
                byte[] byteBuffer = messageDigest.digest();

                // 將 byte 轉換爲 string
                StringBuilder strHexString = new StringBuilder();
                // 遍歷 byte buffer
                for (int i = 0; i < byteBuffer.length; i++) {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                // 得到返回結果
                strResult = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return strResult;
    }
}

