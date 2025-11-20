package controllers;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.SQLException;

import dao.*;

import Objects.*;

@WebServlet("/login")
public class Register extends HttpServlet{
	

	private static final long serialVersionUID = 1L;
	
	private login_dao dao;
	private contentAdmin_dao contentAdminDao;
	private admin_dao adminDao;
	private customer_dao customerDao;
	
	public Register() {
		super();

		dao= new login_dao();
		contentAdminDao= new contentAdmin_dao();
		adminDao = new admin_dao();
		customerDao = new customer_dao();
	}
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
	
		
		String username=request.getParameter("username");
		String password=request.getParameter("pword");
		
		password=password+dao.getSalt(username);
		MessageDigest digest;
		try {
				digest = MessageDigest.getInstance("SHA-1");
				byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
				password=dao.bytesToHex(encodedhash);
		
		
		
		boolean check_user = dao.login(username,password);
		boolean check_contentAdmin = contentAdminDao.roleSearch(username);
		boolean check_admin = adminDao.roleSearch(username);
		boolean check_customer = customerDao.roleSearch(username);
		
		if(check_user==true && check_contentAdmin==true) {
			
			int ID = contentAdminDao.role_ID(username);
			HttpSession session = request.getSession();
			synchronized(session) {
				@SuppressWarnings("unchecked")
				String username1=request.getParameter("username");
				String password1=request.getParameter("pword");
				ContentAdmins contentAdmin = new ContentAdmins(dao.getName(username),username1,password1, ID, dao.getSalt(username) );
				session.setAttribute("user", contentAdmin);
				
				RequestDispatcher view = request.getRequestDispatcher("/Content_Admins.jsp");
		        view.forward(request, response);
			}
		}
		else if(check_user==true && check_admin==true) {
			
			int ID = adminDao.role_ID(username);
			HttpSession session = request.getSession();
			synchronized(session) {
				@SuppressWarnings("unchecked")
				String username1=request.getParameter("username");
				String password1=request.getParameter("pword");
				Admins admin = new Admins(dao.getName(username),username1,password1, ID, dao.getSalt(username) );
				session.setAttribute("user", admin);
				
				RequestDispatcher view = request.getRequestDispatcher("/Admins.jsp");
		        view.forward(request, response);
			}
		}
		else if(check_user==true && check_customer==true) {
			
			int ID = customerDao.role_ID(username);
			HttpSession session = request.getSession();
			synchronized(session) {
				@SuppressWarnings("unchecked")
				String username1=request.getParameter("username");
				String password1=request.getParameter("pword");
				Customers customer = new Customers(dao.getName(username),username1,password1, ID, dao.getSalt(username) );
				session.setAttribute("user", customer);
				
				RequestDispatcher view = request.getRequestDispatcher("/Customers.jsp");
		        view.forward(request, response);
			}
		}
		else {
			response.sendRedirect("log_in_try.html");
		}
	}
		catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		}
	}

	}