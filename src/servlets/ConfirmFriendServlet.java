package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FriendRequestDAO;
import dao.FriendsListDAO;
import models.FriendRequest;
import models.FriendsList;

/**
 * Servlet implementation class ConfirmFriendServlet
 */
@WebServlet("/ConfirmFriendServlet")
public class ConfirmFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmFriendServlet() {
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
		FriendsListDAO flDAO = new FriendsListDAO();
		FriendRequestDAO frDAO = new FriendRequestDAO();
		
		
		if(action.equals("Accept")){		
		ArrayList<FriendRequest> fr = frDAO.getRequestsBySenderAndReceiver(senderAcc, receiverAcc);
		for (FriendRequest f : fr) {
			f.setPending(true);
			frDAO.updateRequest(f);
		}
		FriendsList fl = new FriendsList(receiverAcc,senderAcc);
		flDAO.addFriends(fl);
		
		}
		else {
			ArrayList<FriendRequest> fr = frDAO.getRequestsBySenderAndReceiver(senderAcc, receiverAcc);
			for (FriendRequest f : fr) {
				
				frDAO.deleteRequest(f);
			}
		}
		response.sendRedirect("notifications.jsp");
	}

}
