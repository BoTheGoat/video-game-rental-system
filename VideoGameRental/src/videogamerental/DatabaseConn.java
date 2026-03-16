/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package videogamerental;

/**
 *
 * @author 1zomb
 */

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConn {
    
    
   
    

      public static Connection getConnection(){
          String url = "jdbc:mysql://localhost:3306/game_rentaldb";
          String user = "root";
          String password = "";
         
          
         
           try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database!");
            return connection;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConn.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }        }

      } 



