<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SignUp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body style="background-image: url('https://images.pexels.com/photos/3944463/pexels-photo-3944463.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2'); background-attachment: fixed; background-size: cover;">
  
  <h1 style = "text-align: center; margin-top: 5%;"> Sign Up </h1>
  <form class="row g-3" method="post" action="Admins" style = "margin: 7% 10% 2% 10%; background: rgba(255,255,255,0.7); border-radius: 15px; backdrop-filter: blur(10px); padding: 15px;">
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
    		<input class="btn btn-primary" name = "submit" type="submit" value="Sign Up">
  		</div>
	</form>
	
	<%if(request.getAttribute("flag") != "false" && request.getAttribute("key") == "1"){ %>
	   <h3 style = "text-align: center; margin-bottom: 25px;"> Sign Up has completed Successfully</h3>
	   <div style = "text-align: center;">
	   <a href = "log_in.html" style = "text-decoration: none; color: white; background-color: rgb(13,110,253); border-radius: 0.375rem; padding: 6px 12px;"> Login </a>
	   </div>
	<%}else if (request.getAttribute("flag") == "false" && request.getAttribute("key") == "2"){%>
	   <h3 style = "text-align: center;"> An error occured, Please Try Again</h3>
	<%} %>

</body>
</html>