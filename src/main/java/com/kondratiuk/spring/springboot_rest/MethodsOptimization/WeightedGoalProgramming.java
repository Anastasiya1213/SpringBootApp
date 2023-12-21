package com.kondratiuk.spring.springboot_rest.MethodsOptimization;

import com.kondratiuk.spring.springboot_rest.entity.Candidates;
import com.kondratiuk.spring.springboot_rest.entity.Vacancies;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeightedGoalProgramming {

    // Ваги, встановлені відповідно до пріоритетності атрибутів(повинні в сумі = 1)
    public static final double[] weightsForWeighted = {0.1, 0.15, 0.1, 0.1, 0.1, 0.1, 0.15, 0.1, 0.1};

    public static boolean validateWeights(double[] weightsForWeighted) {
        double sum = 0;
        for (double weight : weightsForWeighted) {
            sum += weight;
        }
        return Math.abs(sum - 1.0) < 0.00001; // де 0.00001 - це допустима похибка
    }


    public double calculateDistance(Candidates candidate, Vacancies idealVacancy, double[] weightsForWeighted) {
        double distance = 0;
        distance += weightsForWeighted[0] * Math.abs(idealVacancy.getRequired_education_level() - candidate.getLevel_of_technical_education());
        distance += weightsForWeighted[1] * Math.abs(idealVacancy.getRequired_experience() - candidate.getWork_experience());
        distance += weightsForWeighted[2] * Math.abs(idealVacancy.getRequired_skills() - candidate.getNumber_of_skills());
        distance += weightsForWeighted[3] * Math.abs(idealVacancy.getRequired_certificates() - candidate.getNumber_of_certificates());
        distance += weightsForWeighted[4] * Math.abs(idealVacancy.getRequired_cooperation() - candidate.getCooperation());
        distance += weightsForWeighted[5] * Math.abs(idealVacancy.getRequired_communication() - candidate.getCommunication());
        distance += weightsForWeighted[6] * Math.abs(idealVacancy.getRequired_english_level() - candidate.getEnglish_level());
        distance += weightsForWeighted[7] * Math.abs(idealVacancy.getCultural_fit_importance() - candidate.getCultural_relevance());
        distance += weightsForWeighted[8] * Math.abs(idealVacancy.getSalary_range() - candidate.getDesired_salary());

        return Math.round(distance * 100.0) / 100.0;
    }


    public List<Candidates> findTopCandidates(List<Candidates> normalizedCandidates, Vacancies idealVacancy, double[] weightsForWeighted, int topN) {
        if (!validateWeights(weightsForWeighted)) {
            throw new IllegalArgumentException("Сума вагових коефіцієнтів не дорівнює 1.");
        }
        // Код для вибору найкращих кандидатів за методом Weighted Goal Programming
        return normalizedCandidates.stream()
                .sorted(Comparator.comparingDouble(candidate -> calculateDistance(candidate, idealVacancy, weightsForWeighted)))
                .limit(topN)
                .collect(Collectors.toList());
    }

}
