package com.example.parfumeria2.Exporter;

import com.example.parfumeria2.Model.Perfume;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.stream.Collectors;

public class CsvExporter implements Exporter{
    @Override
    public void exportList(ObservableList<Perfume> perfumes) throws IOException {
        FileWriter writer = new FileWriter("data.csv");

        for(Perfume perfume : perfumes)
        {
                writer.write("Code: " + perfume.getCode() + "\n");
                writer.write("Name: " + perfume.getName() + "\n");
                writer.write("Manufacturer: " + perfume.getManufacturer() + "\n");
                writer.write("Price: " + perfume.getPrice() + "\n");
                writer.write("Discount: " + perfume.getDiscount() + "\n");
                writer.write("Description: " + perfume.getDescription() + "\n");
                writer.write("Gender: " + perfume.getGender() + "\n");
                writer.write("\n");
            //writer.write(perfume.toString() + "\n \n");
        }
        writer.close();
    }
}
