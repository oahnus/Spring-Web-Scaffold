package com.github.oahnus.scaffold.auth.client;

import com.github.oahnus.scaffold.auth.client.config.AuthConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by ace on 2017/9/15.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AuthConfiguration.class)
@Documented
@Inherited
public @interface EnableAuthClient {
}
