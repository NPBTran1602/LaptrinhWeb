package bttuan4.Controller;

import java.io.File;
import java.io.IOException;

import bttuan4.model.User;
import bttuan4.Service.UserService;
import bttuan4.Service.Impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/dangky")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024) // Giới hạn 5MB
public class Register_Controller extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String fullname = req.getParameter("fullname");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");
        String phone = req.getParameter("phone");
        String roleidStr = req.getParameter("roleid");

        int roleid = 1;
        try {
            roleid = Integer.parseInt(roleidStr);
        } catch (Exception e) {
            roleid = 1;
        }

        // Validate mật khẩu
        if (!password.equals(repassword)) {
            req.setAttribute("alert", "Mật khẩu nhập lại không khớp!");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        // Validate email, username tồn tại
        if (userService.checkExistEmail(email)) {
            req.setAttribute("alert", "Email đã tồn tại!");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }
        if (userService.checkExistUsername(username)) {
            req.setAttribute("alert", "Tài khoản đã tồn tại!");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        // Xử lý upload avatar
        Part avatarPart = req.getPart("avatar");
        String avatarFileName = "uploads/default.png"; // avatar mặc định
        if (avatarPart != null && avatarPart.getSize() > 0) {
            String uploadPath = req.getServletContext().getRealPath("/uploads");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            avatarFileName = "uploads/" + System.currentTimeMillis() + "_" + avatarPart.getSubmittedFileName();
            avatarPart.write(uploadPath + File.separator + avatarFileName.substring(8)); // bỏ "uploads/"
        }

        // Tạo user mới
        User user = new User();
        user.setFullname(fullname);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setRoleid(roleid);
        user.setAvatar(avatarFileName);

        boolean isSuccess = userService.register(user);

        if (isSuccess) {
            resp.sendRedirect(req.getContextPath() + "/Login"); // Redirect đến trang đăng nhập
        } else {
            req.setAttribute("alert", "Lỗi hệ thống!");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        }
    }
}