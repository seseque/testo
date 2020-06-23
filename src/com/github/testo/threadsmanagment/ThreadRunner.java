package com.github.testo.threadsmanagment;

import java.util.Queue;

public class ThreadRunner extends Thread {

    final Queue<Runnable> taskQueue;

    public ThreadRunner(Queue<Runnable> taskQueue) {
        this.taskQueue = taskQueue;
    }


    @Override
    public void run() {
        while (!taskQueue.isEmpty())
        synchronized (taskQueue) {
            taskQueue.poll().run();
        }
    }
}
