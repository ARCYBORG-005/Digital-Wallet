package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDB {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/digital_wallet", "root", "F8or@1975"
            );
            System.out.println("DB Connected!");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}