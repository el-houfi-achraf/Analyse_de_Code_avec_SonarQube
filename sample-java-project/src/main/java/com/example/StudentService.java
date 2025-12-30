package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service de gestion des étudiants
 * Contient volontairement quelques problèmes de code pour démontrer SonarQube
 */
public class StudentService {

    private List<Student> students;

    public StudentService() {
        this.students = new ArrayList<>();
    }

    /**
     * Ajoute un étudiant à la liste
     */
    public void addStudent(Student student) {
        if (student != null) {
            students.add(student);
        }
    }

    /**
     * Recherche un étudiant par ID
     */
    public Optional<Student> findById(Long id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return Optional.of(student);
            }
        }
        return Optional.empty();
    }

    /**
     * Recherche des étudiants par nom
     */
    public List<Student> findByLastName(String lastName) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getLastName().equalsIgnoreCase(lastName)) {
                result.add(student);
            }
        }
        return result;
    }

    /**
     * Retourne tous les étudiants
     */
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    /**
     * Supprime un étudiant par ID
     */
    public boolean removeStudent(Long id) {
        return students.removeIf(s -> s.getId().equals(id));
    }

    /**
     * Calcule la moyenne des notes
     * Code smell : variable inutilisée
     */
    public double calculateAverageGrade() {
        if (students.isEmpty()) {
            return 0.0;
        }
        
        int unusedVariable = 0; // Code smell intentionnel
        double sum = 0.0;
        
        for (Student student : students) {
            sum += student.getGrade();
        }
        
        return sum / students.size();
    }

    /**
     * Compte les étudiants qui ont réussi
     */
    public int countPassedStudents() {
        int count = 0;
        for (Student student : students) {
            if (student.hasPassed()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Compte les étudiants majeurs
     */
    public int countAdultStudents() {
        int count = 0;
        for (Student student : students) {
            if (student.isAdult()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Méthode avec complexité cyclomatique élevée (code smell)
     */
    public String getStudentCategory(Student student) {
        if (student == null) {
            return "UNKNOWN";
        }
        
        String category = "";
        
        if (student.getAge() < 18) {
            if (student.getGrade() >= 16) {
                category = "YOUNG_EXCELLENT";
            } else if (student.getGrade() >= 14) {
                category = "YOUNG_GOOD";
            } else if (student.getGrade() >= 10) {
                category = "YOUNG_AVERAGE";
            } else {
                category = "YOUNG_FAILING";
            }
        } else if (student.getAge() <= 25) {
            if (student.getGrade() >= 16) {
                category = "ADULT_EXCELLENT";
            } else if (student.getGrade() >= 14) {
                category = "ADULT_GOOD";
            } else if (student.getGrade() >= 10) {
                category = "ADULT_AVERAGE";
            } else {
                category = "ADULT_FAILING";
            }
        } else {
            if (student.getGrade() >= 16) {
                category = "SENIOR_EXCELLENT";
            } else if (student.getGrade() >= 14) {
                category = "SENIOR_GOOD";
            } else if (student.getGrade() >= 10) {
                category = "SENIOR_AVERAGE";
            } else {
                category = "SENIOR_FAILING";
            }
        }
        
        return category;
    }

    /**
     * Retourne le nombre total d'étudiants
     */
    public int getTotalStudents() {
        return students.size();
    }

    /**
     * Vide la liste des étudiants
     */
    public void clear() {
        students.clear();
    }
}
