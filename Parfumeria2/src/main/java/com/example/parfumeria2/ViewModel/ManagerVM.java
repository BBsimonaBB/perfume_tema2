package com.example.parfumeria2.ViewModel;

import com.example.parfumeria2.Model.PerfumeInStock;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

public class ManagerVM extends BaseVM {

    private final StringProperty shop;

    public ManagerVM() {
        this.shop = new SimpleStringProperty();
    }


    public StringProperty shopProperty() {
        return shop;
    }

    public Pair<Boolean,ObservableList<PerfumeInStock>> filterAfterShop() {
        ObservableList<PerfumeInStock> stockPerfumes;
        if (shopProperty().get() == null || shopProperty().get().equals("") )
            return new Pair(false,null);
        else {
            stockPerfumes = FXCollections.observableList(perfumePersistence.filterAfterShop(shopProperty().get()));
            return new Pair(true, stockPerfumes);
        }
    }

    public SimpleStringProperty setStockCellValueFactory(Object value) {
        if (value instanceof PerfumeInStock) {
            return new SimpleStringProperty(String.valueOf(((PerfumeInStock) value).getStock()));
        } else {
            return new SimpleStringProperty("");
        }
    }

}
