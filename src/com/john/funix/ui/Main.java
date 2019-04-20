/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.john.funix.ui;

import com.john.funix.business.AllowanceCalulator;
import com.john.funix.business.EmployeeManagement;
import com.john.funix.entity.*;
import com.john.funix.utils.FileUtils;
import com.john.funix.utils.InputHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Trung Nguyen
 */
public class Main {

    public static void main(String[] args) {
        String fileName = FileUtils.LIST_EMPLOYEE_FILE_NAME;
        // create employee management object
        EmployeeManagement empMan = new EmployeeManagement();
        // read data from file
        //empMan.setListE((ArrayList<Employee>) FileUtils.<Employee>readFile(fileName));
        List<String> listEmployeeString = FileUtils.readListString(fileName);
        empMan.mapFileDataToModel(listEmployeeString);

        //menu
        Scanner scan = new Scanner(System.in);
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("University Staff Management 1.0");
            System.out.println("\t1.Add staff");
            System.out.println("\t2.Search staff by name");
            System.out.println("\t3.Search staff by department/faculty");
            System.out.println("\t4.Display all staff");
            System.out.println("\t5.Edit staff/teacher");
            System.out.println("\t6.Exit");
            System.out.print("Select function (1,2,3,4,5, or 6): ");
            int choice = scan.nextInt();
            switch (choice) {
                case 1://add staff/teacher
                    Employee emp = createNewEmployee();
                    float allowance = AllowanceCalulator.calculateAllowance(emp);
                    emp.setAllowance(allowance);
                    empMan.addEmployee(emp);
                    // FileUtils.writeFile(empMan.listAll(), fileName);
                    FileUtils.writeListAsString(emp, fileName);
                    break;
                case 2://search by name
                    display(searchByName(empMan));
                    break;
                case 3://search by dept
                    display(searchByDept(empMan));
                    break;
                case 4://display all
                    ArrayList<Employee> listE = empMan.listAll();
                    display(listE);
                    break;
                case 5:// edit info
                    ArrayList<Employee> foundByName = searchByName(empMan);
                    display(foundByName);
                    if (foundByName.size() > 0) {
                        if (foundByName.size() > 1) {
                            scan = new Scanner(System.in);
                            int pick = 0;
                            while (pick <= 0 || pick >= foundByName.size()) {
                                pick = InputHelper.inputForPositiveIntegerNumber("Select a staff: ", scan);
                                if (pick <= 0 || pick >= foundByName.size()) {
                                    String message = "Please enter a number < %d and >= 0";
                                    System.out.println(String.format(message, foundByName.size()));
                                }
                            }
                            Employee employee = foundByName.get(pick);
                            editStaff(scan, employee);
                        } else {
                            scan.nextLine();
                            editStaff(scan, foundByName.get(0));
                        }
                        FileUtils.clearFile(fileName);
                        FileUtils.writeFile(empMan.listAll(), fileName);
                    }
                    break;
                case 6://exit
                    keepRunning = false;
            }
        }
    }


    //create an employee by inputing it's attribute values from keyboard
    private static Employee createNewEmployee() {
        System.out.print("Do you want to create a Staff or a Teacher (enter S for Staff, otherwise for Teacher)?");

        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();

        // InputHelper is my helper class for validating string & number inputs

        String name = InputHelper.inputForName("Name: ", scan);
        float salaryRatio = (float) InputHelper.inputForPositiveDoubleNumber("Salary ratio: ", scan);

        if (choice.equalsIgnoreCase("s")) {
            Staff s = new Staff();
            s.seteTypeEmployee(EType.STAFF);
            s.setFullName(name);
            s.setSalaryRatio(salaryRatio);

            s.setDepartment(InputHelper.inputForName("Department: ", scan));
            int position = InputHelper.inputForPositiveIntegerNumber(
                    "Position (1=HEAD; 2=VICE HEAD; 3=STAFF): ",
                    scan);
            s.setPosition(EPosition.from(position));
            s.setNoOfWorkingDay(InputHelper.inputForPositiveIntegerNumber("Number of working days: ", scan));

            return s;
        } else {
            Teacher t = new Teacher();
            t.seteTypeEmployee(EType.TEACHER);
            t.setFullName(name);
            t.setSalaryRatio(salaryRatio);

            t.setFaculty(InputHelper.inputForName("Faculty: ", scan));

            int degree = InputHelper.inputForPositiveIntegerNumber("Position (1=BACHELOR; 2=MASTER; 3=DOCTOR): ", scan);
            t.setDegree(EDegree.from(degree));

            t.setTeachingHours(InputHelper.inputForPositiveIntegerNumber("Number of working days: ", scan));
            return t;
        }
    }

    //display a list of employee
    private static void display(ArrayList<Employee> listE) {
        if (listE.size() == 0) {
            System.out.println("Not Found");
            return;
        }
        System.out.println("Results:");
        System.out.println("Name, Fac/Dept, Deg/Pos, Sal Ratio, Allowance, T.Hours/W.Days, Salary");
        for (Employee e : listE) {
            System.out.println(e);
        }
    }

    private static void editStaff(Scanner scan, Employee employee) {
        System.out.println("\t\t====================EDIT-INFO====================");
        System.out.println(String.format("Current staff/teacher: %s", employee.toString()));
        employee.setFullName(InputHelper.inputForName("Name: ", scan));
        employee.setSalaryRatio((float) InputHelper.inputForPositiveDoubleNumber("Salary ratio: ", scan));

        if (employee instanceof Staff) {
            ((Staff) employee).setDepartment(InputHelper.inputForName("Department: ", scan));
            int position = InputHelper.inputForPositiveIntegerNumber(
                    "Position (1=HEAD; 2=VICE HEAD; 3=STAFF): ",
                    scan);
            ((Staff) employee).setPosition(EPosition.from(position));
            ((Staff) employee).setNoOfWorkingDay(InputHelper.inputForPositiveIntegerNumber("Number of working days: ", scan));
        } else if (employee instanceof Teacher) {
            ((Teacher) employee).setFaculty(InputHelper.inputForName("Faculty: ", scan));
            int position = InputHelper.inputForPositiveIntegerNumber(
                    "Position (1=BACHELOR; 2=MASTER; 3=DOCTOR): ",
                    scan);
            ((Teacher) employee).setDegree(EDegree.from(position));
            ((Teacher) employee).setTeachingHours(InputHelper.inputForPositiveIntegerNumber("Number of working days: ", scan));
        }
    }

    private static ArrayList<Employee> searchByDept(EmployeeManagement empMan) {
        System.out.print("\tEnter dept/fac to search: ");
        Scanner scan = new Scanner(System.in);
        String dept = scan.nextLine();
        return empMan.searchByDept(dept);
    }

    private static ArrayList<Employee> searchByName(EmployeeManagement employeeManagement) {
        System.out.print("\tEnter name to search: ");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
        return employeeManagement.searchByName(name);
    }
}
