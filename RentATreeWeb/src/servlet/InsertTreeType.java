package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import db.*;

/**
 * Servlet implementation class InsertTreeType
 */
@WebServlet("/InsertTreeType")
public class InsertTreeType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertTreeType() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String type = request.getParameter("type");
			String material = request.getParameter("material");
			String sname = request.getParameter("sname");
			int height = Integer.parseInt(request.getParameter("height"));
			double price = Double.parseDouble(request.getParameter("price"));
			AdminDBHandler adb = new AdminDBHandler();
			adb.insertTree(type, material, sname, height, price);
			response.sendRedirect("admin.jsp");
		}catch(Exception e) {
			
		}
	}

}
