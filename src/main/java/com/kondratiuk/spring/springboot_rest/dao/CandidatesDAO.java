package com.kondratiuk.spring.springboot_rest.dao;

import com.kondratiuk.spring.springboot_rest.entity.Candidates;
import java.util.List;

public interface CandidatesDAO {
    List<Candidates> getAllCandidates();
}