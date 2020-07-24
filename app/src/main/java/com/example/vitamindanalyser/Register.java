package com.example.vitamindanalyser;

public class Register {
    private Integer skintype;
    private Float weight;
    private Float height;
    private Float bmi;

    public Float getBmi() {
        return bmi;
    }

    public void setBmi(Float bmi) {
        this.bmi = bmi;
    }

    public Register() {
    }

    public Integer getSkintype() {
        return skintype;
    }

    public void setSkintype(Integer skintype) {
        this.skintype = skintype;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }
}
