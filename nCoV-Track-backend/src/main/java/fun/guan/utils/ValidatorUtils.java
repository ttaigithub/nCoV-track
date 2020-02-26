package com.dt.dubai.core.comm;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ValidatorUtils {

    private static final Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    /**
     * 分组进行校验或完整校验
     * 区别在于是否传groups类
     * ps:分组的好处在于同一个DTO分别给不同的方法指定不同的校验参数时候非常有用
     * 也就不用再去重写一个新的相同字段的DTO指定不同校验字段的操作,更灵活
     *
     * @param object 被校验的DTO对象
     * @param groups 分组类，可以是接口也可以是类，仅作为标识
     * @return 想要返回的结果
     */
    public static <T> String validEntity(T object, Class... groups) {
        Set<ConstraintViolation<T>> violations = validator.validate(object, groups);
        if (!violations.isEmpty()) {
            //这里只取第一条错误，防止返回参数过多
            ConstraintViolation violation = violations.iterator().next();
            //下面的代码可以使用公司或个人习惯的返回工具类也可以
//            return violation.getMessage();
            throw new RuntimeException(violation.getMessage());
        }
        return null;
    }

    /**
     * 校验入参对象的某个属性是否满足要求,跳过其它校验, 支持分组
     *
     * @param object       被校验的DTO对象
     * @param propertyName DTO对象内的属性名
     * @param groups       分组名
     * @return 校验出错返回的结果
     */
    public static <T> String validProperty(final T object, String propertyName, Class... groups) {
        Set<ConstraintViolation<T>> violations = validator.validateProperty(object, propertyName, groups);
        if (!violations.isEmpty()) {
            //这里只取第一条错误，防止返回参数过多
            ConstraintViolation violation = violations.iterator().next();
            //下面的代码可以使用公司或个人习惯的返回工具类也可以
//            return violation.getMessage();
            throw new RuntimeException(violation.getMessage());
        }
        return "";
    }


    /**
     * 使用BindingResult与@Valid注解一起使用实现的工具类
     *
     * @param result BindingResult对象
     * @return 结果串
     */
    public static String validEntity(BindingResult result) {
        if (result.hasErrors()) {
            //取一条错误信息
            ObjectError next = result.getAllErrors().iterator().next();
            String defaultMessage = next.getDefaultMessage();
            //后边可以自己返回错误信息也可以自定义
            return "";
        }
        return null;
    }
}
