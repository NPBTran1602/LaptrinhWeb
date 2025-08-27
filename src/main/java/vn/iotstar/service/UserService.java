package vn.iotstar.service;
import vn.iotstar.model.User;

public interface UserService {
    User login(String username, String password);  // kiểm tra đăng nhập
    User get(String username);                     // lấy user theo username
}