package com.edu.chmnu.ki_123.c3;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService {
    public Employee findYoungestWithHighestSalary(List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed()
                        .thenComparingInt(Employee::getAge))
                .findFirst()
                .orElse(null);
    }
}