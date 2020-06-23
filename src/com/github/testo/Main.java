package com.github.testo;


import com.github.testo.reports.Report;
import com.github.testo.threadsmanagment.ThreadManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ThreadManager tm = new ThreadManager(n);

        Report r = new Report();

        while (sc.hasNext()) {
            String className = sc.next();
            tm.addTask(new TestRunner(className, r));
        }
        tm.start();
        tm.shutdown();
        r.printReport();

    }


}
// test input
// 3 com.github.testo.testsuit.TestUnit com.github.testo.testsuit.TestUnit2 com.github.testo.testsuit.TestUnit3 com.github.testo.testsuit.TestUnit4 com.github.testo.testsuit.TestUnit5 com.github.testo.testsuit.TestUnit6 com.github.testo.testsuit.TestUnit7 com.github.testo.testsuit.TestUnit8 com.github.testo.testsuit.TestUnit9