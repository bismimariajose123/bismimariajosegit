package com.churchmanagementsystem.ConnectionString;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionString {

	private static Connection con;
	
	
	 public static Connection getConnection() {
	        try {
	          
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	           
	            try {
	            	 con = DriverManager.getConnection("jdbc:oracle:thin:@sla18326:1521:ukahp1d","AZT_TRN","Azt_trn1#");
	            	// System.out.println("driver manager."); 
	            }
	            catch (SQLException ex) {
	                // log an exception. fro example:
	                System.out.println("Failed to create the database connection."); 
	            }
	        } 
	        catch (ClassNotFoundException ex) {
	            // log an exception. for example:
	            System.out.println("Driver not found."); 
	        }
	        return con;
	    }
}
