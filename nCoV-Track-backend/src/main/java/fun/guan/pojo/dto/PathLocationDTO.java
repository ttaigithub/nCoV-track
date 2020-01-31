package fun.guan.pojo.dto;

import fun.guan.entity.PathLocation;
import lombok.Data;

import java.util.Date;

@Data
public class PathLocationDTO extends PageInfoDTO{
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

    public PathLocation toEntity(){
        PathLocation pathLocation = new PathLocation();
        pathLocation.setRecordId(id);
        pathLocation.setPatientId(patientId);
        if (appearTime!=null){
            pathLocation.setAppearTime(new Date(appearTime));
        }
        pathLocation.setDeparture(departure);
        pathLocation.setDepartureLocation(departureLocation);
        pathLocation.setDestination(destination);
        pathLocation.setDestinationLocation(destinationLocation);
        return pathLocation;
    }

}