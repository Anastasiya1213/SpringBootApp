package com.kondratiuk.spring.springboot_rest.service;


import com.kondratiuk.spring.springboot_rest.MethodsOptimization.AnalysisCoordinator;
import com.kondratiuk.spring.springboot_rest.entity.Candidates;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CandidatesService {

    List<Candidates> getAllCandidates();

    AnalysisCoordinator.AnalysisResults performAnalysis(int vacancyId, String analysisMethod);
}
