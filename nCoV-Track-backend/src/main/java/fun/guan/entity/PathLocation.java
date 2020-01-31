package fun.guan.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "path_location")
public class PathLocation extends BaseEntity implements Serializable {
    /**
     * 患者id
     */
    @Column(name = "patient_id")
    private Long patientId;

    /**
     * 出没时间
     */
    @Column(name = "appear_time")
    private Date appearTime;

    /**
     * 出发地
     */
    @Column(name = "departure")
    private String departure;

    /**
     * 目的地
     */
    @Column(name = "destination")
    private String destination;

    /**
     * 出发地经纬度
     */
    @Column(name = "departure_location")
    private String departureLocation;

    /**
     * 目的地经纬度
     */
    @Column(name = "destination_location")
    private String destinationLocation;

    private static final long serialVersionUID = 1L;
}