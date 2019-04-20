/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.john.funix.entity;


import com.john.funix.business.AllowanceCalulator;

/**
 * @author Trung Nguyen
 */
public class Teacher extends Employee {

    private String faculty;//khoa
    private EDegree degree;//trình độ
    private int teachingHours;//số tiết dạy

    public Teacher() {
    }

    public Teacher(EType eTypeEmployee, String fullName,
                   String faculty, EDegree degree,
                   float salaryRatio, float allowance, int teachingHours) {
        super(eTypeEmployee, fullName, salaryRatio, allowance);
        this.faculty = faculty;
        this.degree = degree;
        this.teachingHours = teachingHours;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public EDegree getDegree() {
        return degree;
    }

    public void setDegree(EDegree degree) {
        this.degree = degree;
    }

    public int getTeachingHours() {
        return teachingHours;
    }

    public void setTeachingHours(int teachingHours) {
        this.teachingHours = teachingHours;
    }

    //sal=Hệ số lương*730+phụ cấp+số tiết dạy*45.
    @Override
    public float getSalary() {
        float sal;
        sal = this.getSalaryRatio() * 730 + this.getAllowance() + this.teachingHours * 45;
        return sal;
    }

    @Override
    public String toString() {
        return this.geteTypeEmployee().type + ", " +
                this.getFullName() + ", " +
                this.getFaculty() + ", " +
                this.getDegree() + ", " +
                this.getSalaryRatio() + ", " +
                this.getAllowance() + ", " +
                this.getTeachingHours() + ", " +
                this.getSalary() + "\n";
    }

}
