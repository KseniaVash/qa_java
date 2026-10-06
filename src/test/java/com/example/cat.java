package com.example.cat;

import com.example.feline.Feline;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CatTest {

    // Мок зависимости Feline
    Feline mockFeline = mock(Feline.class);
    Cat cat = new Cat(mockFeline); // Инъекция мока

    @Test
    void testCatGetSound() {
        // Настраиваем поведение мока
        when(mockFeline.getSound()).thenReturn("Мяу-мяу");
        assertEquals("Мяу-мяу", cat.getSound(), "Звук кота должен быть 'Мяу-мяу'.");
        // Проверяем, что метод getSound() у Feline был вызван
        verify(mockFeline).getSound();
    }

    @Test
    void testCatEatMeat() {
        assertEquals("Есть над чем подумать (с) ", cat.eatMeat(), "Сообщение о еде должно быть верным.");
        // Здесь мы не вызываем eatMeat() у мока Feline, так как Cat добавляет свою логику
    }

    // Параметризованный тест для getKittens
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 3, 5}) // Различные количества котят
    void testCatGetKittens(int numberOfKittens) {
        String expectedMessage = (numberOfKittens == 0) ? "У вас нет котят" : "Количество котят: " + numberOfKittens;
        assertEquals(expectedMessage, cat.getKittens(numberOfKittens), "Сообщение о количестве котят должно быть корректным.");
    }

    // Параметризованный тест с использованием CsvSource
    @ParameterizedTest
    @CsvSource({
            "0, У вас нет котят",
            "1, Количество котят: 1",
            "3, Количество котят: 3"
    })
    void testCatGetKittensWithCsv(int inputKittens, String expectedOutput) {
        assertEquals(expectedOutput, cat.getKittens(inputKittens));
    }

    @Test
    void testCatGetExpectedKittens() {
        // Здесь мы ожидаем, что Cat вернет "Количество котят: 1",
        // так как это захардкожено в методе getExpectedKittens()
        // В идеальном сценарии, getExpectedKittens() должен был бы использовать Feline.
        assertEquals("Количество котят: 1", cat.getExpectedKittens());
    }
}