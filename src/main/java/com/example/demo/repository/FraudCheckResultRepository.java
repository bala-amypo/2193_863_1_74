package com.example.demo.repository;

import com.example.demo.model.FraudCheckResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FraudCheckResultRepository extends JpaRepository<FraudCheckResult, Long> {
    Optional<FraudCheckResult> findByClaimId(Long claimId);
}