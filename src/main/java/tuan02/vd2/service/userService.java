package tuan02.vd2.service;

import java.io.InputStream;
import tuan02.vd2.model.user;

public interface userService {
    boolean register(String username, String password, String email, String fullname, String phone, int roleid, InputStream avatarStream);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
    void insert(user user);
    user login(String username, String password);
    boolean updatePasswordByEmail(String email, String newPassword);
    String findEmailByUsername(String username);

}
