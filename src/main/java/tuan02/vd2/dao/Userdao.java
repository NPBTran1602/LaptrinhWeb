package tuan02.vd2.dao;

import tuan02.vd2.model.user;

public interface Userdao {
    void insert(user User);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
    boolean updatePasswordByEmail(String email, String newPassword);
    user findByUsernameAndPassword(String username, String password);
    
}