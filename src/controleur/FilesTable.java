package controleur;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import modele.MyFile;

public class FilesTable extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;

	private static final int COLUMN_ID = 0;
	private static final int COLUMN_NAME = 1;
	private static final int COLUMN_SIZE = 2;
	
	private String[] columnNames = { "File ID", "File Name", "Size" };
	private List<MyFile> filesList;
	
	public void setFilesList(List<MyFile> fList) {
		filesList = fList;
	}

	public FilesTable(List<MyFile> fList) {
		filesList = fList;
	}

	@Override
	public int getRowCount() {
		if(filesList == null){
			return 0;
		}
		return filesList.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(filesList==null || filesList.size()==0){
			return null;
		}
		
		MyFile file = filesList.get(rowIndex);
		
        Object returnValue = null;
        
        switch (columnIndex) {

	        case COLUMN_ID :
	        	returnValue = file.getFileID();
	        	break;
        	
	        case COLUMN_NAME :
	        	returnValue = file.getName();
	            break;
	        	
	        case COLUMN_SIZE :
	        	returnValue = file.getFileSize() + " bytes";
	        	break;
	            
	        default:
	            throw new IllegalArgumentException("Invalid column index");
	            
        }
        return returnValue;
	}
}