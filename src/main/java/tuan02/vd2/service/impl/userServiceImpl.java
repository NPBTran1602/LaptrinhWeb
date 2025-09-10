package tuan02.vd2.service.impl;

import tuan02.vd2.dao.Userdao;
import tuan02.vd2.dao.impl.UserdaoImpl;
import tuan02.vd2.model.user;
import tuan02.vd2.service.userService;

public class userServiceImpl implements userService {
    Userdao userDao = new UserdaoImpl();

    @Override
    public boolean register(String username, String password, String email, String fullname, String phone) {
        if (userDao.checkExistUsername(username)) {
            return false;
        }
        long millis = System.currentTimeMillis();   
        java.sql.Date date = new java.sql.Date(millis);
        userDao.insert(new user(email, username, fullname, password, null, 5, phone, date));
        return true;
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return userDao.checkExistPhone(phone);
    }

    @Override
    public void insert(user user) {
        userDao.insert(user);
    }
    
    @Override
    public user login(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }
    
    @Override
    public boolean updatePasswordByEmail(String email, String newPassword) {
        return userDao.updatePasswordByEmail(email, newPassword);
    }


}