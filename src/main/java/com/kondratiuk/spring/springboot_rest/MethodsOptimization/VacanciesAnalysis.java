package com.kondratiuk.spring.springboot_rest.MethodsOptimization;

import com.kondratiuk.spring.springboot_rest.dao.VacanciesDAO;
import com.kondratiuk.spring.springboot_rest.entity.Vacancies;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VacanciesAnalysis {

    @Autowired
    private VacanciesDAO vacanciesDAO;

    private Map<Integer, Vacancies> normalizedVacancies;

    public VacanciesAnalysis() {
        this.normalizedVacancies = new HashMap<>(); // Ініціалізація мапи
    }

    @PostConstruct
    private void initializeNormalizedVacancies() {
        List<Vacancies> allVacancies = vacanciesDAO.getAllVacancies();
        this.normalizedVacancies = calculateIdealCandidates(allVacancies);
    }

    public Vacancies selectIdealVacancy(int vacancyId) {
        Vacancies vacancy = this.normalizedVacancies.getOrDefault(vacancyId, null);
        return vacancy;
    }

    public Map<Integer, Vacancies> calculateIdealCandidates(List<Vacancies> vacancies){
        Map<Integer, Vacancies> normalizedVacancies = new HashMap<>();
        for(Vacancies vacancy : vacancies){
            normalizedVacancies.put(vacancy.getVacancy_id(), vacancy);
        }
        return normalizedVacancies;
    }

}