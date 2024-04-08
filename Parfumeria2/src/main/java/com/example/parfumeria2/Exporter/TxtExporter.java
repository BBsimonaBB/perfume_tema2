package com.example.parfumeria2.Exporter;

import com.example.parfumeria2.Model.Perfume;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TxtExporter implements Exporter{
    @Override
    public void exportList(ObservableList<Perfume> perfumes) throws IOException {
        FileWriter file = new FileWriter("data.txt");
        try (BufferedWriter writer = new BufferedWriter(file)) {
            // Write the TXT data for each perfume
            for (Perfume perfume : perfumes) {
                writer.write("Code: " + perfume.getCode() + "\n");
                writer.write("Name: " + perfume.getName() + "\n");
                writer.write("Manufacturer: " + perfume.getManufacturer() + "\n");
                writer.write("Price: " + perfume.getPrice() + "\n");
                writer.write("Discount: " + perfume.getDiscount() + "\n");
                writer.write("Description: " + perfume.getDescription() + "\n");
                writer.write("Gender: " + perfume.getGender() + "\n");
                writer.write("\n");
            }
        }
    }
}
