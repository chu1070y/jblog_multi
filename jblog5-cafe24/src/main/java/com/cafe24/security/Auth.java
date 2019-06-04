package com.cafe24.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// type은 클래스 위에 붙일 수 있도록 해주는 것이고
// method는 메소드 위에 붙일 수 있도록 해주는 것이다.
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {

}
