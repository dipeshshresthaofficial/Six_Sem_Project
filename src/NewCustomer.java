import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class NewCustomer {

	private JFrame frame;
	private JTextField newCustFname;
	private JTextField newCustLname;
	
	private JTextField dobField;
	private JTextField emailField;
	private JTextField pincodeField;
	private JTextField addressField;
	String custFname="",custLname="",custEmail="",custDob="",custPincode="",custAddress="", custUsername="";
	private JTextField usernameField;

	/**
	 * Launch the application.
	 */
	public static void main(String mobileEntered) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewCustomer window = new NewCustomer(mobileEntered);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param mobileEntered 
	 */
	public NewCustomer(String mobileEntered) {
		initialize(mobileEntered);
	}

	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String mobileEntered) {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 455, 445);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel newCustFnameLabel = new JLabel("First Name:");
		newCustFnameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		newCustFnameLabel.setBounds(36, 74, 92, 25);
		frame.getContentPane().add(newCustFnameLabel);
		
		JLabel newCustLnameLabel = new JLabel("Last Name:");
		newCustLnameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		newCustLnameLabel.setBounds(36, 112, 92, 25);
		frame.getContentPane().add(newCustLnameLabel);
		
		newCustFname = new JTextField();
		newCustFname.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				
				custFname=newCustFname.getText();
			}
		});
		newCustFname.setBounds(172, 76, 158, 25);
		frame.getContentPane().add(newCustFname);
		newCustFname.setColumns(10);
		
		newCustLname = new JTextField();
		newCustLname.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				
				custLname=newCustLname.getText();
			}
		});
		newCustLname.setColumns(10);
		newCustLname.setBounds(172, 114, 158, 25);
		frame.getContentPane().add(newCustLname);
		
		
		
		dobField = new JTextField();
		dobField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				custDob= dobField.getText().toString();
				
			}
		});
		dobField.setColumns(10);
		dobField.setBounds(172, 194, 158, 25);
		frame.getContentPane().add(dobField);
		
		JLabel dobLabel = new JLabel("DOB:");
		dobLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dobLabel.setBounds(36, 192, 92, 25);
		frame.getContentPane().add(dobLabel);
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		emailLabel.setBounds(36, 154, 92, 25);
		frame.getContentPane().add(emailLabel);
		
		emailField = new JTextField();
		emailField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				custEmail= emailField.getText().toString();
			}
		});
		emailField.setColumns(10);
		emailField.setBounds(172, 156, 158, 25);
		frame.getContentPane().add(emailField);
		
		JLabel pincodeLabel = new JLabel("Pincode:");
		pincodeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pincodeLabel.setBounds(36, 230, 92, 25);
		frame.getContentPane().add(pincodeLabel);
		
		pincodeField = new JTextField();
		pincodeField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				custPincode = pincodeField.getText().toString();
				
			}
		});
		pincodeField.setColumns(10);
		pincodeField.setBounds(172, 232, 158, 25);
		frame.getContentPane().add(pincodeField);
		
		JLabel addressLabel = new JLabel("Address:");
		addressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addressLabel.setBounds(36, 268, 92, 25);
		frame.getContentPane().add(addressLabel);
		
		addressField = new JTextField();
		addressField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				custAddress = addressField.getText().toString();
			}
		});
		addressField.setColumns(10);
		addressField.setBounds(172, 270, 158, 25);
		frame.getContentPane().add(addressField);
		
		JLabel lblNewLabel = new JLabel("Welcome, Let us know you!");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(48, 11, 339, 32);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setBounds(36, 304, 92, 25);
		frame.getContentPane().add(usernameLabel);
		
		usernameField = new JTextField();
		usernameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				custUsername= usernameField.getText().toString();
			}
		});
		usernameField.setColumns(10);
		usernameField.setBounds(172, 306, 158, 25);
		frame.getContentPane().add(usernameField);
		
		JButton signUpBtn = new JButton("Done");
		signUpBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				 
				try{  
					Class.forName("org.sqlite.JDBC");  
					Connection con=DriverManager.getConnection( 
					"jdbc:sqlite:./Database/lowes.db");  
					//here dipesh is database name, root is username and password  
					Statement stmt=con.createStatement();  
					//ResultSet rs1=stmt.executeQuery("create table if not exists customer(F_name varchar(20),L_name varchar(20),Mobile varchar(10))");  
					
										
					ResultSet rs=stmt.executeQuery("insert into customer values('"+custUsername+"','"+custFname+"','"+custLname+"','"+mobileEntered+"','"+custEmail+"','"+custDob+"','"+custPincode+"','"+custAddress+"')");
//					while(rs.next())  
//					System.out.println(rs.getInt(1)+"  "+rs.getString(2));  
					con.close();  
					
					}catch(Exception e1){ System.out.println(e1);}
				
					JOptionPane.showMessageDialog(null, "Thank you, "+custFname+" Now you can login.");
					frame.dispose();
					}  
			
		});
		signUpBtn.setBounds(48, 354, 339, 25);
		frame.getContentPane().add(signUpBtn);
		
		
	}
}
