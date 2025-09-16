package bttuan4.Service;

import bttuan4.model.User;

public interface UserService {
    User login(String username, String password);
    boolean register(User user);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean updatePassword(String email, String newPass);
    boolean updateProfile(User user);
}
