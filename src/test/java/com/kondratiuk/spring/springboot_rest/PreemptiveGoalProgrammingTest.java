package com.kondratiuk.spring.springboot_rest;

import com.kondratiuk.spring.springboot_rest.MethodsOptimization.PreemptiveGoalProgramming;
import com.kondratiuk.spring.springboot_rest.entity.Candidates;
import com.kondratiuk.spring.springboot_rest.entity.Vacancies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class PreemptiveGoalProgrammingTest {

    private PreemptiveGoalProgramming preemptiveGoalProgramming;

    @BeforeEach
    public void setup() {
        preemptiveGoalProgramming = new PreemptiveGoalProgramming();
    }

    @Test
    public void testCalculateDeviation() {
        Candidates candidate = new Candidates();
        Vacancies idealVacancy = new Vacancies();

        double expectedDeviation = 0;
        double actualDeviation = preemptiveGoalProgramming.calculateDeviation(candidate, idealVacancy);

        assertEquals(expectedDeviation, actualDeviation, "Обчислення відхилення є некоректним");
    }

    @Test
    public void testFindTopCandidatesPreemptive() {
        List<Candidates> candidates = new ArrayList<>();
        Vacancies idealVacancy = new Vacancies();
        int topN = 3;

        List<Candidates> topCandidates = preemptiveGoalProgramming.findTopCandidatesPreemptive(candidates, idealVacancy, topN);
        assertTrue(topCandidates.size() <= topN, "Повернуто більше кандидатів, ніж очікувалося");
    }


}
