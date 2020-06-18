package com.github.testo;

import com.github.testo.annotations.After;
import com.github.testo.annotations.Before;
import com.github.testo.annotations.Ignore;
import com.github.testo.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

class TestRunner implements Runnable {

    final private String testClassName;
    private Method beforeMethod;
    private Method afterMethod;
    final private Set<Method> testMethods = new HashSet<>();
    final private Set<Method> ignoreTestMethods= new HashSet<>();

    TestRunner(String testClassName) {
        this.testClassName = testClassName;
    }

    @Override
    public void run() {
        try {
            Class testClass = Class.forName(testClassName);
            Method[] methods = testClass.getDeclaredMethods();
            for (Method method : methods) {
                Annotation[] annotations = method.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation.getClass().isAnnotationPresent(Before.class)) beforeMethod = method;
                    if (annotation.getClass().isAnnotationPresent(After.class)) afterMethod = method;
                    if (annotation.getClass().isAnnotationPresent(Test.class)) testMethods.add(method);
                    if (annotation.getClass().isAnnotationPresent(Ignore.class)) ignoreTestMethods.add(method);
                }
            }
            testMethods.removeAll(ignoreTestMethods);

            beforeMethod.invoke(null);
            for (Method tm : testMethods) {
                tm.invoke(null);
            }
            afterMethod.invoke(null);
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    ;
}
