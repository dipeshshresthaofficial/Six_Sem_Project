

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
import java.security.PublicKey;
import java.security.Signature;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;

public class Checkout extends Homes {

	private JFrame frame;
	private JTextField cardNoField;
	private JTextField expDateField;
	private JTextField cvvField;
	String cardNo="",cardExpDate="",cardCvv="";
	String[] cardDetailsArr= new String[3];
	String[] encryptedCardDetailsFromDbArr= new String[3];
	String cId = "";
	
	PreparedStatement prepStmt=null; //This is for using bind variable concept for accessing values from database
	
//	cypher declaration
	 byte[] cipherText;
	 String[] encryptedTextArr= new String[3];
	 String[] decryptedTextArr= new String[3];

	/**
	 * Launch the application.
	 * @param string 
	 */
	public static void main(String string, int total, String orderId) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Checkout window = new Checkout(string,total, orderId);
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
	 * @param fixedOrderId 
	 */
	public Checkout(String string, int total, String orderNo) {
		initialize(string,total,orderNo);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param string 
	 * @param total 
	 * @param orderId 
	 */
	private void initialize(String string, int total, String orderNo) {
		
		String cardForAdding = this.cardForAdding;
		System.out.println(cardForAdding);
		System.out.println(string);
		String dbFname= this.temp;
		String username= this.temp;
		frame = new JFrame();
		frame.setBounds(100, 100, 605, 379);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		cardNoField.setBounds(170, 33, 378, 26);
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
				
					if(string.equals(cardForAdding)) {
						
						JOptionPane.showMessageDialog(null, "Please press \"Add\" button for saving your card.");
						
					}else{
												
						
						JOptionPane.showMessageDialog(null, "Please wait your payment is being validated....");
						try {
							Thread.sleep(250);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						JOptionPane.showMessageDialog(null, "Payment of " + total + " Successful for ORDER ID: "+orderNo+". Thank You!");		
						
						Calendar c = Calendar.getInstance();
						
						String date = c.get(Calendar.YEAR)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.DATE);
						String time = c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND);
						
//						Inserting the transaction record details in database
						storeTransaction(total,"card", date, time,"success", orderNo);
						
					}
					
				
			}

