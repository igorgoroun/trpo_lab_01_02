package com.snake.trpo.lab_01;

public class SharedResult {
    private double result = 0;

    public synchronized void addToResult(double value) {
        result += value;
    }

    public synchronized double getResult() {
        return result;
    }

}
