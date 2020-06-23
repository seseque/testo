package com.github.testo.annotations;

import com.github.testo.exceptions.DefaultException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Test {
    Class<? extends Exception> expectedException() default DefaultException.class;
}

