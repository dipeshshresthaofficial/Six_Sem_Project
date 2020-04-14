import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.UIManager;

import org.apache.log4j.BasicConfigurator;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.SwingConstants;
import java.awt.Font;

public class Login {

	private JFrame frame;
	private JTextField mobileField;
	String mobileEntered="";
	String originalPwd = "admin";
	String dbFname="";
	String dbMobileNo="";
	String username="";
	
	PreparedStatement prepStmt=null; //This is for using bind variable concept for accessing values from database
	boolean result;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		BasicConfigurator.configure();
		
			
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		Database();
		initialize();
	}

	private void Database() {
		// TODO Auto-generated method stub
		try{  
			Class.forName("org.sqlite.JDBC");  
			Connection con=DriverManager.getConnection( 
			"jdbc:sqlite:./Database/lowes.db");  
			//here dipesh is database name, root is username and password  
			Statement stmt=con.createStatement();  
			
				String s1 = "create table if not exists customer(C_id varchar(20) PRIMARY KEY NOT NULL, F_name varchar(20) NOT NULL,L_name varchar(20) NOT NULL,Mobile varchar(10) NOT NULL, Email varchar(30), Dob date, Pincode INTEGER, Address varchar(20))";
			
			
				String s2 = "create table if not exists card_type(id TEXT PRIMARY KEY NOT NULL, card_type TEXT UNIQUE NOT NULL)";
			
			
				String s3 = "create table if not exists card_details(id INTEGER PRIMARY KEY AUTOINCREMENT, Card_no BLOB NOT NULL UNIQUE, Exp_date BLOB NOT NULL, Cvv BLOB NOT NULL,Cust_id varchar(20), Card_type_id TEXT, CONSTRAINT FK2 FOREIGN KEY (Cust_id) references customer (C_id) ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT FK3 FOREIGN KEY (Card_type_id) references card_type (id) ON DELETE CASCADE ON UPDATE CASCADE)";
			
			
				String s4 = "create table if not exists orders(Order_no TEXT PRIMARY KEY NOT NULL, Item_id TEXT UNIQUE, Item_name TEXT, Qty INEGER, Price INTEGER, Customer_id varchar(20), CONSTRAINT FK4 FOREIGN KEY (Customer_id) references customer (C_id) ON DELETE CASCADE ON UPDATE CASCADE)";
			
			
				String s5 = "create table if not exists transaction_record(T_id TEXT PRIMARY KEY NOT NULL, T_amount INTEGER, Payment_mode TEXT, T_date TEXT, T_time TEXT, Status TEXT, O_no TEXT, CONSTRAINT FK5 FOREIGN KEY (O_no) references orders (Order_no) ON DELETE CASCADE ON UPDATE CASCADE)";
				
				stmt.executeUpdate(s1);
				stmt.executeUpdate(s2);
				stmt.executeUpdate(s3);
				stmt.executeUpdate(s4);
				stmt.executeUpdate(s5);
				
			
				
			
			con.close();  
			}catch(Exception e){ System.out.println(e);}  
  
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("Button.background"));
		frame.setBounds(100, 100, 570, 314);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel mobileLabel = new JLabel("Enter Your Mobile Number");
		mobileLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		mobileLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mobileLabel.setBackground(Color.LIGHT_GRAY);
		mobileLabel.setBounds(315, 51, 204, 36);
		frame.getContentPane().add(mobileLabel);
		
		mobileField = new JTextField();
		mobileField.setBounds(326, 98, 187, 29);
		frame.getContentPane().add(mobileField);
		mobileField.setColumns(10);
		
		JButton proceedBtn = new JButton("Proceed Securely");
		proceedBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		proceedBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mobileEntered= mobileField.getText();
				System.out.println(mobileEntered);

//				checking if the mobile number entered only contains numbers or not through REGULAR EXPRESSION
				if(!((mobileEntered.matches("[0-9]+") && mobileEntered.length()==10))){
					JOptionPane.showMessageDialog(null, "Please enter valid mobile number");
				}
				else {
					
					try{  
						
						Class.forName("org.sqlite.JDBC");  
						Connection con=DriverManager.getConnection( 
						"jdbc:sqlite:./Database/lowes.db");  
						//here dipesh is database name, root is username and password  
						Statement stmt=con.createStatement();  
//						ResultSet rs=stmt.executeQuery("create table if not exists transaction_record(T_id INTEGER PRIMARY KEY AUTOINCREMENT, T_time time NOT NULL, T_Amount INTEGER NOT NULL, Cust_id INTEGER, CONSTRAINT FK1 FOREIGN KEY (Cust_id) references customer (C_id) ON DELETE CASCADE ON UPDATE CASCADE)");
//						ResultSet rs1=stmt.executeQuery("create table if not exists customer(C_id INTEGER PRIMARY KEY NOT NULL, F_name varchar(20) NOT NULL,L_name varchar(20) NOT NULL,Mobile varchar(10) NOT NULL, Email varchar(30), Dob date, Pincode INTEGER, Address varchar(20))");  
						
//Using BIND VAIRABLE concept for accessing values from database through a specific mobile number present in a VARIABLE
						String select="select C_id,F_name,L_name,Mobile from customer where Mobile=?";
						prepStmt = con.prepareStatement(select);
						prepStmt.setString(1,mobileEntered);
						ResultSet rs2=prepStmt.executeQuery();
						
//Retrived database values are put into the variables and displaying				
						while(rs2.next())
						{
							username=rs2.getString("C_id").toString();
							dbFname=rs2.getString("F_name");
							String lname=rs2.getString("L_name");
							dbMobileNo=rs2.getString("Mobile");
							System.out.println(dbMobileNo);
							System.out.println(dbFname+" "+lname+"  "+dbMobileNo);
						}
							  
						con.close();  
						}catch(Exception e1){ System.out.println(e1);}  
					
					if(mobileEntered.matches(dbMobileNo)) {
//						JOptionPane.showMessageDialog(null, "Correct Password..");
						
						frame.dispose();
						Home h1= new Home(username,dbFname);
						h1.main(username,dbFname);
					}
					else {
						 
						NewCustomer n1=new NewCustomer(mobileEntered);
						n1.main(mobileEntered);
					}
				}
				
			}
		});
		proceedBtn.setBounds(360, 146, 127, 29);
		frame.getContentPane().add(proceedBtn);
		
		JLabel logoImgLabel = new JLabel("");
		logoImgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		Image img = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		Image modifiedImg = img.getScaledInstance(300, 220, java.awt.Image.SCALE_SMOOTH);
		
		logoImgLabel.setIcon(new ImageIcon(modifiedImg));
		logoImgLabel.setBounds(7, 38, 300, 220);
		frame.getContentPane().add(logoImgLabel);
		
		JButton guestLoginBtn = new JButton("Guest Login");
		guestLoginBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Menu m1 = new Menu();
				m1.main(null);
			}
		});
		guestLoginBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		guestLoginBtn.setBounds(360, 193, 127, 29);
		frame.getContentPane().add(guestLoginBtn);
	}
}
