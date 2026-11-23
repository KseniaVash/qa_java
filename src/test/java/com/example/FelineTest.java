package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class FelineTest {

    private static final Logger logger = LoggerFactory.getLogger(FelineTest.class);
    private com.example.Feline maleWithMane;
    private com.example.Feline femaleWithoutMane;

    @BeforeEach
    void setUp() {
        logger.info("=== [SETUP] Инициализация объектов Feline для тестов ===");
        maleWithMane = new com.example.Feline("Самец", true, 5);
        femaleWithoutMane = new com.example.Feline("Самка", false, 3);
    }

    @Test
    @DisplayName("UT-FELINE-01: Проверка конструктора и геттеров")
    void constructor_ShouldInitializeFields() {
        logger.info("Запуск теста: Проверка конструктора и геттеров");
        assertAll(
                () -> assertEquals("Самец", maleWithMane.getSex(), "Пол самца не совпадает"),
                () -> assertTrue(maleWithMane.hasMane(), "У самца должна быть грива"),
                () -> assertEquals(5, maleWithMane.getAge(), "Возраст самца не совпадает")
        );
        logger.info("Тест UT-FELINE-01 успешно завершен");
    }

    @Test
    @DisplayName("UT-FELINE-02: Проверка звука")
    void getSound_ShouldReturnMiau() {
        logger.info("Запуск теста: Проверка звука");
        String sound = maleWithMane.getSound();
        assertEquals("Мяу", sound);
        logger.info("Feline.getSound() вернул: {}", sound);
        logger.info("Тест UT-FELINE-02 успешно завершен");
    }


}