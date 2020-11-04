package com.xtel.training.qlns.model;

import com.xtel.training.qlns.entities.Employee;
import com.xtel.training.qlns.manager.EmployeeManagement;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;

public class EmployeeDao {
    public static void getList() throws SQLException{
        Connection connection = ConnectionFactory.createConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from employee";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                System.out.println(rs.getInt(1) + " " + rs.getInt(2)
                + " " + rs.getString(3) + " " + rs.getInt(4) + " " +rs.getString(5));
            }
        }finally {
            close(connection);
        }
    }

    public static void insert(Employee emp) throws SQLException{
        if((isExistCode(emp.getCode()) || EmployeeManagement.isCheckCharacter)){
            System.out.println("Khong hop le!");
            return;
        };
        Connection connection = ConnectionFactory.createConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into employee(id, code, name, gender, address) values(?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, emp.getId());
            ps.setInt(2, emp.getCode());
            ps.setString(3, emp.getName());
            ps.setInt(4, emp.getGender());
            ps.setString(5, emp.getAddress());
            ps.execute();
        }finally {
            close(ps);
            close(connection);
        }
    }

    public static void delete(int id) throws SQLException{
        Connection connection = ConnectionFactory.createConnection();
        PreparedStatement ps = null;
        try {
            String sql = "delete from employee where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        }finally {
            close(ps);
            close(connection);
        }
    }

    public static void update(Employee emp) throws SQLException{
        Connection connection = ConnectionFactory.createConnection();
        PreparedStatement ps = null;
        try {
            String sql = "update employee set code = ?, name = ?, gender = ?, address = ? where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, emp.getCode());
            ps.setString(2, emp.getName());
            ps.setInt(3, emp.getGender());
            ps.setString(4, emp.getAddress());
            ps.setInt(5, emp.getId());
            ps.execute();
        }finally {
            close(ps);
            close(connection);
        }
    }

    public static void count() throws SQLException{
        Connection connection = ConnectionFactory.createConnection();
        Statement statement = connection.createStatement();
        try {
            String sql = "select count(id) from employee";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt(1));
            }
        }
        finally {
            close(connection);
        }
    }

    public static boolean isExistCode(int code) throws SQLException{
        Connection connection = ConnectionFactory.createConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select count(1) c from employee where code = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, code);
            rs = ps.executeQuery();
            if(rs.next()) {
                if(rs.getInt("c" ) > 0){
                    return true;
                }
            }
        }finally {
            close(rs);
            close(connection);
        }
        return false;
    }

    public static void close(AutoCloseable closeable){
        if(closeable == null) return;
        try {
            closeable.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
