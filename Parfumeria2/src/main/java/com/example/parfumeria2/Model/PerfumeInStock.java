package com.example.parfumeria2.Model;

public class PerfumeInStock {
    private Perfume p;
    private int stock;

    public PerfumeInStock(Perfume p, int stock) {
        this.p = p;
        this.stock = stock;
    }

    public Perfume getPerfume() {
        return p;
    }

    public int getStock() {
        return stock;
    }
}
