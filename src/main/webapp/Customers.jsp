<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customers</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body style="background-image: url('https://images.pexels.com/photos/7291997/pexels-photo-7291997.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2'); background-attachment: fixed; background-size: cover;">
<%@ page import="java.util.ArrayList"%>
<%@ page import="Objects.*"%>


<div style = "background-color: black; padding: 5px;">
<div class="container overflow-hidden text-center">
  <div class="row gx-5">
    <div class="col">
     <div class="p-3">
     <%
		Customers d = (Customers)request.getSession(false).getAttribute("user");
		String naming;
		if (d != null){
			naming = d.getName();
			int id = d.getCustomer_ID();
			
		}
		else naming = "---";
	%>
     	<h5 style = "color: white;">Customer | Hello, <%= naming %> !</h5>
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
      <a style = "text-decoration: none; color: black;" href="Customers?action=Show_Available_Provoles">Show Provoles </a>
    </div>
    <div class="col">
      <a style = "text-decoration: none; color: black;" href="Customers?action=Make_Reservation">Make  Reservation </a>
    </div>
    <div class="col">
      <a style = "text-decoration: none; color: black;" href="Customers?action=View_Reservation">See Reservations </a>
    </div>
    <div class="col">
      <a style = "text-decoration: none; color: black;" href="Customers?action=Cancel_Reservation">Cancel Reservation </a>
    </div>
  </div>
</div>
		

