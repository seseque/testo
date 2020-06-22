package com.github.testo.reports;

import java.util.ArrayList;

public class Report {
    final private ArrayList<String> reports = new ArrayList<>();

    public void addReport(String report) {
        reports.add(report);
    }

    public void printReport() {
        for (String r : reports) {
            System.out.println(r);
        }
    }
}
