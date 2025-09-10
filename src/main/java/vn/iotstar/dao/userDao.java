package vn.iotstar.dao;

import vn.iotstar.model.User;

public interface userDao {
    User get(String username);
}