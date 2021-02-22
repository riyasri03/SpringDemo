package com.example.Springdemo1.dto;

public class Productdto {
    private boolean instock;
    private  int salePrice;
    private  String description;
    private String title;

    public boolean isInstock() {
        return instock;
    }

    public void setInstock(boolean instock) {
        this.instock = instock;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Prductdto{" +
                "instock=" + instock +
                ", salePrice=" + salePrice +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
