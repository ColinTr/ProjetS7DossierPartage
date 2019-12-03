package controleur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modele.DatabaseParameters;

public class Connexion {
	
	private Connection connexion;
	
	public Connexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex1) {
			System.out.println("Pilote non trouvé!");
			System.exit(1);
		}
		
		try {
			connexion = DriverManager.getConnection(DatabaseParameters.getAddress(), DatabaseParameters.getUserInfo());
		} catch (SQLException e) {
    		JOptionPane.showMessageDialog(new JFrame(), "Unable to connect to database\nPlease verify connection parameters.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void closeConnection() {
		if(connexion != null) {
			try {
				connexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Connection getConnection() {
		return connexion;
	}
}