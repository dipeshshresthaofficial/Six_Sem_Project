

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

//cypher import

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;

public class Checkout {

	private JFrame frame;
	private JTextField cardNoField;
	private JTextField expDateField;
	private JTextField cvvField;
	String cardNo="",cardExpDate="",cardCvv="";
	
	PreparedStatement prepStmt=null; //This is for using bind variable concept for accessing values from database
	
//	cypher declaration
	 byte[] cipherText;
	 String[] encryptedTextArr;
	 String[] decryptedTextArr;

	/**
	 * Launch the application.
	 * @param string 
	 */
	public static void main(String username, int total) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Checkout window = new Checkout(username,total);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param string 
	 * @param total 
	 */
	public Checkout(String username, int total) {
		initialize(username,total);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param string 
	 * @param total 
	 */
	private void initialize(String username, int total) {
		frame = new JFrame();
		frame.setBounds(100, 100, 605, 379);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel cardNoLabel = new JLabel("Card Number");
		cardNoLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cardNoLabel.setBounds(39, 34, 104, 26);
		frame.getContentPane().add(cardNoLabel);
		
		cardNoField = new JTextField();
		cardNoField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				cardNo= cardNoField.getText().toString();
				
			}
		});
		cardNoField.setBounds(198, 33, 321, 26);
		frame.getContentPane().add(cardNoField);
		cardNoField.setColumns(10);
		
		JLabel expDateLabel = new JLabel("Exp. Date");
		expDateLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		expDateLabel.setBounds(39, 106, 104, 26);
		frame.getContentPane().add(expDateLabel);
		
		expDateField = new JTextField();
		expDateField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				cardExpDate= expDateField.getText().toString();
			}
		});
		
		expDateField.setBounds(170, 106, 141, 26);
		frame.getContentPane().add(expDateField);
		expDateField.setColumns(10);
		
		JLabel cvvLabel = new JLabel("CVV");
		cvvLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cvvLabel.setBounds(382, 106, 49, 26);
		frame.getContentPane().add(cvvLabel);
		
		cvvField = new JTextField();
		cvvField.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
				cardCvv = cvvField.getText().toString();
			}
			
		});
		cvvField.setBounds(451, 108, 97, 26);
		frame.getContentPane().add(cvvField);
		cvvField.setColumns(10);
		
		JButton confirmBtn = new JButton("Confirm");
		confirmBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
					JOptionPane.showMessageDialog(null, "Please wait your payment is being validated....");
					try {
						Thread.sleep(250);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null, "Payment of " + total + " Successful. Thank You!");				
				
			}

			
		});
		confirmBtn.setForeground(Color.RED);
		confirmBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		confirmBtn.setBounds(227, 206, 135, 35);
		frame.getContentPane().add(confirmBtn);
		
		//Encrypt function 
		JButton encryptBtn = new JButton("Encrypt");
		encryptBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String[] cardDetailsArr= {};
				cardDetailsArr[0]= cardNo;
				cardDetailsArr[1]= cardExpDate;
				cardDetailsArr[2]= cardCvv;
				
				
				for(int i=0;i<3;i++) {
				
					System.out.println(cardDetailsArr[i]);
					try {
						encryptedTextArr[i]= encrypt(cardDetailsArr[i]);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
					
					try {
						
						
						Class.forName("org.sqlite.JDBC");  
						Connection con=DriverManager.getConnection( 
						"jdbc:sqlite:./Database/lowes.db");  
						//here dipesh is database name, root is username and password  
						Statement stmt=con.createStatement();  
						//ResultSet rs1=stmt.executeQuery("create table if not exists customer(F_name varchar(20),L_name varchar(20),Mobile varchar(10))");  
						
											
						ResultSet rs=stmt.executeQuery("insert into card_details (Card_no,Exp_date,Cvv,Cust_id) values('"+encryptedTextArr[0]+"','"+encryptedTextArr[1]+"','"+encryptedTextArr[2]+"','"+username+"')");
//						while(rs.next())  
//						System.out.println(rs.getInt(1)+"  "+rs.getString(2));  
						con.close();  
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				
			}

			private String encrypt(String string) throws Exception {
				// TODO Auto-generated method stub
				
				//Creating a Signature object
		          Signature sign = Signature.getInstance("SHA256withRSA");
		         
		          //Creating KeyPair generator object
		          KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		         
		          //Initializing the key pair generator
		          keyPairGen.initialize(2048);
		         
		          //Generating the pair of keys
		          KeyPair pair = keyPairGen.generateKeyPair();      
		       
		          //Creating a Cipher object
		          Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		           
		          //Initializing a Cipher object
		          cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());
		         
		          //Adding data to the cipher
		          byte[] input = string.getBytes();    
		          cipher.update(input);
		         
		          //encrypting the data
		          cipherText = cipher.doFinal(); 
//		   		Displaying the encrypted form of data
		          System.out.println(new String(cipherText,"UTF8"));
		         
		          return(new String(cipherText,"UTF8"));  
				
				
			}
		});
		encryptBtn.setBounds(39, 206, 118, 35);
		frame.getContentPane().add(encryptBtn);
		
