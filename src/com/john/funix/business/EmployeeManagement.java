/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.john.funix.business;

import com.john.funix.entity.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


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

    public void mapFileDataToModel(List<String> listString) {
        for (String strItem : listString) {
            String[] strArray = strItem.split(", ");
            if (strArray[0].equals(EType.STAFF.getType())) {
                mapFileDataToStaff(strArray);
            } else if (strArray[0].equals(EType.TEACHER.getType())) {
                mapFileDataToTeacher(strArray);
            }
        }
    }

    private void mapFileDataToStaff(String[] strArray) {
        Staff staff = new Staff(
                EType.from(strArray[0]),
                strArray[1],
                strArray[2],
                EPosition.from(strArray[3]),
                Float.parseFloat(strArray[4]),
                Float.parseFloat(strArray[5]),
                Float.parseFloat(strArray[6])
        );
        listE.add(staff);
    }

    private void mapFileDataToTeacher(String[] strArray) {
        Teacher teacher = new Teacher(
                EType.from(strArray[0]),
                strArray[1],
                strArray[2],
                EDegree.from(strArray[3]),
                Float.parseFloat(strArray[4]),
                Float.parseFloat(strArray[5]),
                Integer.parseInt(strArray[6])
        );
        listE.add(teacher);
    }
}
