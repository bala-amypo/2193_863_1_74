package com.example.demo.controller;

import com.example.demo.dto.ClaimDto;
import com.example.demo.model.Claim;
import com.example.demo.service.ClaimService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {
    
    private final ClaimService claimService;
    
    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }
    
    @PostMapping("/{policyId}")
    public ResponseEntity<Claim> createClaim(@PathVariable Long policyId, @RequestBody ClaimDto claimDto) {
        Claim claim = new Claim();
        claim.setClaimDate(claimDto.getClaimDate());
        claim.setClaimAmount(claimDto.getClaimAmount());
        claim.setDescription(claimDto.getDescription());
        
        Claim savedClaim = claimService.createClaim(policyId, claim);
        return ResponseEntity.ok(savedClaim);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Claim> getClaim(@PathVariable Long id) {
        Claim claim = claimService.getClaim(id);
        return ResponseEntity.ok(claim);
    }
    
    @GetMapping
    public ResponseEntity<List<Claim>> getAllClaims() {
        List<Claim> claims = claimService.getAllClaims();
        return ResponseEntity.ok(claims);
    }
}