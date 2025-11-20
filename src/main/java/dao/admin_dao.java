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
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Objects.*;
import controllers.*;


public class admin_dao {

	private Connection con;
	
	public admin_dao() {
		con=utilities.db_Utilities.getConnection();
	}
	
   public boolean roleSearch(String username) {
		
		boolean answer=false;
		
		try {
			PreparedStatement preparedstatement = con.prepareStatement("SELECT user_username FROM coursegrades.admins where user_username=?");
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
		
			PreparedStatement preparedstatement = con.prepareStatement("SELECT ID FROM coursegrades.admins where user_username=?");
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

	
	public boolean CreateUser(Users user) {
		
		try {
			PreparedStatement preparedstatement = con.prepareStatement("INSERT INTO coursegrades.user (username,password,salt,NAME) VALUES (?,?,?,?)" );
			preparedstatement.setString(1, user.getUsername());
			preparedstatement.setString(2,user.getPassword());
			
			preparedstatement.setString(3, user.getSalt());
			
			preparedstatement.setString(4, user.getName());
			preparedstatement.executeUpdate();
			return true;
			
		} catch(Exception e) {
			System.out.println("Database connection problem");
			
		}
		return false;
	}
	
	public boolean DeleteUser(String username) {
		try {
			PreparedStatement preparedstatement = con.prepareStatement("DELETE FROM coursegrades.user WHERE username=?" );
			preparedstatement.setString(1, username);
			int x = preparedstatement.executeUpdate();
			if (x == 0 ) return false;
			
		} catch(Exception e) {
			System.out.println("Database connection problem");
			return false;
		}
		
		return true;
	}
	
	public boolean UpdateUser(String usernameNow,String username,String salt, String name, String password) {
		
		try {
			PreparedStatement preparedstatement = con.prepareStatement("UPDATE coursegrades.user SET username=?,password=?,salt=?,name=? WHERE username=?" );
			preparedstatement.setString(1, username);
			preparedstatement.setString(2, password);
			preparedstatement.setString(3, salt);
			preparedstatement.setString(4, name);
			preparedstatement.setString(5, usernameNow);
			int x = preparedstatement.executeUpdate();
			if (x == 0 ) return false;
			
		} catch(Exception e) {
			System.out.println("Database connection problem");
			return false;
		}
		
		return true;
	}
	
	public Users searchUser(String user_username) {
		
		try {
			PreparedStatement preparedstatement = con.prepareStatement("SELECT * FROM coursegrades.user WHERE username=?");
			preparedstatement.setString(1, user_username);
			ResultSet rs = preparedstatement.executeQuery();
			
			while(rs.next()) {
				
				String username = rs.getString("username");
				String name = rs.getString("NAME");
				String password = rs.getString("password");
				String salt = rs.getString("salt");
				
				
				Users user = new Users(name,username,password,salt);
				return user;
			}
			
		} catch(Exception e) {
			System.out.println("Database connection problem");
			
		}
		
		return null;
	}
	
	public ArrayList<Users> viewAllUsers() {
		
		ArrayList users = new ArrayList<Users>();
		
		try {
			PreparedStatement preparedstatement = con.prepareStatement("SELECT * FROM coursegrades.user ORDER BY username");
			ResultSet rs = preparedstatement.executeQuery();
			
			while(rs.next()) {
				
				String username = rs.getString("username");
				String name = rs.getString("NAME");
				String password = rs.getString("password");
				String salt = rs.getString("salt");
				
				
				Users user = new Users(name,username,password,salt);
				users.add(user);
			}
			
		} catch(Exception e) {
			System.out.println("Database connection problem");
			
		}
		
		return users;
	}


	public boolean CreateRole(int role,String username) {
		
		if(role==1) {
			try {
				PreparedStatement preparedstatement = con.prepareStatement("INSERT INTO coursegrades.customers (ID,user_username) VALUES (?,?)" );
				preparedstatement.setInt(1, find_id_customer());
				preparedstatement.setString(2,username);
				preparedstatement.executeUpdate();
				return true;
				
			} catch(Exception e) {
				System.out.println("Database connection problem");
				return false;
			}
		}
		else if(role==2) {
			try {
				PreparedStatement preparedstatement = con.prepareStatement("INSERT INTO coursegrades.admins (ID,user_username) VALUES (?,?)" );
				preparedstatement.setInt(1, find_id_admin());
				preparedstatement.setString(2,username);
				preparedstatement.executeUpdate();
				return true;
				
			} catch(Exception e) {
				System.out.println("Database connection problem");
				return false;
			}
		}
		else if(role==3) {
			try {
				PreparedStatement preparedstatement = con.prepareStatement("INSERT INTO coursegrades.content_admins (ID,user_username) VALUES (?,?)" );
				preparedstatement.setInt(1, find_id_content_admin());
				preparedstatement.setString(2,username);
				preparedstatement.executeUpdate();
				return true;
				
			} catch(Exception e) {
				System.out.println("Database connection problem");
				return false;
			}
		}
		else {
			return false;
		}
		
	}
	
	
	public boolean DeleteRole(String username) {
		
		boolean check_admin = roleSearch(username);
		
		contentAdmin_dao contentAdmin_Dao;
		contentAdmin_Dao = new contentAdmin_dao();
		
		boolean check_content_admin = contentAdmin_Dao.roleSearch(username);
		
		customer_dao customer_Dao;
		customer_Dao = new customer_dao();
		
		boolean check_customer = customer_Dao.roleSearch(username);
		
		if(check_customer) {
			try {
				PreparedStatement preparedstatement = con.prepareStatement("DELETE FROM coursegrades.customers WHERE user_username=?" );
				
				preparedstatement.setString(1,username);
				int x = preparedstatement.executeUpdate();
				if (x == 0 ) return false;
				return true;
				
			} catch(Exception e) {
				System.out.println("Database connection problem");
				return false;
			}
		}
		else if(check_admin) {
			try {
				PreparedStatement preparedstatement = con.prepareStatement("DELETE FROM coursegrades.admins WHERE user_username=?" );
				
				preparedstatement.setString(1,username);
				int x = preparedstatement.executeUpdate();
				if (x == 0 ) return false;
				return true;
				
			} catch(Exception e) {
				System.out.println("Database connection problem");
				return false;
			}
		}
		else if(check_content_admin) {
			try {
				PreparedStatement preparedstatement = con.prepareStatement("DELETE FROM coursegrades.content_admins WHERE user_username=?" );
				
				preparedstatement.setString(1,username);
				int x = preparedstatement.executeUpdate();
				if (x == 0 ) return false;
				return true;
				
			} catch(Exception e) {
				System.out.println("Database connection problem");
				return false;
			}
		}
		else {
			return false;
		}
		
	}
	
	public boolean UpdateRole(String usernameNow,String username) {
		
		boolean check_admin = roleSearch(usernameNow);
		
		contentAdmin_dao contentAdmin_Dao;
		contentAdmin_Dao = new contentAdmin_dao();
		
		boolean check_content_admin = contentAdmin_Dao.roleSearch(usernameNow);
		
		customer_dao customer_Dao;
		customer_Dao = new customer_dao();
		
		boolean check_customer = customer_Dao.roleSearch(usernameNow);
		
		if(check_customer) {
			
			
			
			try {
				PreparedStatement preparedstatement = con.prepareStatement("UPDATE coursegrades.customers SET user_username=? WHERE user_username=?" );
			
				preparedstatement.setString(1,username);
				preparedstatement.setString(2,usernameNow);
				int x = preparedstatement.executeUpdate();
				if (x == 0 ) return false;
				return true;
				
			} catch(Exception e) {
				System.out.println("Database connection problem");
				return false;
			}
		}
		else if(check_admin) {
			
			try {
				PreparedStatement preparedstatement = con.prepareStatement("UPDATE coursegrades.admins SET user_username=? WHERE user_username=?" );
				
				preparedstatement.setString(1,username);
				preparedstatement.setString(2,usernameNow);
				int x = preparedstatement.executeUpdate();
				if (x == 0 ) return false;
				return true;
				
			} catch(Exception e) {
				System.out.println("Database connection problem");
				return false;
			}
		}
		else if(check_content_admin) {
			
			
			try {
				PreparedStatement preparedstatement = con.prepareStatement("UPDATE coursegrades.content_admins SET user_username=? WHERE user_username=?" );
				
				preparedstatement.setString(1,username);
				preparedstatement.setString(2,usernameNow);
				int x = preparedstatement.executeUpdate();
				if (x == 0 ) return false;
				return true;
				
			} catch(Exception e) {
				System.out.println("Database connection problem");
				return false;
			}
		}
		else {
			return false;
		}
		
	}
	
	
	
	public int find_id_admin() {
		
		int counter = 1;
		try {
		
			PreparedStatement preparedstatement = con.prepareStatement("SELECT MAX(ID) AS MAXID FROM coursegrades.admins");
			ResultSet rs = preparedstatement.executeQuery();
			while(rs.next()) {
				counter=rs.getInt("MAXID");
				counter++;
			}
		}catch(Exception e) {
			System.out.println("Database connection problem");
		}
		return counter;
		
	}
	
	public int find_id_content_admin() {
		
		int counter = 1;
		try {
		
			PreparedStatement preparedstatement = con.prepareStatement("SELECT MAX(ID) AS MAXID FROM coursegrades.content_admins");
			ResultSet rs = preparedstatement.executeQuery();
			while(rs.next()) {
				counter=rs.getInt("MAXID");
				counter++;
			}
		}catch(Exception e) {
			System.out.println("Database connection problem");
		}
		return counter;
		
	}
	
	
	public int find_id_customer() {
		
		int counter = 1;
		try {
		
			PreparedStatement preparedstatement = con.prepareStatement("SELECT MAX(ID) AS MAXID FROM coursegrades.customers");
			ResultSet rs = preparedstatement.executeQuery();
			while(rs.next()) {
				counter=rs.getInt("MAXID");
				counter++;
			}
		}catch(Exception e) {
			System.out.println("Database connection problem");
		}
		return counter;
		
	}
	
	
	 public static String getHashMD5(String unhashed) {
	        return getHashMD5(unhashed, "");
	    }
	
	public static String getHashMD5(String unhashed, String salt) {
        // Hash the password.
        final String toHash = salt + unhashed + salt;
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            return "00000000000000000000000000000000";
        }
        messageDigest.update(toHash.getBytes(), 0, toHash.length());
        String hashed = new BigInteger(1, messageDigest.digest()).toString(16);
        if (hashed.length() < 32) {
            hashed = "0" + hashed;
        }
        return hashed.toUpperCase();
    }
}