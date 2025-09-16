package bttuan4.Controller;

import java.io.IOException;

import bttuan4.Service.UserService;
import bttuan4.Service.Impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/forgetpass")
public class ForgetPassController extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/forgetpassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("checkEmail".equals(action)) {
            String email = req.getParameter("email");
            String username = req.getParameter("username");

            String emailFromDb = userService.checkExistUsername(username) 
                                   ? userService.login(username, "").getEmail() 
                                   : null;

            if (emailFromDb != null && emailFromDb.equals(email)) {
                req.setAttribute("email", email);
                req.getRequestDispatcher("/views/resetpassword.jsp").forward(req, resp);
            } else {
                req.setAttribute("alert", "Email không khớp với tài khoản!");
                req.getRequestDispatcher("/views/forgetpassword.jsp").forward(req, resp);
            }
        }

        if ("resetPassword".equals(action)) {
            String email = req.getParameter("email");
            String newPass = req.getParameter("password");
            String confirmPass = req.getParameter("confirmPassword");

            if (!newPass.equals(confirmPass)) {
                req.setAttribute("email", email);
                req.setAttribute("alert", "Mật khẩu xác nhận không khớp!");
                req.getRequestDispatcher("/views/resetpassword.jsp").forward(req, resp);
                return;
            }

            boolean isSuccess = userService.updatePassword(email, newPass);
            if (isSuccess) {
                req.setAttribute("alert", "Cập nhật mật khẩu thành công, hãy đăng nhập lại!");
                req.getRequestDispatcher("/views/Login.jsp").forward(req, resp);
            } else {
                req.setAttribute("email", email);
                req.setAttribute("alert", "Có lỗi khi cập nhật mật khẩu!");
                req.getRequestDispatcher("/views/resetpassword.jsp").forward(req, resp);
            }
        }
    }
}
