package com.snake.trpo.lab_01;


public class SubFunction {
    int a;
    long i;
    double h;
    double x_i;
    double res;

    public SubFunction(int a, long i, double h) {
        this.a = a;
        this.i = i;
        this.h = h;
        this.x_i = this.get_x_i();
        this.res = fn(this.x_i);
    }

    public static double fn(double x) {
//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        return (1 + x) / Math.sqrt(2 * x);
    }

    public double get_x_i() {
        // System.out.println("Thread: " + Thread.currentThread().getName() + "; Computed Xi("+this.i+"): " + x_i + "; Computed Iter: " + s_iter);
        return this.a + this.i * this.h;
    }
}
