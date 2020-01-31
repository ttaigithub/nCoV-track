package fun.guan.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "patient")
public class Patient extends BaseEntity implements Serializable {
    /**
     * 姓名
     */
    @Column(name = "patient_name")
    private String patientName;

    /**
     * 性别
     */
    @Column(name = "sex")
    private Boolean sex;

    /**
     * 年龄
     */
    @Column(name = "age")
    private Integer age;

    /**
     * 确诊日期
     */
    @Column(name = "confirmed_time")
    private Date confirmedTime;

    /**
     * 状态
     */
    @Column(name = "`state`")
    private Integer state;

    private static final long serialVersionUID = 1L;
}