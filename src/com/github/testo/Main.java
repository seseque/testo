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
// 3 TestUnit TestUnit2 TestUnit3 TestUnit4 TestUnit5 TestUnit6 TestUnit7 TestUnit8 TestUnit9