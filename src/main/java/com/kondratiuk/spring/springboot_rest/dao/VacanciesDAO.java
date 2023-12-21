package com.kondratiuk.spring.springboot_rest.dao;

import com.kondratiuk.spring.springboot_rest.entity.Vacancies;

import java.util.List;

public interface VacanciesDAO {
    List<Vacancies> getAllVacancies();

    Vacancies addVacancy(Vacancies vacancy);

    void deleteVacancy(Integer id);
}
