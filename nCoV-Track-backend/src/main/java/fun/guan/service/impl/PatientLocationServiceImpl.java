package fun.guan.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import fun.guan.mapper.PatientLocationMapper;
import fun.guan.service.PatientLocationService;
@Service
public class PatientLocationServiceImpl implements PatientLocationService{

    @Resource
    private PatientLocationMapper patientLocationMapper;

}
