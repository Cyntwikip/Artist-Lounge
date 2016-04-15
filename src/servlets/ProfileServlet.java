package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import dao.PostDAO;
import models.Account;
import models.Post;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strID = request.getParameter("postID").replace("post", "");
		int postID = Integer.parseInt(strID);
		
		System.out.println(postID);
		
		PostDAO postDAO = new PostDAO();
		Post post = postDAO.getPostById(postID);
		int id = post.getAccountID();
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		AccountDAO accDAO = new AccountDAO();
		Account acc = accDAO.getAccountByUsername(username);
		
		if(acc.getId() != id)
			response.sendRedirect("diffprofile.jsp?id="+id);
		else {
			response.sendRedirect("profile.jsp");
		}
		
		
	}

}
