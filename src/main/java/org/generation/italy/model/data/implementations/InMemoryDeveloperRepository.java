package org.generation.italy.model.data.implementations;

import org.generation.italy.model.Level;
import org.generation.italy.model.Sex;
import org.generation.italy.model.data.abstractions.DeveloperRepository;
import org.generation.italy.model.entities.Competence;
import org.generation.italy.model.entities.Developer;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class InMemoryDeveloperRepository implements DeveloperRepository {
    private static Map<Long, Developer> dataSource;
    private static long nextId;

    //Mostrare un elenco di tutti i Developer presenti nel repo e per ognuno
    // mostrerà nome, cognome, titolo e numero di linguaggi che conosce
    @Override
    public List<Developer> showAllDevelopers() {
        return dataSource.values().stream().toList();
    }

    //Aggiungere un nuovo Developer
    // (glielo chiede in UI) con la possibilità di aggiungere opzionalmente delle Competenze
    @Override
    public void addDeveloper(Developer dev) {
        dev.setId(++nextId);
        dataSource.putIfAbsent(dev.getId(), dev);
    }

    public void fireDeveloper(Developer dev) {
        dataSource.remove(dev.getId());
    }


    public List<Developer> getDevsByCompetence(String part) {
        return dataSource.values().stream()
                .filter(d -> d.getCompetences().stream()
                        .anyMatch(c -> c.getName().equalsIgnoreCase(part)))
                .toList();
    }

    @Override
    public List<Developer> getDevsByCompetenceAndLevel(String part, Level level) {
        return getDevsByCompetence(part).stream()
                .filter(d -> d.getCompetences().stream()
                        .anyMatch(c -> c.getLevel().compareTo(level) >= 0))
                .toList();
    }

    @Override
    public List<Developer> getDevsByCompetenceNum(Integer num) {
        return dataSource.values().stream()
                .filter(d -> d.getCompetences().size() >= num)
                .toList();
    }

    @Override
    public List<Developer> getDevsByCompetenceNumAndLevel(Integer num, Level level) {
        return getDevsByCompetenceNum(num).stream()
                .filter(d -> d.getCompetences().stream()
                        .anyMatch(c -> c.getLevel().compareTo(level) >= 0))
                .toList();
    }

    //Mostrare una lista dei nomi di tutte le Competenze tra tutti i Developer
    @Override
    public Iterable<String> getAllCompetences() { // da rivedere col flatmap
        return dataSource.values().stream()
                                    .flatMap(d -> d.getCompetences().stream())
                                    .map(Competence::getName)
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
                / dataSource.size();
    }

    //Stampare il valore massimo tra gli stipendi dei Developer
    @Override
    public Optional<Double> getMaxSalary() {
        Optional<Double> max = dataSource.values().stream().map(Developer::getSalary).max(Double::compareTo);
        if (max.isPresent()) return max;
        return Optional.empty();
    }

    public Optional<Double> getMaleMinSalary() {
        Optional<Double> min = dataSource.values().stream()
                .filter(d -> d.getSex().equals(Sex.MALE))
                .map(Developer::getSalary)
                .min(Double::compareTo);
        if (min.isPresent()) return min;
        return Optional.empty();
    }

    public Optional<Double> getFemaleMaxSalary() {
        Optional<Double> max =  dataSource.values().stream()
                .filter(d -> d.getSex().equals(Sex.FEMALE))
                .map(Developer::getSalary)
                .max(Double::compareTo);
        if (max.isPresent()) return max;
        return Optional.empty();
    }

    @Override
    public List<Developer> isYourFirmSexist() {
        List<Developer> devsToFire = new ArrayList<>();
        if (getMaleMinSalary().isPresent() && getFemaleMaxSalary().isPresent()) {
            double maleMinSalary = getMaleMinSalary().get();
            double femaleMaxSalary = getFemaleMaxSalary().get();
            if (maleMinSalary < femaleMaxSalary) {
                devsToFire = dataSource.values().stream()
                        .filter(dev -> dev.getSex().equals(Sex.FEMALE))
                        .filter(femaleDev -> femaleDev.getSalary() > maleMinSalary)
                        .toList();
                for (Developer dev : devsToFire) fireDeveloper(dev);
            }
        }
        return devsToFire;
    }

    //Restituire la moda degli anni di lavoro dei miei Developer
    @Override
    public int getModeOfSeniority() {
        Optional<LocalDate> entry = dataSource.values().stream()
                .collect(Collectors.groupingBy(Developer::getRecruitmentYear, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey);
        if (entry.isPresent()) return entry.get().getYear();
        return 0;
    }
}
