package com.example.parfumeria2.ViewModel;

import com.example.parfumeria2.Model.Perfume;
import com.example.parfumeria2.Model.PerfumeInStock;
import com.example.parfumeria2.Model.Person;
import com.example.parfumeria2.Model.Persistence.PersonPersistence;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.util.ArrayList;

public class AdminVM {
    private PersonPersistence personPersistence;
    private ObservableList<Person> persons;

    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty surname;
    private final StringProperty email;
    private final StringProperty password;
    private final StringProperty job;

    public AdminVM() {
        this.personPersistence = new PersonPersistence();
        this.persons = FXCollections.observableArrayList(personPersistence.getAllPersons());
        this.id = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.surname = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.password = new SimpleStringProperty();
        this.job = new SimpleStringProperty();
    }
    public StringProperty idProperty(){
        return id;
    }
    public StringProperty nameProperty(){
        return name;
    }
    public StringProperty surnameProperty(){
        return surname;
    }
    public StringProperty emailProperty(){
        return email;
    }
    public StringProperty passwordProperty(){
        return password;
    }
    public StringProperty jobProperty(){
        return job;
    }
    public ObservableList<Person> getPersons(){
        return persons;
    }
    public ArrayList<Person> getAllPersons(){
        ArrayList<Person> persons = personPersistence.getAllPersons();
        return persons;
    }
    public Pair<Boolean,ObservableList<Person>> filterByRole(){
        ObservableList<Person> filteredPersons = FXCollections.observableList(getAllPersons());
        if(jobProperty().get() == null || jobProperty().get().equals(""))
            return new Pair(false,null);
        else {
            filteredPersons.removeIf(p -> !p.getJob().toString().equals(jobProperty().get()));
            return new Pair(true, filteredPersons);
        }
    }
    public Pair<Boolean, ObservableList<Person>> createPerson() {
        if ((idProperty().get() != null && !idProperty().get().equals("")) &&
                (nameProperty().get() != null && !nameProperty().get().equals("")) &&
                (surnameProperty().get() != null && !surnameProperty().get().equals("")) &&
                (emailProperty().get() != null && !emailProperty().get().equals("")) &&
                (passwordProperty().get() != null && !passwordProperty().get().equals("")) &&
                (jobProperty().get()) != null && !jobProperty().get().equals("")) {
            Person p = new Person(idProperty().get(), nameProperty().get(), surnameProperty().get(), emailProperty().get(), passwordProperty().get(), Person.Job.valueOf(jobProperty().get()));
            personPersistence.createPerson(p);
            persons.add(p);
            return new Pair(true, persons);
        } else return new Pair(false, null);
    }
    public Pair<Boolean, ObservableList<Person>> deletePerson() {
        if (idProperty().toString().isEmpty())
            return new Pair(false, "");
        else {
            personPersistence.deletePerson(idProperty().get());
            persons.removeIf(p -> p.getID().equals(idProperty().get()));
            return new Pair(true, persons);
        }
    }

    public Pair<Boolean, String> readPerson() {
        if (idProperty().toString().isEmpty())
            return new Pair(false, "ID field missing");
        for (Person p : persons) {
            if (p.getID().equals(idProperty().get()))
                return new Pair(true, p.toString());
        }
        return new Pair(false, "ID was not found in the database");
    }
    public Pair<Boolean, ObservableList<Person>> updatePerson() {
        if (idProperty().getValue() == null || personPersistence.searchPersonById(idProperty().get()) == null)
            return new Pair(false, null);
        else if ((nameProperty().getValue() == null) &&
                (surnameProperty().getValue() == null) &&
                (emailProperty().getValue() == null) &&
                (passwordProperty().getValue() == null) &&
                (jobProperty().getValue() == null) )
            return new Pair(false, null);
        else {
            for (Person p : persons) {
                if (p.getID().equals(idProperty().get())) {
                    if (nameProperty().getValue() != null && !nameProperty().getValue().equals("")) {
                        personPersistence.updatePersonField(idProperty().get(), "name",nameProperty().get());
                        p.setName(nameProperty().get());
                    }
                    if (surnameProperty().getValue() != null && !surnameProperty().getValue().equals("")) {
                        personPersistence.updatePersonField(idProperty().get(), "surname", surnameProperty().get());
                        p.setSurname(surnameProperty().get());
                    }
                    if (emailProperty().getValue() != null && !emailProperty().getValue().equals("")) {
                        personPersistence.updatePersonField(idProperty().get(), "email", emailProperty().get());
                        p.setEmail(emailProperty().get());
                    }
                    if (passwordProperty().getValue() != null && !passwordProperty().getValue().equals("")) {
                        personPersistence.updatePersonField(idProperty().get(), "password", passwordProperty().get());
                        p.setPassword(passwordProperty().get());
                    }
                    if (jobProperty().getValue() != null && !jobProperty().getValue().equals("")) {
                        personPersistence.updatePersonField(idProperty().get(), "job", jobProperty().get());
                        p.setJob(Person.Job.valueOf(jobProperty().get()));
                    }
                    break;
                }
            }
            return new Pair<>(true, persons);
        }
    }
    public SimpleStringProperty setIDCellValueFactory(Object value) {
        if (value instanceof Person) {
            return new SimpleStringProperty(((Person) value).getID());
        }  else {
            return new SimpleStringProperty("");
        }
    }
    public SimpleStringProperty setNameCellValueFactory(Object value) {
        if (value instanceof Person) {
            return new SimpleStringProperty(((Person) value).getName());
        }  else {
            return new SimpleStringProperty("");
        }
    }
    public SimpleStringProperty setSurnameCellValueFactory(Object value) {
        if (value instanceof Person) {
            return new SimpleStringProperty(((Person) value).getSurname());
        }  else {
            return new SimpleStringProperty("");
        }
    }
    public SimpleStringProperty setEmailCellValueFactory(Object value) {
        if (value instanceof Person) {
            return new SimpleStringProperty(((Person) value).getEmail());
        }  else {
            return new SimpleStringProperty("");
        }
    }
    public SimpleStringProperty setPasswordCellValueFactory(Object value) {
        if (value instanceof Person) {
            return new SimpleStringProperty(((Person) value).getPassword());
        }  else {
            return new SimpleStringProperty("");
        }
    }
    public SimpleStringProperty setJobCellValueFactory(Object value) {
        if (value instanceof Person) {
            return new SimpleStringProperty(String.valueOf(((Person) value).getJob()));
        }  else {
            return new SimpleStringProperty("");
        }
    }

}
