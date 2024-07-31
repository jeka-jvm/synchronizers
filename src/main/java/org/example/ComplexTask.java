package org.example;

public class ComplexTask implements Runnable {
    private final int taskId;

    public ComplexTask(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        execute();
    }

    public void execute() {
        System.out.println("Задача " + taskId + " выполняется в " + Thread.currentThread().getName());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Задача " + taskId + " выполнена.");
    }
}
