package dao;

import entity.Policy;
import util.DBConnUtil;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class InsuranceServiceImpl implements IPolicyService {
    private Connection conn ;
    public InsuranceServiceImpl() {
        conn = DBConnUtil.getConnection(); 
        if (conn == null) {
            System.err.println("Failed to establish database connection.");}

    }

    @Override
    public boolean createPolicy(Policy policy) {
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Policy (policyId, policyName, policyType, coverageAmount, premium, clientId) VALUES (?, ?, ?, ?, ?, ?)")) {
            stmt.setInt(1, policy.getPolicyId());
            stmt.setString(2, policy.getPolicyName());
            stmt.setString(3, policy.getPolicyType());
            stmt.setBigDecimal(4, policy.getCoverageAmount());
            stmt.setBigDecimal(5, policy.getPremium());
            stmt.setInt(6, policy.getClientId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




    @Override
    public Policy getPolicy(int policyId) {
        Policy policy = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Policy WHERE policyId = ?")) {
            stmt.setInt(1, policyId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                policy = new Policy();
                policy.setPolicyId(rs.getInt("policyId"));
                policy.setPolicyName(rs.getString("policyName"));
                policy.setPolicyType(rs.getString("policyType"));
                policy.setCoverageAmount(rs.getBigDecimal("coverageAmount"));
                policy.setPremium(rs.getBigDecimal("premium"));
                policy.setClientId(rs.getInt("clientId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return policy;
    }

    @Override
    public Collection<Policy> getAllPolicies() {
        Collection<Policy> policies = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Policy")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Policy policy = new Policy();
                policy.setPolicyId(rs.getInt("policyId"));
                policy.setPolicyName(rs.getString("policyName"));
                policy.setPolicyType(rs.getString("policyType"));
                policy.setCoverageAmount(rs.getBigDecimal("coverageAmount"));
                policy.setPremium(rs.getBigDecimal("premium"));
                policy.setClientId(rs.getInt("clientId"));
                policies.add(policy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return policies;
    }

    @Override
    public boolean updatePolicy(Policy policy) {
        try (PreparedStatement stmt = conn.prepareStatement("UPDATE Policy SET policyName = ?, policyType = ?, coverageAmount = ?, premium = ?, clientId = ? WHERE policyId = ?")) {
            stmt.setString(1, policy.getPolicyName());
            stmt.setString(2, policy.getPolicyType());
            stmt.setBigDecimal(3, policy.getCoverageAmount());
            stmt.setBigDecimal(4, policy.getPremium());
            stmt.setInt(5, policy.getClientId());
            stmt.setInt(6, policy.getPolicyId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
public boolean deletePolicy(int policyId) {
    try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Policy WHERE policyId = ?")) {
        stmt.setInt(1, policyId);
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Policy deleted successfully.");
            return true;
        } else {
            System.out.println("No policy found with ID: " + policyId);
            return false;
        }
    } catch (SQLException e) {
        System.out.println("Error deleting policy: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}

}
