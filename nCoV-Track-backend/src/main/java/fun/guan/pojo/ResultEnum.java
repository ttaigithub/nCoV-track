package fun.guan.pojo;

/**
 * @program: maskpaas
 * @description: 返回码和提示信息枚举类
 * @author: Jingxi Zhu
 * @create: 2018-10-25 19:21
 * @Copyright © 2018 Jingxi Zhu. All rights reserved
 **/
public enum ResultEnum implements CodeEnum {
    /**
     * 各种状态
     */
    SUCCESS(0, "success"),
    FAILED(1, "failed"),
    //...

    COMMON_ACCOUNT_ERROR(100001, "账号错误"),
    COMMON_TOKEN_ERROR(100002, "token已过期"),
    COMMON_PARAM_EMPTY(100003, "必选参数为空"),
    COMMON_PARAM_ERROR(100004, "参数格式错误"),
    //...

    FILE_NOT_EXIST(110404, "文件不存在"),
    FILE_FORMAT_ERROR(110303,"文件格式错误"),
    //...

    DATABASE_INSERT_ERROR(200001,"数据录入错误"),
    DATABASE_DELETE_ERROR(200002,"数据删除错误"),
    DATABASE_QUERY_ERROR(200003,"查询条件错误"),
    DATABASE_DUPLICATION_ERROR(200004,"数据已存在"),
    DATABASE_NULL_ERROR(200404,"数据不存在"),

    //...

    REDIS_NULL_ERROR(300404,"临时数据失效"),
    //...

    TOKEN_INVALID(400500,"Token invalid."),
    SYSTEM_UNKNOWN_ERROR(-1, "系统繁忙，请稍后再试....");
    ;

    private int code;

    private String msg;

    ResultEnum(int code) {
        this.code = code;
    }

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
