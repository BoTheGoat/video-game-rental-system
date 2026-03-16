/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package videogamerental;

/**
 *
 * @author 1zomb
 */
public class Games {
    
    int gameID; 
    String title;
    String platform;
    int quantity_available;

    public Games(int gameID, String title, String platform, int quantity_available) {
        this.gameID = gameID;
        this.title = title;
        this.platform = platform;
        this.quantity_available = quantity_available;
    }

    public Games(int gameID) {
        this.gameID = gameID;
    }

    public Games(String title, String platform, int quantity_available) {
        this.title = title;
        this.platform = platform;
        this.quantity_available = quantity_available;
    }
    
    
 

    
    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getQuantity_available() {
        return quantity_available;
    }

    public void setQuantity_available(int quantity_available) {
        this.quantity_available = quantity_available;
    }
    
    
    
    
    @Override
    public String toString(){
        return "Game ID: " + gameID + "| , Title: " + title + "| , Platform: " + platform + "| ,Quantity: " + quantity_available;
    }
    
    
}


 