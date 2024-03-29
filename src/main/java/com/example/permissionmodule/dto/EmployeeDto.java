package com.example.permissionmodule.dto;

import java.time.LocalDate;

public class EmployeeDto {

    private String fullName;
    private LocalDate startOfWork;
    private String language;

    public EmployeeDto() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getStartOfWork() {
        return startOfWork;
    }

    public void setStartOfWork(LocalDate startOfWork) {
        this.startOfWork = startOfWork;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
