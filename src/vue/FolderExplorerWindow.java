package vue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import controleur.FileDAO;
import controleur.FilesTable;
import controleur.FolderDAO;
import controleur.FoldersTable;

import modele.Folder;
import modele.MyFile;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class FolderExplorerWindow {

	private final Color blueColor = new Color(50, 100, 150);
	private final Color colorHover = new Color(169, 171, 184);
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	private JFrame frameFileExplorer;

    private static List<MyFile> listTableFiles = new ArrayList<MyFile>();
	private static FilesTable modelTableFiles = new FilesTable(null);
	private static JTable tableFiles = new JTable(modelTableFiles);

    private static List<Folder> listTableFolders = new ArrayList<Folder>();
	private static FoldersTable modelTableFolders = new FoldersTable(null);
	private static JTable tableFolders = new JTable(modelTableFolders);
	
	private static int currentFolderID = 0;
	private static JLabel lblCurrentFolder;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FolderExplorerWindow window = new FolderExplorerWindow(args);
					window.frameFileExplorer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FolderExplorerWindow(String[] args) {
		initialize(args);
	}
	
	/**
	 * Refresh the contents of the files and folders tables to the new directory.
	 */
	public static void refreshTables(int folderID) {
		currentFolderID = folderID;
		
		lblCurrentFolder.setText("Current folder : " + FolderDAO.getFolderName(currentFolderID));
		
		listTableFiles = FileDAO.getAllFilesFromFolder(folderID);
		modelTableFiles.setFilesList(listTableFiles);
		modelTableFiles.fireTableDataChanged();

		listTableFolders = FolderDAO.getAllFoldersFromParent(folderID);
		modelTableFolders.setFoldersList(listTableFolders);
		modelTableFolders.fireTableDataChanged();
	}
	
	/**
	 * Refresh the contents of the files and folders tables to the current directory.
	 */
	public static void refreshTables() {
		lblCurrentFolder.setText("Current folder : " + FolderDAO.getFolderName(currentFolderID));
		
		listTableFiles = FileDAO.getAllFilesFromFolder(currentFolderID);
		modelTableFiles.setFilesList(listTableFiles);
		modelTableFiles.fireTableDataChanged();

		listTableFolders = FolderDAO.getAllFoldersFromParent(currentFolderID);
		modelTableFolders.setFoldersList(listTableFolders);
		modelTableFolders.fireTableDataChanged();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String[] args) {
		String username = "No user";
		if(args != null && args.length > 0) {
			username = args[0];
		}
		
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
		frameFileExplorer = new JFrame();
		ImageIcon logo = new ImageIcon(LoginWindow.class.getClassLoader().getResource("ressources/file-explorer-icon.png"));
		frameFileExplorer.setIconImage(logo.getImage());
		frameFileExplorer.setTitle("FileExplorer");
		frameFileExplorer.setBounds(dim.width/2-1080/2, dim.height/2-720/2, 1080, 720);
		frameFileExplorer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		frameFileExplorer.getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0, 0};
		gbl_mainPanel.rowHeights = new int[]{120, 400, 400, 0};
		gbl_mainPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		JPanel topBarPanel = new JPanel();
		topBarPanel.setBackground(blueColor);
		GridBagConstraints gbc_topBarPanel = new GridBagConstraints();
		gbc_topBarPanel.fill = GridBagConstraints.BOTH;
		gbc_topBarPanel.gridx = 0;
		gbc_topBarPanel.gridy = 0;
		mainPanel.add(topBarPanel, gbc_topBarPanel);
		GridBagLayout gbl_topBarPanel = new GridBagLayout();
		gbl_topBarPanel.columnWidths = new int[]{289, 141, 459, 0};
		gbl_topBarPanel.rowHeights = new int[]{91, -15, 0};
		gbl_topBarPanel.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_topBarPanel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		topBarPanel.setLayout(gbl_topBarPanel);
		
		JPanel leftTopBarPanel = new JPanel();
		leftTopBarPanel.setPreferredSize(new Dimension(290,50));
		leftTopBarPanel.setBackground(blueColor);
		FlowLayout flowLayout = (FlowLayout) leftTopBarPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_leftTopBarPanel = new GridBagConstraints();
		gbc_leftTopBarPanel.insets = new Insets(0, 0, 5, 5);
		gbc_leftTopBarPanel.anchor = GridBagConstraints.WEST;
		gbc_leftTopBarPanel.gridx = 0;
		gbc_leftTopBarPanel.gridy = 0;
		topBarPanel.add(leftTopBarPanel, gbc_leftTopBarPanel);
        
        JButton homeButton = new JButton("");
        homeButton.setToolTipText("Return to root folder");
        homeButton.setOpaque(true);
        homeButton.setBorder(null);
        homeButton.setBackground(blueColor);
        homeButton.setIcon(new ImageIcon(LoginWindow.class.getClassLoader().getResource("ressources/home_icon.png")));
		homeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//We go back to the root folder :
				refreshTables(0);
            }
		});
		homeButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	homeButton.setBackground(colorHover);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	homeButton.setBackground(blueColor);
		    }
		});
		leftTopBarPanel.add(homeButton);
		homeButton.setHorizontalAlignment(SwingConstants.LEFT);
		
        JButton returnButton = new JButton("");
        returnButton.setToolTipText("Return to previous folder");
        returnButton.setOpaque(true);
        returnButton.setBorder(null);
        returnButton.setBackground(blueColor);
        returnButton.setIcon(new ImageIcon(LoginWindow.class.getClassLoader().getResource("ressources/return_icon.png")));
        returnButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				currentFolderID = FolderDAO.getParentDirectory(currentFolderID);
				refreshTables(currentFolderID);
			}
		});
        returnButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	returnButton.setBackground(colorHover);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	returnButton.setBackground(blueColor);
		    }
		});
		leftTopBarPanel.add(returnButton);
		returnButton.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel currentUserLogo = new JLabel("");
        ImageIcon currentUserImageIcon = new ImageIcon(LoginWindow.class.getClassLoader().getResource("ressources/current_user_icon.png"));
        
        JButton refreshButton = new JButton("");
        refreshButton.setToolTipText("Refresh tables");
        refreshButton.setOpaque(true);
        refreshButton.setBorder(null);
        refreshButton.setBackground(blueColor);
        refreshButton.setIcon(new ImageIcon(LoginWindow.class.getClassLoader().getResource("ressources/refresh_icon.png")));
        refreshButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				refreshTables(currentFolderID);
            }
		});
        refreshButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	refreshButton.setBackground(colorHover);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	refreshButton.setBackground(blueColor);
		    }
		});
        leftTopBarPanel.add(refreshButton);
        currentUserLogo.setIcon(currentUserImageIcon);
		leftTopBarPanel.add(currentUserLogo);
		
		JLabel currentUserName = new JLabel("User : " + username);
		currentUserName.setFont(new Font("Nirmala UI", Font.BOLD, 15));
		leftTopBarPanel.add(currentUserName);
		
		JLabel polytechLogoImage = new JLabel("");
        ImageIcon polyechImageIcon = new ImageIcon(LoginWindow.class.getClassLoader().getResource("ressources/polytech_logo.png"));
		GridBagConstraints gbc_polytechLogoImage = new GridBagConstraints();
		gbc_polytechLogoImage.insets = new Insets(5, 5, 5, 5);
		polytechLogoImage.setIcon(polyechImageIcon);
		gbc_polytechLogoImage.gridx = 1;
		gbc_polytechLogoImage.gridy = 0;
		topBarPanel.add(polytechLogoImage, gbc_polytechLogoImage);
		
		JButton settingsButton = new JButton("");
		settingsButton.setToolTipText("Open the settings window");
		settingsButton.setOpaque(true);
		settingsButton.setBorder(null);
		settingsButton.setBackground(blueColor);
		settingsButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("ressources/settings.png")));
		settingsButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				SettingWindow.main(null);
            }
		});
		settingsButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	settingsButton.setBackground(colorHover);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	settingsButton.setBackground(blueColor);
		    }
		});
		GridBagConstraints gbc_settingsButton = new GridBagConstraints();
		gbc_settingsButton.insets = new Insets(0, 0, 0, 5);
		gbc_settingsButton.anchor = GridBagConstraints.EAST;
		gbc_settingsButton.gridx = 2;
		gbc_settingsButton.gridy = 0;
		topBarPanel.add(settingsButton, gbc_settingsButton);
		
		
		JPanel folderPanel = new JPanel();
		folderPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_folderPanel = new GridBagConstraints();
		gbc_folderPanel.fill = GridBagConstraints.BOTH;
		gbc_folderPanel.gridx = 0;
		gbc_folderPanel.gridy = 1;
		mainPanel.add(folderPanel, gbc_folderPanel);
		GridBagLayout gbl_folderPanel = new GridBagLayout();
		gbl_folderPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_folderPanel.rowHeights = new int[]{0, 280, 50, 0};
		gbl_folderPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_folderPanel.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		folderPanel.setLayout(gbl_folderPanel);
		
		lblCurrentFolder = new JLabel();
		lblCurrentFolder.setFont(new Font("Nirmala UI", Font.BOLD, 13));
		GridBagConstraints gbc_lblCurrentFolder = new GridBagConstraints();
		gbc_lblCurrentFolder.insets = new Insets(5, 5, 5, 0);
		gbc_lblCurrentFolder.anchor = GridBagConstraints.WEST;
		gbc_lblCurrentFolder.gridx = 1;
		gbc_lblCurrentFolder.gridy = 0;
		folderPanel.add(lblCurrentFolder, gbc_lblCurrentFolder);
		
		JScrollPane folderScrollPane = new JScrollPane();
		GridBagConstraints gbc_folderScrollPane = new GridBagConstraints();
		gbc_folderScrollPane.insets = new Insets(0, 50, 5, 50);
		gbc_folderScrollPane.fill = GridBagConstraints.BOTH;
		gbc_folderScrollPane.gridx = 1;
		gbc_folderScrollPane.gridy = 1;
		folderPanel.add(folderScrollPane, gbc_folderScrollPane);
		
		tableFolders.setAutoCreateRowSorter(true);
		tableFolders.getRowSorter().toggleSortOrder(0);
		folderScrollPane.setViewportView(tableFolders);
		
		JPanel folderControlPanel = new JPanel();
		folderControlPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_folderControlPanel = new GridBagConstraints();
		gbc_folderControlPanel.fill = GridBagConstraints.VERTICAL;
		gbc_folderControlPanel.gridx = 1;
		gbc_folderControlPanel.gridy = 2;
		folderPanel.add(folderControlPanel, gbc_folderControlPanel);
		GridBagLayout gbl_folderControlPanel = new GridBagLayout();
		gbl_folderControlPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_folderControlPanel.rowHeights = new int[]{41, 0};
		gbl_folderControlPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_folderControlPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		folderControlPanel.setLayout(gbl_folderControlPanel);
		
		JButton btnMoveToFolder = new JButton("move to folder");
		btnMoveToFolder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowIndex = tableFolders.getSelectedRow();
				int colIndex = tableFolders.getSelectedColumn();
				if(rowIndex != -1 && colIndex != -1) {
	                    Folder folderToMoveTo = listTableFolders.get(tableFolders.convertRowIndexToModel(rowIndex));
	                    refreshTables(folderToMoveTo.getFolderID());
                }
            }
        });
		GridBagConstraints gbc_btnMoveToFolder = new GridBagConstraints();
		gbc_btnMoveToFolder.anchor = GridBagConstraints.NORTH;
		gbc_btnMoveToFolder.insets = new Insets(0, 0, 0, 5);
		gbc_btnMoveToFolder.gridx = 0;
		gbc_btnMoveToFolder.gridy = 0;
		folderControlPanel.add(btnMoveToFolder, gbc_btnMoveToFolder);
		
		JButton btnDeleteFolder = new JButton("delete folder");
		btnDeleteFolder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowIndex = tableFolders.getSelectedRow();
				int colIndex = tableFolders.getSelectedColumn();
				if(rowIndex != -1 && colIndex != -1) {
	                    Folder folderToDelete = listTableFolders.get(tableFolders.convertRowIndexToModel(rowIndex));
	                    
	                    int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure that you want to delete " + folderToDelete.getName() + " and all the files inside ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    	if(dialogResult == JOptionPane.YES_OPTION){
                    		FolderDAO.deleteFolder(folderToDelete.getFolderID());
    	                    refreshTables(currentFolderID);
                    	}
	                    
                }
            }
        });
		
		JButton btnCreateNewFolder = new JButton("create folder");
		btnCreateNewFolder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String[] args = {String.valueOf(currentFolderID)};
                NewFolderWindow.main(args);
            }
        });
		GridBagConstraints gbc_btnCreateNewFolder = new GridBagConstraints();
		gbc_btnCreateNewFolder.anchor = GridBagConstraints.NORTH;
		gbc_btnCreateNewFolder.insets = new Insets(0, 0, 0, 5);
		gbc_btnCreateNewFolder.gridx = 1;
		gbc_btnCreateNewFolder.gridy = 0;
		folderControlPanel.add(btnCreateNewFolder, gbc_btnCreateNewFolder);
		GridBagConstraints gbc_btnDeleteFolder = new GridBagConstraints();
		gbc_btnDeleteFolder.insets = new Insets(0, 0, 0, 5);
		gbc_btnDeleteFolder.anchor = GridBagConstraints.NORTH;
		gbc_btnDeleteFolder.gridx = 2;
		gbc_btnDeleteFolder.gridy = 0;
		folderControlPanel.add(btnDeleteFolder, gbc_btnDeleteFolder);
		
		JButton btnRenameFolder = new JButton("rename folder");
		btnRenameFolder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		int rowIndex = tableFolders.getSelectedRow();
				int colIndex = tableFolders.getSelectedColumn();
				if(rowIndex != -1 && colIndex != -1) {
                    Folder folderToRename = listTableFolders.get(tableFolders.convertRowIndexToModel(rowIndex));
                    String[] args = { String.valueOf(folderToRename.getFolderID()) };
                    RenameFolderWindow.main(args);
                }
            }
        });
		GridBagConstraints gbc_btnRenameFolder = new GridBagConstraints();
		gbc_btnRenameFolder.anchor = GridBagConstraints.NORTH;
		gbc_btnRenameFolder.gridx = 3;
		gbc_btnRenameFolder.gridy = 0;
		folderControlPanel.add(btnRenameFolder, gbc_btnRenameFolder);
		
		JPanel filePanel = new JPanel();
		filePanel.setBackground(Color.WHITE);
		filePanel.setBorder(null);
		GridBagConstraints gbc_filePanel = new GridBagConstraints();
		gbc_filePanel.fill = GridBagConstraints.BOTH;
		gbc_filePanel.gridx = 0;
		gbc_filePanel.gridy = 2;
		mainPanel.add(filePanel, gbc_filePanel);
		GridBagLayout gbl_filePanel = new GridBagLayout();
		gbl_filePanel.columnWidths = new int[]{0, 306, 0, 0};
		gbl_filePanel.rowHeights = new int[]{0, 280, 50, 0};
		gbl_filePanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_filePanel.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		filePanel.setLayout(gbl_filePanel);
		
		JLabel separatorLabel = new JLabel(" ");
		GridBagConstraints gbc_separatorLabel = new GridBagConstraints();
		gbc_separatorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_separatorLabel.gridx = 1;
		gbc_separatorLabel.gridy = 0;
		filePanel.add(separatorLabel, gbc_separatorLabel);
		
		JScrollPane fileScrollPane = new JScrollPane();
		GridBagConstraints gbc_fileScrollPane = new GridBagConstraints();
		gbc_fileScrollPane.insets = new Insets(0, 50, 5, 50);
		gbc_fileScrollPane.fill = GridBagConstraints.BOTH;
		gbc_fileScrollPane.gridx = 1;
		gbc_fileScrollPane.gridy = 1;
		filePanel.add(fileScrollPane, gbc_fileScrollPane);

		tableFiles.setAutoCreateRowSorter(true);
		tableFiles.getRowSorter().toggleSortOrder(0);
		fileScrollPane.setViewportView(tableFiles);
		
		JPanel fileControlPanel = new JPanel();
		fileControlPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_fileControlPanel = new GridBagConstraints();
		gbc_fileControlPanel.insets = new Insets(0, 0, 5, 0);
		gbc_fileControlPanel.fill = GridBagConstraints.VERTICAL;
		gbc_fileControlPanel.gridx = 1;
		gbc_fileControlPanel.gridy = 2;
		filePanel.add(fileControlPanel, gbc_fileControlPanel);
		GridBagLayout gbl_fileControlPanel = new GridBagLayout();
		gbl_fileControlPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_fileControlPanel.rowHeights = new int[]{0, 0};
		gbl_fileControlPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gbl_fileControlPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		fileControlPanel.setLayout(gbl_fileControlPanel);
		
		JButton btnDownloadFile = new JButton("download file");
		btnDownloadFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowIndex = tableFiles.getSelectedRow();
				int colIndex = tableFiles.getSelectedColumn();
				if(rowIndex != -1 && colIndex != -1) {
	                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	                jfc.setDialogTitle("Choose the destination folder : ");
	
	                int returnValue = jfc.showOpenDialog(null);
	
	                if (returnValue == JFileChooser.APPROVE_OPTION) {
	                    File selectedFile = jfc.getSelectedFile();
	                    String destinationFolder = selectedFile.getAbsolutePath();
	                    
	                    MyFile fileToDownload = listTableFiles.get(tableFiles.convertRowIndexToModel(rowIndex));
	                    
	                    destinationFolder = destinationFolder.concat("/"+fileToDownload.getName());
	
	                    try {
							FileDAO.downloadFile(fileToDownload.getFileID(), destinationFolder);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
    				}
                }
            }
        });
		GridBagConstraints gbc_btnDownloadFile = new GridBagConstraints();
		gbc_btnDownloadFile.insets = new Insets(0, 5, 0, 5);
		gbc_btnDownloadFile.gridx = 0;
		gbc_btnDownloadFile.gridy = 0;
		fileControlPanel.add(btnDownloadFile, gbc_btnDownloadFile);
		
		JButton btnUploadFile = new JButton("upload file");
		btnUploadFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                jfc.setDialogTitle("Choose the destination folder : ");

                int returnValue = jfc.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    String fileToUploadPath = selectedFile.getAbsolutePath();
                    
                    try {
						FileDAO.uploadFile(fileToUploadPath, currentFolderID);
						//And finally we refresh the tables
						refreshTables(currentFolderID);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
                }
            }
        });
		GridBagConstraints gbc_btnUploadFile = new GridBagConstraints();
		gbc_btnUploadFile.insets = new Insets(0, 5, 0, 5);
		gbc_btnUploadFile.gridx = 1;
		gbc_btnUploadFile.gridy = 0;
		fileControlPanel.add(btnUploadFile, gbc_btnUploadFile);
		
		JButton btnDeleteFile = new JButton("delete file");
		btnDeleteFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		int rowIndex = tableFiles.getSelectedRow();
				int colIndex = tableFiles.getSelectedColumn();
				if(rowIndex != -1 && colIndex != -1) {
					
                    MyFile fileToDelete = listTableFiles.get(tableFiles.convertRowIndexToModel(rowIndex));
                    
                    int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure that you want to delete " + fileToDelete.getName() + " ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                	if(dialogResult == JOptionPane.YES_OPTION){
                		try {
                        	//We delete the file in the database using the ID
    						FileDAO.deleteFile(fileToDelete.getFileID());
    						//And finally we refresh the tables
    						refreshTables(currentFolderID);
    					} catch (Exception e1) {
    						e1.printStackTrace();
        				}
                	}
                }
            }
        });
		GridBagConstraints gbc_btnDeleteFile = new GridBagConstraints();
		gbc_btnDeleteFile.insets = new Insets(0, 5, 0, 5);
		gbc_btnDeleteFile.gridx = 2;
		gbc_btnDeleteFile.gridy = 0;
		fileControlPanel.add(btnDeleteFile, gbc_btnDeleteFile);
		
		JButton btnRenameFile = new JButton("rename file");
		btnRenameFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		int rowIndex = tableFiles.getSelectedRow();
				int colIndex = tableFiles.getSelectedColumn();
				if(rowIndex != -1 && colIndex != -1) {
                    MyFile FileToRename = listTableFiles.get(tableFiles.convertRowIndexToModel(rowIndex));
                    String[] args = { String.valueOf(FileToRename.getFileID()) };
                    RenameFileWindow.main(args);
                }
            }
        });
		GridBagConstraints gbc_btnRenameFile = new GridBagConstraints();
		gbc_btnRenameFile.insets = new Insets(0, 5, 0, 5);
		gbc_btnRenameFile.gridx = 3;
		gbc_btnRenameFile.gridy = 0;
		fileControlPanel.add(btnRenameFile, gbc_btnRenameFile);
		
		//We initialize the application at the root folder :
		if(args != null && args.length > 0) {
			refreshTables(currentFolderID);
		}
		else {
			lblCurrentFolder.setText("Current folder : Unknown, please refresh when connected to database." );
		}
	}
}