package com.example.parfumeria2.Exporter;

import com.example.parfumeria2.Model.Perfume;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.collections.ObservableList;

public class JsonExporter implements Exporter{
    @Override
    public void exportList(ObservableList<Perfume> perfumes) throws IOException {
        try {
            // Create an ObjectMapper to convert the Perfume objects to JSON
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            File file = new File("data.json");
            // Write the list of perfumes to the file in JSON format
            //mapper.writeValue(file, perfumes); //ar rtb un new line intre parfumuri
            //}
            try (JsonGenerator gen = mapper.getFactory().createGenerator(file, JsonEncoding.UTF8)
                    .useDefaultPrettyPrinter()) {
                // Write each perfume to a new line in the JSON file
                for (Perfume perfume : perfumes) {
                    mapper.writeValue(gen, perfume);
                    gen.writeRaw('\n');
                }
            }
        }
        catch (IOException e) {
            throw new IOException(e);
        }
    }
}
