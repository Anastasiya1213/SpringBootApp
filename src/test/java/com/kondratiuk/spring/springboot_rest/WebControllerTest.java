package com.kondratiuk.spring.springboot_rest;

import com.kondratiuk.spring.springboot_rest.controllers.WebController;
import com.kondratiuk.spring.springboot_rest.dto.UserDTO;
import com.kondratiuk.spring.springboot_rest.entity.Users;
import com.kondratiuk.spring.springboot_rest.service.CandidatesService;
import com.kondratiuk.spring.springboot_rest.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(WebController.class)
public class WebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private CandidatesService candidatesService;

    @Test
    public void whenShowRegistrationForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"));
    }

    @Test
    public void whenRegisterUserWithValidData() throws Exception {
        UserDTO validUser = new UserDTO("username", "email@example.com", "password", "ROLE_USER");

        // Конфігурація поведінки моків
        when(userService.register(Mockito.any(UserDTO.class))).thenReturn(new Users());

        mockMvc.perform(post("/register")
                        .param("username", validUser.getUsername())
                        .param("email", validUser.getEmail())
                        .param("password", validUser.getPassword())
                        .param("role", validUser.getRole()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }



    // Тест для перевірки відображення форми входу
    @Test
    public void testLoginSuccess() throws Exception {
        when(userService.authenticate(anyString(), anyString())).thenReturn(true);

        mockMvc.perform(post("/login")
                        .param("email", "test@example.com")
                        .param("password", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/vacancies/vacanciesList.html")); // Оновлений URL
    }


    @Test
    public void testLoginFailure() throws Exception {
        when(userService.authenticate(anyString(), anyString())).thenReturn(false);

        mockMvc.perform(post("/login")
                        .param("email", "wrong@example.com")
                        .param("password", "wrongpass"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("loginError"));
    }
}
