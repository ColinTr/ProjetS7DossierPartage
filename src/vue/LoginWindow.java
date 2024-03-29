package vue;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import controleur.UserDAO;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Font;

public class LoginWindow {

	private final Color blueColor = new Color(50, 100, 150);
	private final Color colorHover = new Color(169, 171, 184);
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	private JFrame frameLoginWindow;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
					window.frameLoginWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginWindow() {
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
        
		frameLoginWindow = new JFrame();
		frameLoginWindow.getContentPane().setBackground(Color.BLACK);
		ImageIcon logo = new ImageIcon(LoginWindow.class.getClassLoader().getResource("ressources/file-explorer-icon.png"));
		frameLoginWindow.setIconImage(logo.getImage());
		frameLoginWindow.setTitle("Login");
		frameLoginWindow.setBounds(dim.width/2-530/2, dim.height/2-250/2, 530, 250);
		frameLoginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon(LoginWindow.class.getClassLoader().getResource("ressources/user_icon.png"));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{485, 0};
        gridBagLayout.rowHeights = new int[]{250, 0};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        frameLoginWindow.getContentPane().setLayout(gridBagLayout);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.BLACK);
		GridBagConstraints gbc_mainPanel = new GridBagConstraints();
		gbc_mainPanel.fill = GridBagConstraints.BOTH;
		gbc_mainPanel.gridx = 0;
		gbc_mainPanel.gridy = 0;
		frameLoginWindow.getContentPane().add(mainPanel, gbc_mainPanel);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{84, 345};
		gbl_mainPanel.rowHeights = new int[]{250, 0};
		gbl_mainPanel.columnWeights = new double[]{1.0, 1.0};
		gbl_mainPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		JPanel leftPanel = new JPanel();
		GridBagConstraints gbc_leftPanel = new GridBagConstraints();
		gbc_leftPanel.fill = GridBagConstraints.BOTH;
		gbc_leftPanel.gridx = 0;
		gbc_leftPanel.gridy = 0;
		mainPanel.add(leftPanel, gbc_leftPanel);
		leftPanel.setBackground(blueColor);
		GridBagLayout gbl_leftPanel = new GridBagLayout();
		gbl_leftPanel.columnWidths = new int[]{152, 0};
		gbl_leftPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_leftPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_leftPanel.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		leftPanel.setLayout(gbl_leftPanel);
		
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
		gbc_settingsButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_settingsButton.gridx = 0;
		gbc_settingsButton.gridy = 0;
		leftPanel.add(settingsButton, gbc_settingsButton);
		
		JLabel user_icon_image = new JLabel("", SwingConstants.CENTER);
		user_icon_image.setIcon(img);
		GridBagConstraints gbc_user_icon_image = new GridBagConstraints();
		gbc_user_icon_image.gridx = 0;
		gbc_user_icon_image.gridy = 1;
		leftPanel.add(user_icon_image, gbc_user_icon_image);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegisterWindow.main(null);
				frameLoginWindow.dispose();
			}
		});
		
		GridBagConstraints gbc_btnRegister = new GridBagConstraints();
		gbc_btnRegister.insets = new Insets(0, 0, 10, 0);
		gbc_btnRegister.gridx = 0;
		gbc_btnRegister.gridy = 2;
		leftPanel.add(btnRegister, gbc_btnRegister);
		
		JPanel rightPanel = new JPanel();
		GridBagConstraints gbc_rightPanel = new GridBagConstraints();
		gbc_rightPanel.fill = GridBagConstraints.BOTH;
		gbc_rightPanel.gridx = 1;
		gbc_rightPanel.gridy = 0;
		mainPanel.add(rightPanel, gbc_rightPanel);
		rightPanel.setBackground(Color.BLACK);
		GridBagLayout gbl_rightPanel = new GridBagLayout();
		gbl_rightPanel.columnWidths = new int[]{341, 0};
		gbl_rightPanel.rowHeights = new int[]{20, 0, 0, 0, 0, 0};
		gbl_rightPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_rightPanel.rowWeights = new double[]{0.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		rightPanel.setLayout(gbl_rightPanel);
		
		JLabel login_label = new JLabel("LOGIN");
		login_label.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 18));
		login_label.setHorizontalAlignment(SwingConstants.CENTER);
		login_label.setForeground(Color.WHITE);
		
		GridBagConstraints gbc_login_label = new GridBagConstraints();
		gbc_login_label.anchor = GridBagConstraints.SOUTH;
		gbc_login_label.insets = new Insets(10, 0, 5, 0);
		gbc_login_label.gridx = 0;
		gbc_login_label.gridy = 0;
		rightPanel.add(login_label, gbc_login_label);
		JTextField usernameField = new JTextField(15);
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
		gbc_usernameField.insets = new Insets(0, 0, 5, 0);
		gbc_usernameField.gridx = 0;
		gbc_usernameField.gridy = 1;
		rightPanel.add(usernameField, gbc_usernameField);
		JPasswordField passwordField = new JPasswordField(15);
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
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridx = 0;
		gbc_passwordField.gridy = 2;
		rightPanel.add(passwordField, gbc_passwordField);
		
		JLabel errorMessageLabel = new JLabel(" ");
		errorMessageLabel.setForeground(Color.RED);
		GridBagConstraints gbc_errorMessageLabel = new GridBagConstraints();
		gbc_errorMessageLabel.insets = new Insets(0, 0, 5, 0);
		gbc_errorMessageLabel.gridx = 0;
		gbc_errorMessageLabel.gridy = 3;
		rightPanel.add(errorMessageLabel, gbc_errorMessageLabel);
		
		JButton connectionButton = new JButton("Connection");
        connectionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = usernameField.getText();
				String password = String.valueOf(passwordField.getPassword());
				if(username.equals("root") && password.equals("root")) {
					FolderExplorerWindow.main(null);
					frameLoginWindow.dispose();
				}
				else if(UserDAO.testAuth(username, password)) {
					String [] args = new String[2];
				    args[0] = username;
					FolderExplorerWindow.main(args);
					frameLoginWindow.dispose();
				}
				else {
					errorMessageLabel.setText("Error : User doesn't exist in database.");
				}
			}
		});
		GridBagConstraints gbc_connectionButton = new GridBagConstraints();
		gbc_connectionButton.gridx = 0;
		gbc_connectionButton.gridy = 4;
		rightPanel.add(connectionButton, gbc_connectionButton);
	}
}