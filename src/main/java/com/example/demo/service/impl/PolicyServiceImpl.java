package com.example.demo.service.impl;

import com.example.demo.dto.PolicyDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Policy;
import com.example.demo.model.User;
import com.example.demo.repository.PolicyRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PolicyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyServiceImpl implements PolicyService {

    private final PolicyRepository policyRepository;
    private final UserRepository userRepository;

    public PolicyServiceImpl(PolicyRepository policyRepository, UserRepository userRepository) {
        this.policyRepository = policyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Policy createPolicy(Long userId, PolicyDto dto) {
        // Find the user or throw exception
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Validate policy number uniqueness
        if (policyRepository.existsByPolicyNumber(dto.getPolicyNumber())) {
            throw new IllegalArgumentException("Policy number already exists");
        }

        // Validate dates
        if (dto.getEndDate().isBefore(dto.getStartDate()) ||
            dto.getEndDate().isEqual(dto.getStartDate())) {
            throw new IllegalArgumentException("Invalid dates - end date must be after start date");
        }

        // Create policy and link to user
        Policy policy = new Policy();
        policy.setUser(user);
        policy.setPolicyNumber(dto.getPolicyNumber());
        policy.setPolicyType(dto.getPolicyType());
        policy.setStartDate(dto.getStartDate());
        policy.setEndDate(dto.getEndDate());

        // Save and return
        return policyRepository.save(policy);
    }

    @Override
    public List<Policy> getPoliciesByUser(Long userId) {
        // Optionally check if user exists
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return policyRepository.findByUserId(userId);
    }

    @Override
    public Policy getPolicy(Long id) {
        return policyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found"));
    }
}
