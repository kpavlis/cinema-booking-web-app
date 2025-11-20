package controllers;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Objects.*;
import dao.customer_dao;
import dao.login_dao;
import dao.contentAdmin_dao;

import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/Customers")
public class Customers_Servlet extends HttpServlet{

private static final long serialVersionUID = 1L;
	
	private customer_dao customerDao;
	private contentAdmin_dao contentAdminDao;
	
	public Customers_Servlet() {
		super();
		customerDao= new customer_dao();
		contentAdminDao = new contentAdmin_dao();
	}
	
	
public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("Show_Available_Provoles")) {
			request.setAttribute("action","1" );
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Customers.jsp");
			requestDispatcher.forward(request,response);
			
		}
		else if(action.equalsIgnoreCase("Make_Reservation")) {
			request.setAttribute("action","2" );
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Customers.jsp");
			requestDispatcher.forward(request,response);
		}
		else if(action.equalsIgnoreCase("View_Reservation")) {
			request.setAttribute("action","3" );
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Customers.jsp");
			requestDispatcher.forward(request,response);
		}
		else if(action.equalsIgnoreCase("Cancel_Reservation")) {
			request.setAttribute("action","4" );
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Customers.jsp");
			requestDispatcher.forward(request,response);
		}
		
	}
	
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String check = (String)request.getParameter("submit");
		
		if(check.equals("Show")) {
			
			if((String)request.getParameter("sdate")==null || (String)request.getParameter("edate")==null)
			{
				request.setAttribute("done", "Show");
				request.setAttribute("flag", "false");
				request.getRequestDispatcher("Customers.jsp").forward(request, response);
			}
			else {
				
				ArrayList<Provoles> provoles = new ArrayList<Provoles>();
				provoles = customerDao.showAvailableFilms((String)request.getParameter("sdate"),(String)request.getParameter("edate"));
				request.setAttribute("done", "Show");
				request.setAttribute("provoles", provoles);
				request.setAttribute("flag", "true");
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Customers.jsp");
				requestDispatcher.forward(request,response);
			}
			
		}
		else if(check.equals("OK")) {
			String answer;
			if((String)request.getParameter("filmID")==null || (String)request.getParameter("filmName")==null || (String)request.getParameter("CinemaID")==null || (String)request.getParameter("CustomerID")==null)
			{
				answer = "An error has occured - try again !";
				request.setAttribute("answer", answer);
				request.setAttribute("done", "Make");
				request.setAttribute("flag", "false");
				request.getRequestDispatcher("Customers.jsp").forward(request, response);
			}
			else {
				String flag = "false";
				boolean check_provoli = customerDao.checkProvoli((String)request.getParameter("filmID"), (String)request.getParameter("filmName"), (String)request.getParameter("CinemaID"));
				boolean check_availability = customerDao.ProvolesIsAvailable((String)request.getParameter("filmID"), (String)request.getParameter("filmName"), (String)request.getParameter("CinemaID"));
				if(check_provoli==true && check_availability == true) {
					
					boolean checkSeats;
					int seats;
					seats = contentAdminDao.Seats((String)request.getParameter("CinemaID"));
					int reservations;
					reservations = contentAdminDao.Reservations((String)request.getParameter("filmID"), (String)request.getParameter("filmName"), (String)request.getParameter("CinemaID"));
					checkSeats= contentAdminDao.checkSeats(seats, reservations);
					
					flag = Boolean.toString(contentAdminDao.ProvolesAvailability((String)request.getParameter("filmID"), (String)request.getParameter("filmName"), (String)request.getParameter("CinemaID"), checkSeats));
					
					if(flag.equals("true") && checkSeats == true) {
					
						String answer2;
						flag = Boolean.toString(customerDao.makeReservation((String)request.getParameter("filmID"), (String)request.getParameter("filmName"), (String)request.getParameter("CinemaID"), Integer.parseInt(request.getParameter("CustomerID"))));
						if(flag.equals("true")) {
						flag = Boolean.toString(customerDao.AddProvoles((String)request.getParameter("filmID"), (String)request.getParameter("filmName"), (String)request.getParameter("CinemaID")));
						answer2 = "Your reservation has completed !";
						request.setAttribute("answer", answer2);
						}else {
							answer2 = "Your reservation can't completed !";
							request.setAttribute("answer", answer2);
							
						}
						
					}
					else {
						
						answer= "Sorry the cinema is already full!";
						request.setAttribute("answer", answer);
						request.setAttribute("done", "Make");
						request.setAttribute("flag", flag);
						request.getRequestDispatcher("Customers.jsp").forward(request, response);
					}
					
					
				}else {
					answer = "The provoli isn't available!";
					request.setAttribute("answer", answer);
				}
				
				
				
		
				request.setAttribute("done", "Make");
				request.setAttribute("flag", flag);
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Customers.jsp");
				requestDispatcher.forward(request,response);
			}
			
			
			
		}
		else if(check.equals("Cancel")) {
			String answer;
			if((String)request.getParameter("filmID")==null || (String)request.getParameter("filmName")==null || (String)request.getParameter("CinemaID")==null || (String)request.getParameter("CustomerID")==null)
			{
				answer = "An error has occured - try again !";
				request.setAttribute("answer", answer);
				request.setAttribute("done", "Cancel");
				request.setAttribute("flag", "false");
				request.getRequestDispatcher("Customers.jsp").forward(request, response);
				
			}
			else {
				
				String flag="false";
				boolean check_provoli = customerDao.checkProvoli((String)request.getParameter("filmID"), (String)request.getParameter("filmName"), (String)request.getParameter("CinemaID"));
				boolean check_reservation = customerDao.checkReservation((String)request.getParameter("filmID"), (String)request.getParameter("filmName"), (String)request.getParameter("CinemaID"), Integer.parseInt(request.getParameter("CustomerID")));
				if(check_provoli==true && check_reservation==true) {
					
					    flag = Boolean.toString(customerDao.ReduceProvoles((String)request.getParameter("filmID"), (String)request.getParameter("filmName"), (String)request.getParameter("CinemaID")));
					
						flag = Boolean.toString(customerDao.cancelReservation((String)request.getParameter("filmID"), (String)request.getParameter("filmName"), (String)request.getParameter("CinemaID"), Integer.parseInt(request.getParameter("CustomerID"))));
						int seats;
						boolean checkSeats;
						seats = contentAdminDao.Seats((String)request.getParameter("CinemaID"));
						int reservations;
						reservations = contentAdminDao.Reservations((String)request.getParameter("filmID"), (String)request.getParameter("filmName"), (String)request.getParameter("CinemaID"));
						checkSeats= contentAdminDao.checkSeats(seats, reservations);
						flag = Boolean.toString(contentAdminDao.ProvolesAvailability((String)request.getParameter("filmID"), (String)request.getParameter("filmName"), (String)request.getParameter("CinemaID"), checkSeats));
						
						
					
					
					}
				
				
				if(flag.equals("true")) {
					answer = "Your reservation has cancelled!";
				}
				else {
					answer = "We can't find your reservation - try again !";
				}
				
				request.setAttribute("answer", answer);
				
				
				request.setAttribute("done", "Make");
				request.setAttribute("flag", flag);
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Customers.jsp");
				requestDispatcher.forward(request,response);
				
			}
		}
		else if(check.equals("Look")) {
			
			if((String)request.getParameter("CustomerID")==null)
			{
				request.setAttribute("done", "Make");
				request.setAttribute("flag", "false");
				request.getRequestDispatcher("Customers.jsp").forward(request, response);
			}
			else {
			
			
				ArrayList<String> reservations = new ArrayList<String>();
				reservations = customerDao.viewReservation(Integer.parseInt(request.getParameter("CustomerID")));
			
				request.setAttribute("reservations", reservations);
				request.setAttribute("done", "Look");
				request.setAttribute("flag", "true");
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Customers.jsp");
				requestDispatcher.forward(request,response);
			}
			
		}
		
		
	}
	
	
	
}