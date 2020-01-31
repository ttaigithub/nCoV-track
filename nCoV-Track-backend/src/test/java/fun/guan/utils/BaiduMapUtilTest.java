package fun.guan.utils;

import com.google.gson.Gson;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

public class BaiduMapUtilTest {

    @Test
    public void test(){
        BaiduMapUtil baiduMapUtil = new BaiduMapUtil();
        String result = baiduMapUtil.getLatAndLng("上海市");
        System.out.println(result);
    }
}