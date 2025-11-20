package utilities;

import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class db_Utilities {

	private static Connection connection = null;
    private static DataSource datasource = null;

    public static Connection getConnection() {
        if (connection != null) { 
            return connection;}
        else {
            try {
            	InitialContext ctx = new InitialContext();
    			datasource = (DataSource)ctx.lookup("java:comp/env/jdbc/LiveDataSource");
    			connection = datasource.getConnection();
            }catch(Exception e) {            
                e.printStackTrace();
                System.out.println("We have problem");
            }
 
            return connection;
            
        }
        
}
}