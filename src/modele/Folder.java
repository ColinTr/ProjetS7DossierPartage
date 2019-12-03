package modele;

public class Folder {
	//Attributs
	int folderID;
	String name;
	int parentFolderID;
	
	//Constructeurs
	public Folder(int f, String n, int p) {
		folderID = f;
		name = n;
		parentFolderID = p;
	}
	
	//Getters et setters
	public int getParentFolderID() {
		return parentFolderID;
	}

	public int getFolderID() {
		return folderID;
	}

	public String getName() {
		return name;
	}
}