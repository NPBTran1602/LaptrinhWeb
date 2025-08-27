package tuan02.vd2.dao;

import tuan02.vd2.model.user;

public interface Userdao {
    void insert(user User);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
    user findByUsernameAndPassword(String username, String password);
}
