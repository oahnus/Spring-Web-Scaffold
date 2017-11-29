package top.oahnus.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by oahnus on 2017/10/24
 * http 文件下载注解
 * 注解在Controller的下载文件方法上，
 * 需要下载到客户端的文件直接作为方法的返回值返回即可，
 * 目前仅支持单文件下载
 * 10:30.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Download {
}
