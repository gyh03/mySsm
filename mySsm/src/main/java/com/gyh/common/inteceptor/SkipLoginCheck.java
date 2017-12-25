package com.gyh.common.inteceptor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 跳过登录注解，使用该注解的方法，可以跳过登录，如用户登录，或商品展示
 */
@Target(ElementType.METHOD) //注解用于什么地方
@Retention(RetentionPolicy.RUNTIME) //什么时候使用该注解
public @interface SkipLoginCheck {

}
