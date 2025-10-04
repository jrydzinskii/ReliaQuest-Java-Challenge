package com.challenge.api.DTO;

import com.challenge.api.model.Employee;
import java.time.Instant;
import java.util.UUID;

public class EmployeeDTO {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String fullName;
    private Integer salary;
    private Integer age;
    private String jobTitle;
    private String email;
    private Instant contractHireDate;
    private Instant contractTerminationDate;

    public EmployeeDTO() {}

    public EmployeeDTO(Employee p) {
        this.uuid = p.getUuid();
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.fullName = p.getFullName();
        this.salary = p.getSalary();
        this.age = p.getAge();
        this.jobTitle = p.getJobTitle();
        this.email = p.getEmail();
        this.contractHireDate = p.getContractHireDate();
        this.contractTerminationDate = p.getContractTerminationDate();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getContractHireDate() {
        return contractHireDate;
    }

    public void setContractHireDate(Instant date) {
        this.contractHireDate = date;
    }

    public Instant getContractTerminationDate() {
        return contractTerminationDate;
    }

    public void setContractTerminationDate(Instant date) {
        this.contractTerminationDate = date;
    }
}
