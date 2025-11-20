package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;


public class login_dao {

	private Connection con;
	
	public login_dao(){
		con=utilities.db_Utilities.getConnection();
	}
	
	
	public boolean login(String username,String pword) {
		
		boolean answer=false;
		try {
			PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM coursegrades.user where (username=? and password=?)");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, pword);
			
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()==true) {
				
				answer=true;
				String password= rs.getString("password");
				
			}
			
		} catch(Exception e) {
			System.out.println("Database connection problem");
			answer=false;
		}
		
		return answer;
	}
	
	
	public String getSalt(String username) {
		String salt=null;
        try {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT salt from coursegrades.user where username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) 
            { 
            	salt=rs.getString("salt");
        	} 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salt;
	}
	
	public String getName(String username) {
		String name=null;
        try {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT NAME from coursegrades.user where username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) 
            { 
            	name=rs.getString("NAME");
        	} 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
	}
	
	// from 09_mvc-example
	public String getAlphaNumericString(int n) 
    { 
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
        
        return sb.toString(); 
    }
	
	public String bytesToHex(byte[] hash) {
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	    String hex = Integer.toHexString(0xff & hash[i]);
	    if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
}