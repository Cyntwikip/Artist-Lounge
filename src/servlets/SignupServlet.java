package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import models.Account;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filePath;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	// Get the file location where it would be stored.
        filePath = getServletContext().getInitParameter("file-upload"); 
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
		PrintWriter out = response.getWriter();
		
		AccountDAO accDAO = new AccountDAO();
		
		if(accDAO.checkIfUsernameExists(username)) {
			//response.sendRedirect("login.jsp?error=2");
			out.print('0'); //error
			//System.out.println("Username exists!");
			return;
		}
		else {
			//Date timeCreated = (Date) new java.util.Date();
			//Date bday = (Date) new java.util.Date();
			java.util.Date utilDate = new java.util.Date();
		    java.sql.Date bday = new java.sql.Date(utilDate.getTime());
		    
			Account acc = new Account(username, password, name, bday);
			//System.out.println(filePath + "nopic.jpg");
			acc.setProfilePic("nopic.jpg");
			
			accDAO.addAccount(acc);
			
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			
			//response.sendRedirect("home.jsp");
			out.print('1');
		}
	}

}
