package com.example.permissionmodule.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "permission_request")
public class PermissionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "permission_id", referencedColumnName = "id")
    private Permission permission;

    private int requestDay;

    public PermissionRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public int getRequestDay() {
        return requestDay;
    }

    public void setRequestDay(int requestDay) {
        this.requestDay = requestDay;
    }
}
