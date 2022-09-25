package org.lmmrise.transaction.localtranjdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LocalTranJdbcApplication {

    public static void main(String[] args) throws SQLException {
        String plusAmountSQL = "UPDATE T_USER SET amount = amount + 100 WHERE username = ?";
        String minusAmountSQL = "UPDATE T_USER SET amount = amount - 100 WHERE username = ?";

        Connection dbConnection = getDBConnection();
        System.out.println("Begin");
        dbConnection.setAutoCommit(false);

        PreparedStatement plusAmountPS = dbConnection.prepareStatement(plusAmountSQL);
        plusAmountPS.setString(1, "cxk");
        plusAmountPS.executeUpdate();

        simulateError();        // 模拟事务执行到一半出现异常

        PreparedStatement minusAmountPS = dbConnection.prepareStatement(minusAmountSQL);
        minusAmountPS.setString(1, "lxl");
        minusAmountPS.executeUpdate();

        dbConnection.commit();
        System.out.println("Done!");

        plusAmountPS.close();
        minusAmountPS.close();
        dbConnection.close();
    }

    private static void simulateError() throws SQLException {
        throw new SQLException("Simulate some error!");
    }

    private static Connection getDBConnection() throws SQLException {
        String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
        String DB_CONNECTION = "jdbc:mysql://localhost:3306/transaction_test";
        String DB_USER = "root";
        String DB_PASSWORD = "12345678";
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
    }
}
