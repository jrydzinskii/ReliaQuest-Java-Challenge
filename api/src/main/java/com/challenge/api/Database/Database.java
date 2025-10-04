package com.challenge.api.Database;

import com.challenge.api.model.Employee;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Database {
    private static final List<Employee> employees = new ArrayList<>();

    public static List<Employee> getEmployees() {
        return employees;
    }

    public static Employee createEmployee(Employee employee) { // implemented as update as well
        if (employee == null) {
            return null;
        }
        if (employee.getUuid() == null) {
            employee.setUuid(UUID.randomUUID());
        }

        employees.add(employee);
        return employee;
    }
}
