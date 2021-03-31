package cn.ivanzhu.test.enumeration;

import java.lang.annotation.Repeatable;

/**
 * @author ivanzhu
 * @time 2021/3/20 17:51
 * @Version 1.0
 */
@Repeatable(DoubleAnnotations.class)
public @interface DoubleAnnotation {
    String value();
}
