package org.generation.italy.model.data.implementations;

import org.generation.italy.model.Level;
import org.generation.italy.model.Sex;
import org.generation.italy.model.data.abstractions.DeveloperRepository;
import org.generation.italy.model.entities.Competence;
import org.generation.italy.model.entities.Developer;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryDeveloperRepository implements DeveloperRepository {
    private Map<Long, Developer> dataSource;
    private static long nextId;

    //Mostrare un elenco di tutti i Developer presenti nel repo e per ognuno
    // mostrerà nome, cognome, titolo e numero di linguaggi che conosce
    @Override
    public List<Developer> showAllDevelopers() {
        return dataSource.values().stream().toList();
    }

    //Aggiungere un nuovo Developer con la possibilità di aggiungere opzionalmente delle Competenze
    @Override
    public void addDeveloper(Developer dev) {
        dev.setId(++nextId);
        dataSource.putIfAbsent(dev.getId(), dev);
    }

    public void fireDeveloper(Developer dev) {
        dataSource.remove(dev.getId());
    }


    public List<Map.Entry<Long, List<Competence>>> getDevsByCompetence(String part) {
        return dataSource.values().stream().collect(Collectors.toMap(Developer::getId, Developer::getCompetences))
                                            .entrySet()
                                            .stream()
                                            .filter(longListEntry -> longListEntry.getValue().stream()
                                                    .filter(e -> e.getName().contains(part))
                                                    .isParallel())
                                            .toList();
    }

    @Override
    public List<Long> getDevsByCompetenceAndLevel(String part, Level level) {
        return getDevsByCompetence(part).stream().filter(longListEntry -> longListEntry.getValue().stream()
                                                    .filter(e -> e.getLevel().compareTo(level) >= 0)
                                                    .isParallel())
                                            .map(Map.Entry::getKey)
                                            .toList();
    }

    @Override
    public List<Long> getDevsByCompetenceNum(Integer num) {
        return dataSource.values().stream().collect(Collectors.toMap(Developer::getId, Developer::getCompetences))
                                            .entrySet()
                                            .stream()
                                            .filter(entry -> entry.getValue().size() >= num)
                                            .map(Map.Entry::getKey)
                                            .toList();
    }

    @Override
    public List<Long> getDevsByCompetenceNumAndLevel(Integer num, Level level) {
        return dataSource.values().stream().collect(Collectors.toMap(Developer::getId, Developer::getCompetences))
                                            .entrySet()
                                            .stream()
                                            .filter(entry -> entry.getValue().size() >= num)
                                            .filter(entry -> entry.getValue().stream()
                                                    .filter(competence -> competence.getLevel().compareTo(level) >= 0)
                                                    .isParallel())
                                            .map(Map.Entry::getKey)
                                            .toList();
    }

    //Mostrare una lista dei nomi di tutte le Competenze tra tutti i Developer
    @Override
    public Iterable<String> getAllCompetences() {
        return dataSource.values().stream()
                .map(Developer::getCompetences)
                .map(list -> list.stream()
                             .map(Competence::getName)
                             .toString())
                .distinct()
                .toList();
    }

    //Ricerca una Competenza data in input con match esatto, restituendo un true se è conosciuta
    //e il livello minimo e massimo, altrimenti un false
    @Override
    public boolean getCompetenceByExactName(String exactTitle) {
        return false;
    }

    //Stampare il valor medio degli stipendi dei Developer
    @Override
    public double getAverageSalary() {
        return dataSource.values().stream().mapToDouble(Developer::getSalary).sum()
                / dataSource.values().stream().mapToDouble(Developer::getSalary).count();
    }

    //Stampare il valore massimo tra gli stipendi dei Developer
    @Override
    public Optional<Double> getMaxSalary() {
        return dataSource.values().stream().map(Developer::getSalary).max(Double::compareTo);
    }

    public Double getMaleMinSalary() {
        return dataSource.values().stream()
                .filter(d -> d.getSex().equals(Sex.MALE))
                .map(Developer::getSalary)
                .min(Double::compareTo)
                .get();
    }

    public Double getFemaleMaxSalary() {
        return dataSource.values().stream()
                .filter(d -> d.getSex().equals(Sex.FEMALE))
                .map(Developer::getSalary)
                .max(Double::compareTo)
                .get();
    }

    @Override
    public boolean isYourFirmSexist() {
        if (getMaleMinSalary() > getFemaleMaxSalary()) {
            return true;
        }
        List<Developer> idsToDelete = dataSource.values().stream()
                .filter(dev -> dev.getSex().equals(Sex.FEMALE))
                .filter(femaleDev -> femaleDev.getSalary() > getMaleMinSalary())
                .toList();
        for (Developer dev : idsToDelete) fireDeveloper(dev);
        return false;
    }

    //Restituire la moda degli anni di lavoro dei miei Developer
    @Override
    public int getModeOfSeniority() {
        return dataSource.values().stream()
                .collect(Collectors.groupingBy(Developer::getRecruitmentYear, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .get()
                .getYear();
    }
}
