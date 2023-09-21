package com.snake.trpo.lab_01;

import java.util.concurrent.*;


public class IFutures extends IStraight implements Callable {

    public IFutures(Integral integral, SharedResult res, long i_first, long i_last) {
        super(integral, res, i_first, i_last);
    }

    @Override
    public Object call() {
        this.compute();
        return res.getResult();
    }

}

