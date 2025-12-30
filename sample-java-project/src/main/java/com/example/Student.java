package com.example;

/**
 * Classe représentant un étudiant
 * Cette classe contient volontairement quelques "code smells" 
 * pour démontrer l'analyse SonarQube
 */
public class Student {
    
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private double grade;

    // Constructeur par défaut
    public Student() {
    }

    // Constructeur avec paramètres
    public Student(Long id, String firstName, String lastName, String email, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    /**
     * Retourne le nom complet de l'étudiant
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Vérifie si l'étudiant est majeur
     */
    public boolean isAdult() {
        return age >= 18;
    }

    /**
     * Vérifie si l'étudiant a réussi (note >= 10)
     */
    public boolean hasPassed() {
        return grade >= 10.0;
    }

    /**
     * Calcule la mention selon la note
     * Code smell intentionnel : méthode complexe avec magic numbers
     */
    public String getMention() {
        if (grade >= 16) {
            return "Très Bien";
        } else if (grade >= 14) {
            return "Bien";
        } else if (grade >= 12) {
            return "Assez Bien";
        } else if (grade >= 10) {
            return "Passable";
        } else {
            return "Ajourné";
        }
    }

    /**
     * Valide l'email - exemple de code avec potentiel bug
     */
    public boolean isValidEmail() {
        // Bug potentiel : ne vérifie pas si email est null
        return email.contains("@") && email.contains(".");
    }

    /**
     * Méthode avec duplication intentionnelle (code smell)
     */
    public String getFormattedInfo() {
        String info = "";
        info = info + "ID: " + id + "\n";
        info = info + "Nom: " + firstName + " " + lastName + "\n";
        info = info + "Email: " + email + "\n";
        info = info + "Age: " + age + "\n";
        info = info + "Note: " + grade + "\n";
        return info;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", grade=" + grade +
                '}';
    }
}
