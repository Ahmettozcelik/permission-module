package com.example.permissionmodule.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id")
    private Long employeeId;

    private int hasAnnualLeave = 0; // define 0

    private int usedAnnualLeave = 0; // define 0

    public Permission() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public int getHasAnnualLeave() {
        return hasAnnualLeave;
    }

    public void setHasAnnualLeave(int hasAnnualLeave) {
        this.hasAnnualLeave = hasAnnualLeave;
    }

    public int getUsedAnnualLeave() {
        return usedAnnualLeave;
    }

    public void setUsedAnnualLeave(int usedAnnualLeave) {
        this.usedAnnualLeave = usedAnnualLeave;
    }
}
