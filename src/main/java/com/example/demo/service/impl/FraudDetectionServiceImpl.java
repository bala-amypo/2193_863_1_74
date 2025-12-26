package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Claim;
import com.example.demo.model.FraudCheckResult;
import com.example.demo.model.FraudRule;
import com.example.demo.repository.ClaimRepository;
import com.example.demo.repository.FraudCheckResultRepository;
import com.example.demo.repository.FraudRuleRepository;
import com.example.demo.service.FraudDetectionService;
import org.springframework.stereotype.Service; 

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FraudDetectionServiceImpl implements FraudDetectionService {
    
    private final ClaimRepository claimRepository;
    private final FraudRuleRepository fraudRuleRepository;
    private final FraudCheckResultRepository resultRepository;
    
    public FraudDetectionServiceImpl(ClaimRepository claimRepository, 
                                   FraudRuleRepository fraudRuleRepository,
                                   FraudCheckResultRepository resultRepository) {
        this.claimRepository = claimRepository;
        this.fraudRuleRepository = fraudRuleRepository;
        this.resultRepository = resultRepository;
    }
    
    @Override
    public FraudCheckResult evaluateClaim(Long claimId) {
        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not found"));
        
        List<FraudRule> rules = fraudRuleRepository.findAll();
        
        for (FraudRule rule : rules) {
            if (evaluateRule(claim, rule)) {
                FraudCheckResult result = new FraudCheckResult(
                    claim, 
                    true, 
                    rule.getRuleName(), 
                    "Claim flagged by rule: " + rule.getRuleName(),
                    LocalDateTime.now()
                );
                return resultRepository.save(result);
            }
        }
        
        FraudCheckResult result = new FraudCheckResult(
            claim, 
            false, 
            null, 
            "No fraud detected",
            LocalDateTime.now()
        );
        return resultRepository.save(result);
    }
    
    private boolean evaluateRule(Claim claim, FraudRule rule) {
        if ("claimAmount".equals(rule.getConditionField())) {
            double threshold = Double.parseDouble(rule.getValue());
            double claimAmount = claim.getClaimAmount();
            
            switch (rule.getOperator()) {
                case ">": return claimAmount > threshold;
                case "<": return claimAmount < threshold;
                case ">=": return claimAmount >= threshold;
                case "<=": return claimAmount <= threshold;
                case "=": return claimAmount == threshold;
                default: return false;
            }
        }
        return false;
    }
    
    @Override
    public FraudCheckResult getResultByClaim(Long claimId) {
        return resultRepository.findByClaimId(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));
    }
}