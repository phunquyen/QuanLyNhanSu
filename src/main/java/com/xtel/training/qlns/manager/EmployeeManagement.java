package com.xtel.training.qlns.manager;

import com.xtel.training.qlns.entities.Employee;
import com.xtel.training.qlns.entities.EmployeeCheck;
import com.xtel.training.qlns.model.EmployeeDao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagement {
    List<Employee> employeeList = new ArrayList<>();
    List<EmployeeCheck> empCheckInList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public void insertData() throws SQLException {
        System.out.print("Nhap so nhan vien: ");
        int numberOfEmployee = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < numberOfEmployee; i++){
            Employee employee = inputInfoEmployee();
            employeeList.add(employee);
        }

        for(Employee employee : employeeList){
            try {
                EmployeeDao.insert(employee);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

//        for(String empCheck : empCheckInList){
//            try {
//                EmployeeDao.insertCheck(empCheck);
//            }catch (SQLException e){
//                e.printStackTrace();
//            }
//        }
    }
    public void insertCheckData() throws SQLException {
        System.out.print("Ma nhan vien muon them: ");
        String code = sc.nextLine();
        EmployeeDao.insertCheck(code);
    }

    public Employee inputCheckEmployee() throws SQLException {
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

//    public String inputEmpCheckIn() throws SQLException {
//        System.out.print("Nhap ngay: ");
//        int day = Integer.parseInt(sc.nextLine());
//        System.out.print("Nhap thang: ");
//        int month = Integer.parseInt(sc.nextLine());
//        System.out.print("Nhap nam: ");
//        int year = Integer.parseInt(sc.nextLine());
//        System.out.print("Nhap gio di: ");
//        int hourIn = Integer.parseInt(sc.nextLine());
//        System.out.print("Nhap gio ve: ");
//        int hourOut = Integer.parseInt(sc.nextLine());
//        System.out.print("Nhap phut di: ");
//        int minuteIn = Integer.parseInt(sc.nextLine());
//        System.out.print("Nhap phut ve: ");
//        int minuteOut = Integer.parseInt(sc.nextLine());
//        System.out.print("Nhap giay di: ");
//        int secondIn = Integer.parseInt(sc.nextLine());
//        System.out.print("Nhap giay ve: ");
//        int secondOut = Integer.parseInt(sc.nextLine());
//        Calendar cal = Calendar.getInstance();
//        Calendar cal2 = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        cal.set(year, month, day, hourIn, minuteIn, secondIn);
//        cal2.set(year, month, day, hourOut, minuteOut, secondOut);
//        String dateTime = sdf.format(cal.getTime());
//        String dateTime2 = sdf.format(cal2.getTime());
//
//        return dateTime;
//    }

//    public String inputEmpCheckOut() throws SQLException {
//        System.out.print("Nhap ngay: ");
//        int day = Integer.parseInt(sc.nextLine());
//        System.out.print("Nhap thang: ");
//        int month = Integer.parseInt(sc.nextLine());
//        System.out.print("Nhap nam: ");
//        int year = Integer.parseInt(sc.nextLine());
//        System.out.print("Nhap gio: ");
//        int hour = Integer.parseInt(sc.nextLine());
//        System.out.print("Nhap phut: ");
//        int minute = Integer.parseInt(sc.nextLine());
//        System.out.print("Nhap giay: ");
//        int second = Integer.parseInt(sc.nextLine());
//        Calendar cal = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        cal.set(year, month, day, hour, minute, second);
//        String dateTime = sdf.format(cal.getTime());
//        return dateTime;
//    }

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
