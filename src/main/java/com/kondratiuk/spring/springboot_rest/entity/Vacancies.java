package com.kondratiuk.spring.springboot_rest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(
        name = "vacancies"
)
public class Vacancies {
    @Id
    @Column(
            name = "vacancy_id"
    )
    private int vacancy_id;
    @Column(
            name = "vacancy_title"
    )
    private String vacancy_title;
    @Column(
            name = "required_education_level"
    )
    private double required_education_level;
    @Column(
            name = "required_experience"
    )
    private double required_experience;
    @Column(
            name = "required_skills"
    )
    private double required_skills;
    @Column(
            name = "required_certificates"
    )
    private double required_certificates;
    @Column(
            name = "required_cooperation"
    )
    private double required_cooperation;
    @Column(
            name = "required_communication"
    )
    private double required_communication;
    @Column(
            name = "required_english_level"
    )
    private double required_english_level;
    @Column(
            name = "cultural_fit_importance"
    )
    private double cultural_fit_importance;
    @Column(
            name = "salary_range"
    )
    private double salary_range;

    public Vacancies(int vacancy_id, String vacancy_title, double required_education_level, double required_experience, double required_skills, double required_certificates, double required_cooperation, double required_communication, double required_english_level, double cultural_fit_importance, double salary_range) {
        this.vacancy_id = vacancy_id;
        this.vacancy_title = vacancy_title;
        this.required_education_level = required_education_level;
        this.required_experience = required_experience;
        this.required_skills = required_skills;
        this.required_certificates = required_certificates;
        this.required_cooperation = required_cooperation;
        this.required_communication = required_communication;
        this.required_english_level = required_english_level;
        this.cultural_fit_importance = cultural_fit_importance;
        this.salary_range = salary_range;
    }

    public Vacancies() {
    }

    public String toString() {
        return "Vacancies{vacancy_id=" + this.vacancy_id + ", vacancy_title='" + this.vacancy_title + "', required_education_level=" + this.required_education_level + ", required_experience=" + this.required_experience + ", required_skills=" + this.required_skills + ", required_certificates=" + this.required_certificates + ", required_cooperation=" + this.required_cooperation + ", required_communication=" + this.required_communication + ", required_english_level=" + this.required_english_level + ", cultural_fit_importance=" + this.cultural_fit_importance + ", salary_range=" + this.salary_range + "}";
    }

    public int getVacancy_id() {
        return this.vacancy_id;
    }

    public void setVacancy_id(int vacancy_id) {
        this.vacancy_id = vacancy_id;
    }

    public String getVacancy_title() {
        return this.vacancy_title;
    }

    public void setVacancy_title(String vacancy_title) {
        this.vacancy_title = vacancy_title;
    }

    public double getRequired_education_level() {
        return this.required_education_level;
    }

    public void setRequired_education_level(double required_education_level) {
        this.required_education_level = required_education_level;
    }

    public double getRequired_experience() {
        return this.required_experience;
    }

    public void setRequired_experience(double required_experience) {
        this.required_experience = required_experience;
    }

    public double getRequired_skills() {
        return this.required_skills;
    }

    public void setRequired_skills(double required_skills) {
        this.required_skills = required_skills;
    }

    public double getRequired_certificates() {
        return this.required_certificates;
    }

    public void setRequired_certificates(double required_certificates) {
        this.required_certificates = required_certificates;
    }

    public double getRequired_cooperation() {
        return this.required_cooperation;
    }

    public void setRequired_cooperation(double required_cooperation) {
        this.required_cooperation = required_cooperation;
    }

    public double getRequired_communication() {
        return this.required_communication;
    }

    public void setRequired_communication(double required_communication) {
        this.required_communication = required_communication;
    }

    public double getRequired_english_level() {
        return this.required_english_level;
    }

    public void setRequired_english_level(double required_english_level) {
        this.required_english_level = required_english_level;
    }

    public double getCultural_fit_importance() {
        return this.cultural_fit_importance;
    }

    public void setCultural_fit_importance(double cultural_fit_importance) {
        this.cultural_fit_importance = cultural_fit_importance;
    }

    public double getSalary_range() {
        return this.salary_range;
    }

    public void setSalary_range(double salary_range) {
        this.salary_range = salary_range;
    }
}
