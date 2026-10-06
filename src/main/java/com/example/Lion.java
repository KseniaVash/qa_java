package com.example.lion;

import com.example.feline.Feline; // Импортируем Feline

public class Lion {

    private final Feline feline; // Зависимость инжектируется через конструктор

    // Инъекция зависимости через конструктор
    public Lion(Feline feline) {
        // Проверка на null для безопасности
        if (feline == null) {
            throw new IllegalArgumentException("Feline cannot be null");
        }
        this.feline = feline;
    }

    public boolean hasMane() {
        return feline.hasMane();
    }

    public int getKittens() {
        // Предполагаем, что Feline знает, сколько котят.
        // В реальном приложении Feline мог бы иметь метод getKittensCount()
        // Здесь для примера используем 1, если есть грива, иначе 0
        return feline.hasMane() ? 1 : 0;
    }

    public String getSex() {
        return feline.getSex();
    }

    public String eatMeat() {
        // Lion будет использовать eatMeat() от Feline, но может добавить свою логику
        return "Поздравляем, вы получили вкусное мясо — " + feline.eatMeat();
    }
}