package com.kondratiuk.spring.springboot_rest.controllers;

import com.kondratiuk.spring.springboot_rest.entity.Candidates;
import com.kondratiuk.spring.springboot_rest.service.CandidatesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyRESTController {

    private final CandidatesService candidatesService;

    @Autowired
    public MyRESTController(CandidatesService candidatesService) {
        this.candidatesService = candidatesService;
    }

    @GetMapping("/Candidates")
    public List<Candidates> showAllCandidates() {
        return candidatesService.getAllCandidates();
    }
}