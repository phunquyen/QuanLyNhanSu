package com.xtel.training.qlns.manager;

import com.xtel.training.qlns.entities.Employee;
import com.xtel.training.qlns.model.EmployeeDao;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class EmployeeManagement {
    List<Employee> employeeList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public void insertData() throws SQLException {
        System.out.print("Nhap so nhan vien: ");
        int numberOfEmployee = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < numberOfEmployee; i++) {
            Employee employee = inputInfoEmployee();
            employeeList.add(employee);
        }

        for (Employee employee : employeeList) {
            try {
                EmployeeDao.insert(employee);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Employee inputInfoEmployee() throws SQLException {
        System.out.print("Nhap ma nhan vien: ");
        String code = sc.nextLine();
        while (!code.matches("\\d{5}")) {
            System.out.print("Nhap lai ma nhan vien: ");
            code = sc.nextLine();
        }
        System.out.print("Ten nhan vien: ");
        String name = sc.nextLine();
        System.out.print("Gioi tinh: ");
        int gender = Integer.parseInt(sc.nextLine());
        System.out.print("Dia chi: ");
        String address = sc.nextLine();
        return new Employee(0, code, name, gender, address);
    }

    public void deleteData() throws SQLException {
        System.out.print("Ma nhan vien muon xoa: ");
        int id = Integer.parseInt(sc.nextLine());
        EmployeeDao.delete(id);
    }

    public void updateData() throws SQLException {
        System.out.print("Nhap id nhan vien can sua: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Ma nhan vien: ");
        String code = sc.nextLine();
        System.out.print("Ten nhan vien: ");
        String name = sc.nextLine();
        System.out.print("Gioi tinh: ");
        int gender = Integer.parseInt(sc.nextLine());
        System.out.print("Dia chi: ");
        String address = sc.nextLine();
        Employee employee = new Employee(id, code, name, gender, address);
        EmployeeDao.update(employee);
    }
}
