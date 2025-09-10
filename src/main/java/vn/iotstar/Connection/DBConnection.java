package vn.iotstar.Connection;

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

    public static void main(String[] args) {
        // SQL Insert (đầy đủ 5 cột, bỏ id vì tự tăng)
        String sqlInsert = "INSERT INTO users (email, username, fullname, password, avatar) VALUES (?, ?, ?, ?, ?)";
        String sqlSelectAll = "SELECT * FROM users";

        try {
            // connect to database
            Connection conn = new DBConnection().getConnection();

            // create statement to insert
            PreparedStatement stmt = conn.prepareStatement(sqlInsert);
            stmt.setString(1, "tran@gmail.com");
            stmt.setString(2, "tran123");
            stmt.setString(3, "Nguyen Pham Bao Tran");
            stmt.setString(4, "tran1602#"); // password chưa mã hoá
            stmt.setString(5, "avatar.png"); // có thể là link ảnh

            stmt.executeUpdate();

            // select all
            stmt = conn.prepareStatement(sqlSelectAll);

            // get data from table
            ResultSet rs = stmt.executeQuery();

            // show data
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | "
                        + rs.getString("email") + " | "
                        + rs.getString("username") + " | "
                        + rs.getString("fullname") + " | "
                        + rs.getString("password") + " | "
                        + rs.getString("avatar")
                );
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}