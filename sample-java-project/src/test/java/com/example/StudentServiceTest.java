package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe StudentService
 */
class StudentServiceTest {

    private StudentService service;
    private Student student1;
    private Student student2;
    private Student student3;

    @BeforeEach
    void setUp() {
        service = new StudentService();
        
        student1 = new Student(1L, "John", "Doe", "john@example.com", 20);
        student1.setGrade(15.0);
        
        student2 = new Student(2L, "Jane", "Doe", "jane@example.com", 22);
        student2.setGrade(12.5);
        
        student3 = new Student(3L, "Bob", "Smith", "bob@example.com", 17);
        student3.setGrade(8.0);
    }

    @Test
    @DisplayName("Test ajout d'un étudiant")
    void testAddStudent() {
        service.addStudent(student1);
        assertEquals(1, service.getTotalStudents());
    }

    @Test
    @DisplayName("Test ajout d'un étudiant null")
    void testAddNullStudent() {
        service.addStudent(null);
        assertEquals(0, service.getTotalStudents());
    }

    @Test
    @DisplayName("Test recherche par ID - trouvé")
    void testFindByIdFound() {
        service.addStudent(student1);
        service.addStudent(student2);
        
        Optional<Student> result = service.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("John", result.get().getFirstName());
    }

    @Test
    @DisplayName("Test recherche par ID - non trouvé")
    void testFindByIdNotFound() {
        service.addStudent(student1);
        
        Optional<Student> result = service.findById(999L);
        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Test recherche par nom de famille")
    void testFindByLastName() {
        service.addStudent(student1);
        service.addStudent(student2);
        service.addStudent(student3);
        
        List<Student> results = service.findByLastName("Doe");
        assertEquals(2, results.size());
    }

    @Test
    @DisplayName("Test récupération de tous les étudiants")
    void testGetAllStudents() {
        service.addStudent(student1);
        service.addStudent(student2);
        
        List<Student> all = service.getAllStudents();
        assertEquals(2, all.size());
    }

    @Test
    @DisplayName("Test suppression d'un étudiant")
    void testRemoveStudent() {
        service.addStudent(student1);
        service.addStudent(student2);
        
        boolean removed = service.removeStudent(1L);
        assertTrue(removed);
        assertEquals(1, service.getTotalStudents());
    }

    @Test
    @DisplayName("Test calcul moyenne des notes")
    void testCalculateAverageGrade() {
        service.addStudent(student1); // 15.0
        service.addStudent(student2); // 12.5
        
        double average = service.calculateAverageGrade();
        assertEquals(13.75, average, 0.01);
    }

    @Test
    @DisplayName("Test moyenne avec liste vide")
    void testCalculateAverageGradeEmpty() {
        double average = service.calculateAverageGrade();
        assertEquals(0.0, average);
    }

    @Test
    @DisplayName("Test comptage des étudiants qui ont réussi")
    void testCountPassedStudents() {
        service.addStudent(student1); // 15.0 - passed
        service.addStudent(student2); // 12.5 - passed
        service.addStudent(student3); // 8.0 - failed
        
        assertEquals(2, service.countPassedStudents());
    }

    @Test
    @DisplayName("Test comptage des étudiants majeurs")
    void testCountAdultStudents() {
        service.addStudent(student1); // 20 ans - adult
        service.addStudent(student2); // 22 ans - adult
        service.addStudent(student3); // 17 ans - minor
        
        assertEquals(2, service.countAdultStudents());
    }

    @Test
    @DisplayName("Test catégorie étudiant null")
    void testGetStudentCategoryNull() {
        assertEquals("UNKNOWN", service.getStudentCategory(null));
    }

    @Test
    @DisplayName("Test catégorie jeune excellent")
    void testGetStudentCategoryYoungExcellent() {
        student3.setGrade(18.0);
        assertEquals("YOUNG_EXCELLENT", service.getStudentCategory(student3));
    }

    @Test
    @DisplayName("Test catégorie adulte bon")
    void testGetStudentCategoryAdultGood() {
        assertEquals("ADULT_GOOD", service.getStudentCategory(student1));
    }

    @Test
    @DisplayName("Test vidage de la liste")
    void testClear() {
        service.addStudent(student1);
        service.addStudent(student2);
        service.clear();
        
        assertEquals(0, service.getTotalStudents());
    }
}
