package com.example.parfumeria2;

import com.example.parfumeria2.Model.Persistence.CreateDBTables;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Initialize the database and create tables
        CreateDBTables.createDatabase();
        CreateDBTables.createPerfumeTable();
        CreateDBTables.createPersonTable();
        CreateDBTables.createPerfumeShopTable();
        CreateDBTables.createPerfumeInShopTable();
        CreateDBTables cdbt = new CreateDBTables();

        // Populate the tables
        cdbt.populatePerfumeTable();
        cdbt.populatePersonTable();
        cdbt.populatePerfumeShopTable();;
        cdbt.populatePerfumeINShopTable();

        Parent root =   FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        //login 500 330
        //admin 580 580
        //manager 580 580
        //employee 580 580
        Scene scene = new Scene(root, 500, 330);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}