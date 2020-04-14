import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Random;

public class Billing {
	
	private JFrame frame;
	int sum=0;
	private JTextField billAmountField;
	/**
	 * Launch the application.
	 * @param a2 
	 * @param itemPrice 
	 * @param itemName 
	 * @param itemId 
	 */
	public static void main(String[] a2, String[] itemId, String[] itemName, int[] itemQty, int[] itemPrice) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Billing window = new Billing(a2,itemId,itemName,itemQty,itemPrice);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param a2 
	 * @param itemPrice 
	 * @param itemName 
	 * @param itemId 
	 */
	public Billing(String[] a2, String[] itemId, String[] itemName, int[] itemQty, int[] itemPrice) {
		
		storeOrder(itemId,itemName,itemQty,itemPrice);
		initialize(a2,itemPrice);
	}

	private void storeOrder(String[] itemId, String[] itemName, int[] itemQty, int[] itemPrice) {
		// TODO Auto-generated method stub
		PreparedStatement prepStmt=null;
		
		try{  
			Class.forName("org.sqlite.JDBC");  
			Connection con=DriverManager.getConnection( 
			"jdbc:sqlite:./Database/lowes.db");  
			Statement stmt=con.createStatement();  
			
//			generating unique order id
			
			String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
			Calendar gc = Calendar.getInstance();
			
			Random rn = new Random();
						
			String orderId= Integer.toString(rn.nextInt(10))+gc.get(Calendar.YEAR)+months[gc.get(Calendar.MONTH)]+gc.get(Calendar.DATE)+gc.get(Calendar.HOUR)+gc.get(Calendar.MINUTE)+gc.get(Calendar.SECOND);
			
//			Ended generation of order id
			
				for(int x=0;x< itemId.length;x++) {
					
					prepStmt = con.prepareStatement("Insert into orders values(?,?,?,?,?,?)");
					prepStmt.setString(1, orderId);
					prepStmt.setString(2, itemId[x]);
					prepStmt.setString(3, itemName[x]);
					prepStmt.setInt(4, itemQty[x]);
					prepStmt.setInt(5, itemPrice[x]);
					
					
//					prepStmt.setString(6, x);
				}
			
				
			
			con.close();  
			}catch(Exception e){ System.out.println(e);}
		
	}

	/**
	 * Initialize the contents of the frame.
	 * @param a2 
	 */
	private void initialize(String[] a2, int[] a	) {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 534, 466);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea itemList = new JTextArea();
		itemList.setBounds(10, 11, 498, 284);
		frame.getContentPane().add(itemList);
		
		JLabel billAmountLabel = new JLabel("Bill Amount:");
		billAmountLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		billAmountLabel.setForeground(Color.RED);
		billAmountLabel.setBackground(Color.ORANGE);
		billAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		billAmountLabel.setBounds(80, 323, 134, 38);
		frame.getContentPane().add(billAmountLabel);
		
		billAmountField = new JTextField();
		billAmountField.setBounds(244, 326, 216, 38);
		frame.getContentPane().add(billAmountField);
		billAmountField.setColumns(10);
		
//		converting integer to string
		for(int i=0;i<a2.length;i++) {
			
//			String str1 = Integer.toString(a2[i]);
			itemList.append(a2[i]+"\n");
			sum += a[i];
		}
		
		
		
		
//		System.out.println("a[0]:"+a[0]);
//		System.out.println("a[1]:"+a[1]);
		
		System.out.println("Total:"+sum);
		billAmountField.setText(Integer.toString(sum));
		
		JButton checkOutBtn = new JButton("Check Out");
		checkOutBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Checkout c1= new Checkout("billCard",sum);
				c1.main("billCard",sum);
			}
		});
		checkOutBtn.setBounds(147, 393, 163, 23);
		frame.getContentPane().add(checkOutBtn);
	}
}
