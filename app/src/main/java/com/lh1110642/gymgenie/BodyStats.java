package com.lh1110642.gymgenie;

public class BodyStats {
    String statOne;
    String statTwo;

    String Stat;

    String uid;

    int age;

    public BodyStats(String statOne, String statTwo, String Stat) {
        this.statOne = statOne;
        this.statTwo = statTwo;
        this.Stat = Stat;
        this.uid = "0000";
    }
    //Needed for Object decerlization, Null constructor
    public BodyStats() {

    }
    public String getstatOne() {
        return statOne;
    }

    public void setstatOne(String statOne) {
        this.statOne = statOne;
    }

    public String getStat() {
        return Stat;
    }

    public void setStat(String Stat) {
        this.Stat = Stat;
    }

    public String getstatTwo() {
        return statTwo;
    }

    public void setstatTwo(String statTwo) {
        this.statTwo = statTwo;
    }

    public String getuid() {
        return uid;
    }

    public void setuid(String uid) {
        this.uid = uid;
    }

    public void setAge(int age) {this.age = age;

    }

    public int getAge() {return age;}
}
