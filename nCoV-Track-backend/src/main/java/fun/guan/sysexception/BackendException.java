package fun.guan.sysexception;

import fun.guan.pojo.ResultEnum;

/**
 * @program: light-privacy-backend
 * @description: 文件格式错误异常
 * @author: JingXi
 * @create: 2019-11-06 10:35
 * @Copyright © 2019 JingXi. All rights reserved
 **/
public class BackendException extends RuntimeException{

    private ResultEnum resultEnum;

    public BackendException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.resultEnum = resultEnum;

    }

    public int getCode() {
        return resultEnum.getCode();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}

