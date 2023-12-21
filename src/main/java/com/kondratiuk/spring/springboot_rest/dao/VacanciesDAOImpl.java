package com.kondratiuk.spring.springboot_rest.dao;

import com.kondratiuk.spring.springboot_rest.entity.Vacancies;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;


@Repository
public class VacanciesDAOImpl implements VacanciesDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Vacancies> getAllVacancies() {
        return entityManager.createQuery("SELECT v FROM Vacancies v", Vacancies.class).getResultList();
    }

    @Override
    @Transactional
    public Vacancies addVacancy(Vacancies vacancy) {
        entityManager.persist(vacancy);
        return vacancy;
    }

    @Override
    @Transactional
    public void deleteVacancy(Integer id) {
        Vacancies vacancy = entityManager.find(Vacancies.class, id);
        if (vacancy != null) {
            entityManager.remove(vacancy);
        }
    }
}
