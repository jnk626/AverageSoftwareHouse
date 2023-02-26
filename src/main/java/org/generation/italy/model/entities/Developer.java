package org.generation.italy.model.entities;

import org.generation.italy.model.Sex;

import java.time.LocalDate;
import java.util.List;

public class Developer {
    private long id;
    private Sex sex;
    private String name, surname;
    private LocalDate recruitmentYear;
    private double salary;
    private String grade;
    private List<Competence> competences;

    public Developer() {
    }

    public Developer(Sex sex, String name, String surname, LocalDate recruitmentYear, double salary,
                     String grade, List<Competence> competences) {
        //this.id = id;
        this.sex = sex;
        this.name = name;
        this.surname = surname;
        this.recruitmentYear = recruitmentYear;
        this.salary = salary;
        this.grade = grade;
        this.competences = competences;
    }

    public Developer(Sex sex, String name, String surname, LocalDate recruitmentYear, double salary,
                     String grade) {
        //this.id = id;
        this.sex = sex;
        this.name = name;
        this.surname = surname;
        this.recruitmentYear = recruitmentYear;
        this.salary = salary;
        this.grade = grade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getRecruitmentYear() {
        return recruitmentYear;
    }

    public void setRecruitmentYear(LocalDate recruitmentYear) {
        this.recruitmentYear = recruitmentYear;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public List<Competence> getCompetences() {
        return competences;
    }

    public void setCompetences(List<Competence> competences) {
        this.competences = competences;
    }
}
