package com.Pooling;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;


import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.commons.dbcp2.ConnectionFactory;

public class DBCPDemo2 {

  public static DataSource dataSource = null;
  static {
    Properties properties = new Properties();
    properties.setProperty("user", "root");
    properties.setProperty("password", "Nasim646016");
    
    ConnectionFactory cF = new DriverManagerConnectionFactory(
        "jdbc:mysql://localhost:3306/mydb",properties);
    
    PoolableConnectionFactory pCF = new PoolableConnectionFactory(cF,null);
    GenericObjectPoolConfig<PoolableConnection> config = new GenericObjectPoolConfig<>();
    
    config.setMinIdle(5);
    config.setMaxIdle(25);
    config.setMaxTotal(25);
    
    ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(pCF, config);
    pCF.setPool(connectionPool);
   
   dataSource = new PoolingDataSource<>(connectionPool);
    
   
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
          System.out.println("studentID" + resultSet.getInt("studentID"));
          System.out.println("studentName"+ resultSet.getString("studentName"));
          System.out.println("dob"+ resultSet.getDate("dob"));
          System.out.println("address"+ resultSet.getString("address"));
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

