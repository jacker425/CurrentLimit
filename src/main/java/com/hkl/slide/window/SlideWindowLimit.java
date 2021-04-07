package com.hkl.slide.window;

import java.lang.annotation.*;

/**
 * @Author jacker425
 * @Description //滑动窗口注解
 * @Date 上午10:55 2021/4/7
 * @Param
 * @return
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SlideWindowLimit {
}
