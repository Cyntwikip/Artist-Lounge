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
import dao.PostDAO;
import models.Account;
import models.Post;

/**
 * Servlet implementation class SearchPost
 */
@WebServlet("/SearchPostServlet")
public class SearchPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String text = request.getParameter("search");
		
		System.out.println(text);
		
		PostDAO postDAO = new PostDAO();
    	ArrayList<Post> posts = new ArrayList<Post>();
    	posts = postDAO.getPostsByText(text);
    	
    	PrintWriter out = response.getWriter();
    	
    	for(int i=0; i<posts.size(); i++) {
    		Post post = posts.get(i);
    		AccountDAO accDAO = new AccountDAO();
    		Account acc = accDAO.getAccountByID(post.getAccountID());
    		String name = acc.getName();
    		String paint = "stash/" + post.getPaint();
    		paint = paint.replaceAll(" ", "%20"); //so that HTML can read filename with spaces
    		String dp = acc.getProfilePic();
    		dp = "stash/" + dp.replaceAll(" ", "%20");
            int postID = post.getId();
            
            String output = paint+" "+dp+" "+postID+" "+name;
            System.out.println(output);
            out.println(output);
    	}
	}
    	

}
