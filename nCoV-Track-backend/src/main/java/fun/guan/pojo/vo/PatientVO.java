package fun.guan.pojo.vo;

import fun.guan.entity.Patient;
import lombok.Data;

import java.util.Date;

@Data
public class PatientVO {
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
    private int sex;

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

    public PatientVO() {
    }

    public PatientVO(Patient patient) {
        this.setId(patient.getRecordId());
        this.setConfirmedTime(patient.getConfirmedTime().getTime());
        this.setState(patient.getState());
        this.setAge(patient.getAge());
        if (patient.getSex()){
            this.setSex(1);
        }else {
            this.setSex(0);
        }
        this.setPatientName(patient.getPatientName());
    }

    /**
     * dto转entity
     * @return entity
     */
    public Patient toPatient(){
        Patient patient = new Patient();
        patient.setPatientName(patientName);
        switch (sex){
            case 1:
                patient.setSex(true);
                break;
            default:
                patient.setSex(false);
        }
        patient.setAge(age);
        patient.setConfirmedTime(new Date(confirmedTime));
        patient.setState(state);
        return patient;
    }
}