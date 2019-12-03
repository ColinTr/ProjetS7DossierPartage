package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import controleur.FileDAO;

public class RenameFileWindow {
	
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

	private JFrame frameRenameFileWindow;
	private JTextField newNameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RenameFileWindow window = new RenameFileWindow(args);
					window.frameRenameFileWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RenameFileWindow(String[] args) {
		initialize(args);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String[] args) {
		int fileToRenameID = -1;
		if(args != null && args.length > 0) {
			fileToRenameID = Integer.valueOf(args[0]);
		}
		//This id is defined so it can be used inside the scope of actionListener
		final int fileID = fileToRenameID;
		String fileToRenameName = FileDAO.getFileName(fileToRenameID);
		
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
		frameRenameFileWindow = new JFrame();
		frameRenameFileWindow.setTitle("Rename a file");
		ImageIcon logo = new ImageIcon(LoginWindow.class.getClassLoader().getResource("ressources/file-explorer-icon.png"));
		frameRenameFileWindow.setIconImage(logo.getImage());
		frameRenameFileWindow.getContentPane().setBackground(Color.WHITE);
		frameRenameFileWindow.setBounds(dim.width/2-300/2, dim.height/2-170/2, 300, 170);
		frameRenameFileWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{434, 0};
		gridBagLayout.rowHeights = new int[]{0, 1, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		frameRenameFileWindow.getContentPane().setLayout(gridBagLayout);
		
		JLabel newNameLabel = new JLabel("New name for " + fileToRenameName + " :");
		GridBagConstraints gbc_newNameLabel = new GridBagConstraints();
		gbc_newNameLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_newNameLabel.insets = new Insets(5, 10, 5, 0);
		gbc_newNameLabel.gridx = 0;
		gbc_newNameLabel.gridy = 0;
		frameRenameFileWindow.getContentPane().add(newNameLabel, gbc_newNameLabel);
		
		newNameField = new JTextField();
		newNameField.setText(fileToRenameName);
		GridBagConstraints gbc_newNameField = new GridBagConstraints();
		gbc_newNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_newNameField.insets = new Insets(5, 20, 5, 20);
		gbc_newNameField.gridx = 0;
		gbc_newNameField.gridy = 1;
		frameRenameFileWindow.getContentPane().add(newNameField, gbc_newNameField);
		
		JLabel errorMessage = new JLabel(" ");
		errorMessage.setForeground(Color.RED);
		GridBagConstraints gbc_errorMessage = new GridBagConstraints();
		gbc_errorMessage.insets = new Insets(5, 0, 5, 0);
		gbc_errorMessage.gridx = 0;
		gbc_errorMessage.gridy = 2;
		frameRenameFileWindow.getContentPane().add(errorMessage, gbc_errorMessage);
		
		JPanel optionsPanel = new JPanel();
		optionsPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_optionsPanel = new GridBagConstraints();
		gbc_optionsPanel.insets = new Insets(5, 0, 0, 0);
		gbc_optionsPanel.gridx = 0;
		gbc_optionsPanel.gridy = 3;
		frameRenameFileWindow.getContentPane().add(optionsPanel, gbc_optionsPanel);
		GridBagLayout gbl_optionsPanel = new GridBagLayout();
		gbl_optionsPanel.columnWidths = new int[]{0, 0, 0};
		gbl_optionsPanel.rowHeights = new int[]{0, 0};
		gbl_optionsPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_optionsPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		optionsPanel.setLayout(gbl_optionsPanel);
		
		JButton applyButton = new JButton("Apply");
		applyButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String fileName = newNameField.getText();
				if(fileName == null || fileName.equals("") || fileName.isBlank()) {
					errorMessage.setText("New name can't be blank.");
				}
				else {
					FileDAO.renameFile(fileID, newNameField.getText());
				}
				FolderExplorerWindow.refreshTables();
				frameRenameFileWindow.dispose();
            }
		});
		GridBagConstraints gbc_applyButton = new GridBagConstraints();
		gbc_applyButton.insets = new Insets(0, 0, 5, 5);
		gbc_applyButton.gridx = 0;
		gbc_applyButton.gridy = 0;
		optionsPanel.add(applyButton, gbc_applyButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frameRenameFileWindow.dispose();
            }
		});
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.insets = new Insets(0, 5, 5, 0);
		gbc_cancelButton.gridx = 1;
		gbc_cancelButton.gridy = 0;
		optionsPanel.add(cancelButton, gbc_cancelButton);
	}

}
