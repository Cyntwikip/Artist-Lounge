<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dao.*" %>
<%@ page import="models.*" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">        
    <link rel="stylesheet" type="text/css" href="css/home.css">
    <link rel="stylesheet" type="text/css" href="css/navbar.css">
    <link rel="stylesheet" type="text/css" href="css/post.css">
    <link rel="stylesheet" type="text/css" href="css/post-popup.css">
    <link rel="stylesheet" type="text/css" href="css/add-post-popup.css">


    <script src="js/jquery-2.2.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/post.js"></script>
    <script src="js/comment.js"></script>
    
    <title>Home</title>
</head>
<body>
        <nav class="navbar navbar-default navbar-fixed-top">
          <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <a class="navbar-brand" href="#">
<!--                    <img alt="Artist's Lounge" src="Logo/logo.png" id="logo"></img>-->
<!--                    Artist's Lounge-->
                </a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
              <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home <span class="sr-only">(current)</span></a></li>
                <li><a href="profile.jsp">Profile</a></li>
                <li><a href="notifications.html">Notifications</a></li>
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
        
        <div class="container news-feed">
                    <div class="center-contents" id="posts">
                    </div>
            
            <!-- The Modal -->
            <div id="myModal" class="post-popup">
                <div id="popup-content" class="container">
                    <!-- Modal content -->
                    <div class="modal-content">
                        <div id="popup-image-container">
<!--                            <img id="popup-image" src=""></img>-->
                            <div id="popup-image"></div>
                        </div>
                        <div id="comment-section">
                            <div id="popup-image-details">
                                <img src="assets/Akali.png" id="popup-image-user-dp"></img>
                                <div id="popup-image-user-name">Akali</div>
                            </div>
                            <div id="popup-features-container">
                                <button id="like" class="btn btn-info features">Like</button>
                                <button id="comment" class="btn btn-danger features">Comment</button>
                            </div>
                            <div id="comments">
<!--                                comments-->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <!--add-post popup-->
            <div id="add-post-modal" class="post-popup">
                <div id="popup-content" class="container">
                    <div class="add-post-modal-content">
                        <form id="add-post-form" method="POST" action="AddPostServlet" enctype="multipart/form-data">
                            <textarea name="description" placeholder="Description..." cols="70" rows="6" maxlength="250" style="resize:none"></textarea>
                            <input required name="file" type="file" id="myFile" accept="image/*" style="padding:0px; margin:30px auto">
                            <input type="submit" class="btn btn-primary" value="Submit"></input>
                            <input type="button" value="Cancel" onClick="closeAddPost()" class="btn btn-danger"></input>
<!--                            <button onClick="closeAddPost()" class="btn btn-danger">Cancel</button>-->
                        </form>
                    </div>
                </div>
            </div>
            <!--end of add-post popup-->

<!--            <div id="add-post" onclick="addPost(new Post('Cute Girl', 'assets/Finale.jpg', 'assets/Finale.jpg'));">+</div>-->
            <button id="add-post">+</button>

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
	            		//System.out.println(post.getAccountID());
	            		AccountDAO accDAO = new AccountDAO();
	            		Account acc = accDAO.getAccountByID(post.getAccountID());
	            		String name = acc.getName();
	            		//System.out.println(name);
	            		//String paint = "file:///C:/apache-tomcat-8.0.32/webapps/data/" + post.getPaint();
	            		String paint = "stash/" + post.getPaint();
	            		paint = paint.replaceAll(" ", "%20"); //so that HTML can read filename with spaces
	            		//System.out.println(paint);
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
//                alert("Clicked!");
                modal.style.display = "block";
                //var id = $(this).attr('id');
                //alert($(this).attr('id'));//prints id
                var image = $(this).find(">:first-child").css('background-image');
                //var image = $(this).find(">:first-child").css('background-image', 'url(' + "stash/p1.png" + ')');
                //var image = $(this).first().css('background-image');
                var patt=/\"|\'|\)/g;
                image = image.split('/').pop().replace(patt,'');
                //image = image.replace('url(','').replace(')','');
                //$('#popup-image').css("background-image", 'url(' + "stash/p1.png" + ')');
                //alert(image);
//                $('#popup-image').attr("src", "stash/"+image);
                $('#popup-image').css('background-image', 'url(' + 'stash/' + image + ')');
                
                addComment(new Comment('assets/Finale.jpg', 'Whoa! cute!', 0));
                addComment(new Comment('assets/Luna%20Lovegood.png', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ultrices sit amet nunc at ornare. Aenean ornare, quam ac dapibus scelerisque, turpis risus placerat justo, id tristique mauris orci a ante. Donec pharetra metus id erat facilisis posuere.', 1));
                addComment(new Comment('assets/Finale.jpg', 'Whoa! cute!', 2));
                addComment(new Comment('assets/Finale.jpg', 'Whoa! cute!', 3));
                addComment(new Comment('assets/Finale.jpg', 'Whoa! cute!', 4));
            });

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function(event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                    emptyCommentSection();
                }
                else if (event.target == content) {
                    modal.style.display = "none";
                    emptyCommentSection();
                }
            }
            
            function closeAddPost() {
                addpost.style.display = "none";
            }
            
        </script>
        
    </body>
</html>