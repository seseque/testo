package com.github.testo.reports;

public interface Reporter {


    String reportFail(String methodName, Throwable cause);

    String reportSuccess(String methodName);

    void addResult(String result);

    void makeReport(String className, Report report);

}
