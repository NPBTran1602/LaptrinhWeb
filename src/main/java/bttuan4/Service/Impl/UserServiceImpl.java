package bttuan4.Service.Impl;

import bttuan4.DAO.UserDAO;
import bttuan4.DAO.Impl.UserDAO_Impl;
import bttuan4.Service.UserService;
import bttuan4.model.User;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDAO_Impl();

    @Override
    public User login(String username, String password) {
        return userDAO.login(username, password);
    }

    @Override
    public boolean register(User user) {
        return userDAO.insert(user);
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDAO.existsByEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDAO.existsByUsername(username);
    }

    @Override
    public boolean updatePassword(String email, String newPass) {
        return userDAO.updatePassword(email, newPass);
    }

    @Override
    public boolean updateProfile(User user) {
        return userDAO.update(user);
    }
}
