package servlet;

import java.io.IOException;
import java.util.ArrayList;

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
		/*HttpSession session = request.getSession();
		Basket b = (Basket)session.getAttribute("basket");
		Transactions t = new Transactions(b, (String)session.getAttribute("username"), "2021-11-03", "2021-11-06");
		TransactionDBHandler t_handler = new TransactionDBHandler(t);
		t_handler.enterTransaction();
		b.clearBasket();
		response.sendRedirect("Shop.jsp");*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Basket b = (Basket)session.getAttribute("basket");
		String start = (String)session.getAttribute("start");
		String end = (String)session.getAttribute("end");
		if(start==null || end==null || b.getBasket()==null) {
			response.sendRedirect("Shop.jsp");
		}
		if(b.getBasket().size()==0) {
			response.sendRedirect("Shop.jsp");
		}
		String deliveryslot = request.getParameter("deliveryslot");
		String returnslot = request.getParameter("returnslot");
		Transactions t = new Transactions(b, (String)session.getAttribute("username"), start, end, deliveryslot, returnslot);
		TransactionDBHandler t_handler = new TransactionDBHandler(t);
		t_handler.enterTransaction();
		String houseno = (String)request.getParameter("address");
		String street = (String)request.getParameter("address2");
		String city = (String)request.getParameter("city");
		String postcode = (String)request.getParameter("postcode");
		System.out.println(houseno+street+city+postcode);
		DeliveryHandler dh = new DeliveryHandler(houseno, street, city, postcode, t_handler.getfid());
		dh.performDeliveryRecordOperations();
		ArrayList<Product> invoiceBasket = new ArrayList<Product>();
		for(Product p: b.getBasket()) {
			invoiceBasket.add(p);
		}
		session.setAttribute("invoiceBasket", invoiceBasket);
		b.clearBasket();
		response.sendRedirect("Invoice.jsp");
	}

}
