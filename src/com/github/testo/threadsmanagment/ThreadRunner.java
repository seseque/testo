package com.github.testo.threadsmanagment;

import java.util.Queue;

public class ThreadRunner extends Thread {

    final Queue<Runnable> taskQueue;

    public ThreadRunner(Queue<Runnable> taskQueue) {
        this.taskQueue = taskQueue;
    }


    @Override
    public void run() {
        synchronized (taskQueue) {
            while (!taskQueue.isEmpty())
                taskQueue.poll().run();
        }
    }
}
