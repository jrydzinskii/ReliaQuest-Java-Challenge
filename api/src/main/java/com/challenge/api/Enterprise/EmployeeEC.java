package com.challenge.api.Enterprise;

import com.challenge.api.DTO.EmployeeDTO;
import com.challenge.api.Database.Database;
import com.challenge.api.model.Employee;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class EmployeeEC {
    public EmployeeEC() {}

    public List<EmployeeDTO> Employees() {
        return Database.getEmployees().stream().map(EmployeeDTO::new).collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeByUuid(UUID uuid) {
        return Database.getEmployees().stream()
                .filter(p -> p.getUuid().equals(uuid))
                .findFirst()
                .map(EmployeeDTO::new)
                .orElse(null);
    }

    public EmployeeDTO createEmployee(Employee requestBody) {
        if (requestBody == null) {
            return null;
        }
        Employee created = Database.createEmployee(requestBody);
        return new EmployeeDTO(created);
    }
}
