package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.ProductDBHandler;
import models.ProductFilter;
import java.util.*;
import models.*;
import db.*;

/**
 * Servlet implementation class ProductFilterServlet
 */
@WebServlet("/ProductFilterServlet")
public class ProductFilterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductFilterServlet() {
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
		ArrayList<String> types = ProductFilter.uniqueTypes();
		HttpSession session = request.getSession();
		ArrayList<String> requiredValsType = new ArrayList<>();
		ArrayList<String> requiredValsMat = new ArrayList<>();
		ArrayList<String> requiredValsSup = new ArrayList<>();
		String[] valType = request.getParameterValues("type");
		String[] valMaterial = request.getParameterValues("material");
		String[] valSupplier = request.getParameterValues("supplier");
		ProductDBHandler h = new ProductDBHandler();
		ArrayList<Product> all = h.getProducts();
		ProductFilter pf = new ProductFilter(all);
		ArrayList<Product> filtered = all;
		response.sendRedirect("Shop.jsp");
		if(valType!=null){
			for(String t: valType) {
				requiredValsType.add(t);
			}
			pf = new ProductFilter(all);
			filtered = pf.filterOnType(requiredValsType);
		}
		if(valMaterial!=null){
			for(String t: valMaterial) {
				requiredValsMat.add(t);
			}
			pf = new ProductFilter(filtered);
			filtered = pf.filterOnMaterial(requiredValsMat);
		}
		if(valSupplier!=null){
			for(String t: valSupplier) {
				requiredValsSup.add(t);
			}
			pf = new ProductFilter(filtered);
			filtered = pf.filterOnSupplier(requiredValsSup);
		}
		session.setAttribute("products", filtered);
		
	}
}