<%if (request.getAttribute("action")=="1") {%>
		
	<h2 style ="text-align:center; margin-top: 35px"> Available Provoles</h2>	
	<form class="row g-3" method="post" action="Customers" style = "margin: 3% 10%;">
  		<div class="col-md-6">
    		<label class="form-label">Start Date</label>
    		<input class="form-control" name = "sdate" type = "date" >
  		</div>
  		<div class="col-md-6">
    		<label class="form-label">End Date</label>
   			<input class="form-control" name = "edate" type = "date" >
  		</div>
  		<div class="col-12">
    		<input class="btn btn-primary" name = "submit" type="submit" value="Show">
  		</div>
	</form>
	
	

<%} else if (request.getAttribute("action")=="2") { %>

	<h2 style ="text-align:center; margin-top: 35px; margin-bottom: 35px;"> Make Reservation</h2>
	
	<form class="row g-3" method="post" action="Customers" style = "margin: 3% 10%;">
  		<div class="col-md-6">
    		<label class="form-label">Film ID</label>
    		<input class="form-control" name = "filmID" type = "text" >
  		</div>
  		<div class="col-md-6">
    		<label class="form-label">Film Name</label>
   			<input class="form-control" name = "filmName" type = "text" >
  		</div>
  		<div class="col-md-6">
    		<label class="form-label">Cinema ID</label>
   			<input class="form-control" name = "CinemaID" type = "text" >
  		</div>
  		<div class="col-md-6">
    		<label class="form-label">Customer ID</label>
   			<input class="form-control" name = "CustomerID" type = "number"  value= <% if (d!=null) { int id = d.getCustomer_ID();  %> <%=id %> <%} %> readonly >
  		</div>
  		<div class="col-12">
    		<input class="btn btn-primary" name = "submit" type="submit" value="OK">
  		</div>
	</form>

<%} else if (request.getAttribute("action")=="3") { %>
		<h2 style ="text-align:center; margin-top: 35px; margin-bottom: 35px;"> See Reservations</h2>

		<form class="row g-3" method="post" action="Customers" style = "margin: 0% 10%;">
			<div class="col-md-6" >
    		<label class="form-label">Customer ID</label>
   			<input class="form-control" name = "CustomerID" type = "number"  value= <% if (d!=null) { int id = d.getCustomer_ID();  %> <%=id %> <%} %> readonly >
  			</div>
			<div class="col-12">
    		<input class="btn btn-primary" name = "submit" type="submit" value="Look">
  			</div>


		</form>
		
		
		
		
		
		
		
		
	<% } else if (request.getAttribute("action")=="4") {%>
		
		
		<h2 style ="text-align:center; margin-top: 35px"> Cancel Reservation</h2>
		<form class="row g-3" method="post" action="Customers" style = "margin: 3% 10%;">
  		<div class="col-md-6">
    		<label class="form-label">Film ID</label>
    		<input class="form-control" name = "filmID" type = "text" >
  		</div>
  		<div class="col-md-6">
    		<label class="form-label">Film Name</label>
   			<input class="form-control" name = "filmName" type = "text" >
  		</div>
  		<div class="col-md-6">
    		<label class="form-label">Cinema ID</label>
   			<input class="form-control" name = "CinemaID" type = "text" >
  		</div>
  		<div class="col-md-6">
  			<label class="form-label">Customer ID</label>
  			<input class="form-control" name = "CustomerID" type = "text" value= <% if (d!=null) { int id = d.getCustomer_ID();  %> <%=id %> <%} %> readonly>
  		</div>
  		<div class="col-12">
    		<input class="btn btn-primary" name = "submit" type="submit" value="Cancel">
  		</div>
	</form>	
	
	
	
<%} 
if (((Customers)request.getSession(false).getAttribute("user")) == null) response.sendRedirect("log_in.html");%>
<% String done = (String)request.getAttribute("done"); %>
<% String flag = (String)request.getAttribute("flag"); %>
<% if(done!=null){ %>

<%if (done.equals("Show")) {%>
	<p style = "text-align: center; font-weight: bold; font-size: 25px;">
	
	<% 
	ArrayList<Provoles> answer = new ArrayList<Provoles>();
	answer =(ArrayList<Provoles>)request.getAttribute("provoles"); %>
	
	<% if(answer==null) {%>
	
		None provoles!
	
	<% }
	else{ %>
	<h2 style ="text-align:center; margin-top: 35px; margin-bottom: 35px;"> Available Provoles</h2>
	<table border=\"1\" class="table table-striped table-bordered" style = "width: 90%; margin: 3% 4%;">
	<tr>


	<th style = "text-align:center;">Provoli ID</th>
	<th style = "text-align:center;">Film ID</th>
	<th style = "text-align:center;">Cinema ID</th>
	<th style = "text-align:center;">Film Name</th>
	<th style = "text-align:center;">Content Admin ID</th>
	<th style = "text-align:center;">Provoli Start Date </th>
	<th style = "text-align:center;">Provoli End Date </th>
	<th style = "text-align:center;">Provoli Number Of Reservations</th>
	</tr>
	<% 
	for (Provoles x : answer) { 
	
			String provoli_id = x.getPRVOLI_ID();
			String film_id = x.getProvoliFilm();
			String cinema_id = x.getProvoliCinema();
			String film_name = x.getFILM_NAME();
			int content_admin_id = x.getCONTENT_ADMIN_ID();
			String provoliStartDate = x.getProvoliStartDate();
			String provoliEndDate = x.getProvoliEndDate();
			int provoliNumberOfReservations = x.getProvoliNumberOfReservations();
	
	%>
	<tr>
		<th><%=provoli_id %></th>
		<th><%=film_id %></th>
		<th><%=cinema_id%></th>
		<th><%=film_name  %>
		<th><%=content_admin_id %></th>
		<th><%=provoliStartDate%></th>
		<th><%=provoliEndDate%></th>
		<th><%=provoliNumberOfReservations%></th>
	</tr>	
	<% }%>

	<% } %>
	</table>
	
	
	
<% }else if(done.equals("Make")) {%>
	<p style = "text-align: center; font-weight: bold; font-size: 25px;"> <%=request.getAttribute("answer") %></p>
	
	
<% }else if(done.equals("Cancel")) {%>
	<p style = "text-align: center; font-weight: bold; font-size: 25px;"> --- </p>
	
	
	
<% }else if(done.equals("Look")) {%>

	<h2 style ="text-align:center; margin-top: 35px; margin-bottom: 35px;"> Your Reservations</h2>
		
		<!-- Position for an Array -->
		
		
		
		
		
		<% 
	ArrayList<String> answer = new ArrayList<String>();
	answer =(ArrayList<String>)request.getAttribute("reservations"); %>
	
	<% if(answer==null) {%>
	
		None provoles!
	
	<% }
	else{ %>
	<table border=\"1\" class="table table-striped table-bordered" style = "width: 80%; margin: 3% 10%;">
	<tr>


	<th style = "text-align:center;">Film ID</th>
	<th style = "text-align:center;">Film name</th>
	<th style = "text-align:center;">Cinema ID</th>
	</tr>
	<% 
	for(int i=0 ; i<answer.size(); i=i+3){
	
			String film_id = answer.get(i);
			String film_name =answer.get(i+1);
			String cinema_id =answer.get(i+2);
	
	%>
	<tr>
		<th><%=film_id%></th>
		<th><%=film_name %></th>
		<th><%=cinema_id%></th>
	</tr>	
	<% }%>

	<% } %>
	</table>


<% }else{ %>
	<p> Out of index </p>
<%} %>


<%if (flag.equals("true")) {%>
	<p style = "text-align: center;"> The action has completed succesfully. </p>
<% }else{%>
	<p style = "text-align: center;"> The action has not completed succesfully. </p>
<% } %>
<% } %>

	
</body>
</html>