/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package videogamerental;

/**
 *
 * @author 1zomb
 */ import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;


public class UserDAO {
           String url = "jdbc:mysql://localhost:3306/game_rentaldb";
          String user = "root";
          String password = "";
    
    private Connection connection;

    public UserDAO() throws SQLException {
        this.connection = DriverManager.getConnection(url, user, password);
    }
    
    
    public void addUser(User user) throws SQLException{
        String sql = "Insert INTO user (user_id, name) VALUES (?,?)";
        // Safe way to run SQL query from java
        PreparedStatement state = connection.prepareStatement(sql);
        // adding values into user field
        state.setInt(1, user.getUser_id());
        state.setString(2, user.getName());
        
        System.out.println("User added successfully: " + user.getName());
       state.executeUpdate();
       state.close();
    }
}
