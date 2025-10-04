package com.challenge.api;

import static org.junit.jupiter.api.Assertions.*;

import com.challenge.api.DTO.EmployeeDTO;
import com.challenge.api.Database.Database;
import com.challenge.api.model.Implementation;
import java.time.Instant;
import java.util.UUID;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl;
    private static UUID createdEmployeeUuid;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/api/v1/employee";
    }

    @AfterEach
    void tearDown() {
        Database.getEmployees().clear(); // erase employee history
    }

    @Test
    @Order(1)
    @DisplayName("Get all employees (empty):") // this returns empty list
    void testGetAllEmployees_EmptyList() {
        ResponseEntity<EmployeeDTO[]> response = restTemplate.getForEntity(baseUrl, EmployeeDTO[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().length);
    }

    @Test
    @Order(2)
    @DisplayName("Create new employee:")
    void testCreateEmployee_Success() {

        Implementation newEmployee = new Implementation( // creates employee data
                "Glinda", "Witch", 75000, 32, "Headwitch", "witchywoman@aol.com", Instant.now(), null);

        ResponseEntity<EmployeeDTO> response = restTemplate.postForEntity(baseUrl, newEmployee, EmployeeDTO.class);//pushes the action through API

        assertEquals(HttpStatus.OK, response.getStatusCode());

        System.out.println("Response body: " + response.getBody());
        System.out.println("Response headers: " + response.getHeaders());

        assertNotNull(response.getBody(), "Response body should not be null"); // if null
        EmployeeDTO createdEmployee = response.getBody(); // check whats being sent
        assertNotNull(createdEmployee.getUuid(), "UUID should not be null"); // asures isnt null
        assertEquals("Glenda", createdEmployee.getFirstName()); // makes sure value match
        assertEquals("Witch", createdEmployee.getLastName());
        assertEquals("Glenda Witch", createdEmployee.getFullName());
        assertEquals(75000, createdEmployee.getSalary());
        assertEquals(32, createdEmployee.getAge());
        assertEquals("Headwitch", createdEmployee.getJobTitle());
        assertEquals("witchywoman@aol.com", createdEmployee.getEmail());
        assertNotNull(createdEmployee.getContractHireDate());
        assertNull(createdEmployee.getContractTerminationDate()); // if not null

        createdEmployeeUuid = createdEmployee.getUuid(); // saves for next
    }

    @Test
    @Order(3)
    @DisplayName("Get all employees:") // actual data
    void testGetAllEmployees_WithData() {

        Implementation employee1 = new Implementation( // added more employees
                "Casper", "Ghost", 80000, 12, "Ghostman", "boo@aol.com", Instant.now(), null);
        Implementation employee2 =
                new Implementation("Harry", "Potter", 90000, 35, "Wizzard", "harry@aol.com", Instant.now(), null);

        restTemplate.postForEntity(baseUrl, employee1, EmployeeDTO.class); // post requests
        restTemplate.postForEntity(baseUrl, employee2, EmployeeDTO.class);

        ResponseEntity<EmployeeDTO[]> response =
                restTemplate.getForEntity(baseUrl, EmployeeDTO[].class); // get requests

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().length);

        EmployeeDTO[] employees = response.getBody();
        assertTrue(employees[0].getFirstName().equals("Casper")
                || employees[0].getFirstName().equals("Harry"));
    }

    @Test
    @Order(4)
    @DisplayName("Get by UUID:")
    void testGetEmployeeByUuid_Success() {
        Implementation newEmployee = new Implementation(
                "Alice", "Wonderland", 85000, 14, "<Magician>", "rabbithole@aol.com", Instant.now(), null);

        ResponseEntity<EmployeeDTO> createResponse =
                restTemplate.postForEntity(baseUrl, newEmployee, EmployeeDTO.class);

        UUID employeeUuid = createResponse.getBody().getUuid();

        ResponseEntity<EmployeeDTO> response =
                restTemplate.getForEntity(baseUrl + "/" + employeeUuid, EmployeeDTO.class); // get request

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        EmployeeDTO employee = response.getBody();
        assertEquals(employeeUuid, employee.getUuid());
        assertEquals("Alice", employee.getFirstName());
        assertEquals("Wonderland", employee.getLastName());
        assertEquals("Magician", employee.getJobTitle());
    }
}
