package fun.guan.controller;

import fun.guan.pojo.ResponseResult;
import fun.guan.pojo.ResultEnum;
import fun.guan.pojo.vo.TwoPlaceVO;
import fun.guan.service.PathLocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(value = "data", tags = "数据展示")
@RestController
@Slf4j
@RequestMapping(value = "data")
public class DataController {

    private final PathLocationService pathLocationService;

    public DataController(PathLocationService pathLocationService) {
        this.pathLocationService = pathLocationService;
    }

    @ApiOperation(value = "queryByTime", notes = "患者信息查询，示例json：" +
            "\n{\"startTime\":123456,\"endTime\":1580300165738}")
    @PostMapping(value = "queryByTime")
    public ResponseResult queryByTime(@RequestParam(value = "data") String data){
        List<TwoPlaceVO> vo = this.pathLocationService.queryByTime(data);
        return new ResponseResult<>(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg(),vo);
    }

    @ApiOperation(value = "queryLocationByTime", notes = "患者信息查询，示例json：" +
            "\n{\"startTime\":123456,\"endTime\":1580300165738,\"state\":1}" +
            "\n所有状态都返回不用传state字段：{\"startTime\":123456,\"endTime\":1580300165738}")
    @PostMapping(value = "queryLocationByTime")
    public ResponseResult queryLocationByTime(@RequestParam(value = "data") String data){
        Map<Integer,Object> vo = this.pathLocationService.queryLocationByTime(data);
        return new ResponseResult<>(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg(),vo);
    }
}

