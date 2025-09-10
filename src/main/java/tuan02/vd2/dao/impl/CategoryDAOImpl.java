package tuan02.vd2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import tuan02.vd2.config.DBconnection;
import tuan02.vd2.dao.CategoryDAO;
import tuan02.vd2.model.Category;

public class CategoryDAOImpl extends DBconnection implements CategoryDAO {

    @Override
    public void insert(Category category) {
        String sql = "INSERT INTO Category(cate_name, icons) VALUES (?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, category.getCatename());
            ps.setString(2, category.getIcon());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(Category category) {
        String sql = "UPDATE Category SET cate_name=?, icons=? WHERE cate_id=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, category.getCatename());
            ps.setString(2, category.getIcon());
            ps.setInt(3, category.getCateid());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Category WHERE cate_id=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Category get(int id) {
        String sql = "SELECT * FROM Category WHERE cate_id=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Category(
                        rs.getInt("cate_id"),
                        rs.getString("cate_name"),
                        rs.getString("icons"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Category get(String name) {
        String sql = "SELECT * FROM Category WHERE cate_name=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Category(
                        rs.getInt("cate_id"),
                        rs.getString("cate_name"),
                        rs.getString("icons"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> getAll() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM Category";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(
                        rs.getInt("cate_id"),
                        rs.getString("cate_name"),
                        rs.getString("icons")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Category> search(String keyword) {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM Category WHERE cate_name LIKE ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(
                        rs.getInt("cate_id"),
                        rs.getString("cate_name"),
                        rs.getString("icons")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}