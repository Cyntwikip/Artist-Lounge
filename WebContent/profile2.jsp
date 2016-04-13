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

      
        <script src="js/jquery-2.2.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/post.js"></script>
    <script src="js/comment.js"></script>
        
		<title>You</title>
	</head>
<body>

	<% 
        String filepath = "stash/";
		AccountDAO accDAO = new AccountDAO();
		Account currentAcc = accDAO.getAccountByUsername((String) session.getAttribute("username"));
		String fullname = currentAcc.getName();
        String profilePic = filepath + currentAcc.getProfilePic();
        profilePic = profilePic.replaceAll(" ", "%20");
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
                <li class="active"><a href="#">Profile <span class="sr-only">(current)</span></a></li>
                <li><a href="notifications.html">Notifications</a></li>
                <li><a href="challenge.html">Challenge</a></li>
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
                    <form method="POST" action="ChangeDPServlet" enctype="multipart/form-data">
                        <label for="change-pic">
                            <img class="profile-picture" src="<%=profilePic%>" /> 
                        </label>
                        <input id="change-pic" type="file" name="file" accept="image/*" onchange="this.form.submit();" style="display:none;" />
                    </form>
                    <div class="profile-name"><%=fullname%></div>
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
                                <p class="profile-details-content" id="birthdate">Birthday: July 1996</p>
                            </div>
                            
                            <div class="profile-details">
                                <p class="profile-details-header">Friends</p>
                                <div id="friends-list">
                                    <img src="assets/Akali.png" class="friend-dp"></img>
                                    <img src="assets/deadpool.jpg" class="friend-dp"></img>
                                    <img src="assets/garfield.jpg" class="friend-dp"></img>
                                    <img src="assets/Akali.png" class="friend-dp"></img>
                                    <img src="assets/garfield.jpg" class="friend-dp"></img>
                                    <img src="assets/deadpool.jpg" class="friend-dp"></img>

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
                    
            var addpost = document.getElementById('add-post-modal');
            // Add new post 
            $("#add-post").click(function() {
                addpost.style.display = "block";
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
            
            function closeAddPost() {
                addpost.style.display = "none";
            }
            
        </script>

</body>
</html>