package main;

import dao.IPolicyService;
import dao.InsuranceServiceImpl;
import entity.Policy;
import exception.PolicyNotFoundException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainModule {
    public static void main(String[] args) {
        IPolicyService policyService = new InsuranceServiceImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Create Policy");
            System.out.println("2. Get Policy");
            System.out.println("3. Get All Policies");
            System.out.println("4. Update Policy");
            System.out.println("5. Delete Policy");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter Policy Name:");
                    String policyName = scanner.nextLine();
                    System.out.println("Enter Policy Type:");
                    String policyType = scanner.nextLine();
                    System.out.println("Enter Coverage Amount:");
                    BigDecimal coverageAmount = scanner.nextBigDecimal();
                    System.out.println("Enter Premium:");
                    BigDecimal premium = scanner.nextBigDecimal();
                    System.out.println("Enter Client ID:");
                    int clientId = scanner.nextInt();
                    Policy newPolicy = new Policy();
                    newPolicy.setPolicyName(policyName);
                    newPolicy.setPolicyType(policyType);
                    newPolicy.setCoverageAmount(coverageAmount);
                    newPolicy.setPremium(premium);
                    newPolicy.setClientId(clientId);
                    boolean created = policyService.createPolicy(newPolicy);
                    if (created) {
                        System.out.println("Policy created successfully.");
                    } else {
                        System.out.println("Failed to create policy.");
                    }
                    break;

                case 2:
                    System.out.println("Enter Policy ID:");
                    int policyId = scanner.nextInt();
                    try {
                        Policy policy = policyService.getPolicy(policyId);
                        if (policy != null) {
                            System.out.println(policy);
                        } else {
                            throw new PolicyNotFoundException("Policy not found.");
                        }
                    } catch (PolicyNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    Collection<Policy> policies = policyService.getAllPolicies();
                    System.out.println("All Policies:");
                    for (Policy policy : policies) {
                        System.out.println(policy);
                    }
                    break;

                case 4:
                    System.out.println("Enter Policy ID to update:");
                    int updatePolicyId = scanner.nextInt();
                    scanner.nextLine(); 
                    try {
                        Policy policy = policyService.getPolicy(updatePolicyId);
                        if (policy == null) {
                            throw new PolicyNotFoundException("Policy not found.");
                        }

                        boolean updated = false;
                        while (!updated) {
                            System.out.println("Which field would you like to update?");
                            System.out.println("1. Policy Name");
                            System.out.println("2. Policy Type");
                            System.out.println("3. Coverage Amount");
                            System.out.println("4. Premium");
                            System.out.println("5. Client ID");
                            System.out.println("6. Done Updating");

                            int updateChoice = scanner.nextInt();
                            scanner.nextLine(); 

                            switch (updateChoice) {
                                case 1:
                                    System.out.println("Enter new Policy Name:");
                                    policyName = scanner.nextLine();
                                    policy.setPolicyName(policyName);
                                break;
                                case 2:
                                    System.out.println("Enter new Policy Type:");
                                    policyType = scanner.nextLine();
                                    policy.setPolicyType(policyType);
                                break;
                                case 3:
                                    System.out.println("Enter new Coverage Amount:");
                                    coverageAmount = new BigDecimal(scanner.nextLine());
                                    policy.setCoverageAmount(coverageAmount);
                                break;
                                case 4:
                                    System.out.println("Enter new Premium:");
                                    premium = new BigDecimal(scanner.nextLine());
                                    policy.setPremium(premium);
                                break;
                                case 5:
                                    System.out.println("Enter new Client ID:");
                                    clientId = scanner.nextInt();
                                    scanner.nextLine(); 
                                    policy.setClientId(clientId);
                                break;
                                case 6:
                                    updated = policyService.updatePolicy(policy);
                                    if (updated) {
                                        System.out.println("Policy updated successfully.");
                                    } else {
                                        System.out.println("Failed to update policy.");
                                    }
                                break;
                                default:
                                    System.out.println("Invalid choice. Try again.");
                            }
                        }
                    } 
                    catch (PolicyNotFoundException e) 
                    {
                        System.out.println(e.getMessage());
                    } 
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid input. Please enter the correct data type.");
                        scanner.nextLine(); 
                    }
                break;

                case 5:
                    System.out.println("Enter Policy ID to delete:");
                    int deletePolicyId = scanner.nextInt();
                    boolean deleted = policyService.deletePolicy(deletePolicyId);
                    if (deleted) {
                        System.out.println("Policy deleted successfully.");
                    } else {
                        System.out.println("Failed to delete policy.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
