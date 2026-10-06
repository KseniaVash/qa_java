package com.example.lion;

import com.example.feline.Feline;

public class Lion {

    private final Feline feline;
    private final String sex;

    public Lion(Feline feline, String sex) {
        if (feline == null) {
            throw new IllegalArgumentException("Feline cannot be null");
        }
        if (sex == null || sex.isBlank()) {
            throw new IllegalArgumentException("Sex cannot be null or blank");
        }
        this.feline = feline;
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public boolean hasMane() {

        return "Самец".equals(sex);
    }

    public int getKittens() {

        return hasMane() ? 1 : 0;
    }

    public String getFood() throws Exception {
        if ("Самец".equals(sex)) {

            return feline.eatMeat();
        } else if ("Самка".equals(sex)) {

            return feline.eatMeat();
        } else {
            throw new IllegalArgumentException("Неизвестный пол: " + sex);
        }
    }

    public String eatMeat() {
        return "Поздравляем, вы получили вкусное мясо — " + feline.eatMeat();
    }
}