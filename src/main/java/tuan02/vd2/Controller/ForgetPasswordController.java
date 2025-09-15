package tuan02.vd2.Controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import tuan02.vd2.service.userService;
import tuan02.vd2.service.impl.userServiceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/forgetpassword")
public class ForgetPasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/forgetpassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action"); 
        userService service = new userServiceImpl();

        if ("checkEmail".equals(action)) {
            String email = req.getParameter("email");
            String username = req.getParameter("username"); // lấy username từ form

            String emailFromDb = service.findEmailByUsername(username);

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

            boolean isSuccess = service.updatePasswordByEmail(email, newPass);
            if (isSuccess) {
                req.setAttribute("alert", "Cập nhật mật khẩu thành công, hãy đăng nhập lại!");
                req.getRequestDispatcher("/views/dangnhap.jsp").forward(req, resp);
            } else {
                req.setAttribute("email", email);
                req.setAttribute("alert", "Có lỗi khi cập nhật mật khẩu!");
                req.getRequestDispatcher("/views/resetpassword.jsp").forward(req, resp);
            }
        }
    }
}