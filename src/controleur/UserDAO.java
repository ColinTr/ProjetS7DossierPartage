package controleur;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {
	
	private static String SHA512(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] messageDigest = md.digest(str.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static boolean testAuth(String login, String password) {
		Connexion connexion = new Connexion();
		
		try {
			Statement stmt = connexion.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT password FROM user WHERE login = '" + login + "'");
			if(rs.next() == true) {
				if(rs.getString("password").equals(SHA512(password))) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connexion.closeConnection();
		}
		
		connexion.closeConnection();
		
		return false;
	}

	private static boolean doesLoginExist(String login) {
		Connexion connexion = new Connexion();
		
		try {
			Statement stmt = connexion.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE login = '" + login + "'");
			if(rs.next() == true) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connexion.closeConnection();
		}
		
		connexion.closeConnection();
		
		return false;
	}
	
	/*
	 * @return : Returns true if user was successfully created, false otherwise.
	 */
	public static boolean createUser(String login, String password) {
		if(doesLoginExist(login) == false) {
			Connexion connexion = new Connexion();
			
			try {
				Statement stmt = connexion.getConnection().createStatement();
			
				stmt.executeUpdate("INSERT INTO user (login, password) VALUES ('" + login + "','" + SHA512(password) + "');");
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				connexion.closeConnection();
			}
			
			connexion.closeConnection();
			return true;
		}
		else {
			return false;
		}
	}
}