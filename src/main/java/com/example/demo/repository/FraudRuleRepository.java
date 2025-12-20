package com.example.demo.repository;

import com.example.demo.model.FraudRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FraudRuleRepository extends JpaRepository<FraudRule, Long> {
    Optional<FraudRule> findByRuleName(String ruleName);
}