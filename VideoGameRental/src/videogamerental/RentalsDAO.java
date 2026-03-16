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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.sql.Date;

public class RentalsDAO {
     String url = "jdbc:mysql://localhost:3306/game_rentaldb";
          String user = "root";
          String password = "";
   
    private Connection connection;

    public RentalsDAO() throws SQLException {
        this.connection = DriverManager.getConnection(url, user, password);
      
    }
    
    public void updateGameQuantity(int gameID)throws SQLException{
        String sql = "UPDATE games SET quantity_available = quantity_available - 1 WHERE game_id = ?";
        PreparedStatement state = connection.prepareStatement(sql);
        
        state.setInt(1, gameID);
        
        state.executeUpdate();
        state.close();
        
        
    }
    
    public void addRental(int userID, int gameID) throws SQLException{
        
        LocalDate rentalDate = LocalDate.now();
        LocalDate dueDate = rentalDate.plusDays(7);
        Date sqlRentalDate = Date.valueOf(rentalDate);
        Date sqlDueDate = Date.valueOf(dueDate);
        Date sqlReturnDate = null;
        
        int quantity = 0;
        
        String sql = "SELECT quantity_available FROM games WHERE gameID =?";
        PreparedStatement state = connection.prepareStatement(sql);
        state.setInt(1,gameID);
        ResultSet results = state.executeQuery();
        if(results.next()){
          quantity = results.getInt("quantity_available");
        }
        else{
             System.out.println("Game not found");
             return;
    }
      state.close();
      results.close();
        
       GamesDAO games = new GamesDAO();
        
      

        
       if(quantity <= 0){
           System.out.println("Game out of stock");
           return;
           
       } 
       
           String sql2 = "INSERT into rentals (user_id, game_id, rental_date, due_date, return_date) VALUES (?,?,?,?,?)";
           state = connection.prepareStatement(sql2);
           state.setInt(1, userID);
           state.setInt(2, gameID);
           state.setDate(3, sqlRentalDate);
           state.setDate(4, sqlDueDate);
           state.setDate(5, null);
           
           state.executeUpdate();
           
           games.updateGameQuantity(gameID);
           
           state.close();

       
       
        
    }
    
    public void returnRental(int rentalID) throws SQLException{
         LocalDate returnDate = LocalDate.now();
         Date sqlReturnDate = Date.valueOf(returnDate);
         int gameID = 0;
         
         GamesDAO games = new GamesDAO();
        
         String sql = "SELECT game_id FROM rentals WHERE rental_id = ?";

         PreparedStatement state = connection.prepareStatement(sql);
         
         state.setInt(1, rentalID);
         ResultSet results = state.executeQuery();
         
         if(results.next()){
           gameID = results.getInt("game_id");
         }
         else{
             System.out.println("Rental not found");
             return;
         }
         results.close();
         state.close();
         
         
         
         String sql2 = "UPDATE rentals SET return_date = ? WHERE rental_id = ?";
         
         state = connection.prepareStatement(sql2);
         state.setDate(1, sqlReturnDate);
         state.setInt(2, rentalID);
         
         state.executeUpdate();
         
         games.increaseGameQuantity(gameID);
         
         state.close();
       
    }
    
    public List<Rentals> getAllRentals() throws SQLException{
       
        String sql = "SELECT * FROM rentals";
        PreparedStatement state = connection.prepareStatement(sql);
        
        List<Rentals> rentalGames = new ArrayList<>();
        
        ResultSet results = state.executeQuery();
        
        while(results.next()){
            int rental_id = results.getInt("rental_id");
            int use_id = results.getInt("user_id");
            int game_id = results.getInt("game_id");
            Date rentalDate = results.getDate("rental_date");
            Date dueDate = results.getDate("due_date");
            Date returnDate = results.getDate("return_date");
            
            User users = new User(use_id);
            Games rentedGames = new Games(game_id);
            
          //  Rentals gameRentals = new Rentals(rental_id,users,rentedGames,rentalDate,dueDate,returnDate);
          
          Rentals rental = new Rentals(
        results.getInt("rental_id"),
        users,
        rentedGames,
        results.getDate("rental_date").toLocalDate(),
        results.getDate("due_date").toLocalDate(),
        results.getDate("return_date") != null ? results.getDate("return_date").toLocalDate() : null);
            
          rentalGames.add(rental);
            
            
        }
        
        return rentalGames;
    }
    
    public Rentals getRentalByID(int rentalID) throws SQLException{
        String sql = "SELECT * FROM rentals where rental_id = ?";
        PreparedStatement state = connection.prepareStatement(sql);
        
        state.setInt(1, rentalID);
        
        ResultSet results = state.executeQuery();
        
        if(results.next()){
            int use_id = results.getInt("user_id");
            int game_id = results.getInt("game_id");
            Date rentalDate = results.getDate("rental_date");
            Date dueDate = results.getDate("due_date");
            Date returnDate = results.getDate("return_date");
            
            User users = new User(use_id);
            Games rentedGames = new Games(game_id);
            
            Rentals rental = new Rentals(
        results.getInt("rental_id"),
        users,
        rentedGames,
        results.getDate("rental_date").toLocalDate(),
        results.getDate("due_date").toLocalDate(),
        results.getDate("return_date") != null ? results.getDate("return_date").toLocalDate() : null);
            state.close();
            return rental;
            
        }
        else{
            return null;
        }
    }
    
    
    
    
    
    
}
