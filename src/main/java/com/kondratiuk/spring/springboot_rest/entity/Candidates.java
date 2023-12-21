package com.kondratiuk.spring.springboot_rest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(
        name = "candidates"
)
public class Candidates {
    @Id
    @Column(
            name = "candidate_id"
    )
    private int candidate_id;
    @Column(
            name = "name"
    )
    private String name;
    @Column(
            name = "level_of_technical_education"
    )
    private double level_of_technical_education;
    @Column(
            name = "work_experience"
    )
    private double work_experience;
    @Column(
            name = "number_of_skills"
    )
    private double number_of_skills;
    @Column(
            name = "number_of_certificates"
    )
    private double number_of_certificates;
    @Column(
            name = "cooperation"
    )
    private double cooperation;
    @Column(
            name = "communication"
    )
    private double communication;
    @Column(
            name = "english_level"
    )
    private double english_level;
    @Column(
            name = "cultural_relevance"
    )
    private double cultural_relevance;
    @Column(
            name = "desired_salary"
    )
    private double desired_salary;

    public Candidates() {
    }

    public Candidates(int candidate_id, String name, double level_of_technical_education, double work_experience, double number_of_skills, double number_of_certificates, double cooperation, double communication, double english_level, double cultural_relevance, double desired_salary) {
        this.candidate_id = candidate_id;
        this.name = name;
        this.level_of_technical_education = level_of_technical_education;
        this.work_experience = work_experience;
        this.number_of_skills = number_of_skills;
        this.number_of_certificates = number_of_certificates;
        this.cooperation = cooperation;
        this.communication = communication;
        this.english_level = english_level;
        this.cultural_relevance = cultural_relevance;
        this.desired_salary = desired_salary;
    }

    public String toString() {
        return "Candidates{candidate_id=" + this.candidate_id + ", name='" + this.name + "', level_of_technical_education='" + this.level_of_technical_education + "', work_experience=" + this.work_experience + ", number_of_skills=" + this.number_of_skills + ", number_of_certificates=" + this.number_of_certificates + ", cooperation=" + this.cooperation + ", communication=" + this.communication + ", english_level=" + this.english_level + ", cultural_relevance=" + this.cultural_relevance + ", desired_salary=" + this.desired_salary + "}";
    }

    public int getId() {
        return this.candidate_id;
    }

    public void setId(int id) {
        this.candidate_id = this.candidate_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLevel_of_technical_education() {
        return this.level_of_technical_education;
    }

    public void setLevel_of_technical_education(double level_of_technical_education) {
        this.level_of_technical_education = level_of_technical_education;
    }

    public double getWork_experience() {
        return this.work_experience;
    }

    public void setWork_experience(double work_experience) {
        this.work_experience = work_experience;
    }

    public double getNumber_of_skills() {
        return this.number_of_skills;
    }

    public void setNumber_of_skills(double number_of_skills) {
        this.number_of_skills = number_of_skills;
    }

    public double getNumber_of_certificates() {
        return this.number_of_certificates;
    }

    public void setNumber_of_certificates(double number_of_certificates) {
        this.number_of_certificates = number_of_certificates;
    }

    public double getCooperation() {
        return this.cooperation;
    }

    public void setCooperation(double cooperation) {
        this.cooperation = cooperation;
    }

    public double getCommunication() {
        return this.communication;
    }

    public void setCommunication(double communication) {
        this.communication = communication;
    }

    public double getEnglish_level() {
        return this.english_level;
    }

    public void setEnglish_Level(double english_level) {
        this.english_level = english_level;
    }

    public double getCultural_relevance() {
        return this.cultural_relevance;
    }

    public void setCultural_relevance(double cultural_relevance) {
        this.cultural_relevance = cultural_relevance;
    }

    public double getDesired_salary() {
        return this.desired_salary;
    }

    public void setDesired_salary(double desired_salary) {
        this.desired_salary = desired_salary;
    }

    public List<Double> getData() {
        return Arrays.asList(this.level_of_technical_education, this.work_experience, this.number_of_skills, this.number_of_certificates, this.cooperation, this.communication, this.english_level, this.cultural_relevance, this.desired_salary);
    }
}
