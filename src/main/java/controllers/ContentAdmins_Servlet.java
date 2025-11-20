package controllers;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Objects.*;
import dao.contentAdmin_dao;
import dao.login_dao;

import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/Content_Admins")
public class ContentAdmins_Servlet extends HttpServlet{
	
private static final long serialVersionUID = 1L;
	
	private contentAdmin_dao contentAdminDao;
	
	public ContentAdmins_Servlet() {
		super();
		contentAdminDao= new contentAdmin_dao();
	}

	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
	  if (request.getSession(false) != null) {
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("Assign_Film_To_Cinema")) {
			request.setAttribute("action","4" );
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Content_Admins.jsp");
			requestDispatcher.forward(request,response);
		}
		else if (action.equalsIgnoreCase("Delete_Film")) {
			request.setAttribute("action","3" );
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Content_Admins.jsp");
			requestDispatcher.forward(request,response);
		}
		else if (action.equalsIgnoreCase("Show_Available_Films")) {
			request.setAttribute("action","2" );
			ArrayList<Provoles> provoles = new ArrayList<Provoles>();
			provoles = contentAdminDao.AvailableFilms();
			request.setAttribute("provoles", provoles);
			request.getRequestDispatcher("Content_Admins.jsp").forward(request, response);
		}
		else if (action.equalsIgnoreCase("Insert_Film")) {
			request.setAttribute("action","1" );
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Content_Admins.jsp");
			requestDispatcher.forward(request,response);
		}
		else if (action.equalsIgnoreCase("Show_Films")) {
			request.setAttribute("action","5" );
			ArrayList<Films> films = new ArrayList<Films>();
			films = contentAdminDao.ShowFilms();
			request.setAttribute("films", films);
			request.getRequestDispatcher("Content_Admins.jsp").forward(request, response);
		}
	  }else {
		  response.sendRedirect("log_in.html");
	  }
	}
	
	
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
	  if (request.getSession(false) != null) {
		String check = (String)request.getParameter("submit");
		if (check.equals("Insert")) {
			if(request.getParameter("filmDuration").equals("") || request.getParameter("ContentAdminID").equals("")) {
				request.setAttribute("done", "insert");
				request.setAttribute("flag", "false");
				request.getRequestDispatcher("Content_Admins.jsp").forward(request, response);
			}
			else {
			Films film = new Films(request.getParameter("filmID") , request.getParameter("filmTitle") ,
					request.getParameter("filmCategory") ,request.getParameter("filmDescription"), 
					Integer.parseInt(request.getParameter("filmDuration")) ,Integer.parseInt(request.getParameter("ContentAdminID")));
			String flag = Boolean.toString(contentAdminDao.InsertFilm(film));
			request.setAttribute("done", "insert");
			request.setAttribute("flag", flag);
			request.getRequestDispatcher("Content_Admins.jsp").forward(request, response);
			}
		}
		else if (check.equals("Delete")) {
	        String deleted = Boolean.toString(contentAdminDao.DeleteFilm(request.getParameter("filmID"), request.getParameter("filmName")));
			request.setAttribute("done", "delete");
			request.setAttribute("flag", deleted);
			request.getRequestDispatcher("Content_Admins.jsp").forward(request, response);
		}
		else if (check.equals("Assign")) {
			boolean check1 = contentAdminDao.checkCinema(request.getParameter("CINEMA_ID"));
			boolean check2 = contentAdminDao.checkFilm(request.getParameter("FILM_NAME"),request.getParameter("FILM_ID"));
			
			if(check1&check2) {
				Provoles provoli = new Provoles(request.getParameter("PROVOLI_ID"), request.getParameter("FILM_ID") ,request.getParameter("CINEMA_ID"),
				request.getParameter("provoliStartDate") , request.getParameter("provoliEndDate") ,
				Integer.parseInt(request.getParameter("provoliNumberOfReservations")), request.getParameter("provoliIsAvailable").equals("0"),
				request.getParameter("FILM_NAME") , Integer.parseInt( request.getParameter("CONTENT_ADMIN_ID")));
				String assigned = Boolean.toString(contentAdminDao.AssignFilmToCinema(provoli));
				request.setAttribute("done", "assign");
				request.setAttribute("flag", assigned);
			}
			else 
			{
				request.setAttribute("done", "assign");
				request.setAttribute("flag", "false");
			}
			request.getRequestDispatcher("Content_Admins.jsp").forward(request, response);
		}
		
	  }else {
		  response.sendRedirect("log_in.html");
	  }

	}
}