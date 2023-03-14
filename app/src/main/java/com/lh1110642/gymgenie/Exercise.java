package com.lh1110642.gymgenie;


public class Exercise {
    String name;
    String type;
    String muscle;
    String equipment;
    String difficulty;
    String instructions;
    String uid;

    String workOutGroup;

    String reps;
    String sets;

    public Exercise(String name, String type, String muscle, String equipment, String difficulty, String instructions) {
        this.name = name;
        this.type = type;
        this.muscle = muscle;
        this.equipment = equipment;
        this.difficulty = difficulty;
        this.instructions = instructions;
        this.uid = "0000";
    }

   //Needed for Object decerlization, Null constructor
    public Exercise() {

    }

    public String getReps() {
        return reps;
    }
    public void setReps(String str) {
        this.reps = str;
    }
    public String getSets() {
        return sets;
    }
    public void setSets(String str) {
        this.sets = str;
    }
    public String getuid() {
        return uid;
    }
    public void setuid(String str) {
        this.uid = str;
    }

    public String getWorkOutGroup() {
        return workOutGroup;
    }
    public void setWorkOutGroup(String str) {
        this.workOutGroup = str;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMuscle() {
        return muscle;
    }

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String excerciseToString(){
        String str = getName() +"   " + getType() + "   " + getMuscle() + "   " + getDifficulty() + "   " + getEquipment() + "   " + getInstructions();
        return str;
    }
}
