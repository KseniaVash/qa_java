package com.example.cat;

import com.example.feline.Feline;

public class Cat {
    private final String name;
    private final Feline feline;

    public Cat(String name, Feline feline) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя кота не может быть пустым");
        }
        if (feline == null) {
            throw new IllegalArgumentException("Feline не может быть null");
        }
        this.name = name;
        this.feline = feline;
    }

    public String getName() {
        return name;
    }

    public String getSound() {
        return feline.getSound();
    }


    public String eatMeat() {
        return "Есть над чем подумать (с) ";
    }

    public String getKittens(int numberOfKittens) {
        if (numberOfKittens == 0) {
            return "У вас нет котят";
        } else {
            return "Количество котят: " + numberOfKittens;
        }
    }

    public String getExpectedKittens() {

        return "Количество котят: " + 1;
    }
}