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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import controleur.UserDAO;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;

public class RegisterWindow {
	
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

	private JFrame frameRegisterWindow;
	private JPasswordField passwordField;
	private JTextField usernameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterWindow window = new RegisterWindow();
					window.frameRegisterWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegisterWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        frameRegisterWindow = new JFrame();
		ImageIcon logo = new ImageIcon(LoginWindow.class.getClassLoader().getResource("ressources/file-explorer-icon.png"));
		frameRegisterWindow.setIconImage(logo.getImage());
		frameRegisterWindow.setTitle("Register");
		frameRegisterWindow.getContentPane().setBackground(Color.WHITE);
		frameRegisterWindow.setBounds(dim.width/2-330/2, dim.height/2-180/2, 330, 180);
		frameRegisterWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{434, 0};
		gridBagLayout.rowHeights = new int[]{10, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		frameRegisterWindow.getContentPane().setLayout(gridBagLayout);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_mainPanel = new GridBagConstraints();
		gbc_mainPanel.fill = GridBagConstraints.BOTH;
		gbc_mainPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_mainPanel.gridx = 0;
		gbc_mainPanel.gridy = 0;
		frameRegisterWindow.getContentPane().add(mainPanel, gbc_mainPanel);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0, 0};
		gbl_mainPanel.rowHeights = new int[]{0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_topPanel = new GridBagConstraints();
		gbc_topPanel.fill = GridBagConstraints.BOTH;
		gbc_topPanel.insets = new Insets(0, 0, 5, 0);
		gbc_topPanel.gridx = 0;
		gbc_topPanel.gridy = 0;
		mainPanel.add(topPanel, gbc_topPanel);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[]{0, 0, 0};
		gbl_topPanel.rowHeights = new int[]{0, 0};
		gbl_topPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_topPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		topPanel.setLayout(gbl_topPanel);
		
		JLabel titleLabel = new JLabel("Register a new user :");
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.insets = new Insets(0, 5, 0, 0);
		gbc_titleLabel.fill = GridBagConstraints.BOTH;
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;
		topPanel.add(titleLabel, gbc_titleLabel);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);
		errorLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_errorLabel = new GridBagConstraints();
		gbc_errorLabel.insets = new Insets(0, 0, 0, 10);
		gbc_errorLabel.anchor = GridBagConstraints.EAST;
		gbc_errorLabel.fill = GridBagConstraints.VERTICAL;
		gbc_errorLabel.gridx = 1;
		gbc_errorLabel.gridy = 0;
		topPanel.add(errorLabel, gbc_errorLabel);
		
		JPanel bodyPanel = new JPanel();
		bodyPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_bodyPanel = new GridBagConstraints();
		gbc_bodyPanel.insets = new Insets(0, 15, 0, 15);
		gbc_bodyPanel.weighty = 5.0;
		gbc_bodyPanel.fill = GridBagConstraints.BOTH;
		gbc_bodyPanel.gridx = 0;
		gbc_bodyPanel.gridy = 1;
		mainPanel.add(bodyPanel, gbc_bodyPanel);
		GridBagLayout gbl_bodyPanel = new GridBagLayout();
		gbl_bodyPanel.columnWidths = new int[]{0, 0, 0};
		gbl_bodyPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_bodyPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_bodyPanel.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		bodyPanel.setLayout(gbl_bodyPanel);
		
		JLabel lblUsername = new JLabel("Username :");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.WEST;
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 0;
		bodyPanel.add(lblUsername, gbc_lblUsername);
		
		usernameField = new JTextField(15);
		usernameField.setText("Username");
		usernameField.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0){
				if(usernameField.getText().equals("Username")){
					usernameField.setText("");
				}
			}
			
			@Override
			public void focusLost(FocusEvent arg0){
				if(usernameField.getText().isEmpty()){
					usernameField.setText("Username");
				}
			}
		});
		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameField.gridx = 1;
		gbc_usernameField.gridy = 0;
		bodyPanel.add(usernameField, gbc_usernameField);
		usernameField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password :");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 1;
		bodyPanel.add(lblPassword, gbc_lblPassword);
		
		passwordField = new JPasswordField(15);
		passwordField.setText("********");
		passwordField.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0){
				if(String.valueOf(passwordField.getPassword()).equals("********")){
					passwordField.setText("");
				}
			}
			
			@Override
			public void focusLost(FocusEvent arg0){
				if(String.valueOf(passwordField.getPassword()).isEmpty()){
					passwordField.setText("********");
				}
			}
		});
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 1;
		bodyPanel.add(passwordField, gbc_passwordField);
		
		JButton btnCreateAccount = new JButton("Create account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//First we test if the user entered a password
				if( String.valueOf(passwordField.getPassword()).equals("********") ){
					errorLabel.setText("Please enter a password.");
				}
				
				//Then we test if the user entered a username
				else if(usernameField.getText().equals("Username")){
					errorLabel.setText("Please enter a username.");
				}
				
				//Finally we try to create a user
				else {
					String username = usernameField.getText();
					String password = String.valueOf(passwordField.getPassword());
					
					//If we could create the user, we go back to the login window
					if(UserDAO.createUser(username, password)) {
						LoginWindow.main(null);
						frameRegisterWindow.dispose();
					}
					//If we couldn't, we display an error message
					else {
						errorLabel.setText("Username already taken.");
					}
				}
				
			}
		});
		GridBagConstraints gbc_btnCreateAccount = new GridBagConstraints();
		gbc_btnCreateAccount.insets = new Insets(0, 0, 0, 10);
		gbc_btnCreateAccount.anchor = GridBagConstraints.WEST;
		gbc_btnCreateAccount.gridx = 0;
		gbc_btnCreateAccount.gridy = 2;
		bodyPanel.add(btnCreateAccount, gbc_btnCreateAccount);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginWindow.main(null);
				frameRegisterWindow.dispose();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 10, 0, 0);
		gbc_btnCancel.anchor = GridBagConstraints.EAST;
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 2;
		bodyPanel.add(btnCancel, gbc_btnCancel);
	}

}