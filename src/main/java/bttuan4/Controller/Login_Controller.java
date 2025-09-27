package bttuan4.Controller;

import java.io.IOException;

import bttuan4.model.User;
import bttuan4.Service.UserService;
import bttuan4.Service.Impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@SuppressWarnings("serial")
public class Login_Controller extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/Login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User u = userService.login(username, password);
        System.out.println("Login attempt for " + username + ", User: " + u);

        if (u != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("account", u);
            System.out.println("Redirecting to /profile");
            resp.sendRedirect(req.getContextPath() + "/profile"); // Redirect đến /profile
        } else {
            req.setAttribute("alert", "Sai tài khoản hoặc mật khẩu!");
            req.getRequestDispatcher("/views/Login.jsp").forward(req, resp);
        }
    }
}