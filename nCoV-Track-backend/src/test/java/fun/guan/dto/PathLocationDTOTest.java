package fun.guan.pojo.dto;

import com.google.gson.*;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class PathLocationDTOTest {

    @Test
    public void dtoToJson(){
        List<PathLocationDTO> dtoList = new LinkedList<>();
        for (int i=0;i<5;i++){
            PathLocationDTO dto = new PathLocationDTO();
            dto.setId(1L);
            dto.setAppearTime(new Date().getTime());
            dto.setPatientId(1L);
            dto.setDeparture("出发地");
            dto.setDepartureLocation("114.21892734521,29.575429778924");
            dto.setDestination("目的地");
            dto.setDestinationLocation("114.21892734521,29.575429778924");
            dtoList.add(dto);
        }
        System.out.println(new Gson().toJson(dtoList));

        String data = "[{\"id\":1,\"patientId\":1,\"appearTime\":1580308578220,\"departure\":\"出发地\",\"destination\":\"目的地\",\"departureLocation\":\"114.21892734521,29.575429778924\",\"destinationLocation\":\"114.21892734521,29.575429778924\"},{\"id\":1,\"patientId\":1,\"appearTime\":1580308578220,\"departure\":\"出发地\",\"destination\":\"目的地\",\"departureLocation\":\"114.21892734521,29.575429778924\",\"destinationLocation\":\"114.21892734521,29.575429778924\"},{\"id\":1,\"patientId\":1,\"appearTime\":1580308578220,\"departure\":\"出发地\",\"destination\":\"目的地\",\"departureLocation\":\"114.21892734521,29.575429778924\",\"destinationLocation\":\"114.21892734521,29.575429778924\"},{\"id\":1,\"patientId\":1,\"appearTime\":1580308578220,\"departure\":\"出发地\",\"destination\":\"目的地\",\"departureLocation\":\"114.21892734521,29.575429778924\",\"destinationLocation\":\"114.21892734521,29.575429778924\"},{\"id\":1,\"patientId\":1,\"appearTime\":1580308578220,\"departure\":\"出发地\",\"destination\":\"目的地\",\"departureLocation\":\"114.21892734521,29.575429778924\",\"destinationLocation\":\"114.21892734521,29.575429778924\"}]\n";


    }

    @Test
    public void testJson(){
        String data = "{\"address\":[{\"id\":1,\"name\":\"云赛空间\"},{\"id\":2,\"name\":\"上海金融中心\"}]}";
        JsonArray j = new JsonParser().parse(data).getAsJsonObject().get("address").getAsJsonArray();
        for (int i=0;i<j.size();i++){
            System.out.println(j.get(i));
        }
    }

}