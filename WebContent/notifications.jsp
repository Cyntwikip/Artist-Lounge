<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dao.*" %>
<%@ page import="models.*" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/navbar.css">
    <link rel="stylesheet" type="text/css" href="css/notifications.css">
	<link rel="stylesheet" type="text/css" href="css/notifications-page.css">
    
    <script src="js/jquery-2.2.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/notifications.js"></script>
	
	<title>Notifications</title>
	
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top">
          <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <a class="navbar-brand" href="#">
<!--                    <img alt="Artist's Lounge" src="logo"></img>-->
<!--                    Artist's Lounge	-->
                </a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
              <ul class="nav navbar-nav">
                <li><a href="home.jsp">Home</a></li>
                <li><a href="profile.jsp">Profile</a></li>
                <li class="active"><a href="#">Notifications <span class="sr-only">(current)</span></a></li>
                <li><a href="challenge.jsp">Challenge</a></li>
              </ul>
                
              <form class="navbar-form navbar-left" role="search" id="search-bar">
                <div class="form-group">
                  <input type="text" class="form-control" placeholder="Search">
                    <span>
                        <button type="submit" class="btn btn-default">
                            <img src="assets/icons/search.png">
                        </button>
                    </span>
                </div>
              </form>
                
              <ul class="nav navbar-nav navbar-right">
                  
<!--
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Settings <span class="caret"></span></a>
                  <ul class="dropdown-menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="#">Separated link</a></li>
                  </ul>
                </li>
-->
                  
                <li><a href="login.html">Sign out</a></li>
              </ul>
            </div><!-- /.navbar-collapse -->
          </div><!-- /.container-fluid -->
        </nav>
		
		<div class="container notifications-field">
			<h4>Notifcations</h4>
			<div class="center-contents" id="notifs"></div>
			<hr>
			<h4>History</h4>
			<div class="center-contents" id="histories"></div>
		</div>
		
		<script>
		
		<%
			try{
				FriendRequestDAO frDAO = new FriendRequestDAO();
				ChallengeRequestDAO crDAO = new ChallengeRequestDAO();
				AccountDAO accDAO = new AccountDAO();
				
				Account currentAcc = accDAO.getAccountByUsername((String) session.getAttribute("username"));
				ArrayList<FriendRequest> fr = frDAO.getRequestsByReceiver(currentAcc.getId(), 0);
				ArrayList<ChallengeRequest> cr = crDAO.getRequestsByReceiver(currentAcc.getId(), 0);
				
				for(FriendRequest f:fr){
					Account sender = accDAO.getAccountByID(f.getSenderID());
					String dp = sender.getProfilePic();
            		dp = "stash/" + dp.replaceAll(" ", "%20");
					
				%>addNotif(new Notification('<%=sender.getName() %>', '1', '<%=dp %>', '<%=sender.getId() %>', '<%=currentAcc.getId()%>'));<%
				}
				
				for(ChallengeRequest c:cr){
					Account sender = accDAO.getAccountByID(c.getSenderID());
					String dp = sender.getProfilePic();
            		dp = "stash/" + dp.replaceAll(" ", "%20");
					
				%>addNotif(new Notification('<%=sender.getName() %>', '2', '<%=dp %>', '<%=sender.getId() %>', '<%=currentAcc.getId()%>'));<%
				}
				
			} catch (Exception e){}
		%>
		
		
			//addNotif(new Notification('Akali', '1', 'assets/Akali.png', senderid));
			//addNotif(new Notification('Garfield', '2', 'assets/Garfield.jpg'));
			//addNotif(new Notification('Doge', '1', 'assets/doge.jpg'));
			//addNotif(new Notification('Deadpool', '2', 'assets/deadpool.jpg'));
		</script>
</body>
</html>