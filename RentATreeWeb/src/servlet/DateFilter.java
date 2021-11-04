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
import java.util.*;


/**
 * Servlet implementation class DateFilter
 */
@WebServlet("/DateFilter")
public class DateFilter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DateFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	private void filter( HttpServletRequest request, HttpServletResponse response) {
		try {
		String start = request.getParameter("rentfrom");
		String end = request.getParameter("rentto");
		System.out.println(start);
		System.out.println(end);
		if(start==null || end == null) {
			response.sendRedirect("Shop.jsp");
		}
		HttpSession session = request.getSession();
		session.setAttribute("start", start);
		session.setAttribute("end", end);
		ProductDBHandler ph = new ProductDBHandler();
		ArrayList<Product> prods = (ArrayList<Product>)session.getAttribute("products");
		if(prods==null) {
			prods = ph.getProducts();
		}
		ArrayList<Product> datefiltered = ph.getProductDateFiltered(start, end);
		ArrayList<Product> final_p = new ArrayList<Product>();
		ArrayList<Integer> pids = new ArrayList<Integer>();
		for(Product p:datefiltered) {
			pids.add(p.getID());
		}
		for(Product p: prods) {
			if(pids.contains(p.getID())) {
				final_p.add(p);
			}
		}
		session.setAttribute("products", final_p);
		response.sendRedirect("Shop.jsp");
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		filter(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		filter(request, response);
	}

}
