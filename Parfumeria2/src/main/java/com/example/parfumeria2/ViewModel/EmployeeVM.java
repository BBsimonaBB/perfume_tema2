package com.example.parfumeria2.ViewModel;

import com.example.parfumeria2.Model.Perfume;
import javafx.collections.ObservableList;
import javafx.util.Pair;


public class EmployeeVM extends BaseVM {


    public EmployeeVM() {
    }

    public Pair<Boolean, ObservableList<Perfume>> createPerfume() {
        if ((idProperty().get() != null && !idProperty().get().equals("")) &&
                (nameProperty().get() != null && !nameProperty().get().equals("")) &&
                (brandProperty().get() != null && !brandProperty().get().equals("")) &&
                (priceProperty().get() != null && !priceProperty().get().equals("")) &&
                (discountProperty().get() != null && !discountProperty().get().equals("")) &&
                (descriptionProperty().get()) != null && !descriptionProperty().get().equals("")) {
            Perfume p = new Perfume(idProperty().get(), nameProperty().get(), brandProperty().get(), Float.parseFloat(priceProperty().get()), Float.parseFloat(discountProperty().get()), descriptionProperty().get(), Perfume.Gender.F);
            perfumePersistence.createPerfume(p);
            perfumes.add(p);
            return new Pair(true, perfumes);
        } else return new Pair(false, null);
    }

    public Pair<Boolean, ObservableList<Perfume>> deletePerfume() {
        if (idProperty().get() == null || idProperty().get().equals(""))
            return new Pair(false, "");
        else {
            perfumePersistence.deletePerfume(idProperty().get());
            perfumes.removeIf(p -> p.getCode().equals(idProperty().get()));
            return new Pair(true, perfumes);
        }
    }

    public Pair<Boolean, String> readPerfume() {
        if (idProperty().toString().isEmpty())
            return new Pair(false, "ID field missing");
        for (Perfume p : perfumes) {
            if (p.getCode().equals(idProperty().get()))
                return new Pair(true, p.toString());
        }
        return new Pair(false, "ID was not found in the database");
    }

    public Pair<Boolean, ObservableList<Perfume>> updatePerfume() {
        if (idProperty().getValue() == null)
            return new Pair(false, null);
        else if ((nameProperty().getValue() == null) &&
                (brandProperty().getValue() == null) &&
                (priceProperty().getValue() == null) &&
                (discountProperty().getValue() == null) &&
                (descriptionProperty().getValue() == null) )
            return new Pair(false, null);
        else {
            for (Perfume p : perfumes) {
                if (p.getCode().equals(idProperty().get())) {
                    if (nameProperty().getValue() != null && !nameProperty().getValue().equals("")) {
                        perfumePersistence.updatePerfumeField(idProperty().get(), "name",nameProperty().get());
                        p.setName(nameProperty().get());
                    }
                    if (brandProperty().getValue() != null && !brandProperty().getValue().equals("")) {
                        perfumePersistence.updatePerfumeField(idProperty().get(), "manufacturer", brandProperty().get());
                        p.setManufacturer(brandProperty().get());
                    }
                    if (priceProperty().getValue() != null && !priceProperty().getValue().equals("")) {
                        perfumePersistence.updatePerfumeField(idProperty().get(), "price", priceProperty().get());
                        p.setPrice(Float.parseFloat(priceProperty().get()));
                    }
                    if (discountProperty().getValue() != null && !discountProperty().getValue().equals("")) {
                        perfumePersistence.updatePerfumeField(idProperty().get(), "discount", discountProperty().get());
                        p.setDiscount(Float.parseFloat(discountProperty().get()));
                    }
                    if (descriptionProperty().getValue() != null && !descriptionProperty().getValue().equals("")) {
                        perfumePersistence.updatePerfumeField(idProperty().get(), "description", descriptionProperty().get());
                        p.setDescription(descriptionProperty().get());
                    }
                    break;
                }
            }
            return new Pair<>(true, perfumes);
        }
    }
}
