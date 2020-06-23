package com.github.testo.reports;

import com.github.testo.exceptions.AssertException;

import java.util.ArrayList;

public class AssertReporter implements Reporter {

    final private ArrayList<String> results = new ArrayList<>();

    @Override
    public void addFailedResult(String methodName, Throwable cause) {
        AssertException assertCause = (AssertException) cause;
        String sCause = "Expected: " + assertCause.expected + ", Actual: " + assertCause.actual + ".";
        results.add("Test " + methodName + " failed: " + sCause);
    }

    @Override
    public void addFailedResult(String methodName, String  cause) {
        results.add("Test " + methodName + " failed: " + cause);
    }

    @Override
    public void addSuccessedResult(String methodName) {
        results.add("Test " + methodName + " successfully passed.");
    }

    @Override
    public void makeReport(String className, Report report) {
        StringBuilder piece = new StringBuilder();
        piece.append("Well... Test Report for class " + className + " is be like ...");
        for (String r : results) {
            piece.append(r);
            piece.append(' ');
        }
        report.addReport(piece.toString());
    }
}
