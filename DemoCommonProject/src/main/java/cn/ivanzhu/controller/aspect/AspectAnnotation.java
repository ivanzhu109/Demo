package cn.ivanzhu.controller.aspect;


import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ivanzhu
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AspectAnnotation {
    @AliasFor("editName")
    boolean editName() default false;
    @AliasFor("editAge")
    boolean editAge() default false;
}
