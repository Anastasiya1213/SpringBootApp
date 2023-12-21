package com.kondratiuk.spring.springboot_rest.MethodsOptimization;

import ch.qos.logback.classic.Logger;
import com.kondratiuk.spring.springboot_rest.entity.Candidates;
import com.kondratiuk.spring.springboot_rest.entity.Vacancies;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalysisCoordinator {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(AnalysisCoordinator.class);

    private final WeightedGoalProgramming weightedGoalProgramming;
    private final DataNormalizer dataNormalizer;
    private final VacanciesAnalysis vacanciesAnalysis;

    @Autowired
    public AnalysisCoordinator(WeightedGoalProgramming weightedGoalProgramming,
                               DataNormalizer dataNormalizer,
                               VacanciesAnalysis vacanciesAnalysis) {
        this.weightedGoalProgramming = weightedGoalProgramming;
        this.dataNormalizer = dataNormalizer;
        this.vacanciesAnalysis = vacanciesAnalysis;
    }

    public class AnalysisResults {
        private List<Candidates> normalizedCandidates;
        private Map<Integer, Double> distances;
        private Map<Integer, Double> deviations;
        private Vacancies idealVacancy;
        private List<Candidates> topCandidates;
        private double[] weightsForWeighted;

        public Map<Integer, Double> getDeviations() {
            return deviations;
        }

        public void setDeviations(Map<Integer, Double> deviations) {
            this.deviations = deviations;
        }

        public List<Candidates> getNormalizedCandidates() {
            return normalizedCandidates;
        }

        public void setNormalizedCandidates(List<Candidates> normalizedCandidates) {
            this.normalizedCandidates = normalizedCandidates;
        }

        public Map<Integer, Double> getDistances() {
            return distances;
        }

        public void setDistances(Map<Integer, Double> distances) {
            this.distances = distances;
        }

        public Vacancies getIdealVacancy() {
            return idealVacancy;
        }

        public void setIdealVacancy(Vacancies idealVacancy) {
            this.idealVacancy = idealVacancy;
        }

        public List<Candidates> getTopCandidates() {
            return topCandidates;
        }

        public void setTopCandidates(List<Candidates> topCandidates) {
            this.topCandidates = topCandidates;
        }

        public double[] getWeightsForWeighted() {
            return weightsForWeighted;
        }

        public void setWeightsForWeighted(double[] weightsForWeighted) {
            this.weightsForWeighted = weightsForWeighted;
        }
    }

    private AnalysisResults performWeightedAnalysis(List<Candidates> normalizedCandidates, Vacancies idealVacancy) {
        WeightedGoalProgramming weightedGoalProgramming = new WeightedGoalProgramming();
        List<Candidates> topCandidates = weightedGoalProgramming.findTopCandidates(normalizedCandidates, idealVacancy, WeightedGoalProgramming.weightsForWeighted, 3);

        Map<Integer, Double> candidateDistances = new HashMap<>();
        for (Candidates candidate : normalizedCandidates) {
            double distance = weightedGoalProgramming.calculateDistance(candidate, idealVacancy, WeightedGoalProgramming.weightsForWeighted);
            candidateDistances.put(candidate.getId(), distance);
        }

        AnalysisResults results = new AnalysisResults();
        results.setNormalizedCandidates(normalizedCandidates);
        results.setTopCandidates(topCandidates);
        results.setDistances(candidateDistances);
        results.setWeightsForWeighted(WeightedGoalProgramming.weightsForWeighted);
        results.setIdealVacancy(idealVacancy);

        return results;
    }

    public AnalysisResults performPreemptiveAnalysis(List<Candidates> normalizedCandidates, Vacancies idealVacancy) {
        PreemptiveGoalProgramming preemptiveGoalProgramming = new PreemptiveGoalProgramming();
        List<Candidates> topCandidates = preemptiveGoalProgramming.findTopCandidatesPreemptive(normalizedCandidates, idealVacancy, 3);

        Map<Integer, Double> calculateDeviations = new HashMap<>();
        for (Candidates candidate : normalizedCandidates) {
            double deviation = preemptiveGoalProgramming.calculateDeviation(candidate, idealVacancy);
            calculateDeviations.put(candidate.getId(), deviation);
        }

        AnalysisResults results = new AnalysisResults();
        results.setNormalizedCandidates(normalizedCandidates);
        results.setTopCandidates(topCandidates);
        results.setDeviations(calculateDeviations);
        results.setIdealVacancy(idealVacancy);

        return results;

    }
    public AnalysisResults performAnalysis(List<Candidates> allCandidates, Vacancies idealVacancy, String analysisMethod) {
        logger.info("Performing " + analysisMethod + " analysis for Vacancy ID: " +
                (idealVacancy != null ? idealVacancy.getVacancy_id() : "null"));

        List<Candidates> normalizedCandidates = dataNormalizer.normalizedCandidates(allCandidates);

        if ("weighted".equals(analysisMethod)) {
            return performWeightedAnalysis(normalizedCandidates, idealVacancy);
        } else if ("preemptive".equals(analysisMethod)) {
            return performPreemptiveAnalysis(normalizedCandidates, idealVacancy);
        } else {
            throw new IllegalArgumentException("Unknown analysis method: " + analysisMethod);
        }

    }
}