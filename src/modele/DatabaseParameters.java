package modele;

import java.util.Properties;

public class DatabaseParameters {
	
	private static String address;
	private static Properties userInfo;
	
	public static String getAddress() {
		if(address == null) {
			address = "jdbc:mysql://127.0.0.1/projet_dossier_partage?serverTimezone=UTC";
		}
		return address;
	}
	
	//Si les attributs sont null on les défini avec les valeurs par défaut dans tous les getters :
	
	public static String getUser() {
		if(userInfo == null) {
			userInfo = new Properties();
			userInfo.put("user", "root");
			userInfo.put("password", "");
		}
		return userInfo.getProperty("user");
	}
	
	public static String getPassword() {
		if(userInfo == null) {
			userInfo = new Properties();
			userInfo.put("user", "root");
			userInfo.put("password", "");
		}
		return userInfo.getProperty("password");
	}
	
	public static Properties getUserInfo() {
		if(userInfo == null) {
			userInfo = new Properties();
			userInfo.put("user", "root");
			userInfo.put("password", "");
		}
		return userInfo;
	}
	
	public static void setAddress(String newAddress) {
		address = newAddress;
	}
	
	
	public static void setUserInfo(Properties newInfos) {
		userInfo = newInfos;
	}
}