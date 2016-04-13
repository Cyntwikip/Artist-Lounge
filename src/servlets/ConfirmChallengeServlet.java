package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChallengeDAO;
import dao.ChallengeRequestDAO;
import dao.FriendRequestDAO;
import dao.FriendsListDAO;
import models.Challenge;
import models.ChallengeRequest;
import models.FriendRequest;
import models.FriendsList;

/**
 * Servlet implementation class ConfirmChallengeServlet
 */
@WebServlet("/ConfirmChallengeServlet")
public class ConfirmChallengeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmChallengeServlet() {
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
		// TODO Auto-generated method stub
System.out.println("hello world");
		
		int senderAcc = new Integer (request.getParameter("senderID"));
		int receiverAcc = new Integer (request.getParameter("receiverID"));
		String action = request.getParameter("action");
		ChallengeDAO cDAO = new ChallengeDAO();
		ChallengeRequestDAO crDAO = new ChallengeRequestDAO();
		String s = "Challenge";
		
		
		if(action.equals("Accept")){		
		ArrayList<ChallengeRequest> cr = crDAO.getRequestsBySenderAndReceiver(senderAcc, receiverAcc);
		for (ChallengeRequest c : cr) {
			c.setPending(true);
			crDAO.updateRequest(c);
		}
		
		}
		else {
			ArrayList<ChallengeRequest> cr = crDAO.getRequestsBySenderAndReceiver(senderAcc, receiverAcc);
			for (ChallengeRequest c : cr) {
				
				crDAO.deleteRequest(c);
			}
		}
		response.sendRedirect("challenge.jsp");
	}

}
