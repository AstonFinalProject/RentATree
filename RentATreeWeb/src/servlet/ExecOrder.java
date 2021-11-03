package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.*;
import db.*;
/**
 * Servlet implementation class ExecOrder
 */
@WebServlet("/ExecOrder")
public class ExecOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Basket b = (Basket)session.getAttribute("basket");
		Transactions t = new Transactions(b, (String)session.getAttribute("username"), "2021-11-03", "2021-11-06");
		TransactionDBHandler t_handler = new TransactionDBHandler(t);
		t_handler.enterTransaction();
		response.sendRedirect("Shop.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Basket b = (Basket)session.getAttribute("basket");
		Transactions t = new Transactions(b, (String)session.getAttribute("username"), "", "");
		TransactionDBHandler t_handler = new TransactionDBHandler(t);
		t_handler.enterTransaction();
		response.sendRedirect("Shop.jsp");
	}

}
