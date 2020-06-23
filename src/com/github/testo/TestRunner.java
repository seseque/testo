package com.github.testo;

import com.github.testo.annotations.After;
import com.github.testo.annotations.Before;
import com.github.testo.annotations.Ignore;
import com.github.testo.annotations.Test;
import com.github.testo.reports.AssertReporter;
import com.github.testo.reports.Report;
import com.github.testo.reports.Reporter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class TestRunner implements Runnable {

    final private String testClassName;
    Report report;

    private Set<Method> beforeMethod = new HashSet<>();
    private Set<Method> afterMethod = new HashSet<>();
    final private Map<Method, Class<? extends Exception>> testMethods = new HashMap<>();
    Reporter reporter = new AssertReporter();

    TestRunner(String testClassName, Report report) {
        this.testClassName = testClassName;
        this.report = report;
    }

    @Override
    public void run() {
        try {
            Class testClass = Class.forName(testClassName);
            Method[] methods = testClass.getDeclaredMethods();
            for (Method method : methods) {
                method.setAccessible(true);
                if (method.isAnnotationPresent(Ignore.class)) {
                    break;
                }
                if (method.isAnnotationPresent(Before.class)) {
                    beforeMethod.add(method);
                }
                if (method.isAnnotationPresent(After.class)) {
                    afterMethod.add(method);
                }
                if (method.isAnnotationPresent(Test.class)) {
                    Test t = method.getAnnotation(Test.class);
                    testMethods.put(method, t.expectedException());
                }
            }
            for (Method method : beforeMethod) {
                try {
                    method.invoke(null);
                    reporter.addSuccessedResult(method.getName());
                } catch (InvocationTargetException e) {
                    reporter.addFailedResult(method.getName(), e.getCause());
                }
            }

            for (Method tm : testMethods.keySet()) {
                try {
                    tm.invoke(null);
                    reporter.addSuccessedResult(tm.getName());
                } catch (InvocationTargetException e) {
                    Throwable cause = e.getCause();
                    Class<? extends Exception> expected = testMethods.get(tm);
                    if (cause.getClass() != expected) {
                        reporter.addFailedResult(tm.getName(), e.getCause());
                    } else {
                        reporter.addSuccessedResult(tm.getName());
                    }
                }
            }

            for (Method method : afterMethod) {
                try {
                    method.invoke(null);
                    reporter.addSuccessedResult(method.getName());
                } catch (InvocationTargetException e) {
                    reporter.addFailedResult(method.getName(), e.getCause());
                }
            }
            reporter.makeReport(testClassName, report);
        } catch (ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
