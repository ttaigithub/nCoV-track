package fun.guan.controller;

import fun.guan.pojo.ResponseResult;
import fun.guan.pojo.ResultEnum;
import fun.guan.utils.EnumUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: dc-platform
 * @description: 用户逻辑
 * @author: JingXi
 * @create: 2019-08-27 20:58
 * @Copyright © 2019 JingXi. All rights reserved
 **/
@Api(value = "errorCode", tags = "错误码查询")
@RestController
@Slf4j
@RequestMapping(value = "errorCode")
public class CodeErrorController {

    @ApiOperation(value = "错误码查询", notes = "输入错误码即可得到错误码的解释")
    @PostMapping(value = "getCodeImplication")
    public ResponseResult createNewUser(@RequestParam("code") Integer code){
        String msg = EnumUtil.getByCode(code, ResultEnum.class);
        return new ResponseResult<>(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg(),msg);
    }



}

