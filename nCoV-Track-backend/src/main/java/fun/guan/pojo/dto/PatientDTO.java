package fun.guan.pojo.dto;

import fun.guan.entity.Patient;
import lombok.Data;

import java.util.Date;

@Data
public class PatientDTO extends PageInfoDTO {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 姓名
     */
    private String patientName;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 确诊日期
     */
    private long confirmedTime;

    /**
     * 状态
     */
    private Integer state;

    /**
     * dto转entity
     * @return entity
     */
    public Patient toPatient(){
        Patient patient = new Patient();
        patient.setRecordId(id);
        patient.setPatientName(patientName);
        if (sex!=null){
            switch (sex){
                case 1:
                    patient.setSex(true);
                    break;
                default:
                    patient.setSex(false);
            }
        }

        patient.setAge(age);
        // FIXME: 2020/1/30 确诊日期
        patient.setConfirmedTime(new Date());
        patient.setState(state);
        return patient;
    }
}