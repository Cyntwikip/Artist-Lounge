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


    <script src="js/jquery-2.2.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/post.js"></script>
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
                <li><a href="profile.html">Profile</a></li>
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
                  
                <li><a href="login.html">Sign out</a></li>
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
                            <img id="popup-image" src="assets/Finale.jpg"></img>
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
<!--                                fillers-->
                                <div class="comment">
                                    <img src="assets/doge.jpg" class="comment-dp"></img>
                                    <p class="comment-text">doge doge! much doge so wow</p>
                                </div>
                                <div class="comment">
                                    <img src="assets/deadpool.jpg" class="comment-dp"></img>
                                    <p class="comment-text">Akali 234234 234234 2 423 423 4234234 234 2 hahahahah 2342  ddsgsdfgsdfgsdf</p>
                                </div>
                                <div class="comment">
                                    <img src="assets/Finale.jpg" class="comment-dp"></img>
                                    <p class="comment-text">whoa! cute..</p>
                                </div>
                                <div class="comment">
                                    <img src="assets/garfield.jpg" class="comment-dp"></img>
                                    <p class="comment-text">I want lasagna!</p>
                                </div>
                                <div class="comment">
                                    <img src="assets/Luna%20Lovegood.png" class="comment-dp"></img>
                                    <p class="comment-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ultrices sit amet nunc at ornare. Aenean ornare, quam ac dapibus scelerisque, turpis risus placerat justo, id tristique mauris orci a ante. Donec pharetra metus id erat facilisis posuere. </p>
                                </div>   
                                <div class="comment">
                                    <img src="assets/Luna%20Lovegood.png" class="comment-dp"></img>
                                    <p class="comment-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ultrices sit amet nunc at ornare. Aenean ornare, quam ac dapibus scelerisque, turpis risus placerat justo, id tristique mauris orci a ante. Donec pharetra metus id erat facilisis posuere. </p>
                                </div> 
                                <div class="comment">
                                    <img src="assets/Luna%20Lovegood.png" class="comment-dp"></img>
                                    <p class="comment-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ultrices sit amet nunc at ornare. Aenean ornare, quam ac dapibus scelerisque, turpis risus placerat justo, id tristique mauris orci a ante. Donec pharetra metus id erat facilisis posuere. </p>
                                </div> 
                    
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <div id="add-post" onclick="addPost(new Post('Cute Girl', 'assets/Finale.jpg', 'assets/Finale.jpg'));">+</div>

            </div>
        </div>      
        
        <script>
            
        	<%
        		try {
	        		PostDAO postDAO = new PostDAO();
	            	ArrayList<Post> posts = new ArrayList<Post>();
	            	posts = postDAO.getRecentPosts(12);
	            	
	            	for(int i=0; i<posts.size(); i++) {
	            		Post post = posts.get(i);
	            		System.out.println(post.getAccountID());
	            		AccountDAO accDAO = new AccountDAO();
	            		Account acc = accDAO.getAccountByID(post.getAccountID());
	            		String name = acc.getName();
	            		System.out.println(name);
	            		String paint = post.getPaint();
	            		String dp = acc.getProfilePic();
	            		
	            		%>
	            		addPost(new Post('<%=name%>', '<%=paint%>', '<%=dp%>'));
	            		<%
	            	}
        		} catch(Exception e) {}
        	
        	%>
            
            // Get the modal
            var modal = document.getElementById('myModal');
            var content = document.getElementById('popup-content');
            
            // When the user clicks on the button, open the modal 
            $("#posts").on('click', '.post', function() {
//                alert("Clicked!");
                modal.style.display = "block";
            });

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function(event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
                else if (event.target == content) {
                    modal.style.display = "none";
                }
            }
            
        </script>
        
    </body>
</html>