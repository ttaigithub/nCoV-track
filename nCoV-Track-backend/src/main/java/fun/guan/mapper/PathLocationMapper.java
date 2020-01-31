package fun.guan.mapper;

import fun.guan.entity.PathLocation;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.Date;
import java.util.List;

public interface PathLocationMapper extends MyMapper<PathLocation> {
    List<PathLocation> selectByTimeAndState(@Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("state") int state);
}