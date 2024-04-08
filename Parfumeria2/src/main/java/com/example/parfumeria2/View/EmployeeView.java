package com.example.parfumeria2.View;

import com.example.parfumeria2.Model.Perfume;
import com.example.parfumeria2.ViewModel.EmployeeVM;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class EmployeeView implements Initializable {
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField brandField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField discountField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField stockField;
    @FXML
    private Button nextButton;
    @FXML
    private Button viewNameButton;
    @FXML
    private Button viewPriceButton;
    @FXML
    private ComboBox<String> perfumeMenu;
    @FXML
    private TableView perfumeTable;
    @FXML
    private TableColumn<?, String> perfumeID;
    @FXML
    private TableColumn<?, String> perfumeName;
    @FXML
    private TableColumn<?, String> perfumeBrand;
    @FXML
    private TableColumn<?, String> perfumePrice;
    @FXML
    private TableColumn<?, String> perfumeDiscount;
    @FXML
    private TableColumn<?, String> perfumeDescription;


    private final EmployeeVM employeeViewModel = new EmployeeVM();
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idField.textProperty().bindBidirectional(employeeViewModel.idProperty());
        nameField.textProperty().bindBidirectional(employeeViewModel.nameProperty());
        brandField.textProperty().bindBidirectional(employeeViewModel.brandProperty());
        priceField.textProperty().bindBidirectional(employeeViewModel.priceProperty());
        discountField.textProperty().bindBidirectional(employeeViewModel.discountProperty());
        descriptionField.textProperty().bindBidirectional(employeeViewModel.descriptionProperty());
        stockField.textProperty().bindBidirectional(employeeViewModel.stockProperty());

        perfumeID.setCellValueFactory(cellData -> employeeViewModel.setIDCellValueFactory(cellData.getValue()));
        perfumeName.setCellValueFactory(cellData -> employeeViewModel.setNameCellValueFactory(cellData.getValue()));
        perfumeBrand.setCellValueFactory(cellData -> employeeViewModel.setBrandCellValueFactory(cellData.getValue()));
        perfumePrice.setCellValueFactory(cellData -> employeeViewModel.setPriceCellValueFactory(cellData.getValue()));
        perfumeDiscount.setCellValueFactory(cellData -> employeeViewModel.setDiscountCellValueFactory(cellData.getValue()));
        perfumeDescription.setCellValueFactory(cellData -> employeeViewModel.setDescriptionCellValueFactory(cellData.getValue()));

    }

    public void handlerAction(ActionEvent event) {
        String selectedItem = perfumeMenu.getValue();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        switch (selectedItem) {
            case "Filter by price":
                var pair = employeeViewModel.filterByPrice();
                if (!pair.getKey())
                    showAlert("Field empty", "Price field missing information", Alert.AlertType.ERROR);
                else
                    perfumeTable.setItems(FXCollections.observableList(pair.getValue()));
                break;
            case "Filter by brand":
                pair = employeeViewModel.filterByBrand();
                if (!pair.getKey())
                    showAlert("Field empty", "Brand field missing information", Alert.AlertType.ERROR);
                else
                    perfumeTable.setItems(employeeViewModel.filterByBrand().getValue());
                break;
            case "Filter by availability":
                pair = employeeViewModel.filterByAvailability();
                if (!pair.getKey())
                    showAlert("Field empty", "Stock field missing information", Alert.AlertType.ERROR);
                else
                    perfumeTable.setItems(employeeViewModel.filterByAvailability().getValue());
                break;
            case "Create perfume":
                pair = employeeViewModel.createPerfume();
                if (!pair.getKey())
                    showAlert("Field empty", "Missing info on the required fields", Alert.AlertType.ERROR);
                else
                    perfumeTable.setItems(employeeViewModel.getPerfumes());
                break;
            case "Read after ID":
                Pair<Boolean, String> pair2 = employeeViewModel.readPerfume();
                if (!pair2.getKey())
                    showAlert("Field empty", pair2.getValue(), Alert.AlertType.ERROR);
                else
                    showAlert("Read - Perfume", pair2.getValue(), Alert.AlertType.CONFIRMATION);
                break;
            case "Update after ID":
                pair = employeeViewModel.updatePerfume();
                if (!pair.getKey())
                    showAlert("Field empty", "ID field missing or ID not found in database or not at least one other is missing", Alert.AlertType.ERROR);
                else {
                    perfumeTable.setItems(employeeViewModel.getPerfumes());
                    perfumeTable.refresh();
                }
                break;
            case "Delete after ID":
                pair = employeeViewModel.deletePerfume();
                if (!pair.getKey())
                    showAlert("Field empty", "ID field missing or ID not found in database", Alert.AlertType.ERROR);
                else
                    perfumeTable.setItems(employeeViewModel.getPerfumes());
                break;
            case "Search after name":
                pair = employeeViewModel.searchAfterName();
                if (!pair.getKey())
                    showAlert("Field empty", "Name field missing", Alert.AlertType.ERROR);
                else
                    perfumeTable.setItems(pair.getValue());
                break;
            case "Save perfumes list":
                URL url;
                try {
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    url = Paths.get("D:\\An3\\Sem2\\PS\\Tema2\\Parfumeria2\\src\\main\\resources\\com\\example\\parfumeria2\\perfumelist-view.fxml").toUri().toURL();
                    Parent root = FXMLLoader.load(url);
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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

    public void handlerViewName(ActionEvent actionEvent) {
       perfumeTable.setItems(employeeViewModel.viewAfterName());
       perfumeTable.refresh();
    }

    public void handlerViewPrice(ActionEvent actionEvent) {
        perfumeTable.setItems(employeeViewModel.viewAfterPrice());
        perfumeTable.refresh();
    }
}


