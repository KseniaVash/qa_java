package com.example.feline;

public class Feline {

    private final String sex;
    private final boolean hasMane;
    private final int age;

    public Feline(String sex, boolean hasMane, int age) {
        this.sex = sex;
        this.hasMane = hasMane;
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public boolean hasMane() {
        return hasMane;
    }

    public int getAge() {
        return age;
    }

    public String eatMeat() {
        return "Накрыл(а) охотничий столик";
    }

    public String getSound() {
        return "Мяу";
    }
}