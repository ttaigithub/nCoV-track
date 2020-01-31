package fun.guan.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.*;
import fun.guan.entity.PathLocation;
import fun.guan.entity.Patient;
import fun.guan.pojo.ResultEnum;
import fun.guan.pojo.dto.PathLocationDTO;
import fun.guan.pojo.dto.PatientDTO;
import fun.guan.pojo.vo.PathDetailVO;
import fun.guan.pojo.vo.PathLocationVO;
import fun.guan.pojo.vo.PatientVO;
import fun.guan.pojo.vo.TwoPlaceVO;
import fun.guan.service.PatientService;
import fun.guan.sysexception.BackendException;
import fun.guan.utils.BaiduMapUtil;
import fun.guan.mapper.PathLocationMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import fun.guan.service.PathLocationService;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
public class PathLocationServiceImpl implements PathLocationService {

    @Resource
    private PathLocationMapper pathLocationMapper;
    @Resource
    private PatientService patientService;

    /**
     * 一次性添加所有信息
     *
     * @param data {
     *             patientData:{patientName:'kkk'},
     *             addressData:[
     *             {
     *             time:'',
     *             address:[]
     *             }
     *             ]
     *             }
     */
    @Override
    @Transactional(rollbackFor = BackendException.class)
    public void add(String data) {
        try {
            List<PathLocation> pathLocationList = new LinkedList<>();
            JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
            String patientData = jsonObject.get("patientData").getAsJsonObject().toString();
            Long patientId = this.patientService.add(patientData);
            JsonArray addressDataArray = jsonObject.get("addressData").getAsJsonArray();
            for (JsonElement jsonElement:addressDataArray){
                JsonObject addressData = jsonElement.getAsJsonObject();
                Long time = addressData.get("time").getAsLong();
                JsonArray addressArray = addressData.get("address").getAsJsonArray();
                if (addressArray.size()==1){
                    String departureName = addressArray.get(0).getAsJsonObject().get("name").getAsString();
                    // TODO: 2020/1/30 通过百度api获取经纬度
                    String destinationName = addressArray.get(0).getAsJsonObject().get("name").getAsString();
                    PathLocation pathLocation = new PathLocation();
                    pathLocation.setPatientId(patientId);
                    pathLocation.setAppearTime(new Date(time));
                    pathLocation.setDeparture(departureName);
                    pathLocation.setDepartureLocation(BaiduMapUtil.getLatAndLng(departureName));
                    pathLocation.setDestination(destinationName);
                    pathLocation.setDestinationLocation(BaiduMapUtil.getLatAndLng(destinationName));
                    pathLocation.setIsDelete(false);
                    pathLocation.setGmtCreate(new Date());
                    pathLocation.setGmtModified(new Date());
                    pathLocationList.add(pathLocation);
                }else {
                    for (int i=0;i<addressArray.size();i++){

                        if (i!=addressArray.size()-1){
                            String departureName = addressArray.get(i).getAsJsonObject().get("name").getAsString();
                            // TODO: 2020/1/30 调用百度接口获取经纬度
                            String destinationName = addressArray.get(i+1).getAsJsonObject().get("name").getAsString();
                            // TODO: 2020/1/30 入库
                            PathLocation pathLocation = new PathLocation();
                            pathLocation.setPatientId(patientId);
                            pathLocation.setAppearTime(new Date(time));
                            pathLocation.setDeparture(departureName);
                            pathLocation.setDepartureLocation(BaiduMapUtil.getLatAndLng(departureName));
                            pathLocation.setDestination(destinationName);
                            pathLocation.setDestinationLocation(BaiduMapUtil.getLatAndLng(destinationName));
                            pathLocation.setIsDelete(false);
                            pathLocation.setGmtCreate(new Date());
                            pathLocation.setGmtModified(new Date());
                            pathLocationList.add(pathLocation);
                        }else {
                            break;
                        }
                    }
                }

            }
            this.pathLocationMapper.insertList(pathLocationList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BackendException(ResultEnum.DATABASE_INSERT_ERROR);
        }
    }

    @Override
    public PageInfo<PathLocationVO> query(String data) {
        PathLocationDTO dto = new Gson().fromJson(data,PathLocationDTO.class);
        int pageNum = dto.getPageNum();
        int pageSize = dto.getPageSize();
        Page page = PageHelper.startPage(pageNum, pageSize);

        PathLocation pathLocation = dto.toEntity();
        Example example = new Example(PathLocation.class);
        Example.Criteria criteria = example.createCriteria();
        List<PathLocation> pathLocationList = new ArrayList<>();
        List<PathLocationVO> voList = new ArrayList<>();
        if (pathLocation.getPatientId()!=null){
            criteria.andEqualTo("patientId",pathLocation.getPatientId());
            pathLocationList = this.pathLocationMapper.selectByExample(example);
        }else{
            pathLocationList = this.pathLocationMapper.selectAll();
        }
        for (PathLocation p:pathLocationList){
            PathLocationVO vo = new PathLocationVO(p);
            voList.add(vo);
        }
        PageInfo pageInfo = new PageInfo<>(page);
        pageInfo.setList(voList);
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = BackendException.class)
    public PathDetailVO queryDetail(String data) {
        PatientDTO dto = new Gson().fromJson(data,PatientDTO.class);
        Long patientId = dto.getId();
        Patient patient = this.patientService.selectByRecordId(patientId);
        Example example = new Example(PathLocation.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("patientId",patientId);
        List<PathLocation> pathLocationList = this.pathLocationMapper.selectByExample(example);
        List<PathLocationVO> pathLocationVOList = new ArrayList<>();
        for (PathLocation p:pathLocationList){
            PathLocationVO vo = new PathLocationVO();
            vo.setId(p.getRecordId());
            vo.setAppearTime(p.getAppearTime().getTime());
            vo.setDeparture(p.getDeparture());
            vo.setDepartureLocation(p.getDepartureLocation());
            vo.setDestination(p.getDestination());
            vo.setDestinationLocation(p.getDestinationLocation());
            vo.setPatientId(patientId);
            pathLocationVOList.add(vo);
        }
        PathDetailVO vo = new PathDetailVO();
        PatientVO patientVO = new PatientVO();
        patientVO.setId(patient.getRecordId());
        patientVO.setPatientName(patient.getPatientName());
        if (patient.getSex()){
            patientVO.setSex(1);
        }else {
            patientVO.setSex(0);
        }
        patientVO.setAge(patient.getAge());
        patientVO.setState(patient.getState());
        patientVO.setConfirmedTime(patient.getConfirmedTime().getTime());
        vo.setPatientData(patientVO);
        vo.setAddressData(pathLocationVOList);
        return vo;
    }

    @Override
    public int deleteByPatientId(Long patientId) {
        Example example = new Example(PathLocation.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("patientId",patientId);
        return this.pathLocationMapper.deleteByExample(example);
    }

    @Override
    public void update(String data) {
        try {
            List<PathLocation> pathLocationList = new LinkedList<>();
            JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
            String patientData = jsonObject.get("patientData").getAsJsonObject().toString();
            this.patientService.delete(patientData);
            Long patientId = this.patientService.add(patientData);
            JsonArray addressDataArray = jsonObject.get("addressData").getAsJsonArray();
            for (JsonElement jsonElement:addressDataArray){
                JsonObject addressData = jsonElement.getAsJsonObject();
                Long time = addressData.get("time").getAsLong();
                JsonArray addressArray = addressData.get("address").getAsJsonArray();
                if (addressArray.size()==1){
                    String departureName = addressArray.get(0).getAsJsonObject().get("name").getAsString();
                    // TODO: 2020/1/30 通过百度api获取经纬度
                    String destinationName = addressArray.get(0).getAsJsonObject().get("name").getAsString();
                    PathLocation pathLocation = new PathLocation();
                    pathLocation.setPatientId(patientId);
                    pathLocation.setAppearTime(new Date(time));
                    pathLocation.setDeparture(departureName);
                    pathLocation.setDepartureLocation(BaiduMapUtil.getLatAndLng(departureName));
                    pathLocation.setDestination(destinationName);
                    pathLocation.setDestinationLocation(BaiduMapUtil.getLatAndLng(destinationName));
                    pathLocation.setIsDelete(false);
                    pathLocation.setGmtCreate(new Date());
                    pathLocation.setGmtModified(new Date());
                    pathLocationList.add(pathLocation);
                }else {
                    for (int i=0;i<addressArray.size();i++){

                        if (i!=addressArray.size()-1){
                            String departureName = addressArray.get(i).getAsJsonObject().get("name").getAsString();
                            // TODO: 2020/1/30 调用百度接口获取经纬度
                            String destinationName = addressArray.get(i+1).getAsJsonObject().get("name").getAsString();
                            // TODO: 2020/1/30 入库
                            PathLocation pathLocation = new PathLocation();
                            pathLocation.setPatientId(patientId);
                            pathLocation.setAppearTime(new Date(time));
                            pathLocation.setDeparture(departureName);
                            pathLocation.setDepartureLocation(BaiduMapUtil.getLatAndLng(departureName));
                            pathLocation.setDestination(destinationName);
                            pathLocation.setDestinationLocation(BaiduMapUtil.getLatAndLng(destinationName));
                            pathLocation.setIsDelete(false);
                            pathLocation.setGmtCreate(new Date());
                            pathLocation.setGmtModified(new Date());
                            pathLocationList.add(pathLocation);
                        }else {
                            break;
                        }
                    }
                }

            }
            this.pathLocationMapper.insertList(pathLocationList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BackendException(ResultEnum.DATABASE_INSERT_ERROR);
        }
    }

    @Override
    public List<TwoPlaceVO> queryByTime(String data) {
        JsonObject dataJson = new JsonParser().parse(data).getAsJsonObject();
        Long startTime = dataJson.get("startTime").getAsLong();
        Long endTime = dataJson.get("endTime").getAsLong();
        Example example = new Example(PathLocation.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andBetween("appearTime",new Date(startTime),new Date(endTime));
        List<PathLocation> pathLocationList = this.pathLocationMapper.selectByExample(example);
        List<TwoPlaceVO> voList = new ArrayList<>();
        for (PathLocation p:pathLocationList){
            TwoPlaceVO vo = new TwoPlaceVO();
            vo.setDeparture(p.getDeparture());
            vo.setDestination(p.getDestination());
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public Map<Integer, Object> queryLocationByTime(String data) {
        JsonObject dataJson = new JsonParser().parse(data).getAsJsonObject();
        Long startTime = dataJson.get("startTime").getAsLong();
        Long endTime = dataJson.get("endTime").getAsLong();
        List<PathLocation> pathLocationList = new ArrayList<>();
        int state;
        if (dataJson.get("state")!=null){
            state = dataJson.get("state").getAsInt();
            pathLocationList = this.pathLocationMapper.selectByTimeAndState(new Date(startTime),new Date(endTime),state);
            Map<Integer,Object> vo = new HashMap<>();
            for (int i=0;i<pathLocationList.size();i++){
                PathLocation pathLocation = pathLocationList.get(i);
                List<String> locationList = new ArrayList<>();
                locationList.add(pathLocation.getDepartureLocation());
                locationList.add(pathLocation.getDestinationLocation());
                vo.put(i,locationList);
            }
            return vo;
        }else {
            List<PathLocation> pathLocation0 = this.pathLocationMapper
                    .selectByTimeAndState(new Date(startTime),new Date(endTime),0);
            List<PathLocation> pathLocation1 = this.pathLocationMapper
                    .selectByTimeAndState(new Date(startTime),new Date(endTime),1);
            List<PathLocation> pathLocation2 = this.pathLocationMapper
                    .selectByTimeAndState(new Date(startTime),new Date(endTime),2);
            List<PathLocation> pathLocation3 = this.pathLocationMapper
                    .selectByTimeAndState(new Date(startTime),new Date(endTime),3);
            List<PathLocation> pathLocation4 = this.pathLocationMapper
                    .selectByTimeAndState(new Date(startTime),new Date(endTime),4);
            Map<Integer,Object> vo = new HashMap<>();
//            Map<Integer,Object> vo0 = new HashMap<>();
//            Map<Integer,Object> vo1 = new HashMap<>();
//            Map<Integer,Object> vo2 = new HashMap<>();
//            Map<Integer,Object> vo3 = new HashMap<>();
//            Map<Integer,Object> vo4 = new HashMap<>();
            List<Object> vo0 = new LinkedList<>();
            List<Object> vo1 = new LinkedList<>();
            List<Object> vo2 = new LinkedList<>();
            List<Object> vo3 = new LinkedList<>();
            List<Object> vo4 = new LinkedList<>();
            for (int i=0;i<pathLocation0.size();i++){
                PathLocation pathLocation = pathLocation0.get(i);
                List<String> locationList = new ArrayList<>();
                locationList.add(pathLocation.getDepartureLocation());
                locationList.add(pathLocation.getDestinationLocation());
                vo0.add(locationList);
            }
            for (int i=0;i<pathLocation1.size();i++){
                PathLocation pathLocation = pathLocation1.get(i);
                List<String> locationList = new ArrayList<>();
                locationList.add(pathLocation.getDepartureLocation());
                locationList.add(pathLocation.getDestinationLocation());
                vo1.add(locationList);
            }
            for (int i=0;i<pathLocation2.size();i++){
                PathLocation pathLocation = pathLocation2.get(i);
                List<String> locationList = new ArrayList<>();
                locationList.add(pathLocation.getDepartureLocation());
                locationList.add(pathLocation.getDestinationLocation());
                vo2.add(locationList);
            }
            for (int i=0;i<pathLocation3.size();i++){
                PathLocation pathLocation = pathLocation3.get(i);
                List<String> locationList = new ArrayList<>();
                locationList.add(pathLocation.getDepartureLocation());
                locationList.add(pathLocation.getDestinationLocation());
                vo3.add(locationList);
            }
            for (int i=0;i<pathLocation4.size();i++){
                PathLocation pathLocation = pathLocation4.get(i);
                List<String> locationList = new ArrayList<>();
                locationList.add(pathLocation.getDepartureLocation());
                locationList.add(pathLocation.getDestinationLocation());
                vo4.add(locationList);
            }
            vo.put(0,vo0);
            vo.put(1,vo1);
            vo.put(2,vo2);
            vo.put(3,vo3);
            vo.put(4,vo4);
            return vo;
        }

    }
}

