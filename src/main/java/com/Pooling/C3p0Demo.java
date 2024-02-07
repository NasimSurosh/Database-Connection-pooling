package com.Pooling;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0Demo {
  public static ComboPooledDataSource cPDS = null;
  
  static {
    cPDS = new ComboPooledDataSource();
    cPDS.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
    cPDS.setUser("root");
    cPDS.setPassword("Nasim646016");
    
    cPDS.setMinPoolSize(3);
    cPDS.setMaxPoolSize(6);
  }

  public static void main(String[] args) throws SQLException {
    
    Connection connection = null;
    Statement statement = null;
    ResultSet rS = null;
    
    try {
      connection = cPDS.getConnection();
      statement = connection.createStatement();
      rS = statement.executeQuery("select * from tblStudent");
      
      while(rS.next()) {
        System.out.println("StudentID = "+ rS.getInt("studentID"));
        System.out.println("StudentName = " + rS.getString("studentName"));
        System.out.println("dob = " + rS.getDate("dob"));
        System.out.println("Address = " +rS.getString("address"));
      }
    }catch(Exception e) {
      System.out.println(e.getMessage());
    }
    finally {
      rS.close();
      statement.close();
      connection.close();
    }
    

  }

}
