package com.snake.trpo.lab_01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IntegralCalculatorTest {

    long n;
    int thr;

    @BeforeEach
    void setUp() {
        this.thr = 1;
        this.n = 10;
        System.out.println("-----------------");
    }

    @Test
    void test_compute_one_n4() {
        this.n = (long) Math.pow(this.n, 4);
        this.execute_basic_test();
        this.execute_threads_test();
        this.execute_futures_test();
    }

    @Test
    void test_compute_threaded_n6() {
        this.n = (long) Math.pow(this.n, 6);
        this.execute_basic_test();
        this.thr = 5;
        this.execute_threads_test();
        this.execute_futures_test();
        this.thr = 10;
        this.execute_threads_test();
        this.execute_futures_test();
        this.thr = 50;
        this.execute_threads_test();
        this.execute_futures_test();
        this.thr = 100;
        this.execute_threads_test();
        this.execute_futures_test();
    }

    @Test
    void test_compute_threaded_n8() {
        this.n = (long) Math.pow(this.n, 8);
        this.execute_basic_test();
        this.thr = 5;
        this.execute_threads_test();
        this.execute_futures_test();
        this.thr = 10;
        this.execute_threads_test();
        this.execute_futures_test();
        this.thr = 20;
        this.execute_threads_test();
        this.execute_futures_test();
        this.thr = 50;
        this.execute_threads_test();
        this.execute_futures_test();
        this.thr = 100;
        this.execute_threads_test();
        this.execute_futures_test();
    }

    @Test
    void test_compute_threaded_n10() {
        this.n = (long) Math.pow(this.n, 10);
        this.execute_basic_test();
        this.thr = 5;
        this.execute_threads_test();
        this.execute_futures_test();
        this.thr = 10;
        this.execute_threads_test();
        this.execute_futures_test();
        this.thr = 20;
        this.execute_threads_test();
        this.execute_futures_test();
        this.thr = 50;
        this.execute_threads_test();
        this.execute_futures_test();
        this.thr = 100;
        this.execute_threads_test();
        this.execute_futures_test();

    }

    @Test
    void test_compute_threaded_n11() {
        this.n = (long) Math.pow(this.n, 11);
        this.thr = 5;
        this.execute_threads_test();
        this.execute_futures_test();
        this.thr = 10;
        this.execute_threads_test();
        this.execute_futures_test();
        this.thr = 20;
        this.execute_threads_test();
        this.execute_futures_test();
    }


    void execute_futures_test() {
        long exec_start = System.currentTimeMillis();
        Integral integral = new Integral(1, 4, this.n, this.thr);
        double res = integral.compute_futures();
        long exec_end = System.currentTimeMillis();
        System.out.println("Result FUTURES: " + res +"; Time: " + (exec_end-exec_start) + " ms; Threads: " + this.thr + "; n: " + this.n);
    }

    void execute_threads_test() {
        long exec_start = System.currentTimeMillis();
        Integral integral = new Integral(1, 4, this.n, this.thr);
        double res = integral.compute_threads();
        long exec_end = System.currentTimeMillis();
        System.out.println("Result THREADS: " + res +"; Time: " + (exec_end-exec_start) + " ms; Threads: " + this.thr + "; n: " + this.n);
    }

    void execute_basic_test() {
        long exec_start = System.currentTimeMillis();
        Integral calc = new Integral(1, 4, this.n, this.thr);
        double res = calc.compute_straight();
        long exec_end = System.currentTimeMillis();
        System.out.println("Result STRAIGHT: " + res +"; Time: " + (exec_end-exec_start) + " ms; n: " + this.n);
    }

}