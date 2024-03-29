package com.example.permissionmodule.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String fullName;
        private LocalDate startOfWork;
        private String language;

        public Employee() {
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
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
