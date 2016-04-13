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
        <link rel="stylesheet" type="text/css" href="css/profile.css">
        <link rel="stylesheet" type="text/css" href="css/navbar.css">
        <link rel="stylesheet" type="text/css" href="css/post.css">
        <link rel="stylesheet" type="text/css" href="css/post-popup.css">
    	<link rel="stylesheet" type="text/css" href="css/add-post-popup.css">
    	<link rel="stylesheet" type="text/css" href="css/edit-profile-popup.css">
    	<link rel="stylesheet" type="text/css" href="css/diffprofile.css">

      
        <script src="js/jquery-2.2.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/post.js"></script>
    <script src="js/comment.js"></script>
        
		<title></title>
	</head>
<body>

	<% 
        String filepath = "stash/";
		AccountDAO accDAO = new AccountDAO();
		Account currentAcc = accDAO.getAccountByID(new Integer (request.getParameter("id")));
		Account senderAcc = accDAO.getAccountByUsername((String) session.getAttribute("username"));
		String fullname = currentAcc.getName();
        String profilePic = filepath + currentAcc.getProfilePic();
        profilePic = profilePic.replaceAll(" ", "%20");
        
        FriendRequestDAO frDAO = new FriendRequestDAO();
        ArrayList<FriendRequest> fr = new ArrayList<FriendRequest>();
        //ArrayList<FriendRequest> fr = frDAO.getRequestsBySenderAndReceiver(senderAcc.getId(), currentAcc.getId());
        fr = frDAO.getRequestsBySenderAndReceiver(senderAcc.getId(), currentAcc.getId());
	%>
    

	<nav class="navbar navbar-default navbar-fixed-top">
          <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <a class="navbar-brand" href="#">
<!--                    <img alt="Artist's Lounge" src="logo"></img>-->
<!--                    Artist's Lounge-->
                </a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
              <ul class="nav navbar-nav">
                <li><a href="home.jsp">Home</a></li>
                <li><a href="profile.jsp">Profile</a></li>
                <li><a href="notifications.jsp">Notifications</a></li>
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
                <li><a href="login.jsp">Sign out</a></li>
              </ul>
            </div><!-- /.navbar-collapse -->
          </div><!-- /.container-fluid -->
        </nav>
        
        <div class="container-fluid">
            <div class="profile-header">
                <div class="container profile-header-center">
                    <img class="profile-picture" src="<%=profilePic%>" /> 
                    <div class="profile-name"><%=fullname%></div>
                    <%if(fr != null && fr.size() < 1){ %>
<div class="add-friend-area">
	<form class="add-friend" method="POST" action="AddFriendServlet">
		<input id="add-friend-submit" class="btn btn-primary" type="submit" value="Add as Friend">
		<input type="hidden" name="senderID" value="<%=senderAcc.getId() %>">
		<input type="hidden" name="receiverID" value="<%=currentAcc.getId() %>">
		
	</form>
</div>  
<% } %>  
                </div>
            </div>
            <div class="profile-content">
                <div class="profile-summary"></div>
                <div class="container">
                    <div class="column">
                        <div class="col-lg-4 col-md-4 hidden-sm hidden-xs">
                            <div class="profile-details">
                                <p class="profile-details-header">Personal Details</p>
                                <p class="profile-details-content" id="date-joined">Joined February 2016</p>
                                <hr>
                                <!--  <p class="profile-details-content" id="birthdate">Birthday: July 1996</p>-->
                               
                            </div>
                            
                            <div class="profile-details">
                                <p class="profile-details-header">Friends</p>
                                <div id="friends-list">
									<%
										try{
											
										FriendsListDAO flDAO = new FriendsListDAO();
										int[] friends = flDAO.getFriendsByID(currentAcc.getId());
										int counter = 0;
										for(int j: friends){
											if(counter > 5) break;
											AccountDAO accDAO2 = new AccountDAO();
											Account friend = accDAO2.getAccountByID(j);
											
											String fprofilePic = filepath + friend.getProfilePic();
											counter++;
									%><a href="diffprofile.jsp?id=<%=friend.getId()%>"><img src="<%=fprofilePic%>" class="friend-dp"></img></a><%
										}
									if(friends.length > 6){
									%><br><a href="javascript:showHideMoreFriends()" id="showMore">See More Friends</a>
									<div class="moreFriends">
									<%
										for (int j = 6; j < friends.length; j++){
											AccountDAO accDAO2 = new AccountDAO();
											Account friend = accDAO2.getAccountByID(friends[j]);
											
											String fprofilePic = filepath + friend.getProfilePic();
											%><a href="diffprofile.jsp?id=<%=friend.getId()%>"><img src="<%=fprofilePic%>" class="friend-dp"></img></a><%
										}
									%>
									</div>
									<%
									}
									
									}catch(Exception e) {}
									%>
										
                                </div>
                            </div>
                        </div>
                        
                        <div class="col-lg-8 col-md-8 col-sz-12 col-xs-12">
                            <div class="center-contents" id="posts">
                            </div>
                        </div>
                    </div>
                    
                </div>
            </div>

            <!-- The Modal -->
            <div id="myModal" class="post-popup">
                <div id="popup-content" class="container">
                    <!-- Modal content -->
                    <div class="modal-content">
<!--                        <span class="close">x</span>-->
                        <div id="popup-image-container">
                            <img id="popup-image"></img>
                        </div>
