package modele;

public class MyFile {
	//Attributs
	int fileID;
	String name;
	int folderID;
	long fileSize;
	
	//Constructeurs
	public MyFile(int fi, String n, int fo, long fsize){
		fileID = fi;
		name = n;
		folderID = fo;
		fileSize = fsize;
	}
	
	//Getters et setters
	public int getFileID() {
		return fileID;
	}

	public String getName() {
		return name;
	}

	public int getFolderID() {
		return folderID;
	}
	
	public long getFileSize() {
		return fileSize;
	}
}