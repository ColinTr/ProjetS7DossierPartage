package controleur;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import modele.Folder;

public class FoldersTable extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;

	private static final int COLUMN_ID = 0;
	private static final int COLUMN_NAME = 1;
	
	private String[] columnNames = { "Folder ID", "Folder Name" };
	private List<Folder> foldersList;
	
	public void setFoldersList(List<Folder> fList) {
		foldersList = fList;
	}

	public FoldersTable(List<Folder> fList) {
		foldersList = fList;
	}

	@Override
	public int getRowCount() {
		if(foldersList == null){
			return 0;
		}
		return foldersList.size();
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
		if(foldersList==null || foldersList.size()==0){
			return null;
		}
		
		Folder folder = foldersList.get(rowIndex);
		
        Object returnValue = null;
        
        switch (columnIndex) {
        
	        case COLUMN_NAME :
	        	returnValue = folder.getName();
	            break;
	            
	        case COLUMN_ID :
	        	returnValue = folder.getFolderID();
	            break;
	            
	        default:
	            throw new IllegalArgumentException("Invalid column index");
	            
        }
        return returnValue;
	}
}