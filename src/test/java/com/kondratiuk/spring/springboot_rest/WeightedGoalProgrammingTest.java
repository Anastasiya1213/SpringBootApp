package com.kondratiuk.spring.springboot_rest;

import com.kondratiuk.spring.springboot_rest.MethodsOptimization.WeightedGoalProgramming;
import com.kondratiuk.spring.springboot_rest.entity.Candidates;
import com.kondratiuk.spring.springboot_rest.entity.Vacancies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class WeightedGoalProgrammingTest {

    private WeightedGoalProgramming weightedGoalProgramming;

    @BeforeEach
    public void setup() {
        weightedGoalProgramming = new WeightedGoalProgramming();
    }


    @Test
    public void testCalculateDistance() {
        Candidates candidate = new Candidates();
        Vacancies idealVacancy = new Vacancies();
        double[] weights = new double[]{0.1, 0.15, 0.1, 0.1, 0.1, 0.1, 0.15, 0.1, 0.1};

        double expectedDistance = 0.0;
        double actualDistance = weightedGoalProgramming.calculateDistance(candidate, idealVacancy, weights);

        assertEquals(expectedDistance, actualDistance, "Розрахунок відстані не коректний");
    }

    @Test
    public void testFindTopCandidates() {
        List<Candidates> candidates = new ArrayList<>();
        Vacancies idealVacancy = new Vacancies();
        double[] weights = new double[]{0.1, 0.15, 0.1, 0.1, 0.1, 0.1, 0.15, 0.1, 0.1};
        int topN = 3;

        assertDoesNotThrow(() -> {
            List<Candidates> topCandidates = weightedGoalProgramming.findTopCandidates(candidates, idealVacancy, weights, topN);
            assertTrue(topCandidates.size() <= topN, "Повернулося більше кандидатів, ніж очікувалося");
        });
    }

}
