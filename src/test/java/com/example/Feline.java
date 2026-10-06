package com.example.feline;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FelineTest {

    @Test
    void testFelineEatMeat() {
        Feline feline = new Feline("Female", false, 3);
        assertEquals("Накрыл(а) охотничий столик", feline.eatMeat(), "Метод eatMeat должен возвращать ожидаемую строку.");
    }

    @Test
    void testFelineGetSound() {
        Feline feline = new Feline("Male", true, 5);
        assertEquals("Мяу", feline.getSound(), "Метод getSound должен возвращать 'Мяу'.");
    }

    @Test
    void testFelineProperties() {
        Feline feline = new Feline("Female", false, 2);
        assertEquals("Female", feline.getSex(), "Пол должен быть 'Female'.");
        assertFalse(feline.hasMane(), "У самки не должно быть гривы.");
        assertEquals(2, feline.getAge(), "Возраст должен быть 2.");
    }

    @Test
    void testFelineHasMane() {
        Feline maleFeline = new Feline("Male", true, 6);
        assertTrue(maleFeline.hasMane(), "У самца должна быть грива.");

        Feline femaleFeline = new Feline("Female", false, 4);
        assertFalse(femaleFeline.hasMane(), "У самки не должно быть гривы.");
    }
}