// Decrypt Function
		JButton decryptBtn = new JButton("Decrypt");
		decryptBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String[] encryptedCardDetailsFromDbArr= {};

					try {
												
						Class.forName("org.sqlite.JDBC");  
						Connection con=DriverManager.getConnection( 
						"jdbc:sqlite:./Database/lowes.db");  
						//here dipesh is database name, root is username and password  
						Statement stmt=con.createStatement();  
						//ResultSet rs1=stmt.executeQuery("create table if not exists customer(F_name varchar(20),L_name varchar(20),Mobile varchar(10))");  
						
											
						String select="select Card_no,Exp_date,Cvv from card_details where Cust_id=?";
						prepStmt = con.prepareStatement(select);
						prepStmt.setString(1,username);
						ResultSet rs2=prepStmt.executeQuery();
						
//Retrived database values are put into the variables and displaying				
						while(rs2.next())
						{
							encryptedCardDetailsFromDbArr[0]=rs2.getString("Card_no");
							encryptedCardDetailsFromDbArr[1]=rs2.getString("Exp_date");
							encryptedCardDetailsFromDbArr[2]=rs2.getString("Cvv");
						}
						con.close();  
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

			
			
			for(int i=0;i<3;i++) {
				
				try {
						decryptedTextArr[i]= decrypt(encryptedCardDetailsFromDbArr[i]);
					} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
					}
				
				}
			
			cardNoField.setText(decryptedTextArr[0]);
			expDateField.setText(decryptedTextArr[1]);
			cvvField.setText(decryptedTextArr[2]);
			
			}

			private String decrypt(String string) throws Exception {
				// TODO Auto-generated method stub
				
				//Creating a Signature object
		          Signature sign = Signature.getInstance("SHA256withRSA");
		         
		          //Creating KeyPair generator object
		          KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		         
		          //Initializing the key pair generator
		          keyPairGen.initialize(2048);
		         
		          //Generating the pair of keys
		          KeyPair pair = keyPairGen.generateKeyPair();      
		       
		          //Creating a Cipher object
		          Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		           
		          //Initializing a Cipher object
		          cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());
		         
//		          //Adding data to the cipher
//		          byte[] input = string.getBytes();    
//		          cipher.update(input);
		         
//		          //encrypting the data
//		          cipherText = cipher.doFinal(); 
////		   		Displaying the encrypted form of data
//		          System.out.println(new String(cipherText,"UTF8"));
				
				
				System.out.println("Decripting..."+pair.getPrivate());
	              PrivateKey  key1=pair.getPrivate();
	              cipher.init(Cipher.DECRYPT_MODE, key1);
	              byte[] dec = cipher.doFinal(cipherText);
//	              Displaying the contents after decrypting the encrypted data 
	              System.out.println(new String(dec));
	             
	              return (new String(dec));
			}
		});
		decryptBtn.setBounds(427, 208, 118, 35);
		frame.getContentPane().add(decryptBtn);
		
	}
}
