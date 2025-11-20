package controllers;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.SQLException;

import dao.*;

import Objects.*;

@WebServlet("/logout")
public class UnRegister extends HttpServlet{
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.setHeader("Cache-Control"," no-cache, no-store, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma"," no-cache");
        response.setDateHeader("Expires",0);
        response.sendRedirect("log_in.html");
    }

}
