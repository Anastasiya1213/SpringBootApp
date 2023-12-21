package com.kondratiuk.spring.springboot_rest.dao;

import com.kondratiuk.spring.springboot_rest.entity.Candidates;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CandidatesDAOImpl implements CandidatesDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Candidates> getAllCandidates() {
        return entityManager.createQuery("SELECT c FROM Candidates c", Candidates.class).getResultList();
    }
}