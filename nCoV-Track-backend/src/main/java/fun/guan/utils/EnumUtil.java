package fun.guan.utils;


import fun.guan.pojo.CodeEnum;

/**
 * @program: light-privacy-backend
 * @description: 枚举工具类
 * @author: JingXi
 * @create: 2019-11-06 14:46
 * @Copyright © 2019 JingXi. All rights reserved
 **/
public class EnumUtil {
    public static <T extends CodeEnum> String getByCode(Integer code, Class<T> t){
        for(T item: t.getEnumConstants()){
            if(item.getCode().equals(code)){
                return item.getMsg();
            }
        }
        return "";
    }
}

