package fun.guan.service;

import com.github.pagehelper.PageInfo;
import fun.guan.entity.Patient;
import fun.guan.pojo.vo.PatientVO;

public interface PatientService {


    /**
     * 添加患者信息
     *
     * @param data 信息
     * @return
     */
    Long add(String data);

    /**
     * 患者信息查询
     *
     * @param data 查询条件
     * @return vo
     */
    PageInfo<PatientVO> query(String data);

    Patient selectByRecordId(Long patientId);

    void delete(String data);
}

