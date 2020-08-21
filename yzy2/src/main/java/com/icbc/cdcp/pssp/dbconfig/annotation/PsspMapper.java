package com.icbc.cdcp.pssp.dbconfig.annotation;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Component
@Mapper
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PsspMapper {
    String value() default "";
}
