package com.edu.chmnu.ki_123.c3;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "employees.csv";
        new Main().run(filePath);
    }

    public void run(String filePath) {
        try {
            List<Employee> employees = EmployeeReader.readEmployeesFromFile(filePath);

            EmployeeService employeeService = new EmployeeService();
            Employee youngestWithHighestSalary = employeeService.findYoungestWithHighestSalary(employees);

            if (youngestWithHighestSalary != null) {
                System.out.println("Youngest Employee with Highest Salary:");
                System.out.println(youngestWithHighestSalary);
            } else {
                System.out.println("No employees found in the file.");
            }

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing file data. Ensure the format is [Name; Age; Salary].");
        }
    }
}