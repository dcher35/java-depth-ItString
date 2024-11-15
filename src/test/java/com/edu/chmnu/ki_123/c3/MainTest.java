package com.edu.chmnu.ki_123.c3;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    // Тест методу toString() класу Employee
    @Test
    public void testEmployeeToString() {
        Employee employee = new Employee("Cherednychenko Dmytro", 21, 5264.46);
        String result = employee.toString();
        assertEquals("Name: Cherednychenko Dmytro, Age: 21, Salary: 5264.46", result);
    }

    // Тесту методу читання співробітників з файлу
    @Test
    public void testReadEmployeesFromFile() throws IOException {
        String testFilePath = "test_employees.csv";
        String fileContent = """
            Cherednychenko Dmytro; 21; 5264.46
            Gulmamedov Nikita; 20; 5194.51
            """;
        Path path = Paths.get(testFilePath);
        Files.writeString(path, fileContent);

        List<Employee> employees = EmployeeReader.readEmployeesFromFile(testFilePath);

        assertEquals(2, employees.size());
        assertEquals("Cherednychenko Dmytro", employees.getFirst().getName());
        assertEquals(21, employees.get(0).getAge());
        assertEquals(5264.46, employees.get(0).getSalary(), 0.001);
        assertEquals(5194.51, employees.get(1).getSalary(), 0.001);

        Files.delete(path);
    }


    // Тест методу пошуку наймолодшого співробітника з найбільшою зарплатою
    @Test
    public void testFindYoungestWithHighestSalary() {
        EmployeeService employeeService = new EmployeeService();
        List<Employee> employees = List.of(
                new Employee("Cherednychenko Dmytro", 21, 5264.46),
                new Employee("Gulmamedov Nikita", 20, 5194.51),
                new Employee("Shevchenko Viktor", 19, 5048.26)
        );

        Employee result = employeeService.findYoungestWithHighestSalary(employees);

        assertNotNull(result);
        assertEquals("Cherednychenko Dmytro", result.getName());
        assertEquals(21, result.getAge());
        assertEquals(5264.46, result.getSalary(), 0.001);
    }

    // Тест методу, який повинен повертати null, якщо список співробітників порожній
    @Test
    public void testFindYoungestWithHighestSalaryEmptyList() {
        EmployeeService employeeService = new EmployeeService();
        List<Employee> employees = List.of();

        Employee result = employeeService.findYoungestWithHighestSalary(employees);

        assertNull(result);
    }

    // Тест методу run() з валідним файлом
    @Test
    public void testRunWithValidFile() throws IOException {
        String testFilePath = "test_employees.csv";
        String fileContent = """
                Cherednychenko Dmytro; 21; 5264.46
                Gulmamedov Nikita; 20; 5194.51
                Shevchenko Viktor; 19; 5048.26
                """;
        Path path = Paths.get(testFilePath);
        Files.writeString(path, fileContent);

        Main mainApp = new Main();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        mainApp.run(testFilePath);

        String output = outputStream.toString();
        assertTrue(output.contains("Youngest Employee with Highest Salary:"));
        assertTrue(output.contains("Cherednychenko Dmytro"));

        Files.delete(path);
    }
}