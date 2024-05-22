package entity;

import java.math.BigDecimal;

public class Policy {
    private int policyId;
    private String policyName;
    private BigDecimal coverageAmount;
    private BigDecimal premium;
    private int clientId;
    private String policyType; 


    public Policy() {}

    public Policy(int policyId, String policyName, BigDecimal coverageAmount, BigDecimal premium, int clientId, String policyType) {
        this.policyId = policyId;
        this.policyName = policyName;
        this.coverageAmount = coverageAmount;
        this.premium = premium;
        this.clientId = clientId;
        this.policyType = policyType;
    }


    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public BigDecimal getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(BigDecimal coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    
    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    
    @Override
    public String toString() {
        return "Policy [policyId=" + policyId + ", policyName=" + policyName + ", coverageAmount=" + coverageAmount + ", premium=" + premium + ", clientId=" + clientId + ", policyType=" + policyType + "]";
    }
}
