package fun.guan.controller;

import com.github.pagehelper.PageInfo;
import fun.guan.pojo.ResponseResult;
import fun.guan.pojo.ResultEnum;
import fun.guan.pojo.vo.PatientVO;
import fun.guan.service.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "patient", tags = "患者信息")
@RestController
@Slf4j
@RequestMapping(value = "patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @ApiOperation(value = "add", notes = "患者信息添加，示例json：" +
            "\n{\"patientName\":\"王小明\",\"sex\":1,\"age\":18,\"confirmedTime\":1580300155738,\"state\":1}" +
            "\nsex字段 1 为男性，0为女性" +
            "\nstate字段 0 为疑似，1 为确诊, 2 为死亡, 3 为治愈, 4 为可能是传播者")
    @PostMapping(value = "add")
    public ResponseResult add(@RequestParam("data") String data){
        this.patientService.add(data);
        return new ResponseResult<>(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg());
    }

    @ApiOperation(value = "delete", notes = "患者信息查询，示例json：" +
            "\n{\"id\":3}")
    @PostMapping(value = "delete")
    public ResponseResult delete(@RequestParam(value = "data") String data){
        this.patientService.delete(data);
        return new ResponseResult<>(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg());
    }


    @ApiOperation(value = "query", notes = "患者信息查询，示例json：" +
            "\n{\"pageNum\":\"1\",\"pageSize\":1}")
    @PostMapping(value = "query")
    public ResponseResult query(@RequestParam(value = "data",required = false) String data){
        PageInfo<PatientVO> pageInfo = this.patientService.query(data);
        return new ResponseResult<>(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg(),pageInfo);
    }


}
