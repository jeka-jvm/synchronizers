package org.example;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComplexTaskExecutor {

    private final int numberOfTasks;
    private final CyclicBarrier barrier;

    public ComplexTaskExecutor(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
        this.barrier = new CyclicBarrier(numberOfTasks, () -> System.out.println("Все задачи завершены, объединение результатов..."));
    }

    public void executeTasks(int numberOfTasks) {
        ExecutorService executor = Executors.newFixedThreadPool(numberOfTasks);
        for (int i = 0; i < numberOfTasks; i++) {
            executor.submit(() -> {
                try {
                    ComplexTask task = new ComplexTask((int) (Math.random() * 1000));
                    task.run();
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();
    }
}
