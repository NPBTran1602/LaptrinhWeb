package tuan02.vd2.service;

import tuan02.vd2.model.user;

public interface userService {
    void insert(user User);
    boolean register(String username, String password, String email, String fullname, String phone);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
    boolean updatePasswordByEmail(String email, String newPassword);
    user login(String username, String password);
}