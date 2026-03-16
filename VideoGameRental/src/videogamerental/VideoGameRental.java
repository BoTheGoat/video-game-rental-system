/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package videogamerental;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author 1zomb
 */
public class VideoGameRental {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        UserDAO userDAO = new UserDAO();
        GamesDAO gamesDAO = new GamesDAO();
        RentalsDAO rentalsDAO = new RentalsDAO();
        
        
       ConsoleUI ui = new ConsoleUI(userDAO, gamesDAO, rentalsDAO);
       
       ui.start();
       
          
       
          
   }
   
  
   
   
 
       
   
  }
   
   
  

        
    
    
