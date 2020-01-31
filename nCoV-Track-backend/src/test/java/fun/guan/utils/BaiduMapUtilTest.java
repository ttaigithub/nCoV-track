package fun.guan.utils;

import com.google.gson.Gson;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

public class BaiduMapUtilTest {

    @Test
    public void test(){
        BaiduMapUtil baiduMapUtil = new BaiduMapUtil();
        Map<String, BigDecimal> result = baiduMapUtil.getLatAndLngByAddress("广西贵港市");
        System.out.println(new Gson().toJson(result));
    }
}