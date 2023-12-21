package com.kondratiuk.spring.springboot_rest.repository;


import com.kondratiuk.spring.springboot_rest.entity.Vacancies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacanciesRepository extends JpaRepository<Vacancies, Integer> {
    // Ви можете додати додаткові методи для роботи з даними Vacancies
}