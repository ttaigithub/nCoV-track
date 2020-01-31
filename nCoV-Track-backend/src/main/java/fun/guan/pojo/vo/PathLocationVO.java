package fun.guan.pojo.vo;

import fun.guan.entity.PathLocation;
import lombok.Data;

import java.util.Date;

@Data
public class PathLocationVO {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 患者id
     */
    private Long patientId;

    /**
     * 出没时间
     */
    private Long appearTime;

    /**
     * 出发地
     */
    private String departure;

    /**
     * 目的地
     */
    private String destination;

    /**
     * 出发地经纬度
     */
    private String departureLocation;

    /**
     * 目的地经纬度
     */
    private String destinationLocation;

    public PathLocationVO() {
    }

    public PathLocationVO(PathLocation pathLocation) {
        this.id = pathLocation.getRecordId();
        this.patientId = pathLocation.getPatientId();
        this.appearTime = pathLocation.getAppearTime().getTime();
        this.departure = pathLocation.getDeparture();
        this.departureLocation = pathLocation.getDepartureLocation();
        this.destination = pathLocation.getDestination();
        this.destinationLocation = pathLocation.getDestinationLocation();
    }

    public PathLocation toEntity(){
        PathLocation pathLocation = new PathLocation();
        pathLocation.setPatientId(patientId);
        pathLocation.setAppearTime(new Date(appearTime));
        pathLocation.setDeparture(departure);
        pathLocation.setDepartureLocation(departureLocation);
        pathLocation.setDestination(destination);
        pathLocation.setDestinationLocation(destinationLocation);
        return pathLocation;
    }

}