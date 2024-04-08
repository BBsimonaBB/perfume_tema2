package com.example.parfumeria2.Model;

public class PerfumeInShop {
    private String code;
    private String codePerfume;
    private String codeShop;
    private int stock;

    public PerfumeInShop(String code, String codePerfume, String codeShop, int stock) {
        this.code = code;
        this.codePerfume = codePerfume;
        this.codeShop = codeShop;
        this.stock = stock;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodePerfume() {
        return codePerfume;
    }

    public void setCodePerfume(String codePerfume) {
        this.codePerfume = codePerfume;
    }

    public String getCodeShop() {
        return codeShop;
    }

    public void setCodeShop(String codeShop) {
        this.codeShop = codeShop;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
