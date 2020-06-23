package com.github.testo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотацией @After помечаются те методы в классе,
 * которые должны быть вызваны после методов с аннотацией @Test
 * В случае если таких методов более одного в классе, выполняются все помеченные методы
 * Порядок вызова не определен.
 */
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface After {
}
