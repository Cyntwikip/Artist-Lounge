package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import dao.CommentDAO;
import models.Account;
import models.Comment;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String comment = request.getParameter("comment");
		String idStr = request.getParameter("post-id").replace("post", "");
		int postID = Integer.parseInt(idStr);
		
//		System.out.println(comment);
//		System.out.println(postID);
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		AccountDAO accDAO = new AccountDAO();
		Account acc = accDAO.getAccountByUsername(username);
		
		int accID = acc.getId();
		
		Comment comm = new Comment(accID, postID, comment);
		
		CommentDAO commDAO = new CommentDAO();
		commDAO.addComment(comm);
		
		
		PrintWriter out = response.getWriter();
		ArrayList<Comment> comments = commDAO.getCommentsByPost(postID);
		
		for(Comment c:comments) {
			int id = c.getId();
			accID = c.getAccountID();
			String pic = accDAO.getAccountByID(accID).getProfilePic().replaceAll(" ", "%20");
			pic = "stash/" + pic;
			String text = c.getComment();
			//System.out.println(id + " : " + accID + " : " + text);
			//out.println("addComment(new Comment('assets/Finale.jpg', 'Whoa! cute!', 0));");
			//out.write("Retrieved data");
			out.println(pic+" "+id+" "+text);
		}
		
	}

}
