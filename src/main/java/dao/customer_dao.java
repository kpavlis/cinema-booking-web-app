package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.User;

import java.sql.Statement;
import java.io.*;

import Objects.*;

public class customer_dao {

	private Connection con;
	
	public customer_dao() {
		con=utilities.db_Utilities.getConnection();
	}
	
public boolean roleSearch(String username) {
		
		boolean answer=false;
		
		try {
			PreparedStatement preparedstatement = con.prepareStatement("SELECT user_username FROM coursegrades.customers where user_username=?");
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


	public ArrayList<Provoles> showAvailableFilms(String sdate,String edate) {
		ArrayList provoles = new ArrayList<Provoles>();
		
		try {
			PreparedStatement preparedstatement = con.prepareStatement("SELECT * FROM coursegrades.provoles WHERE provoliIsAvailable=? AND provoliStartDate>=? AND provoliEndDate<=?");
			preparedstatement.setBoolean(1, true);
			preparedstatement.setString(2, sdate);
			preparedstatement.setString(3, edate);
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
	
	public boolean makeReservation(String film_id,String film_name,String cinema_id, int customer_id) {
		try {
			PreparedStatement preparedstatement = con.prepareStatement("INSERT INTO coursegrades.RESERVATIONS VALUES (?,?,?,?)" );
			preparedstatement.setString(1, film_id);
			preparedstatement.setString(2, film_name);
			preparedstatement.setString(3, cinema_id);
			preparedstatement.setInt(4, customer_id);
			preparedstatement.executeUpdate();
			return true;
			
			
		} catch(Exception e) {
			System.out.println("Database connection problem");
			return false;
		}

	}
	
	public ArrayList<String> viewReservation(int customer_id) {
		
		ArrayList Reservations = new ArrayList<String>();
	
		try {
			PreparedStatement preparedstatement = con.prepareStatement("SELECT * FROM coursegrades.RESERVATIONS WHERE CUSTOMERS_ID=?");
			preparedstatement.setInt(1, customer_id);
			ResultSet rs = preparedstatement.executeQuery();
			
			while(rs.next()) {
				
				String film_id = rs.getString("PROVOLES_FILMS_ID");
				String film_name = rs.getString("PROVOLES_FILMS_NAME");
				String cinema_id = rs.getString("PROVOLES_CINEMAS_ID");
				
				Reservations.add(film_id);
				Reservations.add(film_name);
				Reservations.add(cinema_id);
			}
			
		} catch(Exception e) {
			System.out.println("Database connection problem");
			
		}
		
		return Reservations;
	}

	public boolean cancelReservation(String film_id,String film_name,String cinema_id,int customer_id) {
		try {
			PreparedStatement preparedstatement = con.prepareStatement("DELETE FROM coursegrades.RESERVATIONS WHERE PROVOLES_FILMS_ID=? AND PROVOLES_FILMS_NAME=? AND PROVOLES_CINEMAS_ID=? AND CUSTOMERS_ID=?" );
			preparedstatement.setString(1, film_id);
			preparedstatement.setString(2, film_name);
			preparedstatement.setString(3, cinema_id);
			preparedstatement.setInt(4, customer_id);
			int x = preparedstatement.executeUpdate();
			if (x == 0 ) return false;
			
		} catch(Exception e) {
			System.out.println("Database connection problem");
			return false;
	
	}
	
		return true;
	}
	
	public boolean checkReservation(String film_id,String film_name,String cinema_id,int customer_id){
		boolean answer = false;
		try {
			PreparedStatement preparedstatement = con.prepareStatement("SELECT  * FROM coursegrades.RESERVATIONS WHERE PROVOLES_FILMS_ID=? AND PROVOLES_FILMS_NAME=? AND PROVOLES_CINEMAS_ID=? AND CUSTOMERS_ID=?");
			preparedstatement.setString(1, film_id);
			preparedstatement.setString(2, film_name);
			preparedstatement.setString(3, cinema_id);
			preparedstatement.setInt(4, customer_id);
			ResultSet rs = preparedstatement.executeQuery();
			
			if(rs.next()== true) {
					answer=true;
			}
				
				
				
			
			
		} catch(Exception e) {
			System.out.println("Database connection problem");
		}
		return answer;
	}
	
	public boolean checkProvoli(String film_id, String film_name, String cinema_id) {
		
		boolean answer=false;
		
		try {
			PreparedStatement preparedstatement = con.prepareStatement("SELECT * FROM coursegrades.provoles WHERE FILM_ID=? AND FILM_NAME=? AND CINEMA_ID=?");
			preparedstatement.setString(1, film_id);
			preparedstatement.setString(2, film_name);
			preparedstatement.setString(3, cinema_id);
			ResultSet rs = preparedstatement.executeQuery();
			
			if(rs.next()==true) {
				
				
					answer=true;
				
			}
			
		} catch(Exception e) {
			System.out.println("Database connection problem");
		}
		return answer;
	}
	
	
	
	public boolean AddProvoles(String film_id,String film_name, String cinema_id) {
		
		boolean flag=false;
		int number=0;
		try {
			PreparedStatement preparedstatement = con.prepareStatement("SELECT provoliNumberOfReservations FROM coursegrades.PROVOLES WHERE FILM_ID=? AND FILM_NAME=? AND CINEMA_ID=?");
			preparedstatement.setString(1, film_id);
			preparedstatement.setString(2, film_name);
			preparedstatement.setString(3, cinema_id);
			ResultSet rs = preparedstatement.executeQuery();
			
			while(rs.next()) {
				
				number = rs.getInt("provoliNumberOfReservations");
				flag=true;
				
			}
			
		} catch(Exception e) {
			System.out.println("Database connection problem");
			
		}
		
		
		flag=AddProvoles2(film_id, film_name, cinema_id, number);
		
		return flag;
		
	}
	
	
	public boolean AddProvoles2(String film_id,String film_name, String cinema_id, int number) {
		
		number++;
		try {
			PreparedStatement preparedstatement = con.prepareStatement("UPDATE coursegrades.PROVOLES SET provoliNumberOfReservations=? WHERE FILM_ID=? AND FILM_NAME=? AND CINEMA_ID=?");
			preparedstatement.setInt(1, number);
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
	
	
	public int role_ID(String username) {
		int x = 0;
		try {
		
			PreparedStatement preparedstatement = con.prepareStatement("SELECT ID FROM coursegrades.customers where user_username=?");
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
	
	
	public boolean checkProvoliIsAvailable(String film_id, String film_name, String cinema_id) {
		
		boolean answer=false;
		
		try {
			PreparedStatement preparedstatement = con.prepareStatement("SELECT * FROM coursegrades.provoles WHERE FILM_ID=? AND FILM_NAME=? AND CINEMA_ID=? AND provoliIsAvailable=?");
			preparedstatement.setString(1, film_id);
			preparedstatement.setString(2, film_name);
			preparedstatement.setString(3, cinema_id);
			preparedstatement.setBoolean(4, true);
			ResultSet rs = preparedstatement.executeQuery();
			
			if(rs.next()==true) {
				
				answer=true;
				
			}
			
		} catch(Exception e) {
			System.out.println("Database connection problem");
		}
		return answer;
	}
	
	
	
	public boolean ReduceProvoles(String film_id,String film_name, String cinema_id) {
		
		boolean flag=false;
		int number=0;
		try {
			PreparedStatement preparedstatement = con.prepareStatement("SELECT provoliNumberOfReservations FROM coursegrades.PROVOLES WHERE FILM_ID=? AND FILM_NAME=? AND CINEMA_ID=?");
			preparedstatement.setString(1, film_id);
			preparedstatement.setString(2, film_name);
			preparedstatement.setString(3, cinema_id);
			ResultSet rs = preparedstatement.executeQuery();
			
			while(rs.next()) {
				
				number = rs.getInt("provoliNumberOfReservations");
			}
			
		} catch(Exception e) {
			System.out.println("Database connection problem");
			
		}
		
		
		flag=ReduceProvoles2(film_id, film_name, cinema_id, number);
		
		return flag;
		
	}
	
	
	public boolean ReduceProvoles2(String film_id,String film_name, String cinema_id, int number) {
		
		number--;
		try {
			PreparedStatement preparedstatement = con.prepareStatement("UPDATE coursegrades.PROVOLES SET provoliNumberOfReservations=? WHERE FILM_ID=? AND FILM_NAME=? AND CINEMA_ID=?");
			preparedstatement.setInt(1, number);
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
	
	public boolean ProvolesIsAvailable(String film_id,String film_name,String cinema_id) {
		
		try {
			PreparedStatement preparedstatement = con.prepareStatement("SELECT * FROM coursegrades.PROVOLES WHERE FILM_ID=? AND FILM_NAME=? AND CINEMA_ID=?");
			preparedstatement.setString(1, film_id);
			preparedstatement.setString(2, film_name);
			preparedstatement.setString(3, cinema_id);

			ResultSet rs = preparedstatement.executeQuery();
			
			while(rs.next()) {
				Boolean check = rs.getBoolean("provoliIsAvailable");
				if(check == true) {
					return true;
				}
				
			}
			
			
		} catch(Exception e) {
			System.out.println("Database connection problem");
			
		}
		
		return false;
	}

	
}