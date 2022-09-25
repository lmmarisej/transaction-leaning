package org.lmmrise.transaction.localtranjdbc;


import java.sql.*;

public class LocalTranJdbcApplication2 {
    
    public static void main(String[] args) throws SQLException {
        String sql = "SELECT * FROM T_USER where username = 'cxk' FOR UPDATE";
        String plusAmountSQL = "UPDATE T_USER SET amount = ? WHERE username = ?";
        
        Connection dbConnection = getDBConnection();
        System.out.println("Begin session2");
        
        PreparedStatement queryPS = dbConnection.prepareStatement(sql);
        ResultSet rs = queryPS.executeQuery();
        long superManAmount = 0L;
        while (rs.next()) {
            String name = rs.getString("username");
            long amount = rs.getLong("amount");
            System.out.format("{} has amount:{}", name, amount);
            if (name.equals("cxk")) {
                superManAmount = amount;
            }
        }
        
        PreparedStatement updatePS = dbConnection.prepareStatement(plusAmountSQL);
        updatePS.setLong(1, superManAmount + 100);
        updatePS.setString(2, "cxk");
        updatePS.executeUpdate();
        
        System.out.println("Done session2!");
        queryPS.close();
        updatePS.close();
        dbConnection.close();
    }
    
    @SuppressWarnings("all")
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
