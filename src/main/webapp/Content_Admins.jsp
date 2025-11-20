<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Content Admins</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body style="background-image: url('https://images.pexels.com/photos/4466492/pexels-photo-4466492.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2'); background-attachment: fixed; background-size: cover;">
<%@ page import="java.util.ArrayList"%>
<%@ page import="Objects.*"%>


<div style = "background-color: black; padding: 5px;">
<div class="container overflow-hidden text-center">
  <div class="row gx-5">
    <div class="col">
     <div class="p-3">
     <%
		ContentAdmins d = (ContentAdmins)request.getSession(false).getAttribute("user");
		String naming;
		if (d != null){
			naming = d.getName();
		}
		else naming = "---";
	%>
     	<h5 style = "color: white;">Content Admin | Hello, <%= naming %> !</h5>
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
      <a style = "text-decoration: none; color: black;" href="Content_Admins?action=Insert_Film">Insert Film </a>
    </div>
    <div class="col">
      <a style = "text-decoration: none; color: black;" href="Content_Admins?action=Show_Films">Show Films </a>
    </div>
    <div class="col">
      <a style = "text-decoration: none; color: black;" href="Content_Admins?action=Show_Available_Films">Show Provoles </a>
    </div>
    <div class="col">
      <a style = "text-decoration: none; color: black;" href="Content_Admins?action=Delete_Film">Delete Film </a>
    </div>
    <div class="col">
      <a style = "text-decoration: none; color: black;" href="Content_Admins?action=Assign_Film_To_Cinema">Assign film to Cinema </a>
    </div>
  </div>
</div>
		

