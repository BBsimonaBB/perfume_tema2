package com.example.parfumeria2.ViewModel;

import com.example.parfumeria2.Exporter.*;
import com.example.parfumeria2.Model.Perfume;
import com.example.parfumeria2.Model.Persistence.PerfumePersistence;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;

public class PerfumeListVM { //ar trb cumva mutat in baseVM?
    private ObservableList<Perfume> perfumes;
    private PerfumePersistence perfumePersistence;

    public PerfumeListVM(){
        perfumePersistence = new PerfumePersistence();
        perfumes = FXCollections.observableArrayList(perfumePersistence.getAllPerfumes());

    }
    public void convertToCSV() throws IOException {
        Exporter exporter = new CsvExporter();
        exporter.exportList(perfumes);
    }
    public void convertToTXT() throws IOException {
        Exporter exporter = new TxtExporter();
        exporter.exportList(perfumes);
    }
    public void convertToJSON() throws IOException {
        Exporter exporter = new JsonExporter();
        exporter.exportList(perfumes);
    }
    public void convertToXML() throws IOException {
        Exporter exporter = new XmlExporter();
        exporter.exportList(perfumes);
    }
}

