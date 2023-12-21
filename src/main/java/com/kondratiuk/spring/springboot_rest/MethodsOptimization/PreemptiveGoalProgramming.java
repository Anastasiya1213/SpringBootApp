package com.kondratiuk.spring.springboot_rest.MethodsOptimization;

import com.kondratiuk.spring.springboot_rest.entity.Candidates;
import com.kondratiuk.spring.springboot_rest.entity.Vacancies;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PreemptiveGoalProgramming {

    public double calculateDeviation(Candidates candidate, Vacancies idealVacancy) {
        double deviation = 0;
        for (int priorityLevel = 1; priorityLevel <= 9; priorityLevel++) {
            if (priorityLevel <= 3 && !isUniqueSolution(candidate, idealVacancy, priorityLevel)) {
                return Double.MAX_VALUE;
            }
            deviation += calculatePriorityDeviation(candidate, idealVacancy, priorityLevel);
        }
        return Math.round(Math.sqrt(deviation) * 100.0) / 100.0;
    }


    private double calculatePriorityDeviation(Candidates candidate, Vacancies idealVacancy, int priorityLevel) {
        double deviation;
        switch (priorityLevel) {
            case 1: deviation = Math.pow(idealVacancy.getRequired_experience() - candidate.getWork_experience(), 2); break;
            case 2: deviation = Math.pow(idealVacancy.getRequired_english_level() - candidate.getEnglish_level(), 2); break;
            case 3: deviation = Math.pow(idealVacancy.getRequired_education_level() - candidate.getLevel_of_technical_education(), 2); break;

            case 4: deviation = Math.pow(idealVacancy.getRequired_skills() - candidate.getNumber_of_skills(), 2); break;
            case 5: deviation = Math.pow(idealVacancy.getRequired_certificates() - candidate.getNumber_of_certificates(), 2); break;
            case 6: deviation = Math.pow(idealVacancy.getRequired_cooperation() - candidate.getCooperation(), 2); break;
            case 7: deviation = Math.pow(idealVacancy.getRequired_communication() - candidate.getCommunication(), 2); break;
            case 8: deviation = Math.pow(idealVacancy.getCultural_fit_importance() - candidate.getCultural_relevance(), 2); break;
            case 9: deviation = Math.pow(idealVacancy.getSalary_range() - candidate.getDesired_salary(), 2); break;

            default: deviation = 0;
        }
        return priorityLevel <= 3 ? deviation : deviation * 0.1; // Зменшення ваги для нижчих пріоритетів
    }

    private boolean isUniqueSolution(Candidates candidate, Vacancies idealVacancy, int priorityLevel) {
        switch (priorityLevel) {
            case 1:
                return candidate.getLevel_of_technical_education() >= idealVacancy.getRequired_education_level();
            case 2:
                return candidate.getWork_experience() >= idealVacancy.getRequired_experience();
            case 3:
                return candidate.getNumber_of_skills() >= idealVacancy.getRequired_skills();
            case 4:
                return candidate.getNumber_of_certificates() >= idealVacancy.getRequired_certificates();
            case 5:
                return candidate.getCooperation() >= idealVacancy.getRequired_cooperation();
            case 6:
                return candidate.getCommunication() >= idealVacancy.getRequired_communication();
            case 7:
                return candidate.getEnglish_level() >= idealVacancy.getRequired_english_level();
            case 8:
                return candidate.getCultural_relevance() >= idealVacancy.getCultural_fit_importance();
            case 9:
                return candidate.getDesired_salary() <= idealVacancy.getSalary_range();

            default:
                return false;
        }
    }


    public List<Candidates> findTopCandidatesPreemptive(List<Candidates> normalizedCandidates, Vacancies idealVacancy, int topN) {
        // Код для вибору найкращих кандидатів за методом Preemptive Goal Programming з урахуванням приоритетів
        return normalizedCandidates.stream()
                .sorted(Comparator.comparingDouble(candidate -> calculateDeviation(candidate, idealVacancy)))
                .limit(topN)
                .collect(Collectors.toList());
    }

}