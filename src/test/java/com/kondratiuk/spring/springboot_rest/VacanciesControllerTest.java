package com.kondratiuk.spring.springboot_rest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import com.kondratiuk.spring.springboot_rest.controllers.VacanciesController;
import com.kondratiuk.spring.springboot_rest.entity.Vacancies;
import com.kondratiuk.spring.springboot_rest.service.VacanciesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;

public class VacanciesControllerTest {

    private MockMvc mockMvc;

    @Mock
    private VacanciesService vacanciesService;

    @InjectMocks
    private VacanciesController vacanciesController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = standaloneSetup(vacanciesController).build();
    }

    @Test
    public void testListVacancies() throws Exception {
        List<Vacancies> vacanciesList = new ArrayList<>();
        when(vacanciesService.getAllVacancies()).thenReturn(vacanciesList);

        mockMvc.perform(get("/vacancies"))
                .andExpect(status().isOk())
                .andExpect(view().name("vacanciesList"))
                .andExpect(model().attribute("vacancies", vacanciesList));
    }

    @Test
    public void testAddVacancy() throws Exception {
        Vacancies newVacancy = new Vacancies();
        mockMvc.perform(post("/vacancies/add")
                        .flashAttr("vacancy", newVacancy))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/vacancies"));

        verify(vacanciesService).addVacancy(any(Vacancies.class));
    }

    @Test
    public void testShowForm() throws Exception {
        mockMvc.perform(get("/vacancies/form"))
                .andExpect(status().isOk())
                .andExpect(view().name("AddVacancy"))
                .andExpect(model().attributeExists("vacancy"));
    }

    @Test
    public void testDeleteVacancy() throws Exception {
        Integer vacancyId = 1;
        mockMvc.perform(get("/vacancies/delete/{id}", vacancyId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/vacancies"));

        verify(vacanciesService).deleteVacancy(vacancyId);
    }

}

