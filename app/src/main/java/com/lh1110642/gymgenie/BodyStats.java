package com.lh1110642.gymgenie;

public class BodyStats {
    String height;
    String weight;

    String bmi;

    String id;

    public BodyStats(String height, String weight, String bmi, String id) {
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.id = "0000";
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
