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
import java.util.List;
import java.util.ArrayList;
public class GamesDAO {
    private String url = "jdbc:mysql://localhost:3306/game_rentaldb";
    private String user = "root";
    private String password = "";

    private Connection connection;

    public GamesDAO() throws SQLException {
        this.connection = DriverManager.getConnection(url,user,password);
    }

    // Add a new game to the database
    public void addGame(Games game) throws SQLException {
        String sql = "INSERT INTO games (gameID, title, platform, quantity_available) VALUES (?, ?, ?, ?)";
        PreparedStatement state = connection.prepareStatement(sql);
        state.setInt(1, game.getGameID());
        state.setString(2, game.getTitle());
        state.setString(3, game.getPlatform());
        state.setInt(4, game.getQuantity_available());

        state.executeUpdate();
        System.out.println("Game successfully added.");
        state.close();
    }

    // Get a game by its ID
    public Games getGameById(int gameID) throws SQLException {
        String sql = "SELECT * FROM games WHERE gameID = ?";
        PreparedStatement state = connection.prepareStatement(sql);
        state.setInt(1, gameID);

        ResultSet result = state.executeQuery();
        Games game = null;

        if (result.next()) {
            int id = result.getInt("gameID");
            String title = result.getString("title");
            String platform = result.getString("platform");
            int quantity = result.getInt("quantity_available");

            game = new Games(id, title, platform, quantity);
        } else {
            System.out.println("Game ID not found.");
        }

        result.close();
        state.close();
        return game;
    }

    // Get all games from the database
    public List<Games> getAllGames() throws SQLException {
        List<Games> games = new ArrayList<>();
        String sql = "SELECT * FROM games";
        PreparedStatement state = connection.prepareStatement(sql);
        ResultSet result = state.executeQuery();

        while (result.next()) {
            int id = result.getInt("gameID");
            String title = result.getString("title");
            String platform = result.getString("platform");
            int quantity = result.getInt("quantity_available");

            Games game = new Games(id, title, platform, quantity);
            games.add(game);
        }

        result.close();
        state.close();
        return games;
    }

    // Update the quantity of a specific game
    public void updateGameQuantity(int gameID) throws SQLException {
        String sql = "UPDATE games SET quantity_available = quantity_available - 1 WHERE gameID = ?";
        PreparedStatement state = connection.prepareStatement(sql);
        
        state.setInt(1, gameID);

        int rowsAffected = state.executeUpdate();
       if (rowsAffected > 0) {
            System.out.println("Quantity successfully updated.");
        } else {
            System.out.println("No game found with that ID.");
       }

        state.executeUpdate();
        state.close();
    }

    // Delete a game from the database
    public void deleteGame(int gameID) throws SQLException {
        String sql = "DELETE FROM games WHERE gameID = ?";
        PreparedStatement state = connection.prepareStatement(sql);
        state.setInt(1, gameID);

        int rowsAffected = state.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Game successfully deleted.");
        } else {
            System.out.println("No game found with that ID.");
        }

        state.close();
    }
    
    public void increaseGameQuantity(int gameID) throws SQLException{
        String sql = "UPDATE games SET quantity_available = quantity_available + 1 WHERE gameID = ?";
        PreparedStatement state = connection.prepareStatement(sql);
        
        state.setInt(1, gameID);
        state.executeUpdate();
        
        state.close();
        
        
    }
}
