module hu.petrik.calculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens hu.petrik.calculator to javafx.fxml;
    exports hu.petrik.calculator;
}