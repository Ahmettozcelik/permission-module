package com.example.permissionmodule.service;

import com.example.permissionmodule.entity.PermissionRequest;
import com.example.permissionmodule.entity.PermissionResponse;
import com.example.permissionmodule.enums.PermissionStatus;
import com.example.permissionmodule.repository.PermissionRequestRepository;
import com.example.permissionmodule.repository.PermissionResponseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionResponseService {

    private final PermissionResponseRepository permissionResponseRepository;
    private final PermissionRequestRepository permissionRequestRepository;
    private final EmployeeService employeeService;

    public PermissionResponseService(PermissionResponseRepository permissionResponseRepository, PermissionRequestRepository permissionRequestRepository, EmployeeService employeeService) {
        this.permissionResponseRepository = permissionResponseRepository;
        this.permissionRequestRepository = permissionRequestRepository;
        this.employeeService = employeeService;
    }

    public List<PermissionResponse> findAll() {
        return permissionResponseRepository.findAll();
    }

    public PermissionResponse findById(Long id) {
        return permissionResponseRepository.findById(id).orElse(null);
    }

    public void approvePermissionRequestById(Long permissionResponseId) {
        Optional<PermissionResponse> permissionResponse = permissionResponseRepository.findById(permissionResponseId);

        if(permissionResponse.isPresent()){

            PermissionResponse foundPermissionResponse = permissionResponse.get();
            Optional<PermissionRequest> permissionRequest = permissionRequestRepository.findById(foundPermissionResponse.getPermissionRequestId());

            if(permissionRequest.isPresent()){
                PermissionRequest foundPermissionRequest = permissionRequest.get();

                int remainingAnnualLeave = foundPermissionRequest.getPermission().getHasAnnualLeave()-foundPermissionRequest.getRequestDay();

                if(foundPermissionRequest.getPermission().getHasAnnualLeave() >= foundPermissionRequest.getRequestDay() ||
                        (employeeService.getWorkTime(foundPermissionRequest.getEmployee()) < 365 && foundPermissionRequest.getRequestDay() <= 5)){

                    foundPermissionResponse.setPermissionStatus(PermissionStatus.APPROVED.getValue());
                    foundPermissionRequest.getPermission().setHasAnnualLeave(remainingAnnualLeave);
                    foundPermissionRequest.getPermission().setUsedAnnualLeave(foundPermissionRequest.getPermission().getUsedAnnualLeave()+foundPermissionRequest.getRequestDay());
                    permissionResponseRepository.save(foundPermissionResponse);
                }
                else{
                    foundPermissionResponse.setPermissionStatus(PermissionStatus.REJECTED.getValue());
                    permissionResponseRepository.save(foundPermissionResponse);
                }
            }
        }
    }

    public void rejectPermissionRequestById(Long permissionResponseId) {
        Optional<PermissionResponse> permissionResponse = permissionResponseRepository.findById(permissionResponseId);


        if (permissionResponse.isPresent()) {
            PermissionResponse foundPermissionResponse = permissionResponse.get();
            foundPermissionResponse.setPermissionStatus(PermissionStatus.REJECTED.getValue());
            permissionResponseRepository.save(foundPermissionResponse);
        }
    }
}
