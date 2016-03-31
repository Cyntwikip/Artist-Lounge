package servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");		
		String password = request.getParameter("password");
		
		//for checking purposes
		System.out.println(username);
		System.out.println(password);
		
		//Date date = new Date();
	    // display time and date using toString()
	    //String str = String.format("Current Date/Time : %tc", date );
	    //System.out.printf(str);
		
		AccountDAO accDAO = new AccountDAO();
		boolean success = accDAO.checkLoginDetails(username, password);
		
		if(success == true) {
			System.out.println("Success");
			
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			
			response.sendRedirect("home.jsp");
			
		}
		else
			response.sendRedirect("login.jsp");
		
	}

}
