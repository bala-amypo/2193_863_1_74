package com.example.demo.controller;

import com.example.demo.dto.PolicyDto;
import com.example.demo.model.Policy;
import com.example.demo.service.PolicyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {
    
    private final PolicyService policyService;
    
    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }
    
    @PostMapping("/{userId}")
    public ResponseEntity<Policy> createPolicy(@PathVariable Long userId, @RequestBody PolicyDto policyDto) {
        Policy policy = new Policy();
        policy.setPolicyNumber(policyDto.getPolicyNumber());
        policy.setPolicyType(policyDto.getPolicyType());
        policy.setStartDate(policyDto.getStartDate());
        policy.setEndDate(policyDto.getEndDate());
        
        Policy savedPolicy = policyService.createPolicy(userId, policy);
        return ResponseEntity.ok(savedPolicy);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Policy>> getPoliciesByUser(@PathVariable Long userId) {
        List<Policy> policies = policyService.getPoliciesByUser(userId);
        return ResponseEntity.ok(policies);
    }
}