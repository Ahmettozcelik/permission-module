package com.example.permissionmodule.entity;

import com.example.permissionmodule.dto.EmployeeDto;
import jakarta.persistence.*;

@Entity
@Table(name = "permission_response")
public class PermissionResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String approverUser;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
    private Long permissionRequestId;

    private int permissionStatus;

    public PermissionResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApproverUser() {
        return approverUser;
    }

    public void setApproverUser(String approverUser) {
        this.approverUser = approverUser;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getPermissionRequestId() {
        return permissionRequestId;
    }

    public void setPermissionRequestId(Long permissionRequestId) {
        this.permissionRequestId = permissionRequestId;
    }

    public int getPermissionStatus() {
        return permissionStatus;
    }

    public void setPermissionStatus(int permissionStatus) {
        this.permissionStatus = permissionStatus;
    }
}
