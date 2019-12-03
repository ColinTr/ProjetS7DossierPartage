package controleur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modele.Folder;
import modele.MyFile;

public class FolderDAO {
	
	public static String getFolderName(int folderID) {
		String folderName = "Folder not found";
		
		Connexion connexion = new Connexion();
		
		try {
			Statement stmt = connexion.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT name FROM folder WHERE folderID = " + folderID);
			while(rs.next()) {
				folderName = rs.getString("name");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			connexion.closeConnection();
		}
		
		connexion.closeConnection();
		
		return folderName;
	}
	
	public static void renameFolder(int folderID, String newName) {
		Connexion connexion = new Connexion();
		
		try {
			Statement stmt = connexion.getConnection().createStatement();
			stmt.executeUpdate("UPDATE folder SET name = '"+ newName + "' WHERE folderID = " + folderID);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			connexion.closeConnection();
		}
		
		connexion.closeConnection();
	}
	
	public static List<Folder> getAllFoldersFromParent(int parentFolderID){
		List<Folder> folderList = new ArrayList<Folder>();
		
		Connexion connexion = new Connexion();
		
		try {
			Statement stmt = connexion.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM folder WHERE parentFolderID = " + parentFolderID);
			while(rs.next()) {
				folderList.add(new Folder(rs.getInt("folderID"), rs.getString("name"), rs.getInt("parentFolderID")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connexion.closeConnection();
		}
		
		connexion.closeConnection();
		
		return folderList;
	}
	
	/*
	 * This methods returns the number of sub-folders and sub-files from a specific folder
	 */
	public static int getNumberOfSubFoldersAndFiles(int folderID) {
		int result = 0;
		
		Connexion connexion = new Connexion();
		
		try {
			Statement stmt = connexion.getConnection().createStatement();
			ResultSet rs1 = stmt.executeQuery("SELECT * FROM folder WHERE parentFolderID = " + folderID);
			while(rs1.next()) {
				result++;
			}
			ResultSet rs2 = stmt.executeQuery("SELECT * FROM file WHERE folderID = " + folderID);
			while(rs2.next()) {
				result++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connexion.closeConnection();
		}
		
		connexion.closeConnection();
		
		return result;
	}
	
	public static void createFolder(int parentFolderID, String name) {
		Connexion connexion = new Connexion();
		
		try {
			Statement stmt = connexion.getConnection().createStatement();
			stmt.executeUpdate("INSERT INTO folder (parentFolderID, name) VALUES ('" + parentFolderID +"','" + name + "');");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connexion.closeConnection();
		}
		
		connexion.closeConnection();
	}
	
	/*
	 * 
	 */
	public static void deleteFolder(int folderID) {
		//We start by deleting all the files in the folder :
		List<MyFile> filesToDelete = FileDAO.getAllFilesFromFolder(folderID);
		for(MyFile file : filesToDelete) {
			FileDAO.deleteFile(file.getFileID());
		}
		
		//Then we delete everything inside the folders of the folder :
		List<Folder> foldersToDelete = getAllFoldersFromParent(folderID);
		for(Folder folder : foldersToDelete) {
			//If the folder is empty, we delete it
			if(FolderDAO.getNumberOfSubFoldersAndFiles(folderID) == 0) {
				Connexion connexion = new Connexion();
				
				try {
					Statement stmt = connexion.getConnection().createStatement();
					stmt.executeUpdate("DELETE FROM folder WHERE folderID = " + folder.getFolderID());
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					connexion.closeConnection();
				}
				connexion.closeConnection();
			}
			//If it isn't, we apply delete folder again
			else {
				deleteFolder(folder.getFolderID());
			}
		}
		
		//And finally we delete the folder itself because it is now empty :
		Connexion connexion = new Connexion();
		
		try {
			Statement stmt = connexion.getConnection().createStatement();
			stmt.executeUpdate("DELETE FROM folder WHERE folderID = " + folderID);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connexion.closeConnection();
		}
		connexion.closeConnection();
	}
	
	/*
	 * This methods is used when creating a new folder to check if a folder with the same name already exists inside the parent folder.
	 * @return : true if a folder with the same name is present, false otherwise.
	 * 
	 */
	public static boolean isFolderNameTakenInsideThisFolder(String name, int parentFolderID) {
		
		boolean result = false;
		
		Connexion connexion = new Connexion();
		
		try {
			Statement stmt = connexion.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Folder WHERE parentFolderID = " + parentFolderID +" AND name = '" + name + "';");
			while(rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connexion.closeConnection();
		}

		connexion.closeConnection();
		return result;
	}

	/*
	 * Returns the ID of the parent folder.
	 */
	public static int getParentDirectory(int folderID){
		Connexion connexion = new Connexion();
		
		int parentFolderID = -1;
		
		try {
			Statement stmt = connexion.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT parentFolderID FROM folder WHERE folderID = " + folderID);
			while(rs.next()) {
				parentFolderID = rs.getInt("parentFolderID");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connexion.closeConnection();
		}
		
		connexion.closeConnection();
		
		return parentFolderID;
	}
	
}