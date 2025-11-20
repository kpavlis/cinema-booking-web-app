<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admins</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body style="background-image: url('https://images.pexels.com/photos/6610075/pexels-photo-6610075.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2'); background-attachment: fixed; background-size: cover;">
<%@ page import="java.util.ArrayList"%>
<%@ page import="Objects.*"%>


<div style = "background-color: black; padding: 5px;">
<div class="container overflow-hidden text-center">
  <div class="row gx-5">
    <div class="col">
     <div class="p-3">
     <%
		Admins d = (Admins)request.getSession(false).getAttribute("user");
		String naming;
		if (d != null){
			naming = d.getName();
		}
		else naming = "---";
	%>
     	<h5 style = "color: white;">Admin | Hello, <%= naming %> !</h5>
     </div>
    </div>
    <div class="col">
      <div class="p-3">
      	<button type="button" style = "border-radius: 5px; border: none" onclick="window.location.href='logout'">Logout</button>
      </div>
    </div>
  </div>
</div>

</div>

<div class="container text-center" style = " border-radius: 20px; margin-top: 15px; margin-bottom: 15px; background-color: white; padding: 10px;">
<h4 style = "margin: auto auto;"> Select Action </h4>
  <div class="row align-items-start" style = "padding: 12px; border-color: black; border-style: solid; border-radius: 10px; margin: 10px;" >
    <div class="col">
      <a style = "text-decoration: none; color: black;" href="Admins?action=View_all_Users">See All Users </a>
    </div>
    <div class="col">
      <a style = "text-decoration: none; color: black;" href="Admins?action=View_User">Search User </a>
    </div>
    <div class="col">
      <a style = "text-decoration: none; color: black;" href="Admins?action=Update_User">Update User </a>
    </div>
    <div class="col">
      <a style = "text-decoration: none; color: black;" href="Admins?action=Create_User">Sign Up User </a>
    </div>
     <div class="col">
      <a style = "text-decoration: none; color: black;" href="Admins?action=Delete_User">Delete User </a>
    </div>
  </div>
</div>
		

