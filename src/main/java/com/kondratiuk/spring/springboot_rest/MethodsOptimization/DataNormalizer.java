package com.kondratiuk.spring.springboot_rest.MethodsOptimization;

import com.kondratiuk.spring.springboot_rest.entity.Candidates;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataNormalizer {
    public static double round(double value, int places) { // Метод для округлення значення
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public List<Candidates> normalizedCandidates(List<Candidates> candidates) {
        // Код для нормалізації кандидатів включає обчислення максимальних та мінімальних значень та нормалізацію кожного атрибуту знаходимо максимум і мінімум для кожного атрибуту
        double maxEducation = candidates.stream().mapToDouble(Candidates::getLevel_of_technical_education).max().orElse(10);
        double minEducation = candidates.stream().mapToDouble(Candidates::getLevel_of_technical_education).min().orElse(1);

        double maxExperience = candidates.stream().mapToDouble(Candidates::getWork_experience).max().orElse(10);
        double minExperience = candidates.stream().mapToDouble(Candidates::getWork_experience).min().orElse(1);

        double maxSkills = candidates.stream().mapToDouble(Candidates::getNumber_of_skills).max().orElse(10);
        double minSkills = candidates.stream().mapToDouble(Candidates::getNumber_of_skills).min().orElse(1);

        double maxCertificates = candidates.stream().mapToDouble(Candidates::getNumber_of_certificates).max().orElse(10);
        double minCertificates = candidates.stream().mapToDouble(Candidates::getNumber_of_certificates).min().orElse(1);

        double maxCooperation = candidates.stream().mapToDouble(Candidates::getCooperation).max().orElse(10);
        double minCooperation = candidates.stream().mapToDouble(Candidates::getCooperation).min().orElse(1);

        double maxCommunication = candidates.stream().mapToDouble(Candidates::getCommunication).max().orElse(10);
        double minCommunication = candidates.stream().mapToDouble(Candidates::getCommunication).min().orElse(1);

        double maxEnglish = candidates.stream().mapToDouble(Candidates::getEnglish_level).max().orElse(10);
        double minEnglish = candidates.stream().mapToDouble(Candidates::getEnglish_level).min().orElse(1);

        double maxCultural = candidates.stream().mapToDouble(Candidates::getCultural_relevance).max().orElse(10);
        double minCultural = candidates.stream().mapToDouble(Candidates::getCultural_relevance).min().orElse(1);

        double maxSalary = candidates.stream().mapToDouble(Candidates::getDesired_salary).max().orElse(10);
        double minSalary = candidates.stream().mapToDouble(Candidates::getDesired_salary).min().orElse(1);


        return candidates.stream().map(candidate -> {
            double normalizedEducation = normalize(candidate.getLevel_of_technical_education(), maxEducation, minEducation);
            double normalizedExperience = normalize(candidate.getWork_experience(), maxExperience, minExperience);
            double normalizedSkills = normalize(candidate.getNumber_of_skills(), maxSkills, minSkills);
            double normalizedCertificates = normalize(candidate.getNumber_of_certificates(), maxCertificates, minCertificates);
            double normalizedCooperation = normalize(candidate.getCooperation(), maxCooperation, minCooperation);
            double normalizedCommunication = normalize(candidate.getCommunication(), maxCommunication, minCommunication);
            double normalizedEnglish = normalize(candidate.getEnglish_level(), maxEnglish, minEnglish);
            double normalizedCultural = normalize(candidate.getCultural_relevance(), maxCultural, minCultural);
            double normalizedSalary = normalizeSalary(candidate.getDesired_salary(), maxSalary, minSalary);

            return new Candidates(
                    candidate.getId(),
                    candidate.getName(),
                    normalizedEducation,
                    normalizedExperience,
                    normalizedSkills,
                    normalizedCertificates,
                    normalizedCooperation,
                    normalizedCommunication,
                    normalizedEnglish,
                    normalizedCultural,
                    normalizedSalary
            );
        }).collect(Collectors.toList());
    }
    private double normalize(double value, double max, double min) {
        return round(((value - min) / (max - min)) * 9 + 1, 2);
    }
    private double normalizeSalary(double salary, double maxSalary, double minSalary) {
        // Нормалізація для зарплатні: найбільша бажана зарплатня - це найгірша( тобто 10 балів ), а найменшою - найкраща(тобто 1 бал)
        return round(((maxSalary - salary) / (maxSalary - minSalary)) * 9 + 1, 2);
    }
}
