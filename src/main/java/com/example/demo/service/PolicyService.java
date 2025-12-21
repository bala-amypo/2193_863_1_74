package com.example.demo.service;

import com.example.demo.dto.PolicyDto;
import com.example.demo.model.Policy;
import java.util.List;

public interface PolicyService {
    Policy createPolicy(Long userId, PolicyDto dto);
    List<Policy> getPoliciesByUser(Long userId);
    Policy getPolicy(Long id);
}
