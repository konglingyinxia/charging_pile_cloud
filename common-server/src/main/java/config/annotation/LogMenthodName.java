package config.annotation;


import java.lang.annotation.*;

/**
 * @author 张子艺
 * @packge com.controller.annotation
 * @data 2019-01-09 14:47
 * @project Currency
 */
@Documented
@Inherited
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE_USE, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogMenthodName {
    String name() default "未知";
}