			private void storeTransaction(int total, String payment_mode, String date, String time, String status,String orderNo) {
				// TODO Auto-generated method stub
				
				
				String fixedTransactionId= "";
				
				try {
					
					Class.forName("org.sqlite.JDBC");
					Connection con=DriverManager.getConnection( 
							"jdbc:sqlite:./Database/lowes.db");  
							Statement stmt=con.createStatement();  
							
							String select="select C_id from customer where Status=?";
							prepStmt = con.prepareStatement(select);
							prepStmt.setString(1,"active");
							ResultSet rs2=prepStmt.executeQuery();
							//Retrived database values are put into the variables and displaying				
							while(rs2.next())
							{
								cId=rs2.getString("C_id").toString();
								
								System.out.println("C_ID: "+cId);
							}
							
							
							System.out.println(total+ payment_mode+ date+time+status+orderNo);
							
							String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
							Calendar gc = Calendar.getInstance();
										
							String orderId= gc.get(Calendar.YEAR)+months[gc.get(Calendar.MONTH)]+gc.get(Calendar.DATE)+gc.get(Calendar.HOUR)+gc.get(Calendar.MINUTE)+gc.get(Calendar.SECOND);
							fixedTransactionId = orderId.toString();
							
							
							prepStmt = con.prepareStatement("Insert into transaction_record(T_id,T_amount,Payment_mode,T_date,T_time,Status,Order_no,Customer_id) values(?,?,?,?,?,?,?,?)");
							prepStmt.setString(1, fixedTransactionId);
							prepStmt.setInt(2, total);
							prepStmt.setString(3, payment_mode);
							prepStmt.setString(4, date);
							prepStmt.setString(5, time);
							prepStmt.setString(6, status);
							prepStmt.setString(7, orderNo);
							prepStmt.setString(8, cId);
							
							int i = prepStmt.executeUpdate();
							System.out.println(i+" record(s) inserted.");
							
							
							prepStmt = con.prepareStatement("Update customer set Status=? where C_id=?");
							prepStmt.setString(1,"inactive");
							prepStmt.setString(2, cId);
							int ii = prepStmt.executeUpdate();
							System.out.println(ii+" record(s) updated.");
							
							
				}
				catch(Exception e) {
					
					System.out.println(e);
				}
				
			}

			
		});
		confirmBtn.setForeground(Color.RED);
		confirmBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		confirmBtn.setBounds(227, 206, 135, 35);
		frame.getContentPane().add(confirmBtn);
		
		//Encrypt function 
		JButton encryptBtn = new JButton("Encrypt/ Add");
		encryptBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				cardDetailsArr[0]= cardNo;
				cardDetailsArr[1]= cardExpDate;
				cardDetailsArr[2]= cardCvv;
				
				
				for(int i=0;i<3;i++) {
				
					System.out.println(cardDetailsArr[i]);
					try {
						encryptedTextArr[i]= encrypt(cardDetailsArr[i]);
					} 
					catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
				cardNoField.setText(encryptedTextArr[0]);
				expDateField.setText(encryptedTextArr[1]);
				cvvField.setText(encryptedTextArr[2]);
				
				//inserting encrypted data into the database
				
				try{  
					
					String a= encryptedTextArr[0];
					String b= encryptedTextArr[1];
					String c= encryptedTextArr[2];
					Class.forName("org.sqlite.JDBC");  
					Connection con=DriverManager.getConnection( 
					"jdbc:sqlite:./Database/lowes.db");  
					Statement stmt=con.createStatement();  
					
					String rs="insert into card_details (Card_no,Exp_date,Cvv,Cust_id,Card_type_id) values(?,?,?,?,?)";
					
					prepStmt= con.prepareStatement(rs);
					prepStmt.setString(1,a);
					prepStmt.setString(2, b);
					prepStmt.setString(3, c);
					prepStmt.setString(4, cId);
					
					if(cardNo.charAt(0)=='5') {
						prepStmt.setString(5, "M");
					}
					else {
						
						prepStmt.setString(5, "V");
					}
					
					prepStmt.executeUpdate();
					
					con.close();  
					}
				catch(Exception e1)
				{ 
					System.out.println(e1);
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
//				JButton decryptBtn = new JButton("Decrypt");
//				decryptBtn.addMouseListener(new MouseAdapter() {
//					@Override
//					public void mouseClicked(MouseEvent e) {
//						
//						
//
//							try {
//														
//								Class.forName("org.sqlite.JDBC");  
//								Connection con=DriverManager.getConnection( 
//								"jdbc:sqlite:./Database/lowes.db");  
//								//here dipesh is database name, root is username and password  
//								Statement stmt=con.createStatement();  
//								//ResultSet rs1=stmt.executeQuery("create table if not exists customer(F_name varchar(20),L_name varchar(20),Mobile varchar(10))");  
//								
//													
//								String select="select Card_no,Exp_date,Cvv from card_details where Cust_id=?";
//								prepStmt = con.prepareStatement(select);
//								prepStmt.setString(1,username);
//								ResultSet rs2=prepStmt.executeQuery();
//								
//		//Retrived database values are put into the variables and displaying				
//								while(rs2.next())
//								{
//									encryptedCardDetailsFromDbArr[0]=rs2.getString("Card_no");
//									encryptedCardDetailsFromDbArr[1]=rs2.getString("Exp_date");
//									encryptedCardDetailsFromDbArr[2]=rs2.getString("Cvv");
//								}
//								con.close();  
//							} catch (Exception e1) {
//								// TODO Auto-generated catch block
//								e1.printStackTrace();
//							}
//
//					
//					
//					for(int i=0;i<3;i++) {
//						
//						try {
//								decryptedTextArr[i]= decrypt(encryptedCardDetailsFromDbArr[i]).toString();
//							} catch (Exception e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//							
//							}
//						
//						}
//					
//					cardNoField.setText(decryptedTextArr[0]);
//					expDateField.setText(decryptedTextArr[1]);
//					cvvField.setText(decryptedTextArr[2]);
//					
//					}
//
//					private String decrypt(String string) throws Exception {
//						// TODO Auto-generated method stub
//						
//								
//						//Creating a Signature object
//					    Signature sign = Signature.getInstance("SHA256withRSA");
//					    
//				        //Creating KeyPair generator object
//				          KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
//				         
//				        //Initializing the KeyPairGenerator
//				          keyPairGen.initialize(2048);
//				         
//				        //Generate the pair of keys
//				          KeyPair pair = keyPairGen.generateKeyPair();      
//				          
//				        //Getting the public key from the key pair
//				          PublicKey publicKey = pair.getPublic();
//				       
//				        //Creating a Cipher object
//				          Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//				          
//				        //Initializing the same cipher for decryption
//				          cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());
//				     
////				          byte[] b = string.getBytes();
//				           
//				          
//				        //Decrypting the text
//				          byte[] decipheredText = cipher.doFinal(string.getBytes());
//				          
//				          System.out.println(new String(decipheredText));
//				          
////				          System.out.println("\n\n");
//////				   		Displaying the encrypted form of data
////				          System.out.println(new String(b,"UTF8"));
////						
////						
////						System.out.println("\nDecripting..."+pair.getPrivate());
//////			              PrivateKey  key1=pair.getPrivate();
////			              cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());
////			              
////			              byte[] dec = cipher.doFinal(b);
//////			              Displaying the contents after decrypting the encrypted data 
////			              System.out.println(new String(dec));
//			             
//			              return (new String(decipheredText));
//					}
//				});
//				decryptBtn.setBounds(427, 208, 118, 35);
//				frame.getContentPane().add(decryptBtn);
	}
}
