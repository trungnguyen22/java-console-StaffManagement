/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.john.funix.entity;

import java.io.Serializable;

/**
 * @author Trung Nguyen
 */
public abstract class Employee implements Comparable<Employee>, Serializable {
    private EType eTypeEmployee;
    private String fullName;
    private float salaryRatio;
    private float allowance;

    public Employee() { }

    public Employee(EType eTypeEmployee, String fullName, float salaryRatio, float allowance) {
        this.eTypeEmployee = eTypeEmployee;
        this.fullName = fullName;
        this.salaryRatio = salaryRatio;
        this.allowance = allowance;
    }

    public EType geteTypeEmployee() {
        return eTypeEmployee;
    }

    public void seteTypeEmployee(EType eTypeEmployee) {
        this.eTypeEmployee = eTypeEmployee;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public float getSalaryRatio() {
        return salaryRatio;
    }

    public void setSalaryRatio(float salaryRatio) {
        this.salaryRatio = salaryRatio;
    }

    public float getAllowance() {
        return allowance;
    }

    public void setAllowance(float allowance) {
        this.allowance = allowance;
    }

    public abstract float getSalary();

    @Override
    public int compareTo(Employee emp) {
        return this.fullName.compareTo(emp.fullName);
    }
}
