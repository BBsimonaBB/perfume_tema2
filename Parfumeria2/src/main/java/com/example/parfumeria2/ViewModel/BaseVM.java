package com.example.parfumeria2.ViewModel;

import com.example.parfumeria2.Model.Perfume;
import com.example.parfumeria2.Model.PerfumeInStock;
import com.example.parfumeria2.Model.Persistence.PerfumePersistence;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.util.ArrayList;

public abstract class BaseVM {
    protected PerfumePersistence perfumePersistence;
    protected ObservableList<Perfume> perfumes;


    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty brand;
    private final StringProperty price;
    private final StringProperty discount;
    private final StringProperty description;
    private final StringProperty stock;
    // final StringProperty shop;

    public BaseVM() {
        this.perfumePersistence = new PerfumePersistence();
        this.perfumes = FXCollections.observableArrayList(perfumePersistence.getAllPerfumes());
        this.id = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.brand = new SimpleStringProperty();
        this.price = new SimpleStringProperty();
        this.discount = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.stock = new SimpleStringProperty();
        //this.shop = new SimpleStringProperty();
    }

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty brandProperty() {
        return brand;
    }

    public StringProperty priceProperty() {
        return price;
    }

    public StringProperty discountProperty() {
        return discount;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public StringProperty stockProperty() {
        return stock;
    }

    /*public StringProperty shopProperty() {
        return shop;
    }*/

    public ObservableList<Perfume> getPerfumes() {
        return perfumes;
    }

    public ArrayList<Perfume> getAllPerfumes() {
        ArrayList<Perfume> perfumes = perfumePersistence.getAllPerfumes();
        return perfumes;
    }

    public Pair<Boolean, ObservableList<Perfume>> filterByPrice() {
        ObservableList<Perfume> filteredPerfumes = FXCollections.observableArrayList(getAllPerfumes());
        float price;
        if (priceProperty().get() == null || priceProperty().get().equals(""))
            return new Pair(false, filteredPerfumes);
        else price = Float.parseFloat(priceProperty().get());
        filteredPerfumes.removeIf(p -> p.getPrice() != price);
        return new Pair(true, filteredPerfumes);
    }

    public Pair<Boolean, ObservableList<Perfume>> filterByBrand() {
        ObservableList<Perfume> filteredPerfumes = FXCollections.observableArrayList(getAllPerfumes());
        if (brandProperty().get() == null || brandProperty().get().equals(""))
            return new Pair(false, filteredPerfumes);
        else {
            filteredPerfumes.removeIf(p -> !p.getManufacturer().equals(brandProperty().get()));
            return new Pair(true, filteredPerfumes);
        }
    }

    public Pair<Boolean, ObservableList<Perfume>> filterByAvailability() {
        ObservableList<Perfume> filteredPerfumes = FXCollections.observableArrayList(getAllPerfumes());
        if (stockProperty().get() == null || stockProperty().get().equals(""))
            return new Pair(false, filteredPerfumes);
        filteredPerfumes = FXCollections.observableArrayList(perfumePersistence.calcTotalStock(stockProperty().get()));
        return new Pair(true, filteredPerfumes);
    }

    public ObservableList<Perfume> viewAfterName() {
        return FXCollections.observableArrayList(perfumePersistence.getAllPerfumesOrderedByName());
    }

    public ObservableList<Perfume> viewAfterPrice() {
        return FXCollections.observableArrayList(perfumePersistence.getAllPerfumesOrderedByPrice());
    }

    public Pair<Boolean, ObservableList<Perfume>> searchAfterName() {
        ObservableList<Perfume> perfumesWithName = FXCollections.observableArrayList(getAllPerfumes());
        if (nameProperty().get() == null || nameProperty().get().equals(""))
            return new Pair(false, perfumesWithName);
        else {
            perfumesWithName.removeIf(p -> !p.getName().contains((nameProperty().get())));
            return new Pair<>(true, perfumesWithName);
        }
    }

    public SimpleStringProperty setPerfumeIDCellValueFactory(Perfume cellData) {
        return new SimpleStringProperty(cellData.getCode());
    }

    public SimpleStringProperty setIDCellValueFactory(Object value) {
        if (value instanceof Perfume) {
            return new SimpleStringProperty(((Perfume) value).getCode());
        } else if (value instanceof PerfumeInStock) {
            return new SimpleStringProperty(((PerfumeInStock) value).getPerfume().getCode());
        } else {
            return new SimpleStringProperty("");
        }
    }

    public SimpleStringProperty setNameCellValueFactory(Object value) {
        if (value instanceof Perfume) {
            return new SimpleStringProperty(((Perfume) value).getName());
        } else if (value instanceof PerfumeInStock) {
            return new SimpleStringProperty(((PerfumeInStock) value).getPerfume().getName());
        } else {
            return new SimpleStringProperty("");
        }
    }

    public SimpleStringProperty setBrandCellValueFactory(Object value) {
        if (value instanceof Perfume) {
            return new SimpleStringProperty(((Perfume) value).getManufacturer());
        } else if (value instanceof PerfumeInStock) {
            return new SimpleStringProperty(((PerfumeInStock) value).getPerfume().getManufacturer());
        } else {
            return new SimpleStringProperty("");
        }
    }

    public SimpleStringProperty setPriceCellValueFactory(Object value) {
        if (value instanceof Perfume) {
            return new SimpleStringProperty(String.valueOf(((Perfume) value).getPrice()));
        } else if (value instanceof PerfumeInStock) {
            return new SimpleStringProperty(String.valueOf(((PerfumeInStock) value).getPerfume().getPrice()));
        } else {
            return new SimpleStringProperty("");
        }
    }

    public SimpleStringProperty setDiscountCellValueFactory(Object value) {
        if (value instanceof Perfume) {
            return new SimpleStringProperty(String.valueOf(((Perfume) value).getDiscount()));
        } else if (value instanceof PerfumeInStock) {
            return new SimpleStringProperty(String.valueOf(((PerfumeInStock) value).getPerfume().getDiscount()));
        } else {
            return new SimpleStringProperty("");
        }
    }
    public SimpleStringProperty setDescriptionCellValueFactory(Object value) {
        if (value instanceof Perfume) {
            return new SimpleStringProperty(((Perfume) value).getDescription());
        } else if (value instanceof PerfumeInStock) {
            return new SimpleStringProperty(((PerfumeInStock) value).getPerfume().getDescription());
        } else {
            return new SimpleStringProperty("");
        }
    }

}

