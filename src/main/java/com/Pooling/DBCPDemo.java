package com.Pooling;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Hello world!
 *
 */
public class DBCPDemo {
  
  //creating instance of basic data source
  // two types of datasource are present in apache dbcp
  // 1- basic data source 2. poolingdatasource
  
  public static BasicDataSource dataSource = null;
  static {
    dataSource = new BasicDataSource();
    dataSource.setUrl("jdbc:mysql://localhost:3306/mydb?useSSL=false");    
    dataSource.setUsername("root");
    dataSource.setPassword("Nasim646016");
    
    dataSource.setMinIdle(5);
    dataSource.setMaxIdle(10);
    dataSource.setMaxTotal(10);
  }
  
    public static void main( String[] args ) throws SQLException
    {
      
      Connection connection = null;
      Statement statement = null;
      ResultSet resultSet = null;
      try {
        connection = dataSource.getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery("select * from tblStudent");
        while(resultSet.next()) {
          System.out.println("studentID = " + resultSet.getInt("studentID"));
          System.out.println("studentName = "+ resultSet.getString("studentName"));
          System.out.println("dob = "+ resultSet.getDate("dob"));
          System.out.println("address = "+ resultSet.getString("address"));
        }
        
      }catch(Exception ex) {
        System.out.println(ex.getMessage());
      }
      finally {
        resultSet.close();
        statement.close();
        connection.close();
      }
      
        
    }
}
