package tuan02.vd2.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import tuan02.vd2.service.userService;
import tuan02.vd2.service.impl.userServiceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/register")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024) // Giới hạn 5MB
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        // Kiểm tra nếu là multipart (dùng Part API)
        String fullname = req.getParameter("fullname");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");
        String phone = req.getParameter("phone");
        String roleidStr = req.getParameter("roleid");
        InputStream avatarStream = null;

        try {
            Collection<Part> parts = req.getParts();
            for (Part part : parts) {
                String fieldName = part.getName();
                if (part.getContentType() == null) { // Field text
                    String value = req.getParameter(fieldName);
                    switch (fieldName) {
                        case "fullname": fullname = value; break;
                        case "username": username = value; break;
                        case "email": email = value; break;
                        case "password": password = value; break;
                        case "repassword": repassword = value; break;
                        case "phone": phone = value; break;
                        case "roleid": roleidStr = value; break;
                    }
                } else if (fieldName.equals("avatar") && part.getSize() > 0) {
                    avatarStream = part.getInputStream();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("alert", "Lỗi upload file!");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        int roleid = 1;
        try {
            roleid = Integer.parseInt(roleidStr);
        } catch (Exception e) {
            roleid = 1;
        }

        userService service = new userServiceImpl();
        String alertMsg = "";

        if (!password.equals(repassword)) {
            alertMsg = "Mật khẩu nhập lại không khớp!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        if (service.checkExistEmail(email)) {
            alertMsg = "Email đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }
        if (service.checkExistUsername(username)) {
            alertMsg = "Tài khoản đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        boolean isSuccess = service.register(username, password, email, fullname, phone, roleid, avatarStream);
        if (isSuccess) {
            resp.sendRedirect(req.getContextPath() + "/dangnhap");
        } else {
            alertMsg = "Lỗi hệ thống!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        }
    }
}