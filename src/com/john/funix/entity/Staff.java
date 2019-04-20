/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.john.funix.entity;


/**
 * @author Trung Nguyen
 */
public class Staff extends Employee {
    private String department;
    private float noOfWorkingDay;//số ngày công
    private EPosition position;//chức vụ    

    public Staff() {

    }

    public Staff(EType eTypeEmployee, String fullName,
                 String department, EPosition position,
                 float salaryRatio, float allowance, float noOfWorkingDay) {
        super(eTypeEmployee, fullName, salaryRatio, allowance);
        this.department = department;
        this.noOfWorkingDay = noOfWorkingDay;
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public float getNoOfWorkingDay() {
        return noOfWorkingDay;
    }

    public void setNoOfWorkingDay(float noOfWorkingDay) {
        this.noOfWorkingDay = noOfWorkingDay;
    }

    public EPosition getPosition() {
        return position;
    }

    public void setPosition(EPosition position) {
        this.position = position;
    }

    //sal=Hệ số lương*730+phụ cấp+số ngày công*30;
    @Override
    public float getSalary() {
        float sal;
        sal = this.getSalaryRatio() * 730 + this.getAllowance() + this.noOfWorkingDay * 30;
        return sal;
    }

    @Override
    public String toString() {
        return this.geteTypeEmployee().type + ", " +
                this.getFullName() + ", " +
                this.getDepartment() + ", " +
                this.getPosition() + ", " +
                this.getSalaryRatio() + ", " +
                this.getAllowance() + ", " +
                this.getNoOfWorkingDay() + ", " +
                this.getSalary() + "\n";

    }

}
