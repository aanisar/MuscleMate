package com.lh1110642.gymgenie;

public class BodyStats {
    String height;
    String weight;

    String Stat;

    String uid;

    public BodyStats(String height, String weight, String Stat) {
        this.height = height;
        this.weight = weight;
        this.Stat = Stat;
        this.uid = "0000";
    }
    //Needed for Object decerlization, Null constructor
    public BodyStats() {

    }
    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getStat() {
        return Stat;
    }

    public void setStat(String Stat) {
        this.Stat = Stat;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getuid() {
        return uid;
    }

    public void setuid(String uid) {
        this.uid = uid;
    }
}