<%if (request.getAttribute("action")=="1") {%>
		
	<h2 style ="text-align:center; margin-top: 35px"> Insert Film</h2>	
	<form class="row g-3" method="post" action="Content_Admins" style = "margin: 3% 10%;">
  		<div class="col-md-6">
    		<label class="form-label">Film's ID</label>
    		<input class="form-control" name = "filmID" type = "text"  maxlength ="45">
  		</div>
  		<div class="col-md-6">
    		<label class="form-label">Film's Title</label>
   			<input class="form-control" name = "filmTitle" type = "text"  maxlength ="45">
  		</div>
  		<div class="col-md-6">
    		<label class="form-label">Film's Category</label>
    		<input class="form-control" name = "filmCategory" type = "text"  maxlength ="45">
  		</div>
  		<div class="col-md-6">
    		<label class="form-label">Film's Description</label>
    		<input class="form-control" name = "filmDescription" type = "text"  maxlength ="45">
  		</div>
  		<div class="col-md-6">
    		<label class="form-label">Film's Duration</label>
    		<input class="form-control" name = "filmDuration" type = "number">
  		</div>
  		<div class="col-md-6">
    		<label class="form-label">Content Admin's ID</label>
    		<input class="form-control" name = "ContentAdminID" type = "number" value = <% if (d!=null) { int id = d.getContent_admin_ID();  %> <%=id %> <%} %> readonly >
  		</div>
  		<div class="col-12">
    		<input class="btn btn-primary" name = "submit" type="submit" value="Insert">
  		</div>
	</form>

<%} else if (request.getAttribute("action")=="2") { %>

	<% 
	ArrayList<Provoles> answer = new ArrayList<Provoles>();
	answer =(ArrayList<Provoles>)request.getAttribute("provoles"); %>
	
	<% if(answer==null) {%>
	<% }
	else{ %>
	<h2 style ="text-align:center; margin-top: 35px; margin-bottom: 35px;"> Available Provoles</h2>
	<table border=\"1\" class="table table-striped table-bordered" style = "width: 90%; margin: 3% 5%;">
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
	<%}

     if (request.getAttribute("action")=="3") {
	
	
	%>
		
		<h2 style ="text-align:center; margin-top: 35px"> Delete Film</h2>
		<form method="post" action="Content_Admins" style = "margin: 3% 35%;">
			<div class="mb-3">
  				<label class="form-label">Film ID</label>
  				<input class="form-control" name = "filmID" type = "text"  maxlength ="45">
			</div>
			<div class="mb-3">
  				<label class="form-label">Film Name</label>
  				<input class="form-control" name = "filmName" type = "text"  maxlength ="45">
			</div>
			<div class="mb-3">
    			<button name = "submit" type="submit" value = "Delete" class="btn btn-primary">Delete</button>
  			</div>

		</form>
	<% } else if (request.getAttribute("action")=="4") {
	
	
	%>
		
		
		<h2 style ="text-align:center; margin-top: 35px"> Insert Provoli</h2>	
	<form class="row g-3" method="post" action="Content_Admins" style = "margin: 3% 10%;">
  		<div class="col-md-6">
    		<label class="form-label">Provoli ID</label>
    		<input class="form-control" name = "PROVOLI_ID" type = "text"  maxlength ="45">
  		</div>
  		<div class="col-md-6">
    		<label class="form-label">Film ID</label>
   			<input class="form-control" name = "FILM_ID" type = "text"  maxlength ="45">
  		</div>
  		<div class="col-md-6">
    		<label class="form-label">Cinema ID</label>
    		<input class="form-control" name = "CINEMA_ID" type = "text"  maxlength ="45">
  		</div>
  		<div class="col-md-6">
    		<label class="form-label">Film Name</label>
    		<input class="form-control" name = "FILM_NAME" type = "text"  maxlength ="45">
  		</div>
  		<div class="col-md-6">
    		<label class="form-label">Content Admin ID</label>
    		<input class="form-control" name = "CONTENT_ADMIN_ID" type = "number" value = <% if (d!=null) { int id = d.getContent_admin_ID();  %> <%=id %> <%} %> readonly>
  		</div>
  		<div class="col-md-6">
    		<label class="form-label">Start Date</label>
    		<input class="form-control" name = "provoliStartDate" type = "date" >
  		</div>
  		<div class="col-md-6">
    		<label class="form-label">End Date</label>
    		<input class="form-control" name = "provoliEndDate" type = "date" >
  		</div>
  		<div class="col-md-6">
    		<label class="form-label">Number of Reservations</label>
    		<input class="form-control" name = "provoliNumberOfReservations" type = "number" value = "0" readonly>
  		</div>
  		<div class="col-md-6">
    	   <div class= "form-check">	
    		<input class="" name = "provoliIsAvailable" type = "checkbox" value="0" id="check" checked hidden>
    	   </div>
  		</div>
  		<div class="col-12">
    		<button class="btn btn-primary" name = "submit" type="submit" value="Assign"> Assign </button>
  		</div>
	</form>
<% }%>
<%if (request.getAttribute("action")=="5") { %>

	<% 
	ArrayList<Films> answer = new ArrayList<Films>();
	answer =(ArrayList<Films>)request.getAttribute("films"); %>
	
	<% if(answer==null) {%>
	<% }
	else{ %>
	<h2 style ="text-align:center; margin-top: 35px; margin-bottom: 35px;"> Available Films</h2>
	<table border=\"1\" class="table table-striped table-bordered" style = "width: 90%; margin: 3% 5%;">
<tr>


<th style = "text-align:center;">Film ID</th>
<th style = "text-align:center;">Film Title</th>
<th style = "text-align:center;">Film Descreption</th>
<th style = "text-align:center;">Film Duration</th>
<th style = "text-align:center;">Film Category</th>
<th style = "text-align:center;">Content Admin ID </th>
</tr>
	<% 
	for (Films x : answer) { 
	
			String film_id = x.getFilmID();
			String film_title = x.getFilmTitle();
			String film_category = x.getFilmCategory();
			String film_descreption = x.getFilmDescription();
			int film_duration = x.getFilmDuration();
			int content_admin = x.getContentAdminID();
	
	%>
	<tr>
		<th><%=film_id %></th>
		<th><%=film_title %></th>
		<th><%=film_descreption%></th>
		<th><%=film_duration  %>
		<th><%=film_category %></th>
		<th><%=content_admin%></th>
	</tr>	
	<% }%>

	<% } %>
	</table>
<%} 
if (((ContentAdmins)request.getSession(false).getAttribute("user")) == null) response.sendRedirect("log_in.html"); %>
<% String done = (String)request.getAttribute("done"); %>
<% String flag = (String)request.getAttribute("flag"); %>
<% if(done!=null){ %>

<%if (done.equals("insert")) {%>
	<p style = "text-align: center; font-weight: bold; font-size: 25px;"> The film inserted
<% }else if(done.equals("delete")) {%>
	<p style = "text-align: center; font-weight: bold; font-size: 25px;"> The film deleted
<% }else if(done.equals("assign")) {%>
	<p style = "text-align: center; font-weight: bold; font-size: 25px;"> The film assigned to cinema
<% }else{ %>
	<p> Out of index
<%} %>


<%if (flag.equals("true")) {%>
	succesfully! </p>
<% }else{%>
	not succesfully! </p>
<% } %>


<%} %>
	
</body>
</html>