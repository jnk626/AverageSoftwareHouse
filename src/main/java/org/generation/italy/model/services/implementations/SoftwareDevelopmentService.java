package org.generation.italy.model.services.implementations;

import org.generation.italy.model.Level;
import org.generation.italy.model.data.abstractions.DeveloperRepository;
import org.generation.italy.model.entities.Competence;
import org.generation.italy.model.entities.Developer;
import org.generation.italy.model.services.abstractions.AbstractSoftwareDevelopmentService;

import java.util.Map;

public class SoftwareDevelopmentService implements AbstractSoftwareDevelopmentService {
    private DeveloperRepository repo;
    public SoftwareDevelopmentService(DeveloperRepository repo) {
        this.repo = repo;
    }
    @Override
    public Map<Long, Iterable<String>> showAllDevelopers() {
        return null;
    }

    @Override
    public void addDeveloper() {

    }

    @Override
    public void fireDeveloper() {

    }

    //Mostrare i dati di tutti i Developer che hanno una Competenza che contiene una data stringa nel titolo e che ha un
    //certo livello (passo due argomenti, parte del nome della competenza e livello in quella competenza)
    @Override
    public Iterable<Developer> getDevsByGradeAndCompetence(String part, Level minLevel) {
        return null;
    }

    //Mostrare tutti i Developer con un numero di Competenze maggiore o uguale ad un intero passato in input
    @Override
    public Iterable<Developer> getDevsByCompetenceNum(int competenceNum) {
        return null;
    }

    //Variante della 4. in cui accetta in input anche un livello (almeno 3 Competenze e almeno al livello intermedio)
    @Override
    public Iterable<Developer> getDevsByCompetenceNumAndLevel(int competenceNum, Level minLevel) {
        return null;
    }

    @Override
    public Iterable<Competence> getAllCompetences() {
        return null;
    }

    @Override
    public boolean getCompetenceByExactName(String exactTitle) {
        return false;
    }

    @Override
    public double getAverageSalary() {
        return 0;
    }

    @Override
    public double getMaxSalary() {
        return 0;
    }

    //Deve verificare se il minimo salario dei Developer maschi è maggiore di quello dei Developer femmine
    //ed eventualmente licenziare tutte le Developer
    @Override
    public boolean isYourFirmSexist() {
        return false;
    }

    @Override
    public Iterable<Integer> getModesOfSeniority() {
        return null;
    }
}
