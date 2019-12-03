package modele;

public class User {
	//Attributs
	int userID;
	String login;
	String password;
	
	//Constructeurs
	public User(int userID, String login, String password) {
		this.userID = userID;
		this.login = login;
		this.password = password;
	}
	
	//Getters et setters
	public int getUserID() {
		return userID;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}
}