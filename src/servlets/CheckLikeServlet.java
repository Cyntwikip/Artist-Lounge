package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import dao.LikeDAO;

/**
 * Servlet implementation class CheckLikeServlet
 */
@WebServlet("/CheckLikeServlet")
public class CheckLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckLikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int postID = Integer.parseInt(request.getParameter("postID").replace("post", ""));
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		AccountDAO accDAO = new AccountDAO();
		int accID = accDAO.getAccountByUsername(username).getId();
		
		LikeDAO likeDAO = new LikeDAO();
		boolean result = likeDAO.checkIfUserLikesPost(accID, postID);
		
		int num = likeDAO.getNumberOfLikes(postID);
		
		PrintWriter out = response.getWriter();
		//System.out.println(result);
		
		if(result == true)
			out.write("Unlike" + " " + num);
		else
			out.write("Like" + " " + num);
			
	}

}
