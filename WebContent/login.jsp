<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<title>Artist Lounge</title>
       <link rel="stylesheet" type="text/css" href="css/bgslideshow.css" />
	<link rel="stylesheet" type="text/css" href="css/login.css" />
       <link rel="stylesheet" type="text/css" href="css/style.css" />
	<link rel="stylesheet" href="css/bootstrap.min.css">
       
	<script type="text/javascript" src="js/modernizr.custom.86080.js"></script>
	<script src="js/bootstrap.min.js"></script>	
	
</head>
<body id="page">

	<div class="main">
        	<ul class="cb-slideshow">
            	<li><span>Image 01</span></li>
            	<li><span>Image 02</span></li>
            	<li><span>Image 03</span></li>
            	<li><span>Image 04</span></li>
            	<li><span>Image 05</span></li>
            	<li><span>Image 06</span></li>
        	</ul>
        	<div class="onTop">
            	<div class = "description-side">
					<div class="logo-div">
					<img class="logo" src="Logo/Artist%20Lounge%20Logo%205.png">
					</div>
					<br>
					
					<div class="description">
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ultrices sit amet nunc at ornare. Aenean ornare, quam ac dapibus scelerisque, turpis risus placerat justo, id tristique mauris orci a ante. Donec pharetra metus id erat facilisis posuere. </p></div>
				</div>
				<div class = "login-signup-side">
					<div class="login-section">
						<form class="login-section-form" method="POST" action="LoginServlet">
							<div class="login-username-input">
								<input id="login-username" type="text" name="username" placeholder="Username"></input><br>
								<input id="login-password" type="password" name="password" placeholder="Password"></input>
								<div class="login-submit">
									<br>
									<input type="submit" class="btn btn-primary" value="Login"></input>
								</div>
							</div>
								
								<!--Use <a> for demo, no redirect function for input-submit
								<input id="login-submit" class="btn btn-primary" type="submit" value="Login"></input>-->
								
								<!--  
								<a class="btn btn-primary" href="home.html">
								Login								
								</a>
								-->
								
						</form>
						<hr>
					<div class="signup-section">
						<!--<div class="signup-button">
							<button class="show-register btn btn-default">Register</button><br><br>
						</div>-->
                        <div class="signup-section-main">
                            <form class="signup-section-form" method="POST" action="SignupServlet">
                                <div class="signup-input">
                                    <input id="signup-firstname" type="text" name="fname" placeholder="First Name"></input><br>
                                    <input id="signup-middlename" type="text" placeholder="Middle Name"></input><br>
                                    <input id="signup-lastname" type="text" name="lname" placeholder="Last Name"></input><br>
									<input id="signup-username" type="text" name="username" placeholder="New Username"></input><br>
                                    <input id="signup-Password" type="password" name="password" placeholder="Password"></input>
                                </div>
                                <div class="signup-submit">
									<br>
									<input type="submit" class="btn btn-primary" value="Create Your Account!"></input>
                                </div>
                            </form>
                        </div>
					</div>
					</div>
        		</div>
			</div>
		</div>

</body>
</html>