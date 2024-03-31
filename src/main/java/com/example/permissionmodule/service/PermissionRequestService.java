package com.example.permissionmodule.service;

import com.example.permissionmodule.dto.PermissionRequestDto;
import com.example.permissionmodule.entity.Employee;
import com.example.permissionmodule.entity.Permission;
import com.example.permissionmodule.entity.PermissionRequest;
import com.example.permissionmodule.entity.PermissionResponse;
import com.example.permissionmodule.enums.PermissionStatus;
import com.example.permissionmodule.language.LanguageConfig;
import com.example.permissionmodule.repository.EmployeeRepository;
import com.example.permissionmodule.repository.PermissionRepository;
import com.example.permissionmodule.repository.PermissionRequestRepository;
import com.example.permissionmodule.repository.PermissionResponseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionRequestService {

    private final PermissionRequestRepository permissionRequestRepository;
    private final EmployeeRepository employeeRepository;
    private final PermissionRepository permissionRepository;
    private final PermissionResponseRepository permissionResponseRepository;
    private final EmployeeService employeeService;

    public PermissionRequestService(PermissionRequestRepository permissionRequestRepository, EmployeeRepository employeeRepository, PermissionRepository permissionRepository, PermissionResponseRepository permissionResponseRepository, EmployeeService employeeService) {
        this.permissionRequestRepository = permissionRequestRepository;
        this.employeeRepository = employeeRepository;
        this.permissionRepository = permissionRepository;
        this.permissionResponseRepository = permissionResponseRepository;
        this.employeeService = employeeService;
    }

    public List<PermissionRequest> findAll() {
        return permissionRequestRepository.findAll();
    }

    public PermissionRequest findById(Long id) {
        return permissionRequestRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(PermissionRequestDto permissionRequestDto) {

        Optional<Employee> employee = employeeRepository.findById(permissionRequestDto.getEmployeeId());

        if(employee.isPresent()){
            Employee foundEmployee = employee.get();
            Permission permission = permissionRepository.findByEmployeeId(permissionRequestDto.getEmployeeId());

            PermissionRequest permissionRequest = new PermissionRequest();
            permissionRequest.setEmployee(foundEmployee);
            permissionRequest.setPermission(permission);
            permissionRequest.setRequestDay(permissionRequestDto.getRequestDay());

            if (permission.getHasAnnualLeave() >= permissionRequestDto.getRequestDay() ||
                    (employeeService.getWorkTime(permissionRequest.getEmployee()) < 365 && permissionRequest.getRequestDay()+permission.getUsedAnnualLeave() <= 5)) {

                permissionRequestRepository.save(permissionRequest);
                createPermissionResponse(permissionRequest, PermissionStatus.WAITING_FOR_APPROVAL);

            } else if (permission.getHasAnnualLeave() < permissionRequestDto.getRequestDay()){

                throw new IllegalStateException(LanguageConfig.getErrorMessage("employee.not.enough.permission", foundEmployee.getLanguage()));
            }
            else if (employeeService.getWorkTime(permissionRequest.getEmployee()) < 365 && permissionRequest.getRequestDay()+permission.getUsedAnnualLeave() <= 5){

                throw new IllegalStateException(LanguageConfig.getErrorMessage("employee.not.enough.permission.first.year", foundEmployee.getLanguage()));
            }

        }

    }

    private void createPermissionResponse(PermissionRequest permissionRequest, PermissionStatus status) {
        PermissionResponse permissionResponse = new PermissionResponse();
        permissionResponse.setApproverUser("Admin");
        permissionResponse.setEmployee(permissionRequest.getEmployee());
        permissionResponse.setPermissionRequestId(permissionRequest.getId());
        permissionResponse.setPermissionStatus(status.getValue());
        permissionResponseRepository.save(permissionResponse);
    }
}
