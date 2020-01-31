package fun.guan.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

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
    private String ak = "WyVTOuzW1xE3s6rqedhQTIABH7AE5SAZ";

    public static Map<String, BigDecimal> getLatAndLngByAddress(String addr) {
        String address = "";
        try {
            address = java.net.URLEncoder.encode(addr,"UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String url = String.format("http://api.map.baidu.com/place/v2/search?"
                +"ak=WyVTOuzW1xE3s6rqedhQTIABH7AE5SAZ&output=json&query=%s&region=全国",address);
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
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();;
                while ((data = br.readLine()) != null) {
                    sb.append(data);
                }
                insr.close();
            }
        } catch (IOException e) {

        }
        Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
        JSONObject resultJson = JSON.parseObject(sb.toString());
        //resultJson  {"message":"ok","results":[{"uid":"30e1d0bb0c0014f8b6147fe6","name":"攀枝花市","location":{"lng":101.725544,"lat":26.588034}}],"status":0}
        JSONArray jsonArray = (JSONArray)resultJson.get("results");
        JSONObject results0Obj = (JSONObject)jsonArray.get(0);
        if (!resultJson.containsKey("location")){
            return null;
        }
        JSONObject locationObj = (JSONObject)results0Obj.get("location");
        System.out.println(resultJson);
        if (!locationObj.containsKey("lat")){
            return null;
        }
        //纬度
        BigDecimal lat = (BigDecimal)locationObj.get("lat");
        //经度
        BigDecimal lng = (BigDecimal)locationObj.get("lng");
        map.put("lat", lat);
        map.put("lng", lng);
        return map;
    }

    /**
     * 通过地址获取经纬度
     * @param address 地址
     * @return 经纬度json
     */
    public static String getLatAndLng(String address){
        Map<String, BigDecimal> resultMap = getLatAndLngByAddress(address);
        if (resultMap == null){
            Map<String,BigDecimal> resultNull = new HashMap<>();
            resultNull.put("lat",new BigDecimal(BigInteger.ZERO));
            resultNull.put("lng",new BigDecimal(BigInteger.ZERO));
            return new Gson().toJson(resultMap);
        }
        return new Gson().toJson(resultMap);
    }


}
