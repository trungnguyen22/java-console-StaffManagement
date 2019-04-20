package tests;

import com.john.funix.business.AllowanceCalulator;
import com.john.funix.business.EmployeeManagement;
import com.john.funix.entity.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class EmployeeManagementTest {

    private EmployeeManagement management;

    public void mockupData() {
        management = new EmployeeManagement();

        Staff staffDeveloper = new Staff();
        staffDeveloper.setFullName("Nguyen Quoc Trung");
        staffDeveloper.setDepartment("Information Technology");
        staffDeveloper.setNoOfWorkingDay(23);
        staffDeveloper.setPosition(EPosition.STAFF);
        staffDeveloper.setAllowance(AllowanceCalulator.calculateAllowance(staffDeveloper));
        System.out.println("Developer Salary: " + staffDeveloper.getSalary());

        Staff staffDesigner = new Staff();
        staffDesigner.setFullName("Nguyen Hoang Phu");
        staffDesigner.setDepartment("Designer");
        staffDesigner.setNoOfWorkingDay(19);
        staffDesigner.setPosition(EPosition.HEAD);
        staffDesigner.setAllowance(AllowanceCalulator.calculateAllowance(staffDesigner));
        System.out.println("Designer Salary: " + staffDesigner.getSalary());


        Teacher mathsTeacher = new Teacher();
        mathsTeacher.setFullName("Tran Chi Lam");
        mathsTeacher.setFaculty("Maths");
        mathsTeacher.setTeachingHours(192);
        mathsTeacher.setDegree(EDegree.MASTER);
        mathsTeacher.setAllowance(AllowanceCalulator.calculateAllowance(mathsTeacher));
        System.out.println("Maths Teacher Salary: " + mathsTeacher.getSalary());

        management.addEmployee(staffDeveloper);
        management.addEmployee(staffDesigner);
        management.addEmployee(mathsTeacher);
    }

    @Test
    public void searchByName() {
        mockupData();
        // Assert statements
        assertEquals(1, management.searchByName("Trung").size());
        assertEquals(2, management.searchByName("Nguyen").size());
    }

    @Test
    public void searchByDept() {
        mockupData();
        // Assert statements
        assertEquals(1, management.searchByDept("Information Technology").size());
        assertEquals(2, management.searchByDept("t").size());
    }

    @Test
    public void getSalaryTest() {
        mockupData();
        // Assert statements
        // dev salary: 1190, designer: 2570, teacher: 9140
        List<Employee> employeeList = management.getListE();
        assertEquals(1190, employeeList.get(0).getSalary(), 0); // Dev
        assertEquals(2570, employeeList.get(1).getSalary(), 0); // Designer
        assertEquals(9140, employeeList.get(2).getSalary(), 0); // Maths Teacher
    }
}