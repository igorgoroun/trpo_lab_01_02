package com.snake.trpo.lab_01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class Integral {

    int a;
    int b;
    long n;
    int thr;
    double h;
    double border;
    List<List<Long>> chunks = new ArrayList<>();

    public Integral(int a, int b, long n, int thr) {
        this.a = a;
        this.b = b;
        this.n = n;
        if (thr <= 0) {
            this.thr = 1;
        } else {
            this.thr = thr;
        }
        this.h = (double) (this.b - this.a) / this.n;
        this.border = (SubFunction.fn(this.a) + SubFunction.fn(this.n)) / 2;
        this.build_chunks();
    }

    public void build_chunks() {
//        System.out.println("n: " + this.n + ", thr: " + this.thr);
        long full = (this.n - 1) / this.thr;
        long rest = (this.n - 1) % this.thr;
//        System.out.println("Full: " + full + ", rest: " + rest);
        for (long x = 1; x <= full * this.thr; x += full) {
            List<Long> chunk = new ArrayList<>();
            chunk.add(x);
            chunk.add(x + full - 1);
            this.chunks.add(chunk);
        }
        if (rest > 0) {
            List<Long> chunk = new ArrayList<>();
            chunk.add(full * this.thr + 1);
            chunk.add(full * this.thr + rest);
            this.chunks.add(chunk);
        }
//        System.out.println(this.chunks);
    }

    public double compute_straight() {
        SharedResult shared_res = new SharedResult();
        IStraight s = new IStraight(this, shared_res, 1, this.n-1);
        s.compute();
        return this.h * (this.border + shared_res.getResult());
    }

    public double compute_threads() {
        List<Thread> threads = new ArrayList<>();
        SharedResult shared_res = new SharedResult();

        for (List<Long> chunk : this.chunks) {
            IThreads ci = new IThreads(this, shared_res, chunk.get(0), chunk.get(1));
            Thread t = new Thread(ci);
            threads.add(t);
            t.start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return this.h * (this.border + shared_res.getResult());
    }

    public double compute_futures() {
        SharedResult shared_res = new SharedResult();
        ExecutorService executor = Executors.newFixedThreadPool(this.thr);
        List<IFutures> tasks = new ArrayList<>();
        for (List<Long> chunk : this.chunks) {
            IFutures ci = new IFutures(this, shared_res, chunk.get(0), chunk.get(1));
            tasks.add(ci);
        }
        List<Future<?>> futures = new ArrayList<>();
        for (Callable<?> callable : tasks) {
            futures.add(executor.submit(callable));
        }
        double summary = futures.stream().mapToDouble(f -> {
            try {
                return (double) f.get();
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).sum();

        return this.h * (this.border + shared_res.getResult());
    }

}

