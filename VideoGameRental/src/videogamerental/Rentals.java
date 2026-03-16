/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package videogamerental;

import java.time.LocalDate;

/**
 *
 * @author 1zomb
 */
public class Rentals {
    int rental_id;
    User user_id;
    Games gameID;
    LocalDate rental_date;
    LocalDate due_date;
    LocalDate return_date;

    public Rentals(int rental_id, User user_id, Games gameID, LocalDate rental_date, LocalDate due_date, LocalDate return_date) {
        this.rental_id = rental_id;
        this.user_id = user_id;
        this.gameID = gameID;
        this.rental_date = rental_date;
        this.due_date = due_date;
        this.return_date = return_date;
    }
    
    

    public int getRental_id() {
        return rental_id;
    }

    public void setRental_id(int rental_id) {
        this.rental_id = rental_id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Games getGameID() {
        return gameID;
    }

    public void setGameID(Games gameID) {
        this.gameID = gameID;
    }

    public LocalDate getRental_date() {
        return rental_date;
    }

    public void setRental_date(LocalDate rental_date) {
        this.rental_date = rental_date;
    }

    public LocalDate getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDate due_date) {
        this.due_date = due_date;
    }

    public LocalDate getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }
    
    public boolean isReturned(){
        return return_date != null;
    }
    
    public boolean isOverdue(){
        return !isReturned()  && due_date.isBefore(LocalDate.now());
    }
    @Override
    public String toString() {
    return "Rental ID: " + rental_id + ", User: " + user_id.getName() +
           ", Game: " + gameID.getTitle() +
           ", Rental Date: " + rental_date +
           ", Due Date: " + due_date +
           ", Return Date: " + (return_date != null ? return_date : "Not Returned");
    }
}
    

