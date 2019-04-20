/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.john.funix.business;

import com.john.funix.entity.Employee;
import com.john.funix.entity.Staff;
import com.john.funix.entity.Teacher;

import java.util.ArrayList;
import java.util.Collections;


/**
 * @author Trung Nguyen
 */
public class EmployeeManagement {

    //store all staff/teacher
    private ArrayList<Employee> listE;

    public EmployeeManagement() {
        listE = new ArrayList<>();
    }

    public ArrayList<Employee> getListE() {
        return listE;
    }

    public void setListE(ArrayList<Employee> listE) {
        this.listE = listE;
    }

    public void addEmployee(Employee emp) {
        //add emp to listE
        listE.add(emp);
    }

    //search by staff/teacher's name
    public ArrayList<Employee> searchByName(String name) {
        //store all matching staff or teacher
        ArrayList<Employee> empFound = new ArrayList<>();
        for (Employee employee : listE) {
            if (employee.getFullName().toUpperCase().contains(name.toUpperCase())) {
                empFound.add(employee);
            }
        }
        return empFound;
    }

    //search by staff/teacher's department/faculty
    public ArrayList<Employee> searchByDept(String dept) {
        ArrayList<Employee> empFound = new ArrayList<>();
        for (Employee employee : listE) {
            if (employee instanceof Staff) {
                Staff staff = (Staff) employee;
                if (staff.getDepartment().toUpperCase().contains(dept.toUpperCase()))
                    empFound.add(staff);
            }
            if (employee instanceof Teacher) {
                Teacher teacher = (Teacher) employee;
                if (teacher.getFaculty().toUpperCase().contains(dept.toUpperCase()))
                    empFound.add(teacher);
            }
        }
        return empFound;
    }

    public ArrayList<Employee> listAll() {
        Collections.sort(listE);
        return listE;
    }
}
