package fun.guan.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: dc-platform
 * @description:
 * @author: JingXi
 * @create: 2019-08-30 11:02
 * @Copyright © 2019 JingXi. All rights reserved
 **/
public class CommUtil {

    public static String fileHash(String filePath, String method){

        //拿到一个MD5转换器,如果想使用SHA-1或SHA-256，则传入SHA-1,SHA-256
        try {
            MessageDigest md = MessageDigest.getInstance(method);
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
     * unicode 转字符串
     */
    public static String unicode2String(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            string.append((char) data);
        }
        return string.toString();
    }

    /**
     * 字符串转换unicode
     */
    public static String string2Unicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }

    /**
     * 转换bean为map
     *
     * @param source 要转换的bean
     * @param <T>    bean类型
     * @return 转换结果
     */
    public static <T> Map<String, Object> bean2Map(T source) throws IllegalAccessException {
        Map<String, Object> result = new HashMap<>();

        Class<?> sourceClass = source.getClass();
        //拿到所有的字段,不包括继承的字段
        Field[] sourceFiled = sourceClass.getDeclaredFields();
        for (Field field : sourceFiled) {
            //设置可访问,不然拿不到private
            field.setAccessible(true);
            //配置了注解的话则使用注解名称,作为header字段
            FieldName fieldName = field.getAnnotation(FieldName.class);
            if (fieldName == null) {
                result.put(field.getName(), field.get(source));
            } else {
                if (fieldName.Ignore()) {
                    continue;
                }
                result.put(fieldName.value(), field.get(source));
            }
        }
        return result;
    }

    /**
     * map转bean
     * @param source   map属性
     * @param instance 要转换成的备案
     * @return 该bean
     */
    public static <T> T map2Bean(Map<String, Object> source, Class<T> instance) {
        try {
            T object = instance.newInstance();
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                FieldName fieldName = field.getAnnotation(FieldName.class);
                if (fieldName != null){
                    field.set(object,source.get(fieldName.value()));
                }else {
                    field.set(object,source.get(field.getName()));
                }
            }
            return object;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String timeFormat(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 将 s 进行 BASE64 编码
     */
    public static String getBASE64(String s) {
        if (s == null) {
            return null;
        }
        Base64.Encoder encoder = Base64.getEncoder();
        String ecode = encoder.encodeToString(s.getBytes());
        return ecode;
    }

    /**
     * 将 BASE64 编码的字符串 s 进行解码
     */
    public static String getFromBASE64(String s) {
        if (s == null) {
            return null;
        }
        // BASE64Decoder decoder = new BASE64Decoder();
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            byte[] b = decoder.decode(s);
            return new String(b);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 字符串的异或
     * @param str1
     * @param str2
     * @return
     */
    public static String twoStringXor(String str1, String str2) {
        byte b1[] = str1.getBytes();
        byte b2[] = str2.getBytes();
        byte longbytes[],shortbytes[];
        if(b1.length>=b2.length){
            longbytes = b1;
            shortbytes = b2;
        }else{
            longbytes = b2;
            shortbytes = b1;
        }
        byte xorstr[] = new byte[longbytes.length];
        int i = 0;
        for (; i < shortbytes.length; i++) {
            xorstr[i] = (byte)(shortbytes[i]^longbytes[i]);
        }
        for (;i<longbytes.length;i++){
            xorstr[i] = longbytes[i];
        }
        return new String(xorstr);
    }
}

