package com.example.permissionmodule.dto;

public class PermissionRequestDto {

    private Long employeeId;
    private Long permissionId;
    private int requestDay;

    public PermissionRequestDto() {
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public int getRequestDay() {
        return requestDay;
    }

    public void setRequestDay(int requestDay) {
        this.requestDay = requestDay;
    }
}
