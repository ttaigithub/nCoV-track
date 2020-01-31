package fun.guan.service;

import com.github.pagehelper.PageInfo;
import fun.guan.pojo.vo.PathDetailVO;
import fun.guan.pojo.vo.PathLocationVO;
import fun.guan.pojo.vo.TwoPlaceVO;

import java.util.List;
import java.util.Map;

public interface PathLocationService {


    void add(String data);

    PageInfo<PathLocationVO> query(String data);

    PathDetailVO queryDetail(String data);

    int deleteByPatientId(Long patientId);

    void update(String data);

    List<TwoPlaceVO> queryByTime(String data);

    Map<Integer, Object> queryLocationByTime(String data);
}

