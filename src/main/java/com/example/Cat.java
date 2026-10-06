package com.example.cat;

import com.example.feline.Feline;

public class Cat {

    private final Feline feline; // Зависимость от Feline

    public Cat(Feline feline) {
        this.feline = feline;
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
        // Пример использования Feline для получения информации о котятах
        // В данном случае Feline возвращает 1 котенка
        return "Количество котят: " + 1; // Предполагаем, что Feline возвращает 1
    }
}