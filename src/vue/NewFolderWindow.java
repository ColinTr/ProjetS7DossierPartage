package vue;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.UIManager;

import controleur.FolderDAO;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class NewFolderWindow {

	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	private JFrame frameNewFolderWindow;
	private JTextField nameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewFolderWindow window = new NewFolderWindow(args);
					window.frameNewFolderWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NewFolderWindow(String[] args) {
		initialize(args);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        int parentFolderID = 0;
        String folderName = "File Not Found";
		if(args.length > 0) {
			parentFolderID = Integer.valueOf(args[0]);
			folderName = FolderDAO.getFolderName(parentFolderID);
		}
		
		//This id is defined so it can be used inside the scope of actionListener
		final int parentID = parentFolderID;
        
		frameNewFolderWindow = new JFrame();
		ImageIcon logo = new ImageIcon(LoginWindow.class.getClassLoader().getResource("ressources/file-explorer-icon.png"));
		frameNewFolderWindow.setIconImage(logo.getImage());
		frameNewFolderWindow.setTitle("Create new folder");
		frameNewFolderWindow.setBounds(100, 100, 250, 250);
		frameNewFolderWindow.setBounds(dim.width/2-300/2, dim.height/2-150/2, 300, 150);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{514, 0};
		gridBagLayout.rowHeights = new int[]{1, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		frameNewFolderWindow.getContentPane().setLayout(gridBagLayout);
		
		JPanel bodyPanel = new JPanel();
		bodyPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_bodyPanel = new GridBagConstraints();
		gbc_bodyPanel.fill = GridBagConstraints.BOTH;
		gbc_bodyPanel.gridx = 0;
		gbc_bodyPanel.gridy = 0;
		frameNewFolderWindow.getContentPane().add(bodyPanel, gbc_bodyPanel);
		GridBagLayout gbl_bodyPanel = new GridBagLayout();
		gbl_bodyPanel.columnWidths = new int[]{0, 0};
		gbl_bodyPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_bodyPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_bodyPanel.rowWeights = new double[]{1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		bodyPanel.setLayout(gbl_bodyPanel);
		
		JLabel label_1 = new JLabel("Create a new folder in " + folderName + ":");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.WEST;
		gbc_label_1.insets = new Insets(0, 5, 5, 0);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 0;
		bodyPanel.add(label_1, gbc_label_1);
		
		JPanel valuesPanel = new JPanel();
		valuesPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_valuesPanel = new GridBagConstraints();
		gbc_valuesPanel.insets = new Insets(0, 0, 5, 0);
		gbc_valuesPanel.fill = GridBagConstraints.BOTH;
		gbc_valuesPanel.gridx = 0;
		gbc_valuesPanel.gridy = 1;
		bodyPanel.add(valuesPanel, gbc_valuesPanel);
		GridBagLayout gbl_valuesPanel = new GridBagLayout();
		gbl_valuesPanel.columnWidths = new int[]{0, 0, 0};
		gbl_valuesPanel.rowHeights = new int[]{20, 0};
		gbl_valuesPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_valuesPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		valuesPanel.setLayout(gbl_valuesPanel);
		
		JLabel lblNewFolderName = new JLabel("New folder name :");
		GridBagConstraints gbc_lblNewFolderName = new GridBagConstraints();
		gbc_lblNewFolderName.anchor = GridBagConstraints.WEST;
		gbc_lblNewFolderName.insets = new Insets(0, 5, 0, 0);
		gbc_lblNewFolderName.gridx = 0;
		gbc_lblNewFolderName.gridy = 0;
		valuesPanel.add(lblNewFolderName, gbc_lblNewFolderName);
		
		nameField = new JTextField();
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.insets = new Insets(0, 15, 0, 15);
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridx = 1;
		gbc_nameField.gridy = 0;
		valuesPanel.add(nameField, gbc_nameField);
		nameField.setColumns(10);
		
		JLabel errorMessageLabel = new JLabel(" ");
		errorMessageLabel.setForeground(Color.RED);
		GridBagConstraints gbc_errorMessageLabel = new GridBagConstraints();
		gbc_errorMessageLabel.insets = new Insets(0, 0, 5, 0);
		gbc_errorMessageLabel.gridx = 0;
		gbc_errorMessageLabel.gridy = 2;
		bodyPanel.add(errorMessageLabel, gbc_errorMessageLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 3;
		bodyPanel.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String folderName = nameField.getText();
				if(folderName == null || folderName.equals("") || folderName.isBlank()) {
					errorMessageLabel.setText("Folder name can't be blank.");
				}
				else if(FolderDAO.isFolderNameTakenInsideThisFolder(folderName, parentID) == true) {
					errorMessageLabel.setText("A folder with the same name already exists.");
				}
				else {
					FolderDAO.createFolder(parentID, folderName);
					FolderExplorerWindow.refreshTables(parentID);
					frameNewFolderWindow.dispose();
				}
			}
		});
		GridBagConstraints gbc_btnCreate = new GridBagConstraints();
		gbc_btnCreate.insets = new Insets(0, 0, 0, 10);
		gbc_btnCreate.gridx = 0;
		gbc_btnCreate.gridy = 0;
		panel.add(btnCreate, gbc_btnCreate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameNewFolderWindow.dispose();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 10, 0, 0);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 0;
		panel.add(btnCancel, gbc_btnCancel);
	}

}
