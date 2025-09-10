package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vn.iotstar.Connection.DBConnection;
import vn.iotstar.dao.userDao;
import vn.iotstar.model.User;

public class UserDaoImpl implements userDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public User get(String username) {
        String sql = "SELECT * FROM [users] WHERE username = ?";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);  // gán giá trị từ form
            rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUserName(rs.getString("username"));
                user.setFullName(rs.getString("fullname"));
                user.setPassWord(rs.getString("password"));
                user.setAvatar(rs.getString("avatar"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}