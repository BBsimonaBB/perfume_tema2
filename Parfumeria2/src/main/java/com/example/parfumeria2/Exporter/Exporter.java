package com.example.parfumeria2.Exporter;

import com.example.parfumeria2.Model.Perfume;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public interface Exporter {
    void exportList(ObservableList<Perfume> items) throws IOException;
}