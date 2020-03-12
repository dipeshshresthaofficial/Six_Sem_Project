import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;

public class MainLogin {

	private JFrame frame;
	private JTextField textField;
	private JLabel pwdLabel;
	private JTextField pwdField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainLogin window = new MainLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 469, 457);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel usrnameLabel = new JLabel("Username:");
		usrnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		usrnameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		usrnameLabel.setBounds(32, 65, 103, 32);
		frame.getContentPane().add(usrnameLabel);
		
		textField = new JTextField();
		textField.setBounds(32, 108, 243, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		pwdLabel = new JLabel("Password:");
		pwdLabel.setHorizontalAlignment(SwingConstants.LEFT);
		pwdLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pwdLabel.setBounds(32, 159, 103, 32);
		frame.getContentPane().add(pwdLabel);
		
		pwdField = new JTextField();
		pwdField.setColumns(10);
		pwdField.setBounds(32, 202, 243, 32);
		frame.getContentPane().add(pwdField);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		loginBtn.setBounds(32, 257, 97, 41);
		frame.getContentPane().add(loginBtn);
		
		JButton signUpBtn = new JButton("Sign up");
		signUpBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		signUpBtn.setBounds(150, 257, 97, 41);
		frame.getContentPane().add(signUpBtn);
		
		JButton guestLoginBtn = new JButton("Guest Login");
		guestLoginBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		guestLoginBtn.setBounds(32, 328, 243, 32);
		frame.getContentPane().add(guestLoginBtn);
	}
}
