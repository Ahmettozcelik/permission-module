package com.example.permissionmodule.controller;

import com.example.permissionmodule.entity.PermissionResponse;
import com.example.permissionmodule.service.PermissionResponseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/permission/responses")
public class PermissionResponseController {

    private final PermissionResponseService permissionResponseService;

    public PermissionResponseController(PermissionResponseService permissionResponseService) {
        this.permissionResponseService = permissionResponseService;
    }

    @GetMapping
    public List<PermissionResponse> findAllPermissionResponses(){
        return permissionResponseService.findAll();
    }

    @GetMapping("{id}")
    public PermissionResponse findById(@PathVariable Long id){
        return permissionResponseService.findById(id);
    }

    @GetMapping("/approve/{permissionResponseId}")
    public void approvePermissionRequestById(@PathVariable Long permissionResponseId) {
        permissionResponseService.approvePermissionRequestById(permissionResponseId);
    }

    @GetMapping("/reject/{permissionResponseId}")
    public void rejectPermissionRequestById(@PathVariable Long permissionResponseId) {
        permissionResponseService.rejectPermissionRequestById(permissionResponseId);
    }
}
