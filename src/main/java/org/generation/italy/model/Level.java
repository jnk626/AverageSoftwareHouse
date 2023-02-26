package org.generation.italy.model;

public enum Level {
    BASE(0), INTERMEDIATE(1), ADVANCED(2), GURU(3), GOD(4);
    private int grade;
    private Level(int grade){
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }

    /* public boolean greaterThan(Level other) {
        return this.grade > other;
    } */
}
