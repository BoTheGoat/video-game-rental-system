/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package videogamerental;

/**
 *
 * @author 1zomb
 */
public class User {
    int user_id;
    String name;

    public User( String name) {
        
        this.name = name;
    }

    public User(int user_id) {
        this.user_id = user_id;
    }
    
    
    
    
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }
    
    
    
}
