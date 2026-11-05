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

    @Test
    @DisplayName("UT-FELINE-03: Проверка метода охоты")
    void eatMeat_ShouldReturnHuntingMessage() {
        logger.info("Запуск теста: Проверка метода охоты");
        String result = femaleWithoutMane.eatMeat();
        assertEquals("Накрыл(а) охотничий столик", result);
        logger.info("Feline.eatMeat() вернул: {}", result);
        logger.info("Тест UT-FELINE-03 успешно завершен");
    }

    @ParameterizedTest(name = "Параметризованный тест № 1: Пол={0}, Грива={1}, Возраст={2}")
    @CsvSource({
            "Самец, true, 2",
            "Самка, false, 10"
    })
    @DisplayName("UT-FELINE-PAR-01: Параметризованный конструктор")
    void constructor_Parametrized(String sex, boolean hasMane, int age) {
        logger.info("Запуск параметризованного теста с данными: {}, {}, {}", sex, hasMane, age);
        com.example.feline.Feline feline = new com.example.feline.Feline(sex, hasMane, age);
        assertEquals(sex, feline.getSex());
        assertEquals(hasMane, feline.hasMane());
        assertEquals(age, feline.getAge());
        logger.info("Параметризованный тест UT-FELINE-PAR-01 завершен успешно");
    }
}