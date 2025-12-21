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

    // Create a new policy for an existing user
    @PostMapping("/{userId}")
    public ResponseEntity<Policy> createPolicy(
            @PathVariable Long userId,
            @RequestBody PolicyDto dto
    ) {
        Policy createdPolicy = policyService.createPolicy(userId, dto);
        return ResponseEntity.ok(createdPolicy);
    }

    // Get all policies of a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Policy>> getPoliciesByUser(@PathVariable Long userId) {
        List<Policy> policies = policyService.getPoliciesByUser(userId);
        return ResponseEntity.ok(policies);
    }

    // Get a policy by its ID
    @GetMapping("/{policyId}")
    public ResponseEntity<Policy> getPolicy(@PathVariable Long policyId) {
        Policy policy = policyService.getPolicy(policyId);
        return ResponseEntity.ok(policy);
    }
}
