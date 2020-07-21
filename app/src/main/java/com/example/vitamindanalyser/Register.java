package com.example.vitamindanalyser;

public class Register {
    public Integer skintype;
    public Float weight;
    private Float height;
    public Register() {
    }

    public Integer getSkintype(int st) {
        return skintype;
    }

    public void setSkintype(Integer skintype) {
        this.skintype = skintype;
    }

    public Float getWeight(Float weight) {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getHeight(Float height) {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }
}
