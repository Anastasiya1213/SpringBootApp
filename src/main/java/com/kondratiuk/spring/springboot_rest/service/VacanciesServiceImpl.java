package com.kondratiuk.spring.springboot_rest.service;

import com.kondratiuk.spring.springboot_rest.repository.VacanciesRepository;
import com.kondratiuk.spring.springboot_rest.entity.Vacancies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacanciesServiceImpl implements VacanciesService {

    private final VacanciesRepository vacanciesRepository;

    @Autowired
    public VacanciesServiceImpl(VacanciesRepository vacanciesRepository) {
        this.vacanciesRepository = vacanciesRepository;
    }

    @Override
    public Vacancies addVacancy(Vacancies vacancy) {
        return vacanciesRepository.save(vacancy);
    }

    @Override
    public List<Vacancies> getAllVacancies() {
        return vacanciesRepository.findAll();
    }

    @Override
    public void deleteVacancy(Integer id) {
        vacanciesRepository.deleteById(id);
    }

}

