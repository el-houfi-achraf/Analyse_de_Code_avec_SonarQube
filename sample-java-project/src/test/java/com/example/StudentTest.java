package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe Student
 */
class StudentTest {

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student(1L, "John", "Doe", "john.doe@example.com", 20);
        student.setGrade(15.5);
    }

    @Test
    @DisplayName("Test du constructeur et getters")
    void testConstructorAndGetters() {
        assertEquals(1L, student.getId());
        assertEquals("John", student.getFirstName());
        assertEquals("Doe", student.getLastName());
        assertEquals("john.doe@example.com", student.getEmail());
        assertEquals(20, student.getAge());
    }

    @Test
    @DisplayName("Test du nom complet")
    void testGetFullName() {
        assertEquals("John Doe", student.getFullName());
    }

    @Test
    @DisplayName("Test étudiant majeur")
    void testIsAdult() {
        assertTrue(student.isAdult());
        
        Student minorStudent = new Student(2L, "Jane", "Doe", "jane@example.com", 16);
        assertFalse(minorStudent.isAdult());
    }

    @Test
    @DisplayName("Test réussite examen")
    void testHasPassed() {
        student.setGrade(15.0);
        assertTrue(student.hasPassed());

        student.setGrade(8.0);
        assertFalse(student.hasPassed());

        student.setGrade(10.0);
        assertTrue(student.hasPassed());
    }

    @Test
    @DisplayName("Test mention Très Bien")
    void testMentionTresBien() {
        student.setGrade(18.0);
        assertEquals("Très Bien", student.getMention());
    }

    @Test
    @DisplayName("Test mention Bien")
    void testMentionBien() {
        student.setGrade(14.5);
        assertEquals("Bien", student.getMention());
    }

    @Test
    @DisplayName("Test mention Assez Bien")
    void testMentionAssezBien() {
        student.setGrade(12.5);
        assertEquals("Assez Bien", student.getMention());
    }

    @Test
    @DisplayName("Test mention Passable")
    void testMentionPassable() {
        student.setGrade(10.5);
        assertEquals("Passable", student.getMention());
    }

    @Test
    @DisplayName("Test mention Ajourné")
    void testMentionAjourne() {
        student.setGrade(8.0);
        assertEquals("Ajourné", student.getMention());
    }

    @Test
    @DisplayName("Test validation email valide")
    void testValidEmail() {
        assertTrue(student.isValidEmail());
    }

    @Test
    @DisplayName("Test validation email invalide")
    void testInvalidEmail() {
        student.setEmail("invalid-email");
        assertFalse(student.isValidEmail());
    }

    @Test
    @DisplayName("Test toString")
    void testToString() {
        String result = student.toString();
        assertTrue(result.contains("John"));
        assertTrue(result.contains("Doe"));
    }

    @Test
    @DisplayName("Test setters")
    void testSetters() {
        student.setFirstName("Jane");
        student.setLastName("Smith");
        student.setEmail("jane.smith@example.com");
        student.setAge(25);
        student.setGrade(17.0);

        assertEquals("Jane", student.getFirstName());
        assertEquals("Smith", student.getLastName());
        assertEquals("jane.smith@example.com", student.getEmail());
        assertEquals(25, student.getAge());
        assertEquals(17.0, student.getGrade());
    }

    @Test
    @DisplayName("Test formatted info")
    void testGetFormattedInfo() {
        String info = student.getFormattedInfo();
        assertTrue(info.contains("ID:"));
        assertTrue(info.contains("Nom:"));
        assertTrue(info.contains("Email:"));
    }
}