<!--                        <div class="test"></div>-->
                        <div id="comment-section">
                            <div id="popup-image-details">
                                <img src="assets/Akali.png" id="popup-image-user-dp"></img>
                                <div id="popup-image-user-name">Akali</div>
                            </div>
                            <div id="popup-features-container">
                                <button id="like" class="btn btn-info features">Like</button>
                                <button id="comment" class="btn btn-danger features">Comment</button>
                            </div>
<!--                            <div>-->
                            <div id="comments">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
             <!--add-post popup-->
            <div id="edit-profile-modal" class="post-popup">
                <div id="popup-content" class="container">
                    <div class="add-post-modal-content">
                        <!--<form id="add-post-form" method="POST" action="AddPostServlet" enctype="multipart/form-data">
                            <textarea name="description" placeholder="Description..." cols="70" rows="6" maxlength="250" style="resize:none"></textarea>
                            <input required name="file" type="file" id="myFile" accept="image/*" style="padding:0px; margin:30px auto">
                            <input type="submit" class="btn btn-primary" value="Submit"></input>
                            <input type="button" value="Cancel" onClick="closeAddPost()" class="btn btn-danger"></input>
<!--                            <button onClick="closeAddPost()" class="btn btn-danger">Cancel</button>
                        </form>-->
                    	<form id="edit-profile-form" method="POST" action="EditProfileServlet">
                                    <div id="errorMsg2" class="error"></div>
                                    <p>Edit your first name and last name.</p>
                                    <input required id="edit-firstname" type="text" name="efname" placeholder="First Name"></input><br>
                                    <input required id="edit-lastname" type="text" name="elname" placeholder="Last Name"></input><br>
									<input type="submit" class="btn btn-primary" value="Done Editing"></input><hr>
						</form>
						<form id="edit-profile-form-pw" method="POST" action="EditProfilePWServlet">
									<p>Enter your new password.</p>
									<input required id="edit-password" type="password" name="password" placeholder="New Password"></input><br>
									<input type="submit" class="btn btn-primary" value="Done Editing"></input>
									<input type="button" value="Cancel" onClick="closeEditProfile()" class="btn btn-danger"></input>
						</form>
					</div>
                </div>
            </div>
            
        </div>      
 
        <script>
        
	        <%
        		String filePath = "file:///C:/Users/Jude Michael Teves/Pictures/Artist-Lounge Storage/";
        	%>
            
        	<%
        		try {
	        		PostDAO postDAO = new PostDAO();
	            	ArrayList<Post> posts = new ArrayList<Post>();
	            	posts = postDAO.getRecentPosts(12);
	            	
	            	for(int i=0; i<posts.size(); i++) {
	            		Post post = posts.get(i);
	            		Account acc = accDAO.getAccountByID(post.getAccountID());
	            		String name = acc.getName();
	            		String paint = "stash/" + post.getPaint();
	            		paint = paint.replaceAll(" ", "%20"); //so that HTML can read filename with spaces
	            		String dp = acc.getProfilePic();
	            		dp = "stash/" + dp.replaceAll(" ", "%20");
                        int postID = post.getId();
	            		
	            		%>
	            		addPost(new Post('<%=name%>', '<%=paint%>', '<%=dp%>', '<%=postID%>'));
	            		<%
	            		
	            	}
        		} catch(Exception e) {}
        	
        	%>
            
            // Get the modal
            var modal = document.getElementById('myModal');
            var content = document.getElementById('popup-content');
                    
            var editprofile = document.getElementById('edit-profile-modal');
            // Add new post 
            $("#edit-profile").click(function() {
                editprofile.style.display = "block";
            });
            
            // When the user clicks on the posts, open the modal 
            $("#posts").on('click', '.post', function() {
                modal.style.display = "block";
                var image = $(this).find(">:first-child").css('background-image');
                var patt=/\"|\'|\)/g;
                image = image.split('/').pop().replace(patt,'');
//                $('#popup-image').attr("src", "stash/"+image);
                $('#popup-image').css('background-image', 'url(' + 'stash/' + image + ')');
                
                addComment(new Comment('assets/Finale.jpg', 'Whoa! cute!', 0));
                addComment(new Comment('assets/Luna%20Lovegood.png', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ultrices sit amet nunc at ornare. Aenean ornare, quam ac dapibus scelerisque, turpis risus placerat justo, id tristique mauris orci a ante. Donec pharetra metus id erat facilisis posuere.', 1));
                addComment(new Comment('assets/Finale.jpg', 'Whoa! cute!', 2));
                addComment(new Comment('assets/Finale.jpg', 'Whoa! cute!', 3));
                addComment(new Comment('assets/Finale.jpg', 'Whoa! cute!', 4));
            });

            // When the user clicks on the button, open the modal 
//            $(".post").click(function() {
//                modal.style.display = "block";
//                var image = $(this).find(">:first-child").css('background-image');
//                var patt=/\"|\'|\)/g;
//                image = image.split('/').pop().replace(patt,'');
//                $('#popup-image').attr("src", "stash/"+image);
//            });

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function(event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
                else if (event.target == content) {
                    modal.style.display = "none";
                }
            }
            
            function closeEditProfile() {
                editprofile.style.display = "none";
            }
            
            var hidden = true;
            function showHideMoreFriends(){
            	if (hidden){
            		$('.moreFriends').show(200);
            		hidden = false;
            		$('#showMore').text('Show Less');
            	} else {
	            	$('.moreFriends').hide(200);            		
            		hidden = true;
            		$('#showMore').text('Show More Friends');
            	}
            }
            
        </script>

</body>
</html>