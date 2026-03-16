/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package videogamerental;

/**
 *
 * @author 1zomb
 */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;


public class ConsoleUI {
    Scanner userInput = new Scanner(System.in);
    int userChoice;
    String input;
    private UserDAO userDAO;
    private GamesDAO gamesDAO;
    private RentalsDAO rentalsDAO;
    private Connection connection;
   
    

    public ConsoleUI(UserDAO userDAO, GamesDAO gamesDAO, RentalsDAO rentalsDAO) {
        this.userDAO = userDAO;
        this.gamesDAO = gamesDAO;
        this.rentalsDAO = rentalsDAO;
    }
    
    private int displayMenu(){
         while(true){
        System.out.println("Welcome to Blessing's Game Rental!");
        System.out.println("Please make one of the following choices:");
        System.out.println("---------------------------");
        System.out.println("1. Add User");
        System.out.println("2. Add Game");
        System.out.println("3. View All Games");
        System.out.println("4. Rent a Game");
        System.out.println("5. Return a game");
        System.out.println("6. View all rentals");
        System.out.println("7. Exit");

        System.out.print("Enter choice: ");
        String input = userInput.nextLine();

        try{
            int choice = Integer.parseInt(input);

            if(choice >= 1 && choice <= 7){
                return choice;
            }else{
                System.out.println("Please select a option from the menu");
            }

        }catch(NumberFormatException e){
            System.out.println("Invalid input. Please enter a number.");
        }
    }
    }
    public void start() throws SQLException{
        int userChoice;
        
        do{
            userChoice = displayMenu();
            switch(userChoice){
                case 1:
                    addUser();
                    break;
                case 2:
                    addGame();
                    break;
                case 3:
                    viewAllGames();
                    break;
                case 4: 
                    rentGames();
                    break;
                case 5:
                    returnGame();
                    break;
                case 6:
                    viewRentals();
                    break;
                    
                case 7:
                    System.out.println("Exiting System....");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
            
           
            
        }
        while(userChoice != 7);
       
    }
    
    private void addUser() throws SQLException{
        String userName;
       
        do{
        System.out.println("Enter your name in the following format ==> 'First Last' ");
      //  userInput.next();
        userName = userInput.nextLine();  
        
        
        if(userName.trim().isEmpty()){
            System.out.println("Invalid");
        }
        
    }      
        while(userName.trim().isEmpty());
{
         User newUser = new User(userName);
            userDAO.addUser(newUser);

}


    }    
    private void addGame() throws SQLException{
        String title; 
        String platform;
        String quantity;
        int gameQuantity = 0; 
        boolean valid = false; 
        
            do{
            System.out.println("Enter the Title");
            title = userInput.nextLine();
            
            if(title.isBlank()){
                System.out.println("Please Enter a valid Title");
            }
            else{
                valid = true;
            }

            }
            while(!valid);
        
            valid = false;
            
            
            
            do {
            System.out.println("Enter Platform:");
            platform = userInput.nextLine();
            
            if(platform.isBlank()){
                System.out.println("Please enter a valid platform");
                
            }
            else{
                valid = true;
            }
            
            }
            while(!valid);
            
           valid = false;
            do{
                 System.out.println("Enter Quantity (As a number):");
                 quantity = userInput.nextLine();
                 
                 try{ // try catch block to prevent system crash due to wrong user input
                 
                 gameQuantity = Integer.parseInt(quantity);
                 if(gameQuantity >= 0){
                     valid = true;
            }
                 else{
                     System.out.println("Please enter a positive number");
                 }
                 } catch(NumberFormatException e){
                     System.out.println("Please enter a valid number");
                 }
            }
            while(!valid);
            
            
            Games newGame = new Games(title,platform,gameQuantity);
            gamesDAO.addGame(newGame);
            
    }
    private void viewAllGames() throws SQLException{
    List<Games> games = gamesDAO.getAllGames();
       
    System.out.println("\n----- Available Games -----\n");

    System.out.printf("%-5s %-20s %-20s %-10s\n", "ID", "Title", "Platform", "Qty");
    System.out.println("------------------------------------------------------------");

    for(Games game : games){
        System.out.printf("%-5d %-20s %-20s %-10d\n",
                game.getGameID(),
                game.getTitle(),
                game.getPlatform(),
                game.getQuantity_available());
    }

    System.out.println();
    }
 
    private void rentGames() throws SQLException{
        String idInput;
        String idInput1;
        boolean valid = false; 
        
        int userID = 0;
        int gameID = 0;
        
            do{
                
                
            System.out.println("Enter your user ID");
            idInput = userInput.nextLine();
            
            try{
                userID = Integer.parseInt(idInput);
                if(userID > 0){
                    valid = true;
                }
                else{
                    System.out.println("Please enter a valid ID");
                }
            }
            catch(NumberFormatException e ){
                System.out.println("Please enter valid ID");
            }
            }
  
           while(!valid);
            
            
            valid = false;
            
            do{
            System.out.println("Enter your game ID");
            idInput1 = userInput.nextLine();
            
            try{
            gameID = Integer.parseInt(idInput1);
            if(gameID > 0){
                valid = true;
            }
                }
            catch(NumberFormatException e ){
                    
                    }
            }
              while(!valid);
            
            
            
    RentalsDAO rentals = new RentalsDAO();
    
    rentals.addRental(userID,gameID);
            

        
        


        
    }
    private void returnGame() throws SQLException{
       
        String idInput;
        int rentalID = 0;
         boolean valid = false;
        do{
           
              System.out.println("Please enter the rental ID");
            idInput = userInput.nextLine();
            try{
              rentalID = Integer.parseInt(idInput);
              if(rentalID > 0 ){
                  valid = true;
              }
              
            }
            catch(NumberFormatException e){
                System.out.println("Please enter a valid ID");
            }
    
    }
        
        while(!valid);
       
        RentalsDAO rentals = new RentalsDAO();
        
        rentals.returnRental(rentalID);
        
    }
    
    private void viewRentals() throws SQLException{
      List<Rentals> rentals = rentalsDAO.getAllRentals();
      
      System.out.println("\n----- Current Rentals -----\n");

    System.out.printf("%-10s %-10s %-10s %-12s %-12s %-12s\n",
            "RentalID", "UserID", "GameID", "Rented", "Due", "Returned");

    System.out.println("-----------------------------------------------------------------------");

    for(Rentals rental : rentals){
        System.out.printf("%-10d %-10d %-10d %-12s %-12s %-12s\n",
                rental.getRental_id(),
                rental.getUser_id().getUser_id(),
                rental.getGameID().getGameID(),
                rental.getRental_date(),
                rental.getDue_date(),
                rental.getReturn_date());
    }

    System.out.println();
      
    }
    
    }
    
    

