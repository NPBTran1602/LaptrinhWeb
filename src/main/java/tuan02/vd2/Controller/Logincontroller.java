package tuan02.vd2.Controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import tuan02.vd2.model.user;
import tuan02.vd2.service.userService;
import tuan02.vd2.service.impl.userServiceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/dangnhap")
public class Logincontroller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            // Nếu đã login rồi thì chuyển thẳng sang category list
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");
            return;
        }

        req.getRequestDispatcher("/views/dangnhap.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember"); // giá trị checkbox

        userService service = new userServiceImpl();
        user u = service.login(username, password);

        if (u != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("account", u);

            // Cookie lưu username
            Cookie ckUser = new Cookie("username", u.getUserName());
            ckUser.setMaxAge(60 * 60 * 24 * 7); // 7 ngày
            resp.addCookie(ckUser);

            // Nếu tick "Ghi nhớ mật khẩu" thì lưu password
            if (remember != null && remember.equals("on")) {
                Cookie ckPass = new Cookie("password", password);
                ckPass.setMaxAge(60 * 60 * 24 * 7); // 7 ngày
                resp.addCookie(ckPass);
            } else {
                // Xóa cookie password nếu không tick
                Cookie ckPass = new Cookie("password", "");
                ckPass.setMaxAge(0);
                resp.addCookie(ckPass);
            }

            resp.sendRedirect(req.getContextPath() + "/admin/category/list");
        } else {
            req.setAttribute("alert", "Sai tài khoản hoặc mật khẩu!");
            req.getRequestDispatcher("/views/dangnhap.jsp").forward(req, resp);
        }
    }

}