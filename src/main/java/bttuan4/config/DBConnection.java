package bttuan4.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {
    private final String serverName = "localhost"; // hoặc IP
    private final String dbName = "ltweb";
    private final String portNumber = "1433";
    private final String instance = ""; // nếu có instance thì: "SQLEXPRESS"
    private final String userID = "sa";
    private final String password = "1";

    public Connection getConnection() throws Exception {
        String url;
        if (instance == null || instance.trim().isEmpty()) {
            url = "jdbc:sqlserver://" + serverName + ":" + portNumber
                    + ";databaseName=" + dbName
                    + ";encrypt=false;trustServerCertificate=true";
        } else {
            url = "jdbc:sqlserver://" + serverName + "\\" + instance + ":" + portNumber
                    + ";databaseName=" + dbName
                    + ";encrypt=false;trustServerCertificate=true";
        }

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }

    // Đóng kết nối để tránh leak
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (ps != null) ps.close(); } catch (Exception e) {}
        try { if (conn != null) conn.close(); } catch (Exception e) {}
    }

    public static void main(String[] args) {
        // SQL Insert (bỏ id vì tự tăng)
        String sqlInsert = "INSERT INTO [User] (email, username, fullname, password, avatar, roleid, phone, createdDate) VALUES (?, ?, ?, ?, ?, ?, ?, GETDATE())";
        String sqlSelectAll = "SELECT * FROM [User]";
        String sqlUpdate = "UPDATE [User] SET fullname=? WHERE username=?";
        String sqlDelete = "DELETE FROM [User] WHERE username=?";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = new DBConnection().getConnection();

            // 1. Insert user
            stmt = conn.prepareStatement(sqlInsert);
            stmt.setString(1, "tran@gmail.com");
            stmt.setString(2, "tran123");
            stmt.setString(3, "Nguyen Pham Bao Tran");
            stmt.setString(4, "tran1602#");  // password chưa mã hóa
            stmt.setString(5, "avatar.png");
            stmt.setInt(6, 5);               // roleid: user
            stmt.setString(7, "0909123456"); // phone
            stmt.executeUpdate();
            stmt.close();

            // 2. Update user
            stmt = conn.prepareStatement(sqlUpdate);
            stmt.setString(1, "Nguyen Bao Tran (Updated)");
            stmt.setString(2, "tran123");
            stmt.executeUpdate();
            stmt.close();

            // 3. Select all users
            stmt = conn.prepareStatement(sqlSelectAll);
            rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | "
                        + rs.getString("email") + " | "
                        + rs.getString("username") + " | "
                        + rs.getString("fullname") + " | "
                        + rs.getString("password") + " | "
                        + rs.getString("avatar") + " | "
                        + rs.getInt("roleid") + " | "
                        + rs.getString("phone") + " | "
                        + rs.getDate("createdDate")
                );
            }
            stmt.close();

            // 4. Delete user
            stmt = conn.prepareStatement(sqlDelete);
            stmt.setString(1, "tran123");
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, stmt, rs);
        }
    }
}