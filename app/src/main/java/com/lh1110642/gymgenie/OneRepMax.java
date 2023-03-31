package com.lh1110642.gymgenie;

public class OneRepMax {
    String weight;

    String reps;

    String oneRepMax;

    String id;

    public OneRepMax(String weight, String reps, String oneRepMax, String id) {
        this.weight = weight;
        this.reps = reps;
        this.oneRepMax = oneRepMax;
        this.id = "0000";
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public String getOneRepMax() {
        return oneRepMax;
    }

    public void setOneRepMax(String oneRepMax) {
        this.oneRepMax = oneRepMax;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
