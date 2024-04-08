package com.example.parfumeria2.Model.Persistence;


import com.example.parfumeria2.Model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonPersistence {
    private static final Logger LOGGER = Logger.getLogger(PersonPersistence.class.getName());

    public PersonPersistence(){
    }
    public boolean createPerson(Person p){
        String sql = String.format("INSERT INTO Parfumeria.Person (ID, name, surname,email, password, job) VALUES ('%s', '%s', '%s', '%s', '%s', '%s');",
                p.getID(), p.getName(), p.getSurname(), p.getEmail(), p.getPassword(), p.getJob().toString());

        return (Persistence.executeUpdate(sql) == 1);
    }
    public boolean updatePersonField(String id, String fieldName, String fieldValue) {
        String sql = "UPDATE Parfumeria.Person SET " + fieldName + " = ? WHERE ID = ?";
        try (Connection conn = Persistence.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fieldValue);
            stmt.setString(2, id);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, ex.getMessage(), ex);
            return false;
        }
    }


    public boolean deletePerson(String id) {
        String sql = "DELETE FROM Parfumeria.Person WHERE ID = ?";
        try (Connection conn = Persistence.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, ex.getMessage(), ex);
            return false;
        }
    }

    public Person readPerson(String id) {
        String sql = "SELECT * FROM Parfumeria.Person WHERE ID = ?";
        try (Connection conn = Persistence.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Person p = new Person();
                p.setID(rs.getString("ID"));
                p.setEmail(rs.getString("email"));
                p.setPassword(rs.getString("password"));
                p.setJob(Person.Job.valueOf(rs.getString("job")));
                p.setName(rs.getString("name"));
                p.setSurname(rs.getString("surname"));
                return p;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, ex.getMessage(), ex);
            return null;
        }
    }
    public ArrayList<Person> getAllPersons() {
        ArrayList<Person> persons = new ArrayList<>();

        String sql = "SELECT * FROM Parfumeria.Person";

        try (Connection conn = Persistence.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("ID");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Person.Job job = Person.Job.valueOf(rs.getString("job"));
                Person person = new Person(id, name, surname, email, password,job);
                persons.add(person);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, ex.getMessage(), ex);
        }

        return persons;
    }
    public Person searchPersonByName(String name) {
        String sql = "SELECT * FROM Parfumeria.Person WHERE name = ?";
        try (Connection conn = Persistence.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Person p = new Person();
                p.setID(rs.getString("ID"));
                p.setEmail(rs.getString("email"));
                p.setPassword(rs.getString("password"));
                p.setJob(p.getJob().valueOf(rs.getString("job")));
                p.setName(rs.getString("name"));
                p.setSurname(rs.getString("surname"));
                return p;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, ex.getMessage(), ex);
            return null;
        }
    }
    public Person searchPersonById(String id) {
        String sql = "SELECT * FROM Parfumeria.Person WHERE ID = ?";
        try (Connection conn = Persistence.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Person p = new Person();
                p.setID(rs.getString("ID"));
                p.setEmail(rs.getString("email"));
                p.setPassword(rs.getString("password"));
                p.setJob(p.getJob().valueOf(rs.getString("job")));
                p.setName(rs.getString("name"));
                p.setSurname(rs.getString("surname"));
                return p;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, ex.getMessage(), ex);
            return null;
        }
    }









}
