package tuan02.vd2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import tuan02.vd2.config.DBconnection;
import tuan02.vd2.dao.Userdao;
import tuan02.vd2.model.user;

public class UserdaoImpl implements Userdao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public void insert(user User) {
        String sql = "INSERT INTO [User](email, username, fullname, password, avatar, roleid, phone, createddate) VALUES (?,?,?,?,?,?,?,?)";
        try {
            conn = new DBconnection().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, User.getEmail());
            ps.setString(2, User.getUserName());
            ps.setString(3, User.getFullName());
            ps.setString(4, User.getPassWord());
            ps.setString(5, User.getAvatar());
            ps.setInt(6, User.getRoleid());
            ps.setString(7, User.getPhone());
            ps.setDate(8, User.getCreatedDate());
            ps.executeUpdate();
        } catch (Exception e) { 
            e.printStackTrace(); 
        } finally {
            DBconnection.close(conn, ps, rs);
        }
    }

    @Override
    public boolean checkExistEmail(String email) {
        String query = "SELECT * FROM [User] WHERE email = ?";
        try {
            conn = new DBconnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) { 
            e.printStackTrace(); 
        } finally {
            DBconnection.close(conn, ps, rs);
        }
        return false;
    }

    @Override
    public boolean checkExistUsername(String username) {
        String query = "SELECT * FROM [User] WHERE username = ?";
        try {
            conn = new DBconnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) { 
            e.printStackTrace(); 
        } finally {
            DBconnection.close(conn, ps, rs);
        }
        return false;
    }

    @Override
    public boolean checkExistPhone(String phone) {
        String query = "SELECT * FROM [User] WHERE phone = ?";
        try {
            conn = new DBconnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, phone);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) { 
            e.printStackTrace(); 
        } finally {
            DBconnection.close(conn, ps, rs);
        }
        return false;
    }

    // ✅ Thêm hàm login
    public user findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM [User] WHERE username=? AND password=?";
        try {
            conn = new DBconnection().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                user u = new user();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setUserName(rs.getString("username"));
                u.setFullName(rs.getString("fullname"));
                u.setPassWord(rs.getString("password"));
                u.setAvatar(rs.getString("avatar"));
                u.setRoleid(rs.getInt("roleid"));
                u.setPhone(rs.getString("phone"));
                u.setCreatedDate(rs.getDate("createddate"));
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBconnection.close(conn, ps, rs);
        }
        return null;
    }
    @Override
    public boolean updatePasswordByEmail(String email, String newPassword) {
        String sql = "UPDATE [User] SET password=? WHERE email=?";
        try {
            conn = new DBconnection().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, newPassword);
            ps.setString(2, email);
            int row = ps.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBconnection.close(conn, ps, rs);
        }
        return false;
    }

}