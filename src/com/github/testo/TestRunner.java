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
import java.util.Map;

class TestRunner implements Runnable {

    final private String testClassName;
    Report report;

    private Method beforeMethod;
    private Method afterMethod;
    final private Map<Method, Class<? extends Exception>> testMethods = new HashMap<>();
    final private Map<Method, Class<? extends Exception>> ignoreTestMethods = new HashMap<>();
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
                if (method.isAnnotationPresent(Before.class)) beforeMethod = method;
                if (method.isAnnotationPresent(After.class)) afterMethod = method;
                if (method.isAnnotationPresent(Test.class)) {

                    Test t = method.getAnnotation(Test.class);
                    testMethods.put(method, t.exc());
                }
                if (method.isAnnotationPresent(Ignore.class))
                    ignoreTestMethods.put(method, method.getAnnotation(Test.class).exc());
            }
            for (Method ignoreMethod : ignoreTestMethods.keySet()) {
                testMethods.remove(ignoreMethod);
            }

            try {
                beforeMethod.invoke(null);
                reporter.addResult(reporter.reportSuccess(beforeMethod.getName()));
            } catch (InvocationTargetException e) {
                reporter.addResult(reporter.reportFail(beforeMethod.getName(), e.getCause()));
            }

            for (Method tm : testMethods.keySet()) {
                try {
                    tm.invoke(null);
                    reporter.addResult(reporter.reportSuccess(tm.getName()));
                } catch (InvocationTargetException e) {
                    Throwable cause = e.getCause();
                    Class<? extends Exception> expected = testMethods.get(tm);
                    if (cause.getClass() != expected)
                        reporter.addResult(reporter.reportFail(tm.getName(), e.getCause()));
                    else reporter.addResult(reporter.reportSuccess(tm.getName()));
                }
            }
            try {
                afterMethod.invoke(null);
                reporter.addResult(reporter.reportSuccess(afterMethod.getName()));
            } catch (InvocationTargetException e) {
                reporter.addResult(reporter.reportFail(afterMethod.getName(), e.getCause()));
            }

            reporter.makeReport(testClassName, report);
        } catch (ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }


    }

    ;
}
