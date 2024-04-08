package com.example.parfumeria2.Model;


public class Person {
    public enum Job {
        Employee, Admin, Manager
    }
    private String id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Job job;

    public String getID() {
        return id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person(String id, String name, String surname, String email, String password, Job job) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.job = job;
    }
    public Person()
    {
    }
    public String toString(){
        return ("Persoana se numeste " + name + " " + surname + " are adresa de email: " + email + " si are rolul de " + job);
    }

}
