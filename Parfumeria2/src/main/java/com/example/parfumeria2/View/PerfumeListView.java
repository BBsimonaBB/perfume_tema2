package com.example.parfumeria2.View;

import com.example.parfumeria2.Exporter.*;
import com.example.parfumeria2.ViewModel.PerfumeListVM;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PerfumeListView implements Initializable {
    @FXML
    private ChoiceBox formatChoiceBox;
    @FXML
    private Button generateButton;
    private final PerfumeListVM perfumeListVM = new PerfumeListVM();
    private Stage stage;
    private Exporter exporter = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void handlerGenerate(javafx.event.ActionEvent actionEvent) throws IOException {
        String selectedItem = formatChoiceBox.getValue().toString();
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        switch (selectedItem){
            case "JSON":
                perfumeListVM.convertToJSON();
                System.out.println("JSON");
                break;
            case "TXT":
                perfumeListVM.convertToTXT();
                System.out.println("TXT");
                break;
            case "XML":
                perfumeListVM.convertToXML();
                System.out.println("XML");
                break;
            case "CSV": ;
                perfumeListVM.convertToCSV();
                System.out.println("CSV");
                break;
        }
        if (exporter != null) {
            //exporter.export(perfumeListVM., file);
        }
    }
}
/*
* switch (fileExtension.toLowerCase()) {
        case "csv":
            exporter = new CsvExporter();
            break;
        case "json":
            exporter = new JsonExporter();
            break;
        case "txt":
            exporter = new TxtExporter();
            break;
        case "xml":
            exporter = new XmlExporter();
            break;
        default:
            // Handle unsupported file format
            break;
    }
* */