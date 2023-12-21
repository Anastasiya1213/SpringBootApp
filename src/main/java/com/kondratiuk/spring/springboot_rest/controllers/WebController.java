package com.kondratiuk.spring.springboot_rest.controllers;

import com.kondratiuk.spring.springboot_rest.ForErrors.UserAlreadyExistsException;
import com.kondratiuk.spring.springboot_rest.MethodsOptimization.AnalysisCoordinator;
import com.kondratiuk.spring.springboot_rest.dto.UserDTO;
import com.kondratiuk.spring.springboot_rest.service.CandidatesService;
import com.kondratiuk.spring.springboot_rest.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    @Autowired
    private CandidatesService candidatesService;

    @Autowired
    private UserService userService;

    public WebController(CandidatesService candidatesService) {
        this.candidatesService = candidatesService;
    }

    private static final Logger logger = LoggerFactory.getLogger (WebController.class);

    // Ендпоінт для реєстрації
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDTO()); // Припускаючи, що у вас є DTO для реєстрації користувача
        return "registration";
    }

    // Ендпоінт для обробки форми реєстрації
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserDTO userDTO,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            // Якщо є помилки, поверніть форму реєстрації з відповідними повідомленнями
            return "registration";
        }
        try {
            userService.register(userDTO); // Реєстрація користувача
        } catch (UserAlreadyExistsException e) {
            model.addAttribute("registrationError", e.getMessage());
            return "registration";
        }
        return "redirect:/login"; // Перенаправлення на сторінку входу
    }


    // Ендпоінт для відображення форми входу
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // Ендпоінт для обробки входу
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model) {
        boolean isAuthenticated = userService.authenticate(email, password);
            if (isAuthenticated) {
                return "redirect:/vacancies/vacanciesList.html";
            } else {
                model.addAttribute("loginError", "Невірний email або пароль");
                return "login";
            }
    }

    // Ендпоінт для статичних HTTP запитів, повертає HTML через Thymeleaf
    @GetMapping("/analyse/{vacancyId}")
    public String analyseVacancy(@PathVariable("vacancyId") int vacancyId,
                                 @RequestParam(required = false, defaultValue = "weighted") String method,
                                 Model model) {
        logger.info("Analyzing vacancy with ID: " + vacancyId + " using " + method + " method.");
        AnalysisCoordinator.AnalysisResults analysisResults =
                candidatesService.performAnalysis(vacancyId, method);
        model.addAttribute("analysisResults", analysisResults);
        return "index";
    }

    // Ендпоінт для AJAX запитів, повертає JSON
    @ResponseBody
    @GetMapping("/analyse/{vacancyId}/json")
    public ResponseEntity<?> analyseVacancyJson(@PathVariable("vacancyId") int vacancyId,
                                                @RequestParam(required = false, defaultValue = "weighted") String method) {
        logger.info("Analyzing vacancy with ID: " + vacancyId + " using " + method + " method (JSON).");
        AnalysisCoordinator.AnalysisResults analysisResults =
                candidatesService.performAnalysis(vacancyId, method);
        return new ResponseEntity<>(analysisResults, HttpStatus.OK);
    }
}
