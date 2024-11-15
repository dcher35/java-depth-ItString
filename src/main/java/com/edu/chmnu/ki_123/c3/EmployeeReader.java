package com.edu.chmnu.ki_123.c3;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class EmployeeReader {
    public static List<Employee> readEmployeesFromFile(String filePath) throws IOException {
        List<Employee> employees = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        for (String line : lines) {
            String[] parts = line.split(";");
            String name = parts[0].trim();
            int age = Integer.parseInt(parts[1].trim());
            double salary = Double.parseDouble(parts[2].trim());
            employees.add(new Employee(name, age, salary));
        }
        return employees;
    }
}