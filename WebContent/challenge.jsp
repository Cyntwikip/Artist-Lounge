<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<script src="paintScript.js"></script>
    <head>
    	
        
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/style.css">        
        <link rel="stylesheet" type="text/css" href="css/challenge.css">
        <link rel="stylesheet" type="text/css" href="css/navbar.css">
        <link rel="stylesheet" type="text/css" href="css/post.css">


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
<!--                    <img alt="Artist's Lounge" src="logo"></img>-->
<!--                    Artist's Lounge-->
                </a>
            </div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
              <ul class="nav navbar-nav">
                <li><a href="home.jsp">Home <span class="sr-only">(current)</span></a></li>
                <li><a href="profile.jsp">Profile</a></li>
                <li><a href="notifications.jsp">Notifications</a></li>
                <li class="active"><a href="challenge.jsp">Challenge</a></li>
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
		   <div class="container wholearea">
		  <div class="challengescreen">
		    <div class="h1class">
							
		    </div>
		
		    <div class="challengebox">
		    			<div id="clockdiv">
					  <div>
					    <span class="days"></span>
					    <div class="smalltext">Days</div>
					  </div>
					  <div>
					    <span class="hours"></span>
					    <div class="smalltext">Hours</div>
					  </div>
					  <div>
					    <span class="minutes"></span>
					    <div class="smalltext">Minutes</div>
					  </div>
					  <div>
					    <span class="seconds"></span>
					    <div class="smalltext">Seconds</div>
					  </div>
					</div>			
		        <script src="timer.js"></script>
		    
		    
<!--					<div class="challengepictureout">-->
								<img class="battlepicture1" src="assets/Luna Lovegood.png"></img>
								<!--<span>-->
								<img class="versus" src="assets/icons/versusicon.png"></img>
								<img class="battlepicture1" src="assets/Akali.png" ></img>
<!--					</div>-->
			</div>
			<div id="canvasdiv">
			
			
			
			</div>
			
		</div>
		<div id="tools">
		<input type="image" id="eraser" class="eraser" src="assets/icons/eraser.png">
		<input type="image" id="pen" class="pen" src="assets/icons/pen.ico">
		<input type="image" id="save" class="save" src="assets/icons/save.png">
		</div>
		<script src="paintScript.js"></script>
		</div>
	</body>
</html>

