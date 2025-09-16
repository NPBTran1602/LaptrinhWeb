package bttuan4.DAO;

import bttuan4.model.User;

public interface UserDAO {
    User findByUsername(String username);
    User login(String username, String password);
    boolean insert(User user);
    boolean update(User user);
    boolean updatePassword(String email, String newPass);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
