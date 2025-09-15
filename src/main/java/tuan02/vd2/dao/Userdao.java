package tuan02.vd2.dao;

import java.io.InputStream;
import tuan02.vd2.model.user;

public interface Userdao {
    void insert(user User, InputStream avatarStream);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
    boolean updatePasswordByEmail(String email, String newPassword);
    user findByUsernameAndPassword(String username, String password);
	void insert(user User);
	String findEmailByUsername(String username);

}