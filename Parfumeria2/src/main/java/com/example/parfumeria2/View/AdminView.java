package com.example.parfumeria2.View;

import com.example.parfumeria2.Model.Person;
import com.example.parfumeria2.ViewModel.AdminVM;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminView implements Initializable {
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField jobField;

    @FXML
    private ComboBox<String> personMenu;
    @FXML
    private TableView personTable;
    @FXML
    private TableColumn<?, String> personID;
    @FXML
    private TableColumn<?, String> personName;

    @FXML
    private TableColumn<?, String> personSurname;

    @FXML
    private TableColumn<?, String> personEmail;

    @FXML
    private TableColumn<?, String> personPassword;

    @FXML
    private TableColumn<?, String> personJob;

    private final AdminVM adminVM = new AdminVM();
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idField.textProperty().bindBidirectional(adminVM.idProperty());
        nameField.textProperty().bindBidirectional(adminVM.nameProperty());
        surnameField.textProperty().bindBidirectional(adminVM.surnameProperty());
        emailField.textProperty().bindBidirectional(adminVM.emailProperty());
        passwordField.textProperty().bindBidirectional(adminVM.passwordProperty());
        jobField.textProperty().bindBidirectional(adminVM.jobProperty());

        personID.setCellValueFactory(cellData -> adminVM.setIDCellValueFactory(cellData.getValue()));
        personName.setCellValueFactory(cellData -> adminVM.setNameCellValueFactory(cellData.getValue()));
        personSurname.setCellValueFactory(cellData -> adminVM.setSurnameCellValueFactory(cellData.getValue()));
        personEmail.setCellValueFactory(cellData -> adminVM.setEmailCellValueFactory(cellData.getValue()));
        personPassword.setCellValueFactory(cellData -> adminVM.setPasswordCellValueFactory(cellData.getValue()));
        personJob.setCellValueFactory(cellData -> adminVM.setJobCellValueFactory(cellData.getValue()));

    }

    public void handlerAction(ActionEvent actionEvent) {
        String selectedItem = personMenu.getValue();
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        switch(selectedItem) {
            case "See all users":
                personTable.setItems(adminVM.getPersons());
                personTable.refresh();
                break;
            case "Filter by role":
                var pair = adminVM.filterByRole();
                if(!pair.getKey())
                    showAlert("Field missing", "Role field is missing information", Alert.AlertType.ERROR);
                else
                    personTable.setItems(pair.getValue());
                break;
            case "Create User":
                pair = adminVM.createPerson();
                if (!pair.getKey())
                    showAlert("Field empty", "Missing info on the required fields", Alert.AlertType.ERROR);
                else
                    personTable.setItems(adminVM.getPersons());
                break;
            case "Read after ID":
                Pair<Boolean, String> pair2 = adminVM.readPerson();
                if (!pair2.getKey())
                    showAlert("Field empty", pair2.getValue(), Alert.AlertType.ERROR);
                else
                    showAlert("Read - Person", pair2.getValue(), Alert.AlertType.CONFIRMATION);
                break;
            case "Update after ID":
                pair = adminVM.updatePerson();
                if (!pair.getKey())
                    showAlert("Field empty", "ID field missing or ID not found in database or not at least one other is missing", Alert.AlertType.ERROR);
                else {
                    personTable.setItems(adminVM.getPersons());
                    personTable.refresh();
                }
                break;
            case "Delete after ID":
                pair = adminVM.deletePerson();
                if (!pair.getKey())
                    showAlert("Field empty", "ID field missing or ID not found in database", Alert.AlertType.ERROR);
                else
                    personTable.setItems(adminVM.getPersons());
                break;
        }

    }
    public void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
