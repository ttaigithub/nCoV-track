package fun.guan.controller;

import com.github.pagehelper.PageInfo;
import fun.guan.pojo.ResponseResult;
import fun.guan.pojo.ResultEnum;
import fun.guan.pojo.vo.PathDetailVO;
import fun.guan.pojo.vo.PathLocationVO;
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

@Api(value = "track", tags = "路径追踪")
@RestController
@Slf4j
@RequestMapping(value = "track")
public class PathController {

    private final PathLocationService pathLocationService;

    public PathController(PathLocationService pathLocationService) {
        this.pathLocationService = pathLocationService;
    }

    @ApiOperation(value = "add", notes = "患者信息添加，data示例json：" +
            "\n{ \"patientData\":{\"patientName\":\"王小明\",\"sex\":1,\"age\":18,\"confirmedTime\":1580300155738,\"state\":1}, \"addressData\":[{\"time\":1134,\"address\":[{\"id\":1,\"name\":\"云赛空间\"},{\"id\":2,\"name\":\"上海金融中心\"}]}]}")
    @PostMapping(value = "add")
    public ResponseResult add(@RequestParam("data") String data){
        this.pathLocationService.add(data);
        return new ResponseResult<>(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg());
    }

    @ApiOperation(value = "update", notes = "患者信息修改，data示例json：" +
            "\n{ \"patientData\":{\"id\":11,\"patientName\":\"王小明\",\"sex\":1,\"age\":18,\"confirmedTime\":1580300155738,\"state\":1}, \"addressData\":[{\"time\":1134,\"address\":[{\"id\":1,\"name\":\"云赛空间\"},{\"id\":2,\"name\":\"上海金融中心\"}]}]}")
    @PostMapping(value = "update")
    public ResponseResult update(@RequestParam("data") String data){
        this.pathLocationService.update(data);
        return new ResponseResult<>(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg());
    }

    @ApiOperation(value = "query", notes = "患者信息查询，示例json：" +
            "\n查所有\n{\"pageNum\":\"1\",\"pageSize\":1}" +
            "\n根据患者id查询\n{\"pageNum\":\"1\",\"pageSize\":1,\"patientId\":11}")
    @PostMapping(value = "query")
    public ResponseResult query(@RequestParam(value = "data") String data){
        PageInfo<PathLocationVO> pageInfo = this.pathLocationService.query(data);
        return new ResponseResult<>(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg(),pageInfo);
    }

    @ApiOperation(value = "queryDetail", notes = "患者信息查询，示例json：" +
            "\n{\"id\":11}")
    @PostMapping(value = "queryDetail")
    public ResponseResult queryDetail(@RequestParam(value = "data") String data){
        PathDetailVO vo = this.pathLocationService.queryDetail(data);
        return new ResponseResult<>(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg(),vo);
    }

    @ApiOperation(value = "queryByTime", notes = "患者信息查询，示例json：" +
            "\n{\"startTime\":123456,\"endTime\":1234256}")
    @PostMapping(value = "queryByTime")
    public ResponseResult queryByTime(@RequestParam(value = "data") String data){
        List<TwoPlaceVO> vo = this.pathLocationService.queryByTime(data);
        return new ResponseResult<>(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg(),vo);
    }


}
