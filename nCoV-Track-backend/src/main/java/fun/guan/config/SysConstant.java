package fun.guan.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @program: dc-platform
 * @description: 常量
 * @author: JingXi
 * @create: 2019-08-28 14:45
 * @Copyright © 2019 JingXi. All rights reserved
 **/
@Component
public class SysConstant {


    public static final String TOKEN = "token";

    public static final String COMMA = ",";

    /**jwt密钥*/
    public static String JWT_SEED;


    public static String getJwtSeed() {
        return JWT_SEED;
    }

    @Value("${taisecurity.jwtSeed}")
    public void setJwtSeed(String jwtSeed) {
        JWT_SEED = jwtSeed;
    }

}

