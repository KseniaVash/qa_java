package com.example.lion;

import com.example.feline.Feline;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Используем расширение Mockito для JUnit 5
@ExtendWith(MockitoExtension.class)
class LionTest {

    // @Mock создает мок-объект Feline
    @Mock
    Feline mockFeline;

    // @InjectMocks внедряет мок-объекты в Lion
    @InjectMocks
    Lion lion;

    // Тест на наличие гривы
    @Test
    void testLionHasMane_Male() {
        // Настраиваем мок: когда вызывается hasMane(), вернуть true
        when(mockFeline.hasMane()).thenReturn(true);
        // Настраиваем мок: когда вызывается getSex(), вернуть "Male"
        when(mockFeline.getSex()).thenReturn("Male");

        assertTrue(lion.hasMane(), "Лев должен иметь гриву.");
        assertEquals("Male", lion.getSex(), "Пол льва должен быть 'Male'.");
        // Проверяем, что методы мока были вызваны
        verify(mockFeline).hasMane();
        verify(mockFeline).getSex();
    }

    @Test
    void testLionHasMane_Female() {
        // Настраиваем мок для самки
        when(mockFeline.hasMane()).thenReturn(false);
        when(mockFeline.getSex()).thenReturn("Female");

        assertFalse(lion.hasMane(), "Львица не должна иметь гривы.");
        assertEquals("Female", lion.getSex(), "Пол львицы должен быть 'Female'.");
        verify(mockFeline).hasMane();
        verify(mockFeline).getSex();
    }

    // Параметризованный тест для проверки количества котят
    @ParameterizedTest
    @CsvSource({
            "true, 1",  // Если есть грива (Male), то 1 котенок
            "false, 0" // Если нет гривы (Female), то 0 котят
    })
    void testLionGetKittens(boolean hasMane, int expectedKittens) {
        // Настраиваем мок в зависимости от параметра
        when(mockFeline.hasMane()).thenReturn(hasMane);

        assertEquals(expectedKittens, lion.getKittens(), "Количество котят должно соответствовать наличию гривы.");
        // Проверяем, что метод hasMane() у Feline был вызван
        verify(mockFeline).hasMane();
    }

    // Параметризованный тест на пол
    @ParameterizedTest
    @ValueSource(strings = {"Male", "Female"})
    void testLionGetSex(String sex) {
        when(mockFeline.getSex()).thenReturn(sex);
        assertEquals(sex, lion.getSex(), "Пол должен быть корректно получен.");
        verify(mockFeline).getSex();
    }

    @Test
    void testLionEatMeat() {
        String felineMeatMessage = "Вкусная антилопа";
        // Настраиваем мок: метод eatMeat() у Feline возвращает определенное сообщение
        when(mockFeline.eatMeat()).thenReturn(felineMeatMessage);

        // Ожидаемый результат - комбинированное сообщение
        String expected = "Поздравляем, вы получили вкусное мясо — " + felineMeatMessage;
        assertEquals(expected, lion.eatMeat(), "Сообщение о еде льва должно быть комбинированным.");
        // Проверяем, что метод eatMeat() у Feline был вызван
        verify(mockFeline).eatMeat();
    }

    @Test
    void testLionConstructor_NullFeline() {
        // Тест на исключение при передаче null в конструктор
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Lion(null),
                "Должен быть выброшен IllegalArgumentException, если Feline равен null."
        );

        assertTrue(thrown.getMessage().contains("Feline cannot be null"));
    }
}