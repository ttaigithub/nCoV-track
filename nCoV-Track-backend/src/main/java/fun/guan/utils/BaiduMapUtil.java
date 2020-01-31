package fun.guan.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class BaiduMapUtil {

    /**
     * 百度应用ak
     */
    private static String ak = "WyVTOuzW1xE3s6rqedhQTIABH7AE5SAZ";

    public static Map<String, BigDecimal> getLatAndLngByAddress(String addr) {
        String address = "";
        try {
            address = java.net.URLEncoder.encode(addr, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String url = String.format("http://api.map.baidu.com/place/v2/search?"
                + "ak=%s&output=json&query=%s&region=全国", ak, address);
        URL myURL = null;
        URLConnection httpsConn = null;
        //进行转码
        try {
            myURL = new URL(url);
        } catch (MalformedURLException e) {

        }
        StringBuffer sb = new StringBuffer();
        try {
            httpsConn = (URLConnection) myURL.openConnection();
            if (httpsConn != null) {
                InputStreamReader insr = new InputStreamReader(
                        httpsConn.getInputStream(), "UTF-8");
                BufferedReader br = new BufferedReader(insr);
                String data = null;
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                ;
                while ((data = br.readLine()) != null) {
                    sb.append(data);
                }
                insr.close();
            }
        } catch (IOException e) {

        }
        Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
        JsonObject resultJson = new JsonParser().parse(sb.toString()).getAsJsonObject();
        if (resultJson.get("status").getAsInt()!=0){
            return null;
        }
        JsonArray resultsArray = resultJson.get("results").getAsJsonArray();
        JsonObject location = resultsArray.get(0).getAsJsonObject();
        JsonObject locationObj = location.get("location").getAsJsonObject();
        //纬度
        BigDecimal lat = locationObj.get("lat").getAsBigDecimal();
        //经度
        BigDecimal lng = locationObj.get("lng").getAsBigDecimal();
        map.put("lat", lat);
        map.put("lng", lng);
        return map;
    }

    /**
     * 通过地址获取经纬度
     *
     * @param address 地址
     * @return 经纬度json
     */
    public static String getLatAndLng(String address) {
        Map<String, BigDecimal> resultMap = getLatAndLngByAddress(address);
        if (resultMap == null || resultMap.isEmpty()) {
            Map<String, BigDecimal> resultNull = new HashMap<>();
            resultNull.put("lat", new BigDecimal(BigInteger.ZERO));
            resultNull.put("lng", new BigDecimal(BigInteger.ZERO));
            return new Gson().toJson(resultNull);
        }
        return new Gson().toJson(resultMap);
    }


}
