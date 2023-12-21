package com.kondratiuk.spring.springboot_rest.service;

import com.kondratiuk.spring.springboot_rest.entity.Vacancies;

import java.util.List;

public interface VacanciesService  {
    List<Vacancies> getAllVacancies();

    Vacancies addVacancy(Vacancies vacancy);

    void deleteVacancy(Integer id);

}
