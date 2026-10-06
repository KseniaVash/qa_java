package com.example.lion;

import com.example.feline.Feline;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class LionTest {

    private static final Logger logger = LoggerFactory.getLogger(LionTest.class);

    private Feline mockFeline;
    private com.example.lion.Lion lion;

    @BeforeEach
    void setUp() {
        logger.info("=== [SETUP] Инициализация Lion для каждого теста ===");
        mockFeline = Mockito.mock(Feline.class);
        lion = new com.example.lion.Lion(mockFeline, "Самец");
    }

    @Test
    @DisplayName("UT-LION-01: Логика питания для самца")
    void testLionGetFood_Male() throws Exception {
        logger.info("Запуск теста: Логика питания для самца");


        when(mockFeline.eatMeat()).thenReturn("Поздравляем, вы получили вкусное мясо — Свежая антилопа");

        String food = lion.getFood();
        assertEquals("Поздравляем, вы получили вкусное мясо — Свежая антилопа", food);

        verify(mockFeline).eatMeat();
        logger.info("Проверка verify(mockFeline.eatMeat()) пройдена");
        logger.info("Тест UT-LION-01 успешно завершен");
    }

    @Test
    @DisplayName("UT-LION-02: Логика питания для самки")
    void testLionGetFood_Female() throws Exception {
        logger.info("Запуск теста: Логика питания для самки");

        lion = new com.example.lion.Lion(mockFeline, "Самка");
        when(mockFeline.eatMeat()).thenReturn("Поздравляем, вы получили вкусное мясо — Сочная зебра");

        String food = lion.getFood();
        assertEquals("Поздравляем, вы получили вкусное мясо — Сочная зебра", food);

        verify(mockFeline).eatMeat();
        logger.info("Тест UT-LION-02 успешно завершен");
    }

    @ParameterizedTest
    @CsvSource({"Самец", "Самка"})
    @DisplayName("UT-LION-PAR-01: Параметризованная еда (через пол)")
    void testLionGetFood_ParametrizedBySex(String sex) throws Exception {
        logger.info("Запуск параметризованного теста по полу: {}", sex);

        lion = new com.example.lion.Lion(mockFeline, sex);

        when(mockFeline.eatMeat()).thenReturn("Поздравляем, вы получили вкусное мясо — Мясо добычи");

        String food = lion.getFood();
        assertEquals("Поздравляем, вы получили вкусное мясо — Мясо добычи", food);
        verify(mockFeline).eatMeat();
        logger.info("Тест UT-LION-PAR-01 успешно завершен");
    }

    @Test
    @DisplayName("UT-LION-03: Проверка количества котят через hasMane")
    void testLionGetKittens() {
        logger.info("Запуск теста: Проверка количества котят");

        lion = new com.example.lion.Lion(mockFeline, "Самец");
        when(mockFeline.hasMane()).thenReturn(true);
        assertEquals(1, lion.getKittens());

        lion = new com.example.lion.Lion(mockFeline, "Самка");
        when(mockFeline.hasMane()).thenReturn(false);
        assertEquals(0, lion.getKittens());

        logger.info("Тест UT-LION-03 успешно завершен");
    }

    @Test
    @DisplayName("UT-LION-04: Проверка инъекции зависимостей (null)")
    void testLionConstructor_NullFeline() {
        logger.info("Запуск теста: Проверка инъекции зависимостей (null)");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new com.example.lion.Lion(null, "Самец"));
        assertTrue(ex.getMessage().contains("Feline cannot be null"));
        logger.info("Тест UT-LION-04 успешно завершен");
    }

    @Test
    @DisplayName("UT-LION-05: Проверка неизвестного пола")
    void testLionGetFood_UnknownSex() throws Exception {
        logger.info("Запуск теста: Проверка неизвестного пола");
        lion = new com.example.lion.Lion(mockFeline, "Неизвестно");
        Exception ex = assertThrows(IllegalArgumentException.class, lion::getFood);
        assertTrue(ex.getMessage().contains("Неизвестный пол"));
        logger.info("Тест UT-LION-05 успешно завершен");
    }
}