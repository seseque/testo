package com.github.testo.threadsmanagment;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadManager {
    int numberOfThread;

    final private Queue<Runnable> taskQueue = new ConcurrentLinkedQueue<>();

    List<Thread> threadList = new ArrayList<>();

    public ThreadManager(int numberOfThread) {
        this.numberOfThread = numberOfThread;
        for (int i = 0; i < numberOfThread; i++) {
            threadList.add(new ThreadRunner(taskQueue));
        }
    }

    public void addTask(Runnable task) {
        taskQueue.add(task);
    }

    public void start() {
        for (Thread t : threadList) {
            t.start();
        }
    }

    public void shutdown() {
        for (Thread t : threadList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
