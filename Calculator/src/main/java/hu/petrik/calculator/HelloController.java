package hu.petrik.calculator;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {

    @FXML
    private Spinner masikSzamInput;
    @FXML
    private Button oszzeadasButton;
    @FXML
    private Spinner egyikSzamInput;
    @FXML
    private Button szorzasButton;
    @FXML
    private Label eredmeny;
    @FXML
    private Button osztasButton;
    @FXML
    private Button kivonasButton;
    @FXML
    private Button maradekosOsztasButton;


    public void initialize() {
        egyikSzamInput.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(Double.MIN_VALUE, Double.MAX_VALUE, 0.0));
        masikSzamInput.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(Double.MIN_VALUE, Double.MAX_VALUE, 0.0));
        egyikSzamInput.getEditor().textProperty().addListener(this::changed);
        masikSzamInput.getEditor().textProperty().addListener(this::changed);

    }

    private boolean validateInputs() {
        if (egyikSzamInput.getValue() == null || masikSzamInput.getValue() == null) {
            showAlert("Hiba", "Mindkét szám mezőt ki kell tölteni!");
            return false;
        }
        return true;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (!newValue.matches("\\d*\\.?\\d*")) {
            egyikSzamInput.getEditor().setText(newValue.replaceAll("[^\\d.]", ""));
            masikSzamInput.getEditor().setText(newValue.replaceAll("[^\\d.]", ""));
        }

    }

    @FXML
    public void osszead(ActionEvent actionEvent) {
        if (!validateInputs()) return;
        double egyikSzam = (double) egyikSzamInput.getValue();
        double masikSzam = (double) masikSzamInput.getValue();
        double osszeg = egyikSzam + masikSzam;
        eredmeny.setText(String.valueOf(Math.round(osszeg * 100.0) / 100.0));
    }

    @FXML
    public void kivon(ActionEvent actionEvent) {
        if (!validateInputs()) return;
        double egyikSzam = (double) egyikSzamInput.getValue();
        double masikSzam = (double) masikSzamInput.getValue();
        double kulonbseg = egyikSzam - masikSzam;
        eredmeny.setText(String.valueOf(Math.round(kulonbseg * 100.0) / 100.0));
    }

    @FXML
    public void osztas(ActionEvent actionEvent) {
        if (!validateInputs()) return;
        double egyikSzam = (double) egyikSzamInput.getValue();
        double masikSzam = (double) masikSzamInput.getValue();
        double hanyados = (double) egyikSzam / masikSzam;
        eredmeny.setText(String.valueOf(Math.round(hanyados * 100.0) / 100.0));
    }

    @FXML
    public void maradekosOsztas(ActionEvent actionEvent) {
        if (!validateInputs()) return;
        double egyikSzam = (double) egyikSzamInput.getValue();
        double masikSzam = (double) masikSzamInput.getValue();
        double maradek = egyikSzam % masikSzam;
        eredmeny.setText(String.valueOf(Math.round(maradek * 100.0) / 100.0));
    }

    @FXML
    public void szorzas(ActionEvent actionEvent) {
        if (!validateInputs()) return;
        double egyikSzam = (double) egyikSzamInput.getValue();
        double masikSzam = (double) masikSzamInput.getValue();
        double szorzat = egyikSzam * masikSzam;
        eredmeny.setText(String.valueOf(Math.round(szorzat * 100.0) / 100.0));
    }
}