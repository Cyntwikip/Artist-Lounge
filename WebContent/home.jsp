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
                <li><a href="notifications.jsp">Notifications</a></li>
                <li><a href="challenge.jsp">Challenge</a></li>
              </ul>
                
              <form class="navbar-form navbar-left" role="search" id="search-bar" method="POST" action="SearchPostServlet">
                <div class="form-group">
                  <input id="search" name="search" type="text" class="form-control" placeholder="Search"/>
<!--
                    <span>
                        <button type="submit" class="btn btn-default">
                            <img src="assets/icons/search.png">
                        </button>
                    </span>
-->
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
                            <div style="display:flex; flex-direction:column; max-height:100%">
                                
                                <div id="popup-image-details">
<!--                                    <a id="linkToProfile">-->
                                        <form id="linkToProfile" method="POST" action="ProfileServlet">
                                            <input type="image" src="assets/Akali.png" id="popup-image-user-dp" alt="Submit Form"></input>
                                            <input id="linkProfilePostID" type="hidden" name="postID" value=""/>
                                        </form>
<!--                                    </a>-->
                                    <div id="popup-image-user-name">Akali</div>
                                    <p id="post-description"></p>
                                </div>
                                <hr>
                                <div id="popup-features-container">
                                    <p id="numOfLikes" style="display:inline-block; margin-right:10px;">1</p>
<!--                                    <button id="like" class="btn btn-info features" onclick="likeBtn();">Like</button>-->
                                    <input id="like" type="button" class="btn btn-info features" onclick="likeBtn();" value="Like"/>
                                    <button id="comment" class="btn btn-danger features" onclick="$('#comment-field').focus();">Comment</button>
                                </div>
                                <hr>
                                <div id="comments">
    <!--                                comments-->
                                </div> 

                                <form id="add-comment-form" method="POST" action="CommentServlet">
                                    <input id="comment-field" type="text" name="comment" placeholder="Write your comment here..." style="width:100%; padding:10px" maxlength="950">
                                    <input id="postID-comment" type="hidden" name="post-id" value=""/> 
                                </form>
                            
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
                            <textarea name="description" placeholder="Description..." rows="6" maxlength="250" style="resize:none; width:100%"></textarea>
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
            <button id="add-post">
                <p style="  position: absolute;
                            top: 50%;
                            left: 50%;
                            transform: translate(-50%, -50%);">+</p>
            </button>

            </div>
        </div>      
        
        <script>
        
        	<%
        		//String filePath = "file:///C:/Users/Jude Michael Teves/Pictures/Artist-Lounge Storage/";
                String currentUser = (String) session.getAttribute("username");
                AccountDAO accDAO = new AccountDAO();
                int userID = accDAO.getAccountByUsername(currentUser).getId();
        	%>
            
        	<%
        		try {
	        		PostDAO postDAO = new PostDAO();
	            	ArrayList<Post> posts = new ArrayList<Post>();
	            	posts = postDAO.getRecentPosts(12);
	            	
	            	for(int i=0; i<posts.size(); i++) {
	            		Post post = posts.get(i);
	            		//System.out.println(post.getAccountID());
	            		//AccountDAO accDAO = new AccountDAO();
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
                
                //poster's dp
                //var posterDP = ($(this).attr('id') + "> .post-details > .post-dp").attr('src');
                var posterDP = $(this).find('.post-dp').attr('src');
                //alert(posterDP);
                $('#popup-image-user-dp').attr("src", posterDP);
                
                //get post id
                var id = $(this).attr('id');
                $('#postID-comment').attr('value', id);
                $('#linkProfilePostID').attr('value', id);
                
                
                //get and set name
                var name = $(this).find('.post-name').text();
                $('#popup-image-user-name').html(name);
                //$('#popup-image-user-name').empty();
                //$('#popup-image-user-name').append(name);
                
                //set description
//                $('#post-description').html('Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ultrices sit amet nunc at ornare. Aenean ornare, quam ac dapibus scelerisque, turpis risus placerat justo, id tristique mauris orci a ante. Donec pharetra metus id erat facilisis posuere.');
                var xhttp3 = new XMLHttpRequest();
                xhttp3.onreadystatechange = function() {
                    if (xhttp3.readyState == 4 && xhttp3.status == 200) {
                        var output = xhttp3.responseText;
                        $('#post-description').html(output);
                    }
                  };
                xhttp3.open("POST", "GetDescriptionServlet", true);
                xhttp3.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp3.send("postID="+id);
                
                //check like
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function() {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        var output = xhttp.responseText.split(" ");
                        var suffix;
                        //alert(output);
                        $('#like').attr('value', output[0]);
                        if(output[1] == 1)
                            suffix = " like";
                        else
                            suffix = " likes";
                        $('#numOfLikes').html(output[1]+suffix);
                    }
                  };
                xhttp.open("POST", "CheckLikeServlet", true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.send("postID="+id);
                
                //get comments
                var xhttp2 = new XMLHttpRequest();
                xhttp2.onreadystatechange = function() {
                    if (xhttp2.readyState == 4 && xhttp2.status == 200) {
                      //document.getElementById("demo").innerHTML = xhttp.responseText;
                        //alert(xhttp.responseText);
                        var output = xhttp2.responseText.split("\n");
                        for(var i=0; i<output.length; i++) {
                            if(/\S/.test(output[i])) {
                                //alert(output[i]);
                                var output2 = output[i].split(" ", 2);
                                var text = output[i].replace(output2[0]+" "+output2[1]+" ", "");
//                                text = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ultrices sit amet nunc at ornare. Aenean ornare, quam ac dapibus scelerisque, turpis risus placerat justo, id tristique mauris orci a ante. Donec pharetra metus id erat facilisis posuere.';
                                //alert(text);
                                addComment(new Comment(output2[0], text, output2[1]));
                            }
                        }
                    }
                  };
                xhttp2.open("POST", "GetCommentsServlet", true);
                xhttp2.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp2.send("postID="+id);
                
                
//                addComment(new Comment('assets/Finale.jpg', 'Whoa! cute!', 0));
//                addComment(new Comment('assets/Luna%20Lovegood.png', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ultrices sit amet nunc at ornare. Aenean ornare, quam ac dapibus scelerisque, turpis risus placerat justo, id tristique mauris orci a ante. Donec pharetra metus id erat facilisis posuere.', 1));
//                addComment(new Comment('assets/Finale.jpg', 'Whoa! cute!', 2));
//                addComment(new Comment('assets/Finale.jpg', 'Whoa! cute!', 3));
//                addComment(new Comment('assets/Finale.jpg', 'Whoa! cute!', 4));
//                
//                addComment(new Comment('assets/Luna%20Lovegood.png', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ultrices sit amet nunc at ornare. Aenean ornare, quam ac dapibus scelerisque, turpis risus placerat justo, id tristique mauris orci a ante. Donec pharetra metus id erat facilisis posuere.', 5));
//                addComment(new Comment('assets/Luna%20Lovegood.png', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ultrices sit amet nunc at ornare. Aenean ornare, quam ac dapibus scelerisque, turpis risus placerat justo, id tristique mauris orci a ante. Donec pharetra metus id erat facilisis posuere.', 6));
//                addComment(new Comment('assets/Luna%20Lovegood.png', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ultrices sit amet nunc at ornare. Aenean ornare, quam ac dapibus scelerisque, turpis risus placerat justo, id tristique mauris orci a ante. Donec pharetra metus id erat facilisis posuere.', 7));
                
            });

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function(event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                    emptyCommentSection();
                    $('#comment-field').val('');
                }
                else if (event.target == content) {
                    modal.style.display = "none";
                    emptyCommentSection();
                    $('#comment-field').val('');
                }
            }
            
            function closeAddPost() {
                addpost.style.display = "none";
            }
            
            $(document).on("submit", "#add-comment-form", function(event) {
                var $form = $(this);
                $.post($form.attr("action"), $form.serialize(), function(response) {
                    $('#comments').empty();
                    $('#comment-field').val('');
                    var output = response.split("\n");
                    for(var i=0; i<output.length; i++) {
                        if(/\S/.test(output[i])) {
                            var output2 = output[i].split(" ", 2);
                            var text = output[i].replace(output2[0]+" "+output2[1]+" ", "");
                            addComment(new Comment(output2[0], text, output2[1]));
                        }
                    }
                });
                event.preventDefault(); // Important! Prevents submitting the form.
            });        
                    
            function likeBtn() {
                var likeVal = $('#like').attr('value');
                var id = $('#postID-comment').attr('value');
                
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function() {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {   
                        var output = xhttp.responseText;
                        //alert(output);
                        var suffix;
                        if(output == 1)
                            suffix = " like";
                        else
                            suffix = " likes";
                        $('#numOfLikes').html(output+suffix);
                    }
                  };
                xhttp.open("POST", "LikeServlet", true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.send("like="+likeVal+"&postID="+id);
                
                if(likeVal == "Like")
                    $('#like').attr('value', "Unlike");
                else
                    $('#like').attr('value', "Like");
                
            }
                    
            $(document).on("submit", "#search-bar", function(event) {
                var $form = $(this);
                $.post($form.attr("action"), $form.serialize(), function(response) {
                    $('#posts').empty();
                    var output = response.split("\n");
                        for(var i=0; i<output.length; i++) {
                            if(/\S/.test(output[i])) {
                                var output2 = output[i].split(" ", 3);
                                var name = output[i].replace(output2[0]+" "+output2[1]+" "+output2[2]+" ", "");
                                addPost(new Post(name, output2[0], output2[1], output2[2]));
                            }
                        }
                });
                event.preventDefault(); // Important! Prevents submitting the form.
            }); 
                    
                    
        </script>
        
    </body>
</html>