package controllers;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Objects.*;
import dao.admin_dao;
import dao.login_dao;

import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/Admins")
public class Admins_Servlet extends HttpServlet{

private static final long serialVersionUID = 1L;
	
	private admin_dao adminDao;
	private login_dao loginDao;
	
	public Admins_Servlet() {
		super();
		adminDao= new admin_dao();
		loginDao = new login_dao();
	}
	
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("Create_User")) {
			request.setAttribute("action","4" );
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Admins.jsp");
			requestDispatcher.forward(request,response);
			
		}
		else if(action.equalsIgnoreCase("Delete_User")) {
			request.setAttribute("action","5" );
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Admins.jsp");
			requestDispatcher.forward(request,response);
		}
		else if(action.equalsIgnoreCase("Update_User")) {
			request.setAttribute("action","3" );
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Admins.jsp");
			requestDispatcher.forward(request,response);
		}
		else if(action.equalsIgnoreCase("View_User")) {
			request.setAttribute("action","2" );
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Admins.jsp");
			requestDispatcher.forward(request,response);
		}
		else if(action.equalsIgnoreCase("View_all_Users")) {
			request.setAttribute("action","1" );
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Admins.jsp");
			
			ArrayList<Users> users = new ArrayList<Users>();
			users = adminDao.viewAllUsers();
			
			request.setAttribute("users", users);
			requestDispatcher.forward(request,response);
		}
		else if(action.equalsIgnoreCase("Register_Admin")) {
			request.setAttribute("action","6" );
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Admins.jsp");
			requestDispatcher.forward(request,response);
		}
		
	}
	
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String check = (String)request.getParameter("submit");
		String answer;
		if(check.equals("SignUp")){
			
			
			if((String)request.getParameter("username")=="" || (String)request.getParameter("password")=="" || (String)request.getParameter("name")=="" || (String)request.getParameter("role")=="")
			{
				
				answer = "Complete the fields correctly, then try again!";
				request.setAttribute("answer", answer);
				request.setAttribute("done", "SignUp");
				request.setAttribute("flag", "false");
				request.getRequestDispatcher("Admins.jsp").forward(request, response);
			}
			else {
				// from example 09_mvc-example
				String salt=loginDao.getAlphaNumericString(16);
				String password = (String)request.getParameter("password") + salt;
				
				String flag = "false";
				
					
					MessageDigest digest;
					try {
							digest = MessageDigest.getInstance("SHA-1");
							byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
							password=loginDao.bytesToHex(encodedhash);
							password =""+password;
							
							HttpSession session = request.getSession();
							Users user = new Users((String)request.getParameter("name"),(String)request.getParameter("username"),password,salt);
							flag = Boolean.toString(adminDao.CreateUser(user));
							
							if (flag.equals("true")) {
								flag = Boolean.toString(adminDao.CreateRole(Integer.parseInt(request.getParameter("role")),(String)request.getParameter("username")));
								answer = "The SignUp has completed! ";
							}
							else answer = "The user already exists - try again";
					}
					catch(NoSuchAlgorithmException e) 
					{
						e.printStackTrace();
						flag="false";
						answer = "We have a problem - try again";
					}
				request.setAttribute("answer", answer);
				request.setAttribute("done", "SignUp");
				request.setAttribute("flag", flag);
				request.getRequestDispatcher("Admins.jsp").forward(request, response);
			}
			
			
		}
		else if(check.equals("Sign Up")) {
			if((String)request.getParameter("username")==null || (String)request.getParameter("password")==null || (String)request.getParameter("name")==null || (String)request.getParameter("role")==null)
			{
				request.setAttribute("flag", "false");
				request.setAttribute("key", "2");
				request.getRequestDispatcher("SignUp.jsp").forward(request, response);
			}
			else {
				
				String salt=loginDao.getAlphaNumericString(16);
				String password = (String)request.getParameter("password") + salt;
				
				String flag = "false";
					
					MessageDigest digest;
					try {
							digest = MessageDigest.getInstance("SHA-1");
							byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
							password=loginDao.bytesToHex(encodedhash);
							password =""+password;
							
							HttpSession session = request.getSession();
							Users user = new Users((String)request.getParameter("name"),(String)request.getParameter("username"),password,salt);
							flag = Boolean.toString(adminDao.CreateUser(user));
							if (flag.equals("true")) {
								flag = Boolean.toString(adminDao.CreateRole(Integer.parseInt(request.getParameter("role")),(String)request.getParameter("username")));
								request.setAttribute("key", "1");
							}
							else request.setAttribute("key", "2");
					}
					catch(NoSuchAlgorithmException e) 
					{
						e.printStackTrace();
						flag="false";
						request.setAttribute("key", "1");
					}
					
				request.setAttribute("flag", flag);
				request.getRequestDispatcher("SignUp.jsp").forward(request, response);
			}
			
		}
		else if(check.equals("Delete")) {
			
			if((String)request.getParameter("username")=="")
			{
				answer = "Type a valid username, then try again!";
				request.setAttribute("answer", answer);
				request.setAttribute("done", "Delete");
				request.setAttribute("flag", "false");
				request.getRequestDispatcher("Admins.jsp").forward(request, response);
				
			}
			else {
				
				String flag = Boolean.toString(adminDao.DeleteUser((String)request.getParameter("username")));
				if(flag.equals("true")) {
					answer = "The delete has completed!";
				}
				else {
					answer = "We can't find the user, try again";
				}
				request.setAttribute("answer", answer);
				request.setAttribute("done", "Delete");
				request.setAttribute("flag", flag);
				request.getRequestDispatcher("Admins.jsp").forward(request, response);
			}
			
		}
		else if(check.equals("Update")) {
			if((String)request.getParameter("usernameNow")=="" || (String)request.getParameter("username")=="" || (String)request.getParameter("name")=="" || (String)request.getParameter("password")=="")
			{
				answer = "Complete the fields correctly, then try again!";
				request.setAttribute("answer", answer);
				request.setAttribute("done", "Update");
				request.setAttribute("flag", "false");
				request.getRequestDispatcher("Admins.jsp").forward(request, response);
			}
			else {
			    String flag="false";
				
				String salt=loginDao.getAlphaNumericString(16);
				String password = (String)request.getParameter("password") + salt;
				
				
	
				MessageDigest digest;
				try {
						digest = MessageDigest.getInstance("SHA-1");
						byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
						password=loginDao.bytesToHex(encodedhash);
						password =""+password;
						
						HttpSession session = request.getSession();
						flag = Boolean.toString(adminDao.UpdateUser((String)request.getParameter("usernameNow"),(String)request.getParameter("username"),salt,(String)request.getParameter("name"),password));
						if(flag.equals("true")) {
							answer = "The update has completed";
						}
						else {
							answer = "We can't find the user";
						}
				}
				catch(NoSuchAlgorithmException e) 
				{
					e.printStackTrace();
					flag="false";
					answer = "We have a problem, try again!";
				}
				
				request.setAttribute("answer", answer);
				request.setAttribute("done", "Update");
				request.setAttribute("flag", flag);
				request.getRequestDispatcher("Admins.jsp").forward(request, response);
			}
		}
		else if(check.equals("Search")) {
			
			
			
			if((String)request.getParameter("userUsername")==null)
			{
				request.setAttribute("done", "Search");
				request.setAttribute("flag", "false");
				request.getRequestDispatcher("Admins.jsp").forward(request, response);
			}
			else {
				if(adminDao.searchUser((String)request.getParameter("userUsername"))==null){
					request.setAttribute("done", "Search");
					request.setAttribute("flag", "false");
					request.getRequestDispatcher("Admins.jsp").forward(request, response);
				}
				else {
					Users user = adminDao.searchUser((String)request.getParameter("userUsername"));
					
					request.setAttribute("searchedUser",user);
					request.setAttribute("done","Search");
					request.setAttribute("flag","true");
					request.getRequestDispatcher("Admins.jsp").forward(request, response);
				}
			}
		}
		
	}
}