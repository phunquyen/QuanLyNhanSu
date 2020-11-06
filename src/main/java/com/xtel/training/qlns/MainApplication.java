package com.xtel.training.qlns;

import com.xtel.training.qlns.entities.Employee;
import com.xtel.training.qlns.manager.EmployeeManagement;
import com.xtel.training.qlns.model.EmployeeDao;

import java.sql.SQLException;
import java.util.Scanner;

public class MainApplication {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        EmployeeManagement empManagement = new EmployeeManagement();
        do {
            System.out.println("");
            System.out.println("1. Hien thi thong tin nhan vien. ");
            System.out.println("2. Them nhan vien.");
            System.out.println("3. Sua nhan vien.");
            System.out.println("4. Xoa nhan vien.");
            System.out.println("5. So luong nhan vien.");
            System.out.println("6. Bao cao chi tiet cham cong nhan vien. ");
            System.out.println("7. Cham cong di. ");
            System.out.println("8. Cham cong ve. ");

            System.out.print("Lua chon: ");
            int option = Integer.parseInt(sc.nextLine());

            switch (option) {
                case 1:
                    EmployeeDao.getList();
                    break;
                case 2:
                    empManagement.insertData();
                    break;
                case 3:
                    empManagement.updateData();
                    break;
                case 4:
                    empManagement.deleteData();
                    break;
                case 5:
                    EmployeeDao.count();
                    break;
                case 6:
                    EmployeeDao.getReport();
                    break;
                case 7:
                    EmployeeDao.insertCheckIn();
                    break;
                case 8:
                    EmployeeDao.insertCheckOut();
                    break;
                default:
                    System.exit(0);
                    break;
            }
        }
        while (true);
    }
}
