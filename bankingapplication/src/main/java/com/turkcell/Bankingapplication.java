package com.turkcell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bankingapplication {
    public static void main(String[] args) {
        BankService bankService = new BankService();

        Customer customer1 = bankService.createCustomer("C001", "Serhat Ozdemir");
        Customer customer2 = bankService.createCustomer("C002", "Nurgul Yalman");

        bankService.deposit(customer1.getCustomerId(), 2500.0);
        bankService.deposit(customer2.getCustomerId(), 4200.0);
        bankService.deposit(customer1.getCustomerId(), 500.0);

        System.out.println("=== ACCOUNT SUMMARIES ===");
        bankService.showAccountSummary(customer1.getCustomerId());
        System.out.println();
        bankService.showAccountSummary(customer2.getCustomerId());
    }
}

class Customer {
    private String customerId;
    private String fullName;
    private double balance;
    private List<String> transactions;

    public Customer(String customerId, String fullName) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
        this.transactions.add("Customer account created.");
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(amount + " TL deposited.");
    }
}

class CustomerRepository {
    private Map<String, Customer> customerMap = new HashMap<>();

    public void save(Customer customer) {
        customerMap.put(customer.getCustomerId(), customer);
    }

    public Customer findById(String customerId) {
        return customerMap.get(customerId);
    }

    public Collection<Customer> findAll() {
        return customerMap.values();
    }
}

class BankService {
    private CustomerRepository customerRepository = new CustomerRepository();

    public Customer createCustomer(String customerId, String fullName) {
        Customer customer = new Customer(customerId, fullName);
        customerRepository.save(customer);
        System.out.println("Customer created: " + fullName);
        return customer;
    }

    public void deposit(String customerId, double amount) {
        Customer customer = customerRepository.findById(customerId);

        if (customer == null) {
            System.out.println("Customer not found: " + customerId);
            return;
        }

        customer.deposit(amount);
        System.out.println(amount + " TL deposited to " + customer.getFullName());
    }

    public void showAccountSummary(String customerId) {
        Customer customer = customerRepository.findById(customerId);

        if (customer == null) {
            System.out.println("Customer not found: " + customerId);
            return;
        }

        System.out.println("----------------------------");
        System.out.println("Customer ID   : " + customer.getCustomerId());
        System.out.println("Customer Name : " + customer.getFullName());
        System.out.println("Balance       : " + customer.getBalance() + " TL");
        System.out.println("Transactions  :");

        for (String transaction : customer.getTransactions()) {
            System.out.println(" - " + transaction);
        }
    }
}