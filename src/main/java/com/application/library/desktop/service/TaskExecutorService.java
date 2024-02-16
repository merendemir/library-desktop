package com.application.library.desktop.service;

import java.util.concurrent.*;

public class TaskExecutorService {
    private static TaskExecutorService instance;
    private final ExecutorService executorService;
    private final ScheduledExecutorService scheduledExecutorService;

    private TaskExecutorService() {
        executorService = Executors.newFixedThreadPool(7);
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    }

    private static TaskExecutorService getInstance() {
        if (instance == null) {
            synchronized (TaskExecutorService.class) {
                instance = new TaskExecutorService();
            }
        }
        return instance;
    }

    public static void submit(Runnable task) {
        getInstance().executorService.submit(task);
    }

    public static ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long initialDelay, long period, TimeUnit init) {
        return getInstance().scheduledExecutorService.scheduleAtFixedRate(task, initialDelay, period, init);
    }

    public static ScheduledFuture<?> schedule(Runnable task, long initialDelay, TimeUnit init) {
        return getInstance().scheduledExecutorService.schedule(task, initialDelay, init);
    }
}
