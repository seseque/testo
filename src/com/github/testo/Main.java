package com.github.testo;


import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ExecutorService executor = Executors.newFixedThreadPool(n);

        while (sc.hasNext()) {
            String className = sc.next();
            executor.submit(() -> new TestRunner(className).run());
        }

        executor.shutdown();

    }


}
// test input
// 3 com.github.testo.testsuit.TestUnit com.github.testo.testsuit.TestUnit2 com.github.testo.testsuit.TestUnit3 com.github.testo.testsuit.TestUnit4 com.github.testo.testsuit.TestUnit5 com.github.testo.testsuit.TestUnit6 com.github.testo.testsuit.TestUnit7 com.github.testo.testsuit.TestUnit8 com.github.testo.testsuit.TestUnit9