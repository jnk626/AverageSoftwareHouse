package org.generation.italy.model.services.abstractions;

import org.generation.italy.model.Level;
import org.generation.italy.model.entities.Competence;
import org.generation.italy.model.entities.Developer;

import java.util.Map;

public interface AbstractSoftwareDevelopmentService {
    Map<Long, Iterable<String>> showAllDevelopers();
    void addDeveloper();
    void fireDeveloper();
    Iterable<Developer> getDevsByGradeAndCompetence(String part, Level minLevel);
    Iterable<Developer> getDevsByCompetenceNum(int competenceNum);
    Iterable<Developer> getDevsByCompetenceNumAndLevel(int competenceNum, Level minLevel);
    Iterable<Competence> getAllCompetences();
    boolean getCompetenceByExactName(String exactTitle);
    double getAverageSalary();
    double getMaxSalary();
    boolean isYourFirmSexist();
    Iterable<Integer> getModesOfSeniority();
}
