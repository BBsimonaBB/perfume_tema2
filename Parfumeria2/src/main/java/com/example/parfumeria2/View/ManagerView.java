package com.example.parfumeria2.View;

import com.example.parfumeria2.Model.Perfume;
import com.example.parfumeria2.Model.PerfumeInStock;
import com.example.parfumeria2.ViewModel.ManagerVM;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class ManagerView implements Initializable {
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
    private TextField shopField;
    @FXML
    private Button nextButton;
    @FXML
    private ScrollPane scrollPane;
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
    @FXML
    private TableColumn<?, String> perfumeStock;

    private final ManagerVM managerVM = new ManagerVM();
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idField.textProperty().bindBidirectional(managerVM.idProperty());
        nameField.textProperty().bindBidirectional(managerVM.nameProperty());
        brandField.textProperty().bindBidirectional(managerVM.brandProperty());
        priceField.textProperty().bindBidirectional(managerVM.priceProperty());
        discountField.textProperty().bindBidirectional(managerVM.discountProperty());
        descriptionField.textProperty().bindBidirectional(managerVM.descriptionProperty());
        stockField.textProperty().bindBidirectional(managerVM.stockProperty());
        shopField.textProperty().bindBidirectional(managerVM.shopProperty());

        VBox vBox = new VBox(perfumeTable);
        scrollPane.setContent(vBox);

        perfumeID.setCellValueFactory(cellData -> managerVM.setIDCellValueFactory(cellData.getValue()));
        perfumeName.setCellValueFactory(cellData -> managerVM.setNameCellValueFactory(cellData.getValue()));
        perfumeBrand.setCellValueFactory(cellData -> managerVM.setBrandCellValueFactory(cellData.getValue()));
        perfumePrice.setCellValueFactory(cellData -> managerVM.setPriceCellValueFactory(cellData.getValue()));
        perfumeDiscount.setCellValueFactory(cellData -> managerVM.setDiscountCellValueFactory(cellData.getValue()));
        perfumeDescription.setCellValueFactory(cellData -> managerVM.setDescriptionCellValueFactory(cellData.getValue()));
        perfumeStock.setCellValueFactory(cellData -> managerVM.setStockCellValueFactory(cellData.getValue()));
    }

    public void handlerAction(ActionEvent actionEvent) {
        String selectedItem = perfumeMenu.getValue();
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        if (!selectedItem.equals("Filter by Perfume Shop")) {
            perfumeStock.setVisible(false);
        }
         else perfumeStock.setVisible(true);
        switch (selectedItem) {
            case "See all perfumes":
                perfumeTable.setItems(managerVM.getPerfumes());
                break;
            case "Search after name":
                var pair = managerVM.searchAfterName();
                if (!pair.getKey())
                    showAlert("Field empty", "Name field missing", Alert.AlertType.ERROR);
                else
                    perfumeTable.setItems(pair.getValue());
                break;
            case "Filter by price":
                pair = managerVM.filterByPrice();
                if (!pair.getKey())
                    showAlert("Field empty", "Price field missing information", Alert.AlertType.ERROR);
                else
                    perfumeTable.setItems(FXCollections.observableList(pair.getValue()));
                break;
            case "Filter by brand":
                pair = managerVM.filterByBrand();
                if (!pair.getKey())
                    showAlert("Field empty", "Brand field missing information", Alert.AlertType.ERROR);
                else
                    perfumeTable.setItems(managerVM.filterByBrand().getValue());
                break;
            case "Filter by availability":
                pair = managerVM.filterByAvailability();
                if (!pair.getKey())
                    showAlert("Field empty", "Stock field missing information", Alert.AlertType.ERROR);
                else
                    perfumeTable.setItems(managerVM.filterByAvailability().getValue());
                break;
            case "Filter by Perfume Shop":
                var pair3 = managerVM.filterAfterShop();
                if(!pair3.getKey())
                    showAlert("Field empty", "Shop field missing information", Alert.AlertType.ERROR);
                else
                    perfumeTable.setItems(pair3.getValue());
                //}
                break;
            case "View after name":
                perfumeTable.setVisible(true);
                perfumeTable.setItems(managerVM.viewAfterName());
                perfumeTable.refresh();
                break;
            case "View after price":
                perfumeTable.setVisible(true);
                perfumeTable.setItems(managerVM.viewAfterPrice());
                perfumeTable.refresh();
                break;
            case "Save perfumes list":
                URL url;
                try {
                    stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
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
}
