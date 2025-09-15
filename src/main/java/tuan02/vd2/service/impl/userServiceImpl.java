package tuan02.vd2.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Date;

import tuan02.vd2.dao.Userdao;
import tuan02.vd2.dao.impl.UserdaoImpl;
import tuan02.vd2.model.user;
import tuan02.vd2.service.userService;

public class userServiceImpl implements userService {
    Userdao userDao = new UserdaoImpl();

    @Override
    public boolean register(String username, String password, String email, String fullname,
                            String phone, int roleid, InputStream avatarStream) {
        if (userDao.checkExistUsername(username)) {
            return false;
        }
        long millis = System.currentTimeMillis();   
        java.sql.Date date = new java.sql.Date(millis);

        String avatarPath = null;
        if (avatarStream != null) {
            // Lưu file tạm thời
            String uploadDir = "/upload/tmp/";
            String realPath = System.getProperty("catalina.base") + "/webapps/tuan02.vd2" + uploadDir;
            File dir = new File(realPath);
            if (!dir.exists()) dir.mkdirs();
            String fileName = System.currentTimeMillis() + "_" + username + ".jpg";
            File file = new File(realPath + fileName);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = avatarStream.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            avatarPath = uploadDir + fileName;
        }

        user newUser = new user(email, username, fullname, password, avatarPath, roleid, phone, date);
        userDao.insert(newUser, avatarStream); // Truyền cả InputStream nếu dùng BLOB

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
        userDao.insert(user, null); // Nếu không có avatar
    }
    
    @Override
    public user login(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }
    
    @Override
    public boolean updatePasswordByEmail(String email, String newPassword) {
        return userDao.updatePasswordByEmail(email, newPassword);
    }
    
    @Override
    public String findEmailByUsername(String username) {
        return userDao.findEmailByUsername(username);
    }

}