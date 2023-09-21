package com.snake.trpo.lab_01;


public class IStraight {

    Integral integral;
    SharedResult res;
    long i_first;
    long i_last;

    public IStraight(Integral integral, SharedResult res, long i_first, long i_last) {
        this.integral = integral;
        this.res = res;
        this.i_first = i_first;
        this.i_last = i_last;
    }

    public void compute() {
        double summary = 0;
        for (long i = i_first; i <= i_last; i++) {
            SubFunction ci = new SubFunction(integral.a, i, integral.h);
            summary += SubFunction.fn(ci.get_x_i());
        }
        res.addToResult(summary);
    }

}

