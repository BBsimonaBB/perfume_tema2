package com.example.parfumeria2.Model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
@XmlRootElement(name = "perfume")
@XmlAccessorType(XmlAccessType.FIELD)
public class Perfume implements Serializable {
    public enum Gender {
        F, M
    }
    @XmlElement
    private String code;
    @XmlElement
    private String name;
    @XmlElement
    private String manufacturer;
    @XmlElement
    private float price;
    @XmlElement
    private float discount;
    @XmlElement
    private String description;
    @XmlElement
    private Gender gender;

    public Perfume(String code, String name, String manufacter, float price, float discount, String description, Gender gender) {
        this.code = code;
        this.name = name;
        this.manufacturer = manufacter;
        this.price = price;
        this.discount = discount;
        this.description = description;
        this.gender = gender;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Perfume() {
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString(){
        return ("Parfumul este " + getName() + " si apartine brandului " + getManufacturer() + " \nLa magazinul nostru pretul e de " + getPrice() + "\nLa acest pret se aplica un discount de " + getDiscount() + "%"
        + "\nIn urma recenziilor clientilor,\nparfumul se claseaza in categoria " + getDescription());
    }

}
