package org.generation.italy.model.data.abstractions;

import org.generation.italy.model.Level;
import org.generation.italy.model.entities.Developer;

import java.util.List;
import java.util.Optional;

public interface DeveloperRepository {
    List<Developer> showAllDevelopers();
    void addDeveloper(Developer dev);
    List<Long> getDevsByCompetenceAndLevel(String part, Level level);
    List<Long> getDevsByCompetenceNum(Integer num);
    List<Long> getDevsByCompetenceNumAndLevel(Integer num, Level level);
    Iterable<String> getAllCompetences();
    boolean getCompetenceByExactName(String exactTitle);
    double getAverageSalary();
    Optional<Double> getMaxSalary();
    boolean isYourFirmSexist();
    int getModeOfSeniority();
}
