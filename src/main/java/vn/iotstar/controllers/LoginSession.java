package vn.iotstar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class LoginSession
 */
@WebServlet("/LoginSession")
public class LoginSession extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginSession() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("tran".equals(username) && "1602".equals(password)) {
            out.print("Chào mừng bạn, " + username);

            HttpSession session = request.getSession();
            session.setAttribute("name", username);
        } else {
            request.setAttribute("errorMessage", "Tài khoản hoặc mật khẩu không chính xác");
            request.getRequestDispatcher("Session.html").forward(request, response);
        }

    }
}