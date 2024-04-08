package com.example.parfumeria2.ViewModel;

import com.example.parfumeria2.Model.Persistence.PersonPersistence;
import com.example.parfumeria2.Model.Person;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LoginVM {
    private PersonPersistence personPersistence;
    private ObservableList<Person> persons = FXCollections.observableArrayList();


    private final StringProperty username;
    private final StringProperty password;

    public LoginVM() {
        this.personPersistence = new PersonPersistence();
        this.username = new SimpleStringProperty();
        this.password = new SimpleStringProperty();

    }
    public Person searchPerson() {
        return personPersistence.searchPersonByName(usernameProperty().get());
    }
    public StringProperty usernameProperty() {
        return username;
    }
    public StringProperty passwordProperty() {
        return password;
    }

    public String roleFinder(){
        Person p = searchPerson();
        if(p != null)
            if( p.getPassword().equals(passwordProperty().get()))
                return p.getJob().toString();
            return null;
    }

}
