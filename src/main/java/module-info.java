module com.snake.trpo.lab_01 {
    requires javafx.controls;
    requires javafx.fxml;
    requires exp4j;


    opens com.snake.trpo.lab_01 to javafx.fxml;
    exports com.snake.trpo.lab_01;
}