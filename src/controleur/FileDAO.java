package controleur;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modele.MyFile;

public class FileDAO {
	
	public static String getFileName(int fileID) {
		String fileName = "File not found";
		
		Connexion connexion = new Connexion();
		
		try {
			Statement stmt = connexion.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT name FROM file WHERE fileID = " + fileID);
			while(rs.next()) {
				fileName = rs.getString("name");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			connexion.closeConnection();
		}
		
		connexion.closeConnection();
		
		return fileName;
	}
	
	public static void renameFile(int fileID, String newName) {
		Connexion connexion = new Connexion();
		
		try {
			Statement stmt = connexion.getConnection().createStatement();
			stmt.executeUpdate("UPDATE File SET name = '"+ newName + "' WHERE fileID = " + fileID);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			connexion.closeConnection();
		}
		
		connexion.closeConnection();
	}
	
	public static List<MyFile> getAllFilesFromFolder(int folderID){
		List<MyFile> fileList = new ArrayList<MyFile>();
		
		Connexion connexion = new Connexion();
		
		try {
			Statement stmt = connexion.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT fileID, folderID, name, fileSize FROM file WHERE folderID = " + folderID);
			while(rs.next()) {
				fileList.add(new MyFile(rs.getInt("fileID"), rs.getString("name"), rs.getInt("folderID"), rs.getInt("fileSize")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connexion.closeConnection();
		}
		
		connexion.closeConnection();
		
		return fileList;
	}
	
	public static boolean uploadFile(String location, int folderID) throws Exception {
		Connexion connexion = new Connexion();

		File file = new File(location);
		FileInputStream fis = new FileInputStream(file);
		try {
			PreparedStatement ps = connexion.getConnection().prepareStatement("insert into file (folderID, name, fileBLOB, fileSize) values (?,?,?,?)");
			ps.setInt(1, folderID);
			ps.setString(2, file.getName());
			ps.setBinaryStream(3, fis, (int)file.length());
			ps.setInt(4, (int)file.length());
			
			ps.executeUpdate();

			ps.close();

		} catch (Exception e){
			fis.close();
			connexion.closeConnection();
			return false;
		}
		
		fis.close();
		connexion.closeConnection();
		return true;
		
	}

	public static void downloadFile(int fileID, String location) throws Exception {
		Connexion connexion = new Connexion();

		File file = new File(location);
		FileOutputStream fos = new FileOutputStream(file);

		try {
			Statement stmt = connexion.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT fileBLOB FROM file WHERE fileID = '" + fileID + "';");
			if(rs.next()) {
				InputStream fileData = rs.getBinaryStream("fileBLOB");

				byte[] buffer = new byte[1024];
				int length = 0;

				while((length = fileData.read(buffer)) != -1) {
					fos.write(buffer, 0, length);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fos.close();
			connexion.closeConnection();
		}

		connexion.closeConnection();
	}
	
	/*
	 * @return : Returns the number of files deleted (so 0 if none was deleted).
	 */
	public static void deleteFile(int fileID) {
		Connexion connexion = new Connexion();
		
		try {
			Statement stmt = connexion.getConnection().createStatement();
			stmt.executeUpdate("DELETE FROM file WHERE fileID = " + fileID);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connexion.closeConnection();
		}
		
		connexion.closeConnection();
	}

	/*
	 * Returns the ID of the folder.
	 */
	public static int getFolderID(int fileID){
		Connexion connexion = new Connexion();
		
		int folderID = -1;
		
		try {
			Statement stmt = connexion.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT folderID FROM folder WHERE folderID = " + fileID);
			while(rs.next()) {
				folderID = rs.getInt("folderID");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connexion.closeConnection();
		}
		
		connexion.closeConnection();
		
		return folderID;
	}
}