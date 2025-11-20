package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.io.*;

import Objects.*;

public class contentAdmin_dao {

	
	private Connection con;
	
	public contentAdmin_dao() {
		con=utilities.db_Utilities.getConnection();
	}
	
public boolean roleSearch(String username) {
		
		boolean answer=false;
		
		try {
			PreparedStatement preparedstatement = con.prepareStatement("SELECT user_username FROM coursegrades.content_admins where user_username=?");
			preparedstatement.setString(1, username);
			ResultSet rs = preparedstatement.executeQuery();
			
			if(rs.next()==true) {
				answer=true;
			}
			
		} catch(Exception e) {
			System.out.println("Database connection problem");
			answer=false;
		}
		return answer;
	}

public int role_ID(String username) {
	int x = 0;
	try {
		
		PreparedStatement preparedstatement = con.prepareStatement("SELECT ID FROM coursegrades.content_admins where user_username=?");
		preparedstatement.setString(1, username);
		ResultSet rs = preparedstatement.executeQuery();
		while(rs.next()) {
			x = rs.getInt("ID");
		}
	}catch(Exception e) {
		System.out.println("Database connection problem");
	}
	return x;
}
	
	
public boolean AssignFilmToCinema(Provoles provoli) {
	
	
	try {
		PreparedStatement preparedstatement = con.prepareStatement("INSERT INTO coursegrades.provoles (FILM_ID,FILM_NAME,CINEMA_ID,PROVOLI_ID,CONTENT_ADMIN_ID,provoliStartDate,provoliEndDate,provoliNumberOfReservations,provoliIsAvailable) VALUES (?,?,?,?,?,?,?,?,?)" );
		preparedstatement.setString(1, provoli.getProvoliFilm());
		preparedstatement.setString(2, provoli.getFILM_NAME());
		preparedstatement.setString(3, provoli.getProvoliCinema());
		preparedstatement.setString(4, provoli.getPRVOLI_ID());
		preparedstatement.setInt(5, provoli.getCONTENT_ADMIN_ID());
		preparedstatement.setString(6, provoli.getProvoliStartDate());
		preparedstatement.setString(7, provoli.getProvoliEndDate());
		preparedstatement.setInt(8, provoli.getProvoliNumberOfReservations());
		preparedstatement.setBoolean(9, provoli.getProvoliIsAvailable());
		preparedstatement.executeUpdate();
		return true;

		
	} catch(Exception e) {
		System.out.println("Database connection problem");
		return false;
	}
	
}


public boolean InsertFilm(Films film) {
	
	try {
		PreparedStatement preparedstatement = con.prepareStatement("INSERT INTO coursegrades.films (FILM_ID,FILM_TITLE,FILM_DESCREPTION,FILM_DURATION,FILM_CATEGORY,CONTENT_ADMIN_ID) VALUES (?,?,?,?,?,?)" );
		preparedstatement.setString(1, film.getFilmID());
		preparedstatement.setString(2,film.getFilmTitle());
		preparedstatement.setString(3, film.getFilmDescription());
		preparedstatement.setInt(4, film.getFilmDuration());
		preparedstatement.setString(5, film.getFilmCategory());
		preparedstatement.setInt(6, film.getContentAdminID());
		preparedstatement.executeUpdate();
		return true;
		
	} catch(Exception e) {
		System.out.println("Database connection problem");
		return false;
	}

	
}

public boolean DeleteFilm(String filmID, String filmName) {
	
	try {
		PreparedStatement preparedstatement = con.prepareStatement("DELETE FROM coursegrades.films WHERE FILM_ID=? AND FILM_TITLE=?" );
		preparedstatement.setString(1, filmID);
		preparedstatement.setString(2, filmName);
		int x = preparedstatement.executeUpdate();
		if (x == 0 ) return false;
		
	} catch(Exception e) {
		System.out.println("Database connection problem");
		return false;
	}
	return true;
}

public ArrayList<Provoles> AvailableFilms() {
	
	ArrayList provoles = new ArrayList<Provoles>();
	
	try {
		PreparedStatement preparedstatement = con.prepareStatement("SELECT * FROM coursegrades.provoles WHERE provoliIsAvailable=?");
		preparedstatement.setBoolean(1, true);
		ResultSet rs = preparedstatement.executeQuery();
		
		while(rs.next()) {
			
			String film_id = rs.getString("FILM_ID");
			String film_name = rs.getString("FILM_NAME");
			String cinema_id = rs.getString("CINEMA_ID");
			String provoli_id = rs.getString("PROVOLI_ID");
			int content_admin_id = rs.getInt("CONTENT_ADMIN_ID");
			String provoliStartDate = rs.getString("provoliStartDate");
			String provoliEndDate = rs.getString("provoliEndDate");
			int provoliNumberOfReservations = rs.getInt("provoliNumberOfReservations");
			boolean provoliIsAvailable = rs.getBoolean("provoliIsAvailable");
			
			Provoles provoli = new Provoles(provoli_id,film_id,cinema_id,provoliStartDate,provoliEndDate,provoliNumberOfReservations,provoliIsAvailable,film_name,content_admin_id);
			
			provoles.add(provoli);
		}
		
	} catch(Exception e) {
		System.out.println("Database connection problem");
		
	}
	
	return provoles;
}

public ArrayList<Films> ShowFilms() {
	
	ArrayList films = new ArrayList<Films>();
	
	try {
		PreparedStatement preparedstatement = con.prepareStatement("SELECT * FROM coursegrades.films");
		ResultSet rs = preparedstatement.executeQuery();
		
		while(rs.next()) {
			
			String film_id = rs.getString("FILM_ID");
			String film_title = rs.getString("FILM_TITLE");
			String film_descreption = rs.getString("FILM_DESCREPTION");
			int film_duration = rs.getInt("FILM_DURATION");
			String film_category = rs.getString("FILM_CATEGORY");
			int content_admin = rs.getInt("CONTENT_ADMIN_ID");
			
			Films film = new Films(film_id,film_title,film_category,film_descreption,film_duration,content_admin);
			
			films.add(film);
		}
		
	} catch(Exception e) {
		System.out.println("Database connection problem");
		
	}
	
	return films;
}


public boolean checkFilm(String filmTitle,String filmID) {
	
	boolean answer=false;
	
	try {
		PreparedStatement preparedstatement = con.prepareStatement("SELECT * FROM coursegrades.films where (FILM_TITLE=? AND FILM_ID=?)");
		preparedstatement.setString(1, filmTitle);
		preparedstatement.setString(2, filmID);
		ResultSet rs = preparedstatement.executeQuery();
		
		
		if(rs.next()==true) {
			
			answer=true;
		}
		
	} catch(Exception e) {
		System.out.println("Database connection problem");
	}
	return answer;
}

public boolean checkCinema(String cinemaID) {
	
	boolean answer=false;
	
	try {
		PreparedStatement preparedstatement = con.prepareStatement("SELECT ID FROM coursegrades.cinemas where ID=?");
		preparedstatement.setString(1, cinemaID);
		ResultSet rs = preparedstatement.executeQuery();
		
		if(rs.next()==true) {
			
			answer=true;
			
		}
		
	} catch(Exception e) {
		System.out.println("Database connection problem");
	}
	return answer;
}

public int Seats(String cinema_id) {
	
	int seats = 0;
	try {
		
		PreparedStatement preparedstatement = con.prepareStatement("SELECT SEATS FROM coursegrades.CINEMAS where ID=?");
		preparedstatement.setString(1, cinema_id);
		ResultSet rs = preparedstatement.executeQuery();
		while(rs.next()) {
			seats = rs.getInt("SEATS");
			
		}
	}catch(Exception e) {
		System.out.println("Database connection problem");
	}
	return seats;
	
}


public int Reservations(String film_id,String film_name,String cinema_id) {
	
	int reservations = 0;
	try {
		
		PreparedStatement preparedstatement = con.prepareStatement("SELECT provoliNumberOfReservations FROM coursegrades.PROVOLES WHERE FILM_ID=? AND FILM_NAME=? AND CINEMA_ID=?");
		preparedstatement.setString(1, film_id);
		preparedstatement.setString(2, film_name);
		preparedstatement.setString(3, cinema_id);
		ResultSet rs = preparedstatement.executeQuery();
		while(rs.next()) {
			reservations = rs.getInt("provoliNumberOfReservations");
			
		}
	}catch(Exception e) {
		System.out.println("Database connection problem");
	}
	return reservations;
	
}

public boolean checkSeats(int seats, int reservations) {
	
	if(seats<=reservations) {
		return false;
	}

	return true;
	
	
}

public boolean ProvolesAvailability(String film_id,String film_name,String cinema_id,boolean answer) {
	
	try {
		PreparedStatement preparedstatement = con.prepareStatement("UPDATE coursegrades.PROVOLES SET provoliIsAvailable=? WHERE FILM_ID=? AND FILM_NAME=? AND CINEMA_ID=?");
		preparedstatement.setBoolean(1, answer);
		preparedstatement.setString(2, film_id);
		preparedstatement.setString(3, film_name);
		preparedstatement.setString(4, cinema_id);

		int x = preparedstatement.executeUpdate();
		if (x == 0 ) return false;
		return true;
		
	} catch(Exception e) {
		System.out.println("Database connection problem");
		
	}
	
	return false;
}





}