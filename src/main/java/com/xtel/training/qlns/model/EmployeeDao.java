package com.xtel.training.qlns.model;

import com.xtel.training.qlns.entities.Employee;
import com.xtel.training.qlns.manager.EmployeeManagement;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.Calendar;
import java.util.Scanner;

public class EmployeeDao {
    private static int status;
    public static Scanner sc = new Scanner(System.in);

    public static void getList() throws SQLException {
        Connection connection = ConnectionFactory.createConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from employee";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getInt(2)
                        + " " + rs.getString(3) + " " + rs.getInt(4) + " " + rs.getString(5));
            }
        } finally {
            close(connection);
        }
    }

    public static void getReport() throws SQLException {
        Connection connection = ConnectionFactory.createConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from `check`";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getInt(2)
                        + " " + rs.getDate(3) + " " + rs.getDate(4)
                        + " " + rs.getDate(5) + " " + rs.getDate(6));
            }
        } finally {
            close(connection);
        }
    }

    public static void insert(Employee emp) throws SQLException {
        if ((isExistCode(emp.getCode()))) {
            System.out.println("Khong hop le!");
            return;
        }
        ;
        Connection connection = ConnectionFactory.createConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into employee(id, code, name, gender, address) values(?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, emp.getId());
            ps.setString(2, emp.getCode());
            ps.setString(3, emp.getName());
            ps.setInt(4, emp.getGender());
            ps.setString(5, emp.getAddress());
            ps.execute();
        } finally {
            close(ps);
            close(connection);
        }
    }

    public static void insertCheckIn() throws SQLException {
        Connection connection = ConnectionFactory.createConnection();
        PreparedStatement ps = null;
        try {
            System.out.print("Nhap ma nhan vien cua ban de check in: ");
            String code = sc.nextLine();

            if (isExistCode(code)) {
                java.util.Date date = new java.util.Date();

                java.sql.Timestamp sqlTime = new java.sql.Timestamp(date.getTime());
                String sql = "insert into `check`(time_checkin, code_employee, date_check) values(?,?,?)";
                ps = connection.prepareStatement(sql);
                ps.setTimestamp(1, sqlTime);
                ps.setString(2, code);
                ps.setDate(3, new java.sql.Date(date.getTime()));
                ps.execute();
            } else {
                System.out.println("Ma nhan vien khong ton tai !, vui long kiem tra lai");
            }
        } finally {
            close(ps);
            close(connection);
        }
    }

    public static void insertCheckOut() throws SQLException {
        Connection connection = ConnectionFactory.createConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            int hours_In = 0;
            int minutes_In = 0;
            int hours_Out = 0;
            int minutes_Out = 0;

            System.out.print("Nhap ma nhan vien cua ban de check out: ");
            String code = sc.nextLine();
            if (isExistCode(code)) {
                java.util.Date date = new java.util.Date();
                java.sql.Timestamp sqlTime = new java.sql.Timestamp(date.getTime());
                Calendar calendar = Calendar.getInstance();
                Calendar calendar2 = Calendar.getInstance();
                //
//                String sqlGetCheckOut = "select time_checkout from `check` where code_employee = ?";
//                ps = connection.prepareStatement(sqlGetCheckOut);
//                ps.setString(1, code);
//                ps.execute();
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    System.out.println(rs.getTimestamp(1));
//                    Calendar calendar = Calendar.getInstance();
//                    calendar.setTime(rs.getTimestamp(1));
//                    hours_Out = calendar.get(Calendar.HOUR_OF_DAY);
//                    minutes_Out = calendar.get(Calendar.MINUTE);
//                    if (hours_Out < 17 && minutes_Out > 30) {
//                        status = 2;
//                    }
//                }
                //
                String sqlGetCheckIn = "select time_checkin from `check` where code_employee = ?";
                ps = connection.prepareStatement(sqlGetCheckIn);
                ps.setString(1, code);
                ps.execute();
                rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getTimestamp(1));
                    calendar.setTime(rs.getTimestamp(1));
                    hours_In = calendar.get(Calendar.HOUR_OF_DAY);
                    minutes_In = calendar.get(Calendar.MINUTE);
                    if (hours_In > 8 && minutes_In > 45) {
                        status = 1;
                    }
                }
                calendar2.setTime(sqlTime);
                hours_Out = calendar.get(Calendar.HOUR_OF_DAY);
//                minutes_Out = calendar.get(Calendar.MINUTE);
                //
                if (hours_Out < 17 && minutes_Out < 30) status = 2;
                if (hours_In - hours_Out >= 8) status = 3;
                //
//                System.out.println("status: " + status);
                String sql = "update `check` set time_checkout = ?, status = ? where code_employee = ? and date_check = ? ";
                ps = connection.prepareStatement(sql);
                ps.setTimestamp(1, sqlTime);
                ps.setInt(2, status);
                ps.setString(3, code);
                ps.setDate(4, new java.sql.Date(date.getTime()));
                ps.execute();
                //
            } else {
                System.out.println("Ma nhan vien khong ton tai !, vui long kiem tra lai");
            }
        } finally {
            close(ps);
            close(connection);
        }
    }

//    public static boolean ischeckStatusOne() throws SQLException{
//        Connection connection = ConnectionFactory.createConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            System.out.print("Nhap ma nhan vien cua ban de check out: ");
//            String code = sc.nextLine();
//            if (isExistCode(code)) {
//                String sql = "select time_checkin from `check` where code_employee = ?";
//                ps = connection.prepareStatement(sql);
//                ps.setString(1, code);
//                ps.execute();
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    System.out.println(rs.getTimestamp(1));
//                    Calendar calendar = Calendar.getInstance();
//                    calendar.setTime(rs.getTimestamp(1));
//                    int hours = calendar.get(Calendar.HOUR_OF_DAY);
//                    int minutes = calendar.get(Calendar.MINUTE);
//                    if(hours > 8 && minutes > 45){
//                        return true;
//                    }
//                }
//            } else {
//                System.out.println("Ma nhan vien khong ton tai !, vui long kiem tra lai");
//            }
//        }finally {
//            close(ps);
//            close(connection);
//        }
//        return false;
//    }

    public static void delete(int id) throws SQLException {
        Connection connection = ConnectionFactory.createConnection();
        PreparedStatement ps = null;
        try {
            String sql = "delete from employee where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } finally {
            close(ps);
            close(connection);
        }
    }

    public static void update(Employee emp) throws SQLException {
        Connection connection = ConnectionFactory.createConnection();
        PreparedStatement ps = null;
        try {
            String sql = "update employee set code = ?, name = ?, gender = ?, address = ? where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, emp.getCode());
            ps.setString(2, emp.getName());
            ps.setInt(3, emp.getGender());
            ps.setString(4, emp.getAddress());
            ps.setInt(5, emp.getId());
            ps.execute();
        } finally {
            close(ps);
            close(connection);
        }
    }

    public static void count() throws SQLException {
        Connection connection = ConnectionFactory.createConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "select count(id) from employee";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt(1));
            }
        } finally {
            close(connection);
        }
    }

    public static boolean isExistCode(String code) throws SQLException {
        Connection connection = ConnectionFactory.createConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select count(1) c from employee where code = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, code);
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt("c") > 0) {
                    return true;
                }
            }
        } finally {
            close(rs);
            close(connection);
        }
        return false;
    }

    public static void close(AutoCloseable closeable) {
        if (closeable == null) return;
        try {
            closeable.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
