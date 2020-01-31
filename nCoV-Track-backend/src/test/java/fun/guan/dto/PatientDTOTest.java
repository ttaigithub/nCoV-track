package fun.guan.pojo.dto;


import com.google.gson.Gson;
import fun.guan.entity.Patient;
import org.junit.Test;

import java.util.Date;

public class PatientDTOTest {

    @Test
    public void dtoToJson(){
        PatientDTO dto = new PatientDTO();
        dto.setPatientName("王小明");
        dto.setSex(1);
        dto.setAge(18);
        dto.setConfirmedTime(new Date().getTime());
        dto.setState(1);
        System.out.println(new Gson().toJson(dto));
        Patient patient = dto.toPatient();
        System.out.println(new Date(1580299544434L));
    }
}