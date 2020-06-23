package com.github.testo.reports;

public interface Reporter {


    void addFailedResult(String methodName, Throwable cause);

    void addFailedResult(String methodName, String cause);

    void addSuccessedResult(String methodName);

    void makeReport(String className, Report report);

}
