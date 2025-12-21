package com.example.demo.dto;

import java.time.LocalDate;

public class PolicyDto {

    private Long id;
    private String policyNumber;
    private String policyType;
    private LocalDate startDate;
    private LocalDate endDate;

    public PolicyDto() {}

    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
    }

    public String getPolicyNumber() { 
        return policyNumber; 
    }
    public void setPolicyNumber(String policyNumber) { 
        this.policyNumber = policyNumber; 
    }

    public String getPolicyType() { 
        return policyType; 
    }
    public void setPolicyType(String policyType) { 
        this.policyType = policyType; 
    }

    public LocalDate getStartDate() { 
        return startDate; 
    }
    public void setStartDate(LocalDate startDate) { 
        this.startDate = startDate; 
    }

    public LocalDate getEndDate() { 
        return endDate; 
    }
    public void setEndDate(LocalDate endDate) { 
        this.endDate = endDate; 
    }
}
