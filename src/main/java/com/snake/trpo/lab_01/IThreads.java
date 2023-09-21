package com.snake.trpo.lab_01;


public class IThreads extends IStraight implements Runnable {

    public IThreads(Integral integral, SharedResult shared_res, long i_first, long i_last) {
        super(integral, shared_res, i_first, i_last);
    }

    @Override
    public void run() {
        this.compute();
    }

}

