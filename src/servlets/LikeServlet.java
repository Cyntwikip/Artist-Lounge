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
import models.Account;
import models.Like;

/**
 * Servlet implementation class LikeServlet
 */
@WebServlet("/LikeServlet")
public class LikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String likeVal = request.getParameter("like");
		int postID = Integer.parseInt(request.getParameter("postID").replace("post", ""));
		
		//System.out.println(postID);
		//System.out.println(likeVal);
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		AccountDAO accDAO = new AccountDAO();
		Account acc = accDAO.getAccountByUsername(username);
		int accID = acc.getId();
		
		LikeDAO likeDAO = new LikeDAO();
		if(likeVal.equals("Like"))
			likeDAO.addLike(new Like(accID, postID));
		else if(likeVal.equals("Unlike"))
			likeDAO.deleteLike(new Like(accID, postID));
		
		PrintWriter out = response.getWriter();
		int count = likeDAO.getNumberOfLikes(postID);
//		System.out.println(count);
		out.write(count+"");
		
	}

}
