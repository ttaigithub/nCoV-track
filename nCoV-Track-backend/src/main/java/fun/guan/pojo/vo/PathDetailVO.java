package fun.guan.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
public class PathDetailVO {
    PatientVO patientData;
    List<PathLocationVO> addressData;

}
