package fun.guan.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Table(name = "patient_location")
public class PatientLocation extends BaseEntity implements Serializable {
    /**
     * 患者id
     */
    @Column(name = "patient_id")
    private Long patientId;

    /**
     * 上一个地方id
     */
    @Column(name = "last_place_id")
    private Long lastPlaceId;

    /**
     * 下一个地方id
     */
    @Column(name = "next_place_id")
    private Long nextPlaceId;

    /**
     * 出没时间
     */
    @Column(name = "appear_time")
    private Date appearTime;

    /**
     * 出没时间
     */
    @Column(name = "departurer_time")
    private Date departurerTime;

    /**
     * 出发地
     */
    @Column(name = "place")
    private String place;

    /**
     * 经度
     */
    @Column(name = "lng")
    private BigDecimal lng;

    /**
     * 纬度
     */
    @Column(name = "lat")
    private BigDecimal lat;

    private static final long serialVersionUID = 1L;
}