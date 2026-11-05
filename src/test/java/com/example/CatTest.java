package com.example.cat;

import com.example.feline.Feline;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CatTest {

    private static final Logger logger = LoggerFactory.getLogger(CatTest.class);
    private Feline mockFeline;
    private com.example.cat.Cat cat;

    @BeforeEach
    void setUp() {
        logger.info("=== [SETUP] Создание мока Feline и инъекция в Cat ===");
        mockFeline = Mockito.mock(Feline.class);
        cat = new com.example.cat.Cat("Барсик", mockFeline);
    }

    @Test
    @DisplayName("UT-CAT-01: Проверка делегирования звука")
    void testCatGetSound() {
        logger.info("Запуск теста: Проверка делегирования звука");
        when(mockFeline.getSound()).thenReturn("Мяу-мяу");

        String sound = cat.getSound();
        assertEquals("Мяу-мяу", sound);

        verify(mockFeline, times(1)).getSound();
        logger.info("Проверка verify(mockFeline.getSound()) пройдена");
        logger.info("Тест UT-CAT-01 успешно завершен");
    }

    @Test
    @DisplayName("UT-CAT-02: Проверка собственной логики eatMeat")
    void testCatEatMeat() {
        logger.info("Запуск теста: Проверка собственной логики eatMeat");
        String result = cat.eatMeat();
        assertEquals("Есть над чем подумать (с) ", result);
        verifyNoInteractions(mockFeline);
        logger.info("Тест UT-CAT-02 успешно завершен");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 3})
    @DisplayName("UT-CAT-PAR-01: Сообщение о количестве котят")
    void testCatGetKittens(int numberOfKittens) {
        logger.info("Запуск теста: Сообщение о {} котятах", numberOfKittens);
        String expected = (numberOfKittens == 0) ? "У вас нет котят" : "Количество котят: " + numberOfKittens;
        assertEquals(expected, cat.getKittens(numberOfKittens));
        logger.info("Тест UT-CAT-PAR-01 успешно завершен");
    }

    @ParameterizedTest
    @CsvSource({"0, У вас нет котят", "1, Количество котят: 1"})
    @DisplayName("UT-CAT-PAR-02: CsvSource для котят")
    void testCatGetKittensWithCsv(int input, String expected) {
        logger.info("Запуск теста CsvSource: вход {}, ожидание {}", input, expected);
        assertEquals(expected, cat.getKittens(input));
        logger.info("Тест UT-CAT-PAR-02 успешно завершен");
    }
}