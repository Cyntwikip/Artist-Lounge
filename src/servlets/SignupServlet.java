package servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;
import models.Account;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("Inside Signup");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("fname") + " " + request.getParameter("lname");
		
		//Date timeCreated = (Date) new java.util.Date();
		//Date bday = (Date) new java.util.Date();
		java.util.Date utilDate = new java.util.Date();
	    java.sql.Date bday = new java.sql.Date(utilDate.getTime());
	    
		Account acc = new Account(username, password, name, bday);
		
		AccountDAO accDAO = new AccountDAO();
		accDAO.addAccount(acc);
		
		response.sendRedirect("home.jsp");
		
	}

}
