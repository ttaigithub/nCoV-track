package fun.guan.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import fun.guan.entity.Patient;
import fun.guan.pojo.ResultEnum;
import fun.guan.pojo.dto.PatientDTO;
import fun.guan.pojo.vo.PatientVO;
import fun.guan.service.PathLocationService;
import fun.guan.sysexception.BackendException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import fun.guan.mapper.PatientMapper;
import fun.guan.service.PatientService;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Resource
    private PatientMapper patientMapper;

    @Resource
    private PathLocationService pathLocationService;

    @Override
    public Long add(String data) {
        try {
            PatientDTO dto = new Gson().fromJson(data, PatientDTO.class);
            Patient patient = dto.toPatient();
            this.patientMapper.insertSelective(patient);
            System.out.println(patient.getRecordId());
            return patient.getRecordId();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BackendException(ResultEnum.DATABASE_INSERT_ERROR);
        }
    }

    @Override
    public PageInfo<PatientVO> query(String data) {
        PatientDTO dto = new Gson().fromJson(data, PatientDTO.class);
        int pageNum = dto.getPageNum();
        int pageSize = dto.getPageSize();
//        PageHelper.startPage(pageNum, pageSize);
        Page page = PageHelper.startPage(pageNum, pageSize);
//使用page的getTotal()
        Patient patient = dto.toPatient();
        Example example = new Example(Patient.class);
        Example.Criteria criteria = example.createCriteria();
        // TODO: 2020/1/29 查询条件
//        List<Patient> patientList = this.patientMapper.selectAll();
        List<Patient> patientList = new ArrayList<>();
        if (patient.getRecordId()!=null||patient.getPatientName()!=null||patient.getState()!=null||patient.getSex()!=null){
            criteria.andEqualTo("recordId",patient.getRecordId())
            .orLike("patientName","%"+patient.getPatientName()+"%")
                    // FIXME: 2020/1/30 根据性别查询
            .orEqualTo("sex",patient.getSex())
            .orEqualTo("state",patient.getState());
            patientList = this.patientMapper.selectByExample(example);
        }else {
            patientList = this.patientMapper.selectAll();
        }
//        patientList = this.patientMapper.selectAll();
//        long total = page.getTotal();
//        System.out.println("total = "+total);
        List<PatientVO> voList = new ArrayList<>();
        for (Patient p : patientList) {
            PatientVO vo = new PatientVO();
            vo.setId(p.getRecordId());
            vo.setPatientName(p.getPatientName());
            if (p.getSex()) {
                vo.setSex(1);
            } else {
                vo.setSex(0);
            }
            vo.setAge(p.getAge());
            vo.setConfirmedTime(p.getConfirmedTime().getTime());
            vo.setState(p.getState());
            voList.add(vo);
        }
        PageInfo pageInfo = new PageInfo<>(page);
        pageInfo.setList(voList);
        return pageInfo;

//        return new PageInfo<>(voList);
    }

    @Override
    public Patient selectByRecordId(Long patientId) {
        Patient patient = new Patient();
        patient.setRecordId(patientId);
        return this.patientMapper.selectByPrimaryKey(patientId);
    }

    @Override
    @Transactional(rollbackFor = BackendException.class)
    public void delete(String data) {
        try {
            PatientDTO dto = new Gson().fromJson(data,PatientDTO.class);
            int deleteNum = this.pathLocationService.deleteByPatientId(dto.getId());
            this.patientMapper.deleteByPrimaryKey(dto.toPatient());
        }catch (Exception e){
            throw new BackendException(ResultEnum.DATABASE_DELETE_ERROR);
        }
    }
}

