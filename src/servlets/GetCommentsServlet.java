package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;
import dao.CommentDAO;
import models.Comment;

/**
 * Servlet implementation class GetCommentsServlet
 */
@WebServlet("/GetCommentsServlet")
public class GetCommentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCommentsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("postID").replace("post", "");
		int postID = Integer.parseInt(idStr);
		//System.out.println(postID);
		
		PrintWriter out = response.getWriter();
		CommentDAO commDAO = new CommentDAO();
		ArrayList<Comment> comments = commDAO.getCommentsByPost(postID);
		
		AccountDAO accDAO = new AccountDAO();
		
		for(Comment c:comments) {
			int id = c.getId();
			int accID = c.getAccountID();
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
