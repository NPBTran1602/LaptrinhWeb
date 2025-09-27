package bttuan4.Controller;

import java.io.File;
import java.io.IOException;

import bttuan4.model.User;
import bttuan4.Service.UserService;
import bttuan4.Service.Impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;

@SuppressWarnings("serial")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024)
public class ProfileController extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        System.out.println("Profile doGet - Session: " + session + ", Account: " + (session != null ? session.getAttribute("account") : "null"));
        
        if (session != null && session.getAttribute("account") != null) {
            req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
        } else {
            System.out.println("Profile doGet - Redirecting to Login due to no session or account");
            resp.sendRedirect(req.getContextPath() + "/Login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(false);
        
        System.out.println("Profile doPost - Session: " + session + ", Account: " + (session != null ? session.getAttribute("account") : "null"));
        
        if (session == null || session.getAttribute("account") == null) {
            System.out.println("Profile doPost - Redirecting to Login due to no session or account");
            resp.sendRedirect(req.getContextPath() + "/Login");
            return;
        }

        User user = (User) session.getAttribute("account");

        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");

        Part avatarPart = req.getPart("avatar");
        String avatarFileName = user.getAvatar(); // Giữ avatar cũ nếu không upload mới

        if (avatarPart != null && avatarPart.getSize() > 0) {
            String uploadPath = req.getServletContext().getRealPath("/uploads");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            avatarFileName = System.currentTimeMillis() + "_" + avatarPart.getSubmittedFileName();
            avatarPart.write(uploadPath + File.separator + avatarFileName);
        }

        user.setFullname(fullname);
        user.setPhone(phone);
        user.setAvatar(avatarFileName);

        boolean success = userService.updateProfile(user);

        if (success) {
            session.setAttribute("account", user);
            req.setAttribute("alert", "Cập nhật thành công!");
        } else {
            req.setAttribute("alert", "Có lỗi khi cập nhật!");
        }

        req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
    }
}