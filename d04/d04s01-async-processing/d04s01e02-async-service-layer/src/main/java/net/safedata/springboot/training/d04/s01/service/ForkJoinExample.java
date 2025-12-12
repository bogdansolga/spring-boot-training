package net.safedata.springboot.training.d04.s01.service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ForkJoinExample {

    private static final Random RANDOM = new Random(1000);

    // a wrapper for a thread pool
    private static final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    // an ExecutorCompletionService is a wrapper for an ExecutorService that returns a Future for each submitted task
    private static final ExecutorCompletionService<String> executorCompletionService = new ExecutorCompletionService<>(executorService);

    public static void main(String[] args) {
        long now = System.currentTimeMillis();
        System.out.println("Max threads: " + Runtime.getRuntime().availableProcessors());

        // first stage - forking
        Set<String> tasks = IntStream.rangeClosed(0, 50)
                                     .mapToObj(number -> "Task " + number)
                                     .collect(Collectors.toSet());
        for (String task : tasks) {
            executorCompletionService.submit(new ProcessingTask(task));
        }

        Set<String> results = new HashSet<>();

        // second stage - joining
        for (int i = 0; i < tasks.size(); i++) {
            try {
                Future<String> futureResult = executorCompletionService.poll(1, TimeUnit.SECONDS);
                if (futureResult != null) {
                    results.add(futureResult.get());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Finished tasks: " + results.size());
        System.out.println("Time taken: " + (System.currentTimeMillis() - now) + " ms");

        List<Runnable> unfinishedTasks = executorService.shutdownNow();
        System.out.println("There are " + unfinishedTasks.size() + " unfinished tasks");
    }

    private record ProcessingTask(String taskName) implements Callable<String> {

        @Override
            public String call() throws Exception {
                long sleep = RANDOM.nextInt(1000);
                System.out.println("\t[" + Thread.currentThread().getName() + "]: " + taskName + ", sleeping for " + sleep + " ms");
                Thread.sleep(sleep);
                return taskName.toUpperCase();
            }
        }
}
