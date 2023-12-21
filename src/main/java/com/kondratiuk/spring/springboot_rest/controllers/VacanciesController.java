package com.kondratiuk.spring.springboot_rest.controllers;

import com.kondratiuk.spring.springboot_rest.entity.Vacancies;
import com.kondratiuk.spring.springboot_rest.service.VacanciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vacancies")
public class VacanciesController {

    @Autowired
    private VacanciesService vacanciesService;

    public VacanciesController(VacanciesService vacanciesService) {
        this.vacanciesService = vacanciesService;
    }

    @GetMapping
    public String listVacancies(Model model) {
        List<Vacancies> vacanciesList = vacanciesService.getAllVacancies();
        model.addAttribute("vacancies", vacanciesList);
        return "vacanciesList"; // Впевніться, що у вас є в'юшка з цією назвою
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        Vacancies vacancy = new Vacancies(); // Використання конструктора без параметрів
        model.addAttribute("vacancy", vacancy);
        return "AddVacancy";
    }

    @PostMapping("/add")
    public String addVacancy(@ModelAttribute Vacancies vacancy) {
        vacanciesService.addVacancy(vacancy);
        return "redirect:/vacancies"; // Перенаправлення на список вакансій
    }

    @GetMapping("/delete/{id}")
    public String deleteVacancy(@PathVariable Integer id) {
        Vacancies vacancy = new Vacancies(); // Використання конструктора без параметрів
        vacanciesService.deleteVacancy(id);
        return "redirect:/vacancies";
    }

}
