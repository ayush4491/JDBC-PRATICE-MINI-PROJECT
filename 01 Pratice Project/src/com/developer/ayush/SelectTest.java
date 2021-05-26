/**
 *  Java App to get Employee details based on given intial characters   Employee name 
 */
package com.developer.ayush;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest {

	public static void main(String[] args) {
		
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			// read inputs
			sc=new Scanner(System.in);
			String initChars=null;
			if(sc!=null) {
			  System.out.println("Enter intial characters of employee name ::");
			  initChars=sc.next().toUpperCase(); //gives   s
		}
			// covert initial value as required for SQL Query
			initChars ="'"+initChars+"%'";  //gives 's%'
			
			//register  JDBC driver by loading  JDBC driver class
	        //  Class.forName("oracle.jdbc.drvier.OracleDriver");
	
	//establish the connection
	  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "ayush");
	  //create Statement object
	  if(con!=null)
		  st=con.createStatement();
	  //prepare  SQL query
	  
	    // select empno,ename,job,sal  from emp where  ename like 'S%';
	    String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE "+initChars;
	    System.out.println(query);
	    
	    //send and execute SQL query in DB s/w
	    if(st!=null) 
	    	  rs=st.executeQuery(query);
	    
	    //process the ResultSet object
	    
	     if(rs!=null) {
	    	 boolean flag=false;
	    	 while(rs.next()) {
	    		flag=true;
	    		 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"   "+rs.getString(3)+"  "+rs.getFloat(4));
	    	 }//while
	    	 
	    	 if(flag==false)
	    		 System.out.println("No Records  found");
	     }//if

	} // try
		
		catch (SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("Invalid col names or table names or SQL keywords");
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}//finally


  }// main() 

} // class  