<%if (request.getAttribute("action")=="1") {%>
		
	<h2 style ="text-align:center; margin-top: 35px"> All Users</h2>	
	
	<!--  Position For Array -->
	
	
	
	
	<% 
	ArrayList<Users> answer = new ArrayList<Users>();
	answer =(ArrayList<Users>)request.getAttribute("users"); %>
	
	<% if(answer==null) {%>
	<% }
	else{ %>
	<h2 style ="text-align:center; margin-top: 35px; margin-bottom: 35px;"> The users are (order by username): </h2>
	<table border=\"1\" class="table table-striped table-bordered"  style = "width: 80%; margin: 3% 10%;">
<tr>


<th style = "text-align:center;">Username</th>
<th style = "text-align:center;">Name</th>
</tr>
	<% 
	for (Users x : answer) { 
	
			String username = x.getUsername();
			String name = x.getName();
	
	%>
	<tr>
		<th><%=username %></th>
		<th><%=name %></th>
	</tr>	
	<% }%>

	<% } %>
	</table>
	
	
	
	
	

<%} else if (request.getAttribute("action")=="2") { %>

	<h2 style ="text-align:center; margin-top: 35px; margin-bottom: 35px;"> Search User</h2>
	
	<form class="row g-3" method="post" action="Admins" style = "margin: 3% 10%;">
  		<div class="col-md-6">
    		<label class="form-label">Username</label>
    		<input class="form-control" name = "userUsername" type = "text" >
  		</div>
  		<div class="col-12">
    		<input class="btn btn-primary" name = "submit" type="submit" value="Search">
  		</div>
	</form>

	<%} if (request.getAttribute("action")=="3") {%>
		
		<h2 style ="text-align:center; margin-top: 35px"> Update User</h2>
		
		<form class="row g-3" method="post" action="Admins" style = "margin: 3% 10%;">
  		<div class="col-md-6">
    		<label class="form-label">Username Now</label>
    		<input class="form-control" name = "usernameNow" type = "text" >
  		</div>
  		<div class="col-md-6">
    		<label class="form-label">Name</label>
    		<input class="form-control" name = "name" type = "text" >
  		</div>
  		<div class="col-md-6">
    		<label class="form-label">Username</label>
   			<input class="form-control" name = "username" type = "text" >
  		</div>
  		<div class="col-md-6">
    		<label class="form-label">Password</label>
   			<input class="form-control" name = "password" type = "text" >
  		</div>
  		<div class="col-12">
    		<input class="btn btn-primary" name = "submit" type="submit" value="Update">
  		</div>
	</form>
		
	<%} else if (request.getAttribute("action")=="4") {%>
	
	<h2 style ="text-align:center; margin-top: 35px"> Sign Up User</h2>
		
		<form class="row g-3" method="post" action="Admins" style = "margin: 3% 10%;">
  		<div class="col-md-6">
    		<label class="form-label">Name</label>
    		<input class="form-control" name = "name" type = "text" >
  		</div>
  		<div class="col-md-6">
    		<label class="form-label">Username</label>
   			<input class="form-control" name = "username" type = "text" >
  		</div>
  		<div class="col-md-6">
    		<label class="form-label">Password</label>
   			<input class="form-control" name = "password" type = "password" >
  		</div>
  		<div class="col-md-6">
    		<label class="form-label">Role</label>
    		<br>
    		<div class="form-check">
   			<input class="form-check-input" id = "r1" name = "role" type = "radio" value = "1">
   			<label class="form-check-label" for="r1">
            Customer
            </label>
   			</div>
   			<div class="form-check">
   			<input class="form-check-input" id="r2" name = "role" type = "radio" value = "2">
   			<label class="form-check-label" for="r2">
             Admin
            </label>
   			</div>
   			<div class="form-check">
   			<input class="form-check-input" id="r3" name = "role" type = "radio" value = "3">
   			<label class="form-check-label" for="r3">
             Content Admin
            </label>
   			</div>
  		</div>
  		<div class="col-12">
    		<input class="btn btn-primary" name = "submit" type="submit" value="SignUp">
  		</div>
	</form>
	
<%} if (request.getAttribute("action")=="5") {%>

	<h2 style ="text-align:center; margin-top: 35px"> Delete User</h2>
		
		<form class="row g-3" method="post" action="Admins" style = "margin: 3% 10%;">
  		<div class="col-md-6">
    		<label class="form-label">Username</label>
    		<input class="form-control" name = "username" type = "text" >
  		</div>
  		<div class="col-12">
    		<input class="btn btn-primary" name = "submit" type="submit" value="Delete">
  		</div>
	</form>

	
<%}
if (((Admins)request.getSession(false).getAttribute("user")) == null) response.sendRedirect("log_in.html");%>	
<% String done = (String)request.getAttribute("done"); %>
<% String flag = (String)request.getAttribute("flag"); %>
<% if(done!=null){ %>

<%if (done.equals("SignUp")) {%>
	<p style = "text-align: center; font-weight: bold; font-size: 25px;"> <%=request.getAttribute("answer") %>
<% }else if(done.equals("Delete")) {%>
	<p style = "text-align: center; font-weight: bold; font-size: 25px;"> <%=request.getAttribute("answer") %>
<% }else if(done.equals("Update")) {%>
	<p style = "text-align: center; font-weight: bold; font-size: 25px;"> <%=request.getAttribute("answer") %>
<% }else if(done.equals("Search")) {%>
	<h2 style ="text-align:center; margin-top: 35px"> User Details</h2>
	<p style = "text-align: center; font-weight: bold; font-size: 25px; margin-top: 20px;">
	
	<% Users user = ((Users)request.getAttribute("searchedUser")); 
	if(user !=null){%>
	
	<tr>
		<th> Username : <%= user.getUsername() %></th>          </br>
		<th> Name : <%= user.getName() %></th> </p>             </br>
	</tr>	
	
	<%} %>
	
<% }else{ %>
	<p style = "text-align: center;"> Out of index </p>
<%} %>


<%if (flag.equals("true")) {%>
	<p style = "text-align: center;"> The action has completed succesfully. </p>
<% }else{%>
	<p style = "text-align: center;"> The action has not completed succesfully. </p>
<% } %>


<%} %>
	
</body>
</html>