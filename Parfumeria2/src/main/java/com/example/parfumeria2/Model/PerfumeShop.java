package com.example.parfumeria2.Model;

public class PerfumeShop {
    private String code;
    private String location;

    public PerfumeShop(String code, String location) {
        this.code = code;
        this.location = location;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
