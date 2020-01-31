package fun.guan.sysexception;

import fun.guan.pojo.ResponseResult;
import lombok.Data;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: taisecurity
 * @description:
 * @author: JingXi
 * @create: 2019-04-02 14:01
 * @Copyright © 2019 JingXi. All rights reserved
 **/
@ControllerAdvice
@Data
public class MyExceptionHandler {

    @ResponseBody
    @ExceptionHandler(BackendException.class)
    public ResponseResult handleException(BackendException e){
        e.printStackTrace();
        return new ResponseResult<>(e.getCode(),e.getMessage());
    }


}

