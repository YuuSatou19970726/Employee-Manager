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
    private int seniority;
    private String salary;
    private String manager;
}
