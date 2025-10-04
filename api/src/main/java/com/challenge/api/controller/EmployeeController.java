package com.challenge.api.controller;

import com.challenge.api.DTO.EmployeeDTO;
import com.challenge.api.Enterprise.EmployeeEC;
import com.challenge.api.model.Implementation;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.*;

/**
 * Fill in the missing aspects of this Spring Web REST Controller. Don't forget to add a Service layer.
 */
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    // private final EmployeeEC employeeService=new EmployeeEC();
    /**
     * @implNote Need not be concerned with an actual persistence layer. Generate mock Employee models as necessary.
     * @return One or more Employees.
     */
    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return new EmployeeEC().Employees();
    }

    /**
     * @implNote Need not be concerned with an actual persistence layer. Generate mock Employee model as necessary.
     * @param uuid Employee UUID
     * @return Requested Employee if exists
     */
    @GetMapping("/{uuid}")
    public EmployeeDTO getEmployeeByUuid(@PathVariable UUID uuid) {
        return new EmployeeEC().getEmployeeByUuid(uuid);
    }

    /**
     * @implNote Need not be concerned with an actual persistence layer.
     * @param requestBody hint!
     * @return Newly created Employee
     */
    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody Implementation requestBody) {

        return new EmployeeEC().createEmployee(requestBody);
    }
}
