package com.kondratiuk.spring.springboot_rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDTO {

    @NotEmpty(message = "Ім'я користувача не може бути порожнім")
    private String username;

    @Email(message = "Введіть коректну електронну адресу")
    private String email;

    @Size(min = 6, message = "Пароль має бути не менше 6 символів")
    private String password;
    private String role;

    // Конструктори, гетери та сетери
    public UserDTO() {
    }

    public UserDTO(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }


    // standard getters and setters


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

