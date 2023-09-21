package com.snake.trpo.lab_01;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


public class IntegralController {
    @FXML
    private RadioButton version_straight;
    @FXML
    private RadioButton version_threads;
    @FXML
    private RadioButton version_futures;
    @FXML
    private Label result;
    @FXML
    private TextField var_a;
    @FXML
    private TextField var_b;
    @FXML
    private TextField steps;
    @FXML
    private TextField threads;
    @FXML
    private Label exec_time;

//    attrs
    int a;
    int b;
    int n;
    int thr;
    String method;

    private void parse_inputs() {
        this.a = Integer.parseInt(this.var_a.getText());
        this.b = Integer.parseInt(this.var_b.getText());
        this.n = Integer.parseInt(this.steps.getText());
        this.thr = Integer.parseInt(this.threads.getText());
        this.method = "straight";
        if (this.version_threads.isSelected()) {
            this.method = "threads";
        } else if (this.version_futures.isSelected()) {
            this.method = "futures";
        }
    }

    @FXML
    protected void onCalcButtonClick() {
        long exec_start = System.currentTimeMillis();
        this.parse_inputs();

        Integral integral = new Integral(this.a, this.b, this.n, this.thr);
        double res = 0;

        switch (this.method) {
            case "straight":
                res = integral.compute_straight();
                break;
            case "threads":
                res = integral.compute_threads();
                break;
            case "futures":
                res = integral.compute_futures();
                break;
        }

        long exec_end = System.currentTimeMillis();
        result.setText(String.valueOf(res));
        exec_time.setText(exec_end-exec_start + " ms");
    }

}