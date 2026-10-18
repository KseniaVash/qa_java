package com.example.feline;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class FelineTest {

    private static final Logger logger = LoggerFactory.getLogger(FelineTest.class);
    private com.example.feline.Feline maleWithMane;
    private com.example.feline.Feline femaleWithoutMane;

    @BeforeEach
    void setUp() {
        logger.info("=== [SETUP] Инициализация объектов Feline для тестов ===");
        maleWithMane = new com.example.feline.Feline("Самец", true, 5);
        femaleWithoutMane = new com.example.feline.Feline("Самка", false, 3);
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