package org.generation.italy.model.data.abstractions;

import org.generation.italy.model.Level;
import org.generation.italy.model.entities.Developer;

import java.util.List;
import java.util.Optional;

public interface DeveloperRepository {
    List<Developer> showAllDevelopers();
    void addDeveloper(Developer dev);
    List<Developer> getDevsByCompetenceAndLevel(String part, Level level);
    List<Developer> getDevsByCompetenceNum(Integer num);
    List<Developer> getDevsByCompetenceNumAndLevel(Integer num, Level level);
    Iterable<String> getAllCompetences();
    boolean getCompetenceByExactName(String exactTitle);
    double getAverageSalary();
    Optional<Double> getMaxSalary();
    List<Developer> isYourFirmSexist();
    int getModeOfSeniority();
}
