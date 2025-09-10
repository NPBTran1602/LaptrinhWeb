package vn.iotstar.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ProfileSession
 */
@WebServlet("/HelloSession")
public class HelloSession extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HelloSession() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {

	    response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = response.getWriter();

	    HttpSession session = request.getSession(false);

	    if (session != null) {
	        String name = (String) session.getAttribute("name");
	        out.print("Chào bạn, " + name + " đến với trang quản lý tài khoản");
	    } else {
	        out.print("Xin vui lòng đăng nhập");
	        response.sendRedirect("/HelloSession/Login.html");
	    }
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}