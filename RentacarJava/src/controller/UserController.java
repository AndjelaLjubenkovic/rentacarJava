package controller;

import java.sql.*;

import konekcija.DatabaseConnection;




public class UserController {
	
	private Connection connection;
	
	/**
	 * konekcija za mysql bazu u konstruktoru
	 */
	
	public UserController() {
		connection = DatabaseConnection.getInstance().getConnection();
    }
	

	/**
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * slusi za dodavanje novog korisnika u bazu i registraciju korisnika na sistem
	 */
	
	  public void register(String username, String password, String email) {
	        try {
	            // Priprema SQL upita
	            String query = "INSERT INTO User (username, password, email) VALUES (?, ?, ?)";
	            PreparedStatement preparedStatement = connection.prepareStatement(query);

	            // Postavljanje parametara upita
	            preparedStatement.setString(1, username);
	            preparedStatement.setString(2, password);
	            preparedStatement.setString(3, email);

	            // Izvršavanje upita
	            preparedStatement.executeUpdate();
	            connection.zatvoriKonekciju();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
/**
 * 
 * @param username
 * @param password
 * @return boolean
 * login metoda sluzi za logovanje na sistem
 */
	    public boolean login(String username, String password) {
	        try {
	            // Priprema SQL upita
	            String query = "SELECT * FROM User WHERE username = ? AND password = ?";
	            PreparedStatement preparedStatement = connection.prepareStatement(query);

	            // Postavljanje parametara upita
	            preparedStatement.setString(1, username);
	            preparedStatement.setString(2, password);

	            // Izvršavanje upita
	            ResultSet resultSet = preparedStatement.executeQuery();
	            

	            // Provjera rezultata upita
	            if (resultSet.next()) {
	                return true;
	            } else {
	                return false;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	        connection.zatvoriKonekciju();
	    }

	    
	    
}
