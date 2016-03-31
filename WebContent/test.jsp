<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.*, dao.*, java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
		//PrintWriter out = response.getWriter();
		AccountDAO accDAO = new AccountDAO();
		Account acc = new Account();
		boolean success = false;
	%>

	<% //testing deleteAccount
		acc.setId(3);
		success = accDAO.deleteAccount(acc);
		out.println("<h1>"+success+"</h1>");
	%>
	
	<%	//testing updateAccount
		/*
		acc.setId(8);
		acc.setUsername("Luna");
		success = accDAO.updateAccount(acc);
		*/
	%>
		
	<%	//testing getAccountById or username
		/*
		//acc = accDAO.getAccountByID(8);
		acc = accDAO.getAccountByUsername("Renai Spirit");
		String username = acc.getUsername();
		String password = acc.getPassword();
		String name = acc.getName();
		out.print("<h1>"+username+", "+password+", "+name+"</h1>");
		*/
	%>
	
	<%	//testing getAccountsByName
		/*
		ArrayList<Account> accounts = accDAO.getAccountsByName("test");
		for(Account a:accounts) {
			out.print("<h1>"+a.getUsername()+", "+a.getPassword()+", "+a.getName()+"</h1>");
		}	
		*/
	%>
	
	<%	//testing checkLoginDetails
		/*
		success = accDAO.checkLoginDetails("Luna", "la");
		out.println("<h1>"+success+"</h1>");
		*/
	%>
	
	

</body>
</html>