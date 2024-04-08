module com.example.parfumeria2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;
    requires java.desktop;
    requires com.fasterxml.jackson.databind;
    requires java.xml.bind;


    opens com.example.parfumeria2 to javafx.fxml;
    exports com.example.parfumeria2;
    exports com.example.parfumeria2.View;
    opens com.example.parfumeria2.View to javafx.fxml;
    exports com.example.parfumeria2.Model to com.fasterxml.jackson.databind;
    opens com.example.parfumeria2.Model to java.xml.bind;

}