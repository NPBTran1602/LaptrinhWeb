package vn.iotstar.HelloForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Servlet implementation class HelloForm
 */
@WebServlet(urlPatterns= {"/helloform"})
public class HelloForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    		 throws ServletException, IOException {

    		 //Nhận dữ liệu từ request URL
    		 String ten = request.getParameter("ten");
    		String holot = request.getParameter("holot");

    		//Hiển thị dữ liệu ra web bằng đối tượng PrintWriter
    		 PrintWriter out = response.getWriter();
    		 out.println("<b>First Name</b>: " + ten + "<br/><b>Last Name</b>:"+ holot);
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}