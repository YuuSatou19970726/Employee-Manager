package com.example.employeemanager.scheme;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private String name;
    private String birthDay;
    private String address;
    private String email;
    private String phoneNumber;
    private String seniority;
    private String salary;
    private String manager;

    public Employee(String name, String address, String phoneNumber, String email){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Employee(String name, String birthDay, String address, String email, String phoneNumber, String seniority, String salary) {
        this.name = name;
        this.birthDay = birthDay;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.seniority = seniority;
        this.salary = salary;
    }
}
