package com.kondratiuk.spring.springboot_rest.service;

import com.kondratiuk.spring.springboot_rest.MethodsOptimization.AnalysisCoordinator;
import com.kondratiuk.spring.springboot_rest.MethodsOptimization.VacanciesAnalysis;
import com.kondratiuk.spring.springboot_rest.dao.CandidatesDAO;
import com.kondratiuk.spring.springboot_rest.entity.Candidates;
import com.kondratiuk.spring.springboot_rest.entity.Vacancies;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatesServiceImpl implements CandidatesService {

    private final AnalysisCoordinator analysisCoordinator;
    private final CandidatesDAO candidatesDAO;
    private final VacanciesAnalysis vacanciesAnalysis;

    @Autowired
    public CandidatesServiceImpl(AnalysisCoordinator analysisCoordinator,
                                 CandidatesDAO candidatesDAO,
                                 VacanciesAnalysis vacanciesAnalysis) {
        this.analysisCoordinator = analysisCoordinator;
        this.candidatesDAO = candidatesDAO;
        this.vacanciesAnalysis = vacanciesAnalysis;
    }

    @Override
    @Transactional
    public List<Candidates> getAllCandidates(){
        return candidatesDAO.getAllCandidates();
    }

    @Override
    @Transactional
    public AnalysisCoordinator.AnalysisResults performAnalysis(int vacancyId, String analysisMethod) {
        // Отримання всіх кандидатів
        List<Candidates> allCandidates = getAllCandidates();

        // Визначення ідеальної вакансії
        Vacancies idealVacancy = vacanciesAnalysis.selectIdealVacancy(vacancyId);
        if (idealVacancy == null) {
            throw new RuntimeException("Ideal vacancy not found for ID: " + vacancyId);
        }

        // Передача даних про кандидатів та ідеальну вакансію в AnalysisCoordinator
        return analysisCoordinator.performAnalysis(allCandidates, idealVacancy, analysisMethod);
    }

}
