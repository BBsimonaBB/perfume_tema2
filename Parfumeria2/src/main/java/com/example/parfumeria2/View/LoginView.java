package com.example.parfumeria2.View;

import com.example.parfumeria2.ViewModel.LoginVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class LoginView implements Initializable, PropertyChangeListener {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    private final LoginVM loginViewModel = new LoginVM();
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameField.textProperty().bindBidirectional(loginViewModel.usernameProperty());
        passwordField.textProperty().bindBidirectional(loginViewModel.passwordProperty());
    }

    public void handlerLogin(ActionEvent event) {
        String job = loginViewModel.roleFinder(); //string
        if (job != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Login Successfully!");
            alert.setHeaderText(null);
            alert.setContentText("Valid username and password.");
            alert.showAndWait();
            URL url;
            try {
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                switch (job) {
                    case "Employee":
                        url = Paths.get("D:\\An3\\Sem2\\PS\\Tema2\\Parfumeria2\\src\\main\\resources\\com\\example\\parfumeria2\\employee-view.fxml").toUri().toURL();
                        break;
                    case "Manager":
                        url = Paths.get("D:\\An3\\Sem2\\PS\\Tema2\\Parfumeria2\\src\\main\\resources\\com\\example\\parfumeria2\\manager-view.fxml").toUri().toURL();
                        break;
                    case "Admin":
                        url = Paths.get("D:\\An3\\Sem2\\PS\\Tema2\\Parfumeria2\\src\\main\\resources\\com\\example\\parfumeria2\\admin-view.fxml").toUri().toURL();
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value: " + job);
                }
                Parent root = FXMLLoader.load(url);
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password. Please try again.");
            alert.showAndWait();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
