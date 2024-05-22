package dao;

import entity.Policy; // Import Policy class


import java.util.Collection;

public interface IPolicyService {
    boolean createPolicy(Policy policy);
    Policy getPolicy(int policyId);
    Collection<Policy> getAllPolicies();
    boolean updatePolicy(Policy policy);
    boolean deletePolicy(int policyId);
}

