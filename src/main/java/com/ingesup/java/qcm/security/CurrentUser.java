package com.ingesup.java.qcm.security;

import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

/**
 * Created by lopes_f on 1/16/2015.
 * <florian.lopes@outlook.com>
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {
}
