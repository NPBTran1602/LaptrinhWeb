package vn.iotstar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class HelloCookie
 */
@WebServlet(urlPatterns= {"/hello1","/xin-chao"})
public class HelloCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloCookie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
    ServletException, IOException {
	    resp.setContentType("text/html");
	    PrintWriter printWriter = resp.getWriter();
	    String name=
	    "";
	    Cookie[] cookie = req.getCookies();
	    for (Cookie c: cookie) {
	    if(c.getName().equals("username")) {
	    name = c.getValue();}}
	    if(name.equals("")){
	    resp.sendRedirect("/HelloServlet/login");
	    }
	    printWriter.println("Xin chao " + name);
